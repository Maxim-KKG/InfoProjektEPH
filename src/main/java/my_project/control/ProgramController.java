package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.model.enemies.Enemy;
import my_project.model.enemies.Fly;
import my_project.model.enemies.Wasp;
import my_project.model.enemies.Spider;
import my_project.model.enemies.spiders.Web;
import my_project.model.weapons.Egg;
import my_project.model.*;
import my_project.model.weapons.Rocket;

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
        newGame();
        ItemSys itemSys = new ItemSys(player);
        player.setItemSys(itemSys);
    }


    public void newGame(){
        viewController.showScene(0);
        statics = new Statics();
        viewController.draw(statics);
        Background background = new Background();
        viewController.draw(background);
        player = new Player(400-16,500,this);
        viewController.draw(player);
        viewController.register(player);
        EnemySpawner enemySpawner = new EnemySpawner(player,this);
        viewController.draw(enemySpawner);
        viewController.draw(new UI(player));

        viewController.draw(new Button(new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                viewController.removeAllDrawables();
                newGame();
                clickCooldown = 0;
                viewController.showScene(2);
            }

            @Override
            public int getSceneIndex() {
                return 3;
            }

            @Override
            public ViewController getViewController() {
                return viewController;
            }
        },5,200,200,"Restart",100),3);
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
                viewController.draw(f);
                break;
            case 2:
                Wasp w = new Wasp(x,y,p);
                enemies.add(w);
                viewController.draw(w);
                break;
            case 3:
                Spider s = new Spider(x,y,p);
                enemies.add(s);
                viewController.draw(s);
                break;
        }
    }

    public void spawnRocket(double x, double y, Player player,int enemyIndex){
        Rocket rocket = new Rocket(x,y,player,enemyIndex);
        viewController.draw(rocket);
    }
    public void shootNet(double x, double y, Player player){
        Web net = new Web(x,y,player);
        viewController.draw(net);
    }
}
