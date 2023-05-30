package my_project.model.enemies;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;
import my_project.model.Bread;
import my_project.model.Explosion;
import my_project.model.Player;
import my_project.model.Statics;

import java.awt.image.BufferedImage;

public abstract class Enemy extends GraphicalObject {

    protected Player p;
    protected double speed = 100;
    protected double timer;
    protected int health = 1;
    public boolean isDead = false;


    public Enemy(double x, double y, Player p){
        this.x = x;
        this.y = y;
        this.p = p;
    }

    public Enemy(double x, double y, Player p, double speed){
        this.x = x;
        this.y = y;
        this.p = p;
        this.speed = speed;
    }

    public void die(int damage, double camShakeStr, double camShakeDur){
        health -= damage;
        if(health > 0){
            return;
        }
        if(camShakeDur == 0|| camShakeStr == 0)
            Statics.cameraShake(camShakeStr,camShakeDur);
        ProgramController.viewController.draw(new Explosion(x,y,10));
        if(Math.random() > 0.5) // TODO: die Wahrscheinlichkeit ist gerade festgeschrieben, aber es soll so sein, dass man die Drop-Rate steigern kann mit einem Upgrade
            ProgramController.viewController.draw(new Bread(x,y,p));
        ProgramController.viewController.removeDrawable(this);
        isDead = true;
    }
    
    public void die(int damage){
        die(damage,0,0);
    }
}
