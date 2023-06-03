package my_project.model.weapons;

import KAGO_framework.model.GraphicalObject;
import my_project.Config;
import my_project.control.ProgramController;
import my_project.model.Player;
import my_project.model.Statics;
import my_project.model.enemies.Enemy;

import java.awt.*;
import java.util.Iterator;

public abstract class Weapon extends GraphicalObject {
    protected double degrees;
    protected Player player;
    protected int damage = 1;
    protected int level;
    protected boolean hasPierce = true;
    private Graphics2D g2d;

    //Statics for Passives
    public static double explosionRangeUp;
    public static double damageUp;
    public static double rangeUp;

    public Weapon(double x, double y,Player player){
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public Weapon(double x, double y,Player player, int damage){
        this.x = x;
        this.y = y;
        this.player = player;
        this.damage = damage;
    }

    public void update(double dt){
        if(x > Config.WINDOW_WIDTH + 200){
            ProgramController.viewController.removeDrawable(this);
        }else if(x < -200){
            ProgramController.viewController.removeDrawable(this);
        }
        if(y > Config.WINDOW_HEIGHT + 200){
            ProgramController.viewController.removeDrawable(this);
        }else if(y < -200){
            ProgramController.viewController.removeDrawable(this);
        }
    }

    public void upgrade(){
        level++;
    }
    public int getLevel(){
        return level;
    }

    protected void checkAndHandleCollision(){
        checkAndHandleCollision(0,0);

    }

    protected void checkAndHandleCollision(double camShakeAmount, double camShakeDuration){
        Iterator<Enemy> i = ProgramController.enemies.iterator();
        while(i.hasNext()){
            Enemy e = i.next();
            if(e.getX()-x > -15 && e.getX()-x < 15 && e.getY()-y > -15 && e.getY()-y < 15){
                Statics.cameraShake(camShakeAmount,camShakeDuration);
                e.die(damage,this);
                if (ProgramController.viewController.getSoundController().getInitialized()){
                    SoundController.playSound(deathSound);
                }
                if(e.isDead)
                    i.remove();
                if (hasPierce){
                    ProgramController.viewController.removeDrawable(this);
                    break;
                }
            }
        }
    }

    protected void checkAndHandleCollision(double camShakeAmount, double camShakeDuration, double collisionRadius){
        Iterator<Enemy> i = ProgramController.enemies.iterator();
        while(i.hasNext()){
            Enemy e = i.next();
            if(calculateDistance(e.getX(),e.getY()) < collisionRadius){
                Statics.cameraShake(camShakeAmount,camShakeDuration);
                e.die(damage,this);
                if(e.isDead)
                    i.remove();
                if (hasPierce){
                    ProgramController.viewController.removeDrawable(this);
                    break;
                }
            }
        }
    }

    protected void checkAndHandelCollision(GraphicalObject gO){
        Iterator<Enemy> i = ProgramController.enemies.iterator();
        while(i.hasNext()){
            Enemy e = i.next();
            if (e.collidesWith(gO)) {
                e.die(damage,this);
                if(e.isDead)
                    i.remove();
                if (hasPierce){
                    ProgramController.viewController.removeDrawable(this);
                    break;
                }
            }
        }
    }
    protected Enemy randomEnemy() {
        Enemy enemy;
        try {
            enemy = ProgramController.enemies.get((int) (Math.random() * ProgramController.enemies.size()));
        } catch (IndexOutOfBoundsException e) {
            enemy = null;
        }
        return enemy;
    }

    public double calculateDistance(double x2, double y2) {
        return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
    }
}
