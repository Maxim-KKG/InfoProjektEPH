package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.enemies.Enemy;
import my_project.model.enemies.Fly;
import my_project.model.enemies.Wasp;
import my_project.model.enemies.Spider;
import my_project.model.enemies.spiders.Web;
import my_project.model.weapons.Egg;
import my_project.model.*;
import my_project.model.weapons.Rocket;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute


    // Referenzen
    public static ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static boolean gameActive = false;
    public double timer;
    public Player player;
    public Statics statics;
    public double clickCooldown;
    private BufferedImage b;


    /**
     * Konstruktor:
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen.
     * Sie erstellt die leeren Datenstrukturen, zu Beginn nur eine Queue
     */
    public void startProgram() {
        viewController.createScene();
        viewController.createScene();
        viewController.createScene();
        loadAudio();
        newGame();
    }

    private void loadAudio(){
        viewController.getSoundController().loadSound("src/main/resources/sound/EggCrack.mp3","eggCrack",false);
        viewController.getSoundController().loadSound("src/main/resources/sound/MainTrack.mp3","mainTrack",true);
    }

    public void newGame(){
        viewController.showScene(0);
        statics = new Statics();
        viewController.draw(statics);
        Background background = new Background();
        viewController.draw(background);
        viewController.draw(background,1);
        viewController.draw(background,3);
        player = new Player(400-16,500,this);
        viewController.draw(player);
        viewController.register(player);
        EnemySpawner enemySpawner = new EnemySpawner(player,this);
        viewController.draw(enemySpawner);
        ItemSys itemSys = new ItemSys(player);
        player.setItemSys(itemSys);
        viewController.draw(new UI(player,itemSys));
        RetryButton rb = new RetryButton(300,300,this);
        viewController.draw(rb,3);
        viewController.register(rb,3);
        Statics.reset();
    }
    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        clickCooldown += 0.1 * dt;
        if (viewController.getCurrentScene() == 0) {
            timer += dt;
            if ((int) timer > 300) {
                System.out.println("5min");
                timer = 0;
            }
        }

    }

    public void spawnEgg(double x, double y, double degrees){
        Egg e = new Egg(x+Math.cos(degrees)*20,y+Math.sin(degrees)*20,degrees);
        viewController.draw(e);
    }

    public void spawnEnemy(double x, double y, Player p,int type){
        switch (type){
            case 1:
                Fly f = new Fly(x,y,p);
                enemies.add(f);
                viewController.draw(f,0);
                break;
            case 2:
                Wasp w = new Wasp(x,y,p);
                enemies.add(w);
                viewController.draw(w,0);
                break;
            case 3:
                Spider s = new Spider(x,y,p);
                enemies.add(s);
                viewController.draw(s,0);
                break;
        }
    }

    public void spawnRocket(double x, double y, Player player,int enemyIndex){
        Rocket rocket = new Rocket(x,y,player,enemyIndex);
        viewController.draw(rocket);
    }
    public void shootNet(double x, double y, Player player){
        Web net = new Web(x,y,player);
        enemies.add(net);
        viewController.draw(net,0);
    }
}
