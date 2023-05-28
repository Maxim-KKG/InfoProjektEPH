package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bread extends GraphicalObject {

    private BufferedImage image;
    private double dx,dy;
    public Bread(double x,double y) {
        double rand = (Math.random()-0.5)*20;
        dy = Math.cos(rand)*5;
        dx = Math.sin(rand)*5;
        this.x = x;
        this.y = y;
        setNewImage("src/main/resources/graphic/Bread.png");
        image = getMyImage();
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(image,x-8.5,y-8);
    }

    @Override
    public void update(double dt) {
        x += dx;
        y += dy;
        dx = changeSpeed(dx,dt);
        dy = changeSpeed(dy,dt);
    }

    private double changeSpeed(double s, double dt){
        if(s > 0)
            s -= dt * 10;
        else if(s < 0)
            s += dt * 10;
        if(s > -1 && s < 1)
            s = 0;
        return s;
    }
}