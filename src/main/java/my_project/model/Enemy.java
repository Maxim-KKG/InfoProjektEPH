package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Enemy extends GraphicalObject {

    private Player p;
    private Graphics2D g2d;
    private double speed = 100;
    private double degrees = 0;

    public Enemy(double x, double y, Player p){
        this.x = x;
        this.y = y;
        this.p = p;
        setNewImage("src/main/resources/graphic/Enemy.png");
    }

    public void die(){
        ProgramController.viewController.removeDrawable(this);
        Statics.cameraShake(200,0.1);
        if(Math.random() > 0.5) // TODO: die Wahrscheinlichkeit ist gerade festgeschrieben, aber es soll so sein, dass man die Drop-Rate steigern kann mit einem Upgrade
            ProgramController.viewController.draw(new Bread(x,y));
    }
    @Override
    public void draw(DrawTool drawTool) {
        g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();
        degrees = Math.atan2(p.getY()-y,p.getX()-x);
        g2d.rotate(degrees,x,y);
        drawTool.drawImage(getMyImage(),x-15,y-12.5);
        g2d.setTransform(old);
    }

    @Override
    public void update(double dt) {
        double dx = Math.cos(degrees)*speed*dt;
        double dy = Math.sin(degrees)*speed*dt;
        x += dx;
        y += dy;
    }

}
