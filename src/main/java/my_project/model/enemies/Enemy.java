package my_project.model.enemies;

import KAGO_framework.model.GraphicalObject;
import my_project.control.ProgramController;
import my_project.model.*;
import my_project.model.pickups.Bread;
import my_project.model.pickups.Honeycomb;
import my_project.model.weapons.Weapon;

public abstract class Enemy extends GraphicalObject{

    protected Player p;
    public boolean isDead = false;
    public static double breadDroprate = 0.5;
    protected int health = 1;
    protected double knockbackX;
    protected double knockbackY;
    protected double knockbackStrength = 5;

    protected double degrees;
    protected double speed = 100;

    protected int dropRarity = 0;
    protected ProgramController programController;
    protected double damageTimer;
    protected int damage = 1;
    protected double hitRadius = 10;

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
        ProgramController.viewController.draw(new Explosion(x,y,hitRadius),0);
        if(Math.random() < breadDroprate-(dropRarity*0.4)){
            switch (dropRarity){
                case 0 -> ProgramController.viewController.draw(new Bread(x, y, p),0);
                case 1 -> ProgramController.viewController.draw(new Honeycomb(x, y, p),0);
            }
        }
        isDead = true;
        ProgramController.viewController.removeDrawable(this);
    }

    protected void moveTowardsPlayer(double dt){
        degrees = Math.atan2(p.getY()-y,p.getX()-x);
        double dx = Math.cos(degrees)*speed*dt;
        double dy = Math.sin(degrees)*speed*dt;
        x += dx;
        y += dy;
    }

    protected void checkAndHandleCollision(Player p, int damage){
        if(calculateDistance() < hitRadius){
            p.decreaseHealth(damage);
            Statics.cameraShake(70,0.5);
            ProgramController.viewController.draw(new Explosion(x,y,hitRadius),0);
            ProgramController.viewController.removeDrawable(this);
        }
    }

    @Override
    public void update(double dt) {
        checkAndHandleCollision(p,damage);
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

    public double calculateDistance() {
        return Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2));
    }
}
