package my_project.model.weapons;

import KAGO_framework.view.DrawTool;
import my_project.model.Player;

import java.awt.*;

public class Forcefield extends Weapon{


    public Forcefield(double x, double y, Player player){
        super(x,y,player);
        radius = 50;
        hasPierce = false;
    }

    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(255, 131, 0, 166));
        drawTool.drawFilledCircle(x,y,radius);
    }

    public void update(double dt){
        checkAndHandelCollision(this);
        x = player.getX();
        y = player.getY();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        radius += 25;
    }
}
