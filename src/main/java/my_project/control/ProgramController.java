package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.model.enemies.Enemy;
import my_project.model.enemies.Fly;
import my_project.model.enemies.Wasp;
import my_project.model.weapons.Egg;
import my_project.model.*;
import my_project.model.weapons.Forcefield;
import my_project.model.weapons.Gyro;
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
    //public static ArrayList<Egg> eggs = new ArrayList<>();

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
        Statics statics = new Statics();
        viewController.draw(statics);
        Background background = new Background();
        viewController.draw(background);
        Player player = new Player(300,300,this);
        viewController.draw(player);
        viewController.register(player);
        EnemySpawner enemySpawner = new EnemySpawner(player,this);
        viewController.draw(enemySpawner);
        ItemSys itemSys = new ItemSys(player);
        player.setItemSys(itemSys);
    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){

    }

    public void spawnEgg(double x, double y, double degrees){
        Egg e = new Egg(x+Math.cos(degrees)*20,y+Math.sin(degrees)*20,degrees);
        viewController.draw(e);
    }

    public void spawnEnemy(double x, double y, Player p,int type){
        if(type == 1){
            Fly f = new Fly(x,y,p);
            enemies.add(f);
            viewController.draw(f);
        }
        if(type == 2){
            Wasp w = new Wasp(x,y,p);
            enemies.add(w);
            viewController.draw(w);
        }
    }

    public void spawnRocket(double x, double y, Player player,int enemyIndex){
        Rocket rocket = new Rocket(x,y,player,enemyIndex);
        viewController.draw(rocket);
    }
}
