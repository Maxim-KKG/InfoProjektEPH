package my_project.model.weapons;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ProgramController;
import my_project.model.Enemy;
import my_project.model.Player;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Rocket extends Weapon{
    private Graphics2D g2d;
    private Enemy enemy;
    public Rocket(double x, double y, Player player) {
        super(x, y, player);
        radius = 20;
    }

    public void draw(DrawTool drawTool){

        g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();//Hihihaw
        g2d.rotate(degrees+Math.PI*0.5,x,y);
        drawTool.drawFilledCircle(x,y,30);
        g2d.setTransform(old);//PAh Pah Pah
    }
    public void update(double dt){
        moveTowardsTarget(dt,enemy.getX(), enemy.getY());
    }
}
