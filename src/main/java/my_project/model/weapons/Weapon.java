package my_project.model.weapons;

import KAGO_framework.model.GraphicalObject;
import my_project.Config;
import my_project.control.ProgramController;
import my_project.model.Enemy;
import my_project.model.Player;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

public abstract class Weapon extends GraphicalObject {
    protected double degrees;
    protected Player player;
    protected int level;
    protected boolean hasPierce = true;
    private Graphics2D g2d;
    public Weapon(double x, double y,Player player){
        this.x = x;
        this.y = y;
        this.player = player;
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
        Iterator<Enemy> i = ProgramController.enemies.iterator();
        while(i.hasNext()){
            Enemy e = i.next();
            if(e.getX()-x > -15 && e.getX()-x < 15 && e.getY()-y > -15 && e.getY()-y < 15){
                e.die();
                i.remove();
                if (hasPierce){
                    ProgramController.viewController.removeDrawable(this);
                    break;
                }
            }
        }
    }
    protected void checkAndHandelCollision(double x, double y,double radius){
        Iterator<Enemy> i = ProgramController.enemies.iterator();
        while(i.hasNext()){
            Enemy e = i.next();
            if (e.getX() < x + radius && e.getX() + e.getWidth() > x - radius && e.getY() < y + radius && e.getY() + e.getHeight() > y - radius) {
                e.die();
                i.remove();
            }
        }
    }
    protected void checkAndHandelCollision(GraphicalObject gO){
        Iterator<Enemy> i = ProgramController.enemies.iterator();
        while(i.hasNext()){
            Enemy e = i.next();
            if (e.collidesWith(gO)) {
                e.die();
                i.remove();
                if (hasPierce){
                    ProgramController.viewController.removeDrawable(this);
                    break;
                }
            }
        }
    }

    protected void moveTowardsTarget(double dt, double targetX, double targetY){
        double degree = Math.atan2(targetY - this.y, targetX - this.x);
        double dx = Math.cos(degree)*200*dt;
        double dy = Math.sin(degree)*200*dt;
        x += dx;
        y += dy;
    }
}
