package Gava;
import Gava.DefaultGameObjects.FPSdisplay;
import Gava.DefaultGameObjects.GameObjectsDisplay;
import Gava.utility.FpsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Game extends JPanel implements Runnable{
    Thread gameThread;
    private int DrawLayerCount = 10;

    double CurrentFps = 0;
    FpsManager fpsManager = new FpsManager();

    JFrame frame = new JFrame("GameWindow");
    public Canvas canvas = new Canvas();
    static private Game instance = null;
    private final ArrayList<Scene> scenes = new ArrayList<Scene>();

    private int screenWidth = 800;
    private int screenHeight = 600;

    private ImageLibrary imageLibrary;

    private PhysicGlobalRules physRules = new PhysicGlobalRules();

    private Scene currentScene;
    private int FPS = 60;

    Game() {
        super();
    }

    public void MInit(){

        frame.setLayout( new BorderLayout());
        frame.setResizable(false);
        setDoubleBuffered(true);
        setOpaque(true);

        frame.setSize(screenWidth, screenHeight);
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        frame.setTitle("Gava default window");
        frame.setBackground(Color.black);
        addMouseListener(Input.getInstance().getMouseListener());
        addMouseMotionListener(Input.getInstance().getMouseListener());
        addMouseWheelListener(Input.getInstance().getMouseListener());
        frame.addKeyListener(Input.getInstance().getKeyListener());
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Game.getInstance().end();
            }
        });
        frame.add(Game.getInstance());
        frame.setLocationRelativeTo(null);
    }

    public void initImageLibrary(String folderpaht){
        /**
         * need to be called if any image is used in a sprite component
         */
        imageLibrary = new ImageLibrary(folderpaht);

    }

    public PhysicGlobalRules getPhysicGlobalRules(){
        return physRules;
    }

    public ImageLibrary getImageLibrary() {
        if(imageLibrary == null)
            throw new NullPointerException("ImageLibrary is null, have you created one ?");
        return imageLibrary;
    }
    public Scene getCurrentScene() {
        return currentScene;
    }

    public FpsManager getFpsManager() {
        return fpsManager;
    }

    public int getDrawLayerCount() {
        return DrawLayerCount;
    }

    public void setDrawLayerCount(int count){
        DrawLayerCount = count;
    }

    public void setSize(int width, int height){
        setScreenHeight(height);
        setScreenWidth(width);
        frame.setSize(width,height);
    }

    public void setScreenHeight(int height){
        this.screenHeight = height;
    }

    public void setScreenWidth(int width){
        this.screenWidth = width;
    }

    public Dimension getSize() {
        return frame.getSize();
    }

    public void setTitle(String title){
        frame.setTitle(title);
    }

    public void setIcon(String path){
        frame.setIconImage(new ImageIcon(path).getImage());
    }

    static public Game getInstance() {
        if (instance == null) {
            instance = new Game();
            instance.MInit();
        }
        return instance;
    }

    public static GameObject Instantiate(GameObject go){

        Game.getInstance().getCurrentScene().addGameObject(go);
        return go;
    }

    public double getFps(){
        return CurrentFps;
    }

    public double setFps(int fps){
        return FPS = fps;
    }

    public JFrame getFrame(){
        return frame;
    }

    public void addScene(Scene scene){
        scenes.add(scene);
    }

    public void start() {
        gameThread = new Thread(Game.getInstance());
        gameThread.start();
        instance.setVisible(true);
        instance.frame.setVisible(true);
    }

    public void setCurrentScene(int id){
        currentScene = scenes.get(id);
        CreateDebugObjects();
        currentScene.Mstart();

    }

    private void Mupdate(double dt){
        currentScene.Mupdate(dt);
        Input.getInstance().frameReset();
    }

    public void CreateDebugObjects(){
        if (Debug.getDebugOpt("fps"))
            Instantiate(new FPSdisplay());
        if (Debug.getDebugOpt("GameObjects"))
            Instantiate(new GameObjectsDisplay());
    }

    public void run() {
        double drawInterval = (double) 1000000000 /FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            double now = System.nanoTime();
            double remainingTime =0;
            Mupdate(nextDrawTime);
            repaint();
            try {
                remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            CurrentFps = 1000000000/(System.nanoTime()-now);

            if (Debug.getDebugOpt("fps")){
                fpsManager.addFps(CurrentFps);
                fpsManager.mean();
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentScene.Mdraw(g);
    }

    public void end(){
        if (Debug.getDebugOpt("fps")){
            Debug.log(fpsManager.toString());
        }
        currentScene.Mend();
        System.exit(0);
    }

}
