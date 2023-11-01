import Gava.Game;
import Gava.Debug;
public class Main {
    public static void main(String[] args) {
        Game g = Game.getInstance();
        Debug.setDebugOpt("fps",true);
        Debug.setDebugOpt("GameObjects",true);

        Debug.log("add scene");
        g.addScene(new defaultScene());

        Debug.log("init image library");
        g.initImageLibrary("src/Assets/img");
        g.setTitle("black bars");
        g.setFps(120);

        g.start();

    }

}