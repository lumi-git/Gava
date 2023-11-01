package Gava.utility;

import Gava.*;
import Gava.DefaultComponent.ColliderComponent;
import Gava.DefaultComponent.RigidBody;

public class CollisionResolver {

    public static void resolve(ColliderComponent a, ColliderComponent b) {
        //resolveVelocities(a, b);
        resolveInterpenetration(a, b);
    }


    private static double computePenetrationDepth(ColliderComponent a, ColliderComponent b) {
        Transform transformA = a.getParent().getModificationTransform();
        Transform transformB = b.getParent().getModificationTransform();

        Vector2D distance = transformB.getCenteredPosition().subtract(transformA.getCenteredPosition());
        double combinedWidth = (transformA.getScale().x + transformB.getScale().x) / 2.0;
        double combinedHeight = (transformA.getScale().y + transformB.getScale().y) / 2.0;

        double overlapX = combinedWidth - Math.abs(distance.x);
        double overlapY = combinedHeight - Math.abs(distance.y);

        if (overlapX > 0 && overlapY > 0) {
            return Math.min(overlapX, overlapY)/8;
        }

        return 0.0;
    }

    private static void resolveInterpenetration(ColliderComponent a, ColliderComponent b) {
        RigidBody bodyA = (RigidBody) a.getParent().getComponent(RigidBody.class);
        RigidBody bodyB = (RigidBody) b.getParent().getComponent(RigidBody.class);

        if (bodyA == null || bodyB == null) return;

        double penetrationDepth = computePenetrationDepth(a, b);

        if (penetrationDepth <= 0) return; // No overlap found

        Transform transformA = a.getParent().getModificationTransform();
        Transform transformB = b.getParent().getModificationTransform();

        Vector2D distance = transformB.getCenteredPosition().subtract(transformA.getCenteredPosition());
        double overlapX = (transformA.getScale().x + transformB.getScale().x) / 2.0 - Math.abs(distance.x);
        double overlapY = (transformA.getScale().y + transformB.getScale().y) / 2.0 - Math.abs(distance.y);

        Vector2D collisionNormal;
        if (overlapX > 0 && overlapY > 0) {
            if (overlapX < overlapY) {
                collisionNormal = (distance.x > 0) ? new Vector2D(1, 0) : new Vector2D(-1, 0);
            } else {
                collisionNormal = (distance.y > 0) ? new Vector2D(0, 1) : new Vector2D(0, -1);
            }
        } else {
            return;  // No overlap found
        }

        double totalInverseMass = 1.0 / bodyA.getMass() + 1.0 / bodyB.getMass();
        Vector2D movePerIMass = collisionNormal.scale(penetrationDepth / totalInverseMass);

        if (!bodyB.isStatic()){
            bodyB.getParent().getModificationTransform().getPosition().m_add(movePerIMass);
        }

        if (!bodyA.isStatic()) {
            bodyA.getParent().getModificationTransform().getPosition().m_subtract(movePerIMass);
        }
    }
}

