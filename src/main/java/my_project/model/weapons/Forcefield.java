package my_project.model.weapons;

import KAGO_framework.view.DrawTool;
import my_project.model.Player;

public class Forcefield extends Weapon{


    public Forcefield(double x, double y, Player player, double r){
        super(x,y,player);
        radius = r;
    }

    public void draw(DrawTool drawTool){
        drawTool.drawFilledCircle(x,y,radius);
    }

    public void update(double dt){

    }
}
