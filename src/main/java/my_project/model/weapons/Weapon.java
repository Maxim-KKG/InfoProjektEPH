package my_project.model.weapons;

import KAGO_framework.model.GraphicalObject;
import my_project.control.ProgramController;
import my_project.model.Enemy;
import my_project.model.Player;

public abstract class Weapon extends GraphicalObject {
    protected double degrees;
    protected Player player;
    protected int level = 1;
    public Weapon(double x, double y,Player player){
        this.x = x;
        this.y = y;
        this.player = player;
    }
    public void upgrade(){
        level++;
    }
    public int getLevel(){
        return level;
    }

    protected void checkAndHandleCollision(){
        for(Enemy e : ProgramController.enemies){
            if(e.getX()-x > -15 && e.getX()-x < 15 && e.getY()-y > -15 && e.getY()-y < 15){
                ProgramController.enemies.remove(e);
                ProgramController.viewController.removeDrawable(e);
                ProgramController.viewController.removeDrawable(this);
                break;
            }
        }
    }
}
