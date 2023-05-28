package my_project.model.weapons;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ProgramController;
import my_project.model.Enemy;
import my_project.model.Player;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.lang.reflect.GenericArrayType;
import java.util.Iterator;

public class Rocket extends Weapon{
    private Graphics2D g2d;
    private double explosionRadius = 100;
    private Enemy enemy;
    public Rocket(double x, double y, Player player) {
        super(x, y, player);
        radius = 20;
        enemy = ProgramController.enemies.get(0);

    }

    public void draw(DrawTool drawTool){
        g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();
        drawTool.setCurrentColor(Color.CYAN);
        g2d.rotate(degrees+Math.PI*0.5,x,y);
        drawTool.drawFilledCircle(x,y,30);
        g2d.setTransform(old);//PAh Pah Pah
    }
    public void update(double dt){
        super.update(dt);

        for (Enemy e : ProgramController.enemies) {
            if (collidesWith(e) || enemy == null || calculateDistance(enemy.getX(), enemy.getY()) < 5) {
                Iterator<Enemy> i = ProgramController.enemies.iterator();
                while (i.hasNext()) {
                    Enemy e2 = i.next();
                    if (e2.getDistanceTo(this) < explosionRadius) {
                        e2.die();
                        i.remove();
                    }
                }
                ProgramController.viewController.removeDrawable(this);
                break;
            }
        }
        moveTowardsTarget(dt,enemy.getX(), enemy.getY());
    }
    public double calculateDistance(double x2,double y2){
        return Math.sqrt( Math.pow(x-x2, 2) + Math.pow(y-y2,2));

    }
}
