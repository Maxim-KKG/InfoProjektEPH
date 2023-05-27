package my_project.model.weapons;

import KAGO_framework.model.GraphicalObject;

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

    }
}
