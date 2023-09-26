package Gava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Game extends JPanel implements Runnable{
    Thread gameThread;
    double CurrentFps = 0;
    JFrame frame = new JFrame("GameWindow");
    public Canvas canvas = new Canvas();
    static private Game instance = null;
    private final ArrayList<Scene> scenes = new ArrayList<Scene>();


    private Scene currentScene;
    private final int FPS = 60;

    Game() {
        super();
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void MInit(){

        frame.setLayout( new BorderLayout());
        frame.setSize(800, 600);
        System.out.println(frame.getSize());
        frame.setTitle("Gava default window");
        frame.addMouseListener(new GavaMouseListener());
        frame.addKeyListener(new GavaKeyListener());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.add(Game.getInstance());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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
            instance.setVisible(true);
        }
        return instance;
    }

    public static GameObject Instantiate(GameObject go){
        Game.getInstance().currentScene.addGameObject(go);
        return go;
    }

    public void getFps(){
        System.out.println(CurrentFps);
    }

    public JFrame getFrame(){
        return frame;
    }

    public void addScene(Scene scene){
        scenes.add(scene);
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setCurrentScene(int id){
        currentScene = scenes.get(id);
        currentScene.Mstart();
    }

    public void Mupdate(double dt){
        update(dt);
        currentScene.Mupdate(dt);
    }

    public void update(double dt){
    }

    public GavaMouseListener getMouseListener(){
        return (GavaMouseListener)frame.getMouseListeners()[0];
    }

    public GavaKeyListener getKeyListener(){
        return (GavaKeyListener)frame.getKeyListeners()[0];
    }

    public void run() {
        double drawInterval = 1000000000/FPS; // rafraichissement chaque 0.0166666 secondes
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) { //Tant que le thread du jeu est actif
            double now = System.nanoTime();
            double remainingTime = nextDrawTime - System.nanoTime();

            //Permet de mettre � jour les diff�rentes variables du jeu
            Mupdate(nextDrawTime);
            //Dessine sur l'�cran le personnage et la map avec les nouvelles informations. la m�thode "paintComponent" doit obligatoirement �tre appel�e avec "repaint()"

            //permet de detruire les entités inutiles

            //Calcule le temps de pause du thread

            try {
                remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);
                repaint();


                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            CurrentFps = 1000000000/(System.nanoTime()-now);

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(0,0,800,600);

        currentScene.Mdraw(g);
    }

}
