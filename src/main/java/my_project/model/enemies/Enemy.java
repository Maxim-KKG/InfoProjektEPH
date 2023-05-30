package my_project.model.enemies;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;
import my_project.model.Bread;
import my_project.model.Explosion;
import my_project.model.Player;
import my_project.model.Statics;

public abstract class Enemy extends GraphicalObject{

    protected Player p = null;
    public boolean isDead = false;
    public static double breadDroprate = 0.5;
    protected int health;


    public Enemy(double x, double y, Player p){
        this.x = x;
        this.y = y;
        this.p = p;
    }

    public void die(int damage){
        die(damage,0,0);
    }

    public void die(int damage, double camShakeStrength, double camShakeAmount, Weapon w){
        health -= damage;
        if(health > 0) {
            x += Math.sin(w.getX()-x) * -50;
            y += Math.cos(w.getY()-y) * -50;
            return;
        }
        Statics.cameraShake(camShakeStrength,camShakeAmount);
        ProgramController.viewController.draw(new Explosion(x,y,10));
        if(Math.random() > breadDroprate)
            ProgramController.viewController.draw(new Bread(x,y,p));
        ProgramController.viewController.removeDrawable(this);
        isDead = true;
    }
}
