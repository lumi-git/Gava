import Gava.*;
import Gava.DefaultGameObjects.EmbededAwtGameObject;

import java.awt.*;
import java.awt.event.KeyEvent;

public class defaultScene extends Scene {

    @Override
    public void start() {
        Debug.log("main scene");
        int rows = 0;
        int cols = 0;
        int spaces = 2;
        int offsetx = Game.getInstance().getWidth() / 3;
        int offsety = Game.getInstance().getHeight() / 3;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++){
                Vector2D pos = new Vector2D((i * spaces) + offsetx, (j * spaces) + offsety);
                Game.Instantiate(new defaultCube(pos));
            }
        }
        EmbededAwtGameObject eb = new EmbededAwtGameObject(new TextArea("zss"));
        eb.getModificationTransform().setPosition(new Vector2D(100,100));
        Game.Instantiate(eb);

    }

    @Override
    public void update(double dt) {
        if (Input.isMouseClicked(3)){
            Game.Instantiate(new defaultCube(Input.getMousePosition()));
        }
    }


}
