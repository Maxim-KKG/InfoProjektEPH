package my_project.model.weapons;

import KAGO_framework.view.DrawTool;
import my_project.model.Player;

public class Forcefield extends Weapon{


    public Forcefield(double x, double y, Player player){
        super(x,y,player);
        radius = 50;
        hasPierce = false;
    }

    public void draw(DrawTool drawTool){
        drawTool.drawFilledCircle(x,y,radius);
    }

    public void update(double dt){

    }

    @Override
    public void upgrade() {
        super.upgrade();
        radius += 25;
    }
}
