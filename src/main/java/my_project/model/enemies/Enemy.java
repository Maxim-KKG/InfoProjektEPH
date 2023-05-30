package my_project.model.enemies;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;
import my_project.model.Bread;
import my_project.model.Explosion;
import my_project.model.Player;
import my_project.model.Statics;
import my_project.model.weapons.Weapon;

public abstract class Enemy extends GraphicalObject{

    protected Player p;
    public boolean isDead = false;
    public static double breadDroprate = 0.5;
    protected int health = 1;
    protected double knockbackX;
    protected double knockbackY;
    protected double knockbackStrength = 5;


    public Enemy(double x, double y, Player p){
        this.x = x;
        this.y = y;
        this.p = p;
    }

    public void die(Weapon w){
        die(1,w);
    }

    public void die(int damage, Weapon w){
        die(damage,0,0,w);
    }

    public void die(int damage, double camShakeStrength, double camShakeAmount, Weapon w){
        health -= damage;
        if(health > 0) {
            double degrees = Math.atan2(w.getY()-y,w.getX()-x);
            knockbackX = Math.cos(degrees) * -knockbackStrength;
            knockbackY = Math.sin(degrees) * -knockbackStrength;
            return;
        }
        Statics.cameraShake(camShakeStrength,camShakeAmount);
        ProgramController.viewController.draw(new Explosion(x,y,10));
        if(Math.random() < breadDroprate)
            ProgramController.viewController.draw(new Bread(x,y,p));
        ProgramController.viewController.removeDrawable(this);
        isDead = true;
    }

    protected void moveTowardsPlayer(double dt){
        degrees = Math.atan2(p.getY()-y,p.getX()-x);
        double dx = Math.cos(degrees)*speed*dt;
        double dy = Math.sin(degrees)*speed*dt;
        x += dx;
        y += dy;
    }

    @Override
    public void update(double dt) {
        if(Math.abs(knockbackX) > 1 || Math.abs(knockbackY) > 1){
            if(knockbackY > 0){
                y += knockbackY;
                knockbackY -= dt * 10;
            }else{
                y += knockbackY;
                knockbackY += dt * 10;
            }
            if(knockbackX > 0){
                x += knockbackX;
                knockbackX -= dt * 10;
            }else{
                x += knockbackX;
                knockbackX += dt * 10;
            }
        }
    }

    @Override
    public void update(double dt) {
        if(Math.abs(knockbackX) > 1 || Math.abs(knockbackY) > 1){
            if(knockbackY > 0){
                y += knockbackY;
                knockbackY -= dt * 10;
            }else{
                y += knockbackY;
                knockbackY += dt * 10;
            }
            if(knockbackX > 0){
                x += knockbackX;
                knockbackX -= dt * 10;
            }else{
                x += knockbackX;
                knockbackX += dt * 10;
            }
        }
    }
}
