package Gava.SplashScreen;
import Gava.*;
import Gava.DefaultGameObjects.EmbededAwtGameObject;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenScene extends Scene {

    GameObject title;
    @Override
    public void start() {
        Debug.log("splashscreen");
        title = new EmbededAwtGameObject(new TextArea());
        Game.Instantiate(title);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Game.getInstance().startMainScene();
                SoundPlayer.playSound("src/Assets/sounds/jump.wav");
            }
        },2000);
    }

}
