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
    protected int damage;

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
        if(Math.random() < breadDroprate-(dropRarity*0.4)){
            switch (dropRarity){
                case 0 -> ProgramController.viewController.draw(new Bread(x, y, p));
                case 1 -> ProgramController.viewController.draw(new Honeycomb(x, y, p));
            }
        }
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
    protected void checkAndHandleCollision(double dt,Player p,int damage){
        if(p.collidesWith(this)){
            damageTimer -= dt;
            if(damageTimer <= 0){
                p.setHealth(damage);
                damageTimer = 2;
                System.out.println("pupu");
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
