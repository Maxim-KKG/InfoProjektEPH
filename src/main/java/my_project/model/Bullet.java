package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends GraphicalObject {

    private double degrees;
    private BufferedImage pic1;
    private BufferedImage pic2;
    private double timer = 0;
    private BufferedImage currentPic;
    private Graphics2D g2d;

    public Bullet(double x, double y, double degrees){
        this.x = x;
        this.y = y;
        this.degrees = degrees;
        setNewImage("src/main/resources/graphic/Bullet1.png");
        pic1 = getMyImage();
        setNewImage("src/main/resources/graphic/Bullet2.png");
        pic2 = getMyImage();
        currentPic = pic1;
    }

    @Override
    public void draw(DrawTool drawTool) {
        g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();
        g2d.rotate(degrees,x,y);
        drawTool.drawImage(currentPic,x-6,y-4);
        g2d.setTransform(old);
    }

    @Override
    public void update(double dt){
        timer += dt;
        if(timer > 0.05){
            if(currentPic == pic1){
                currentPic = pic2;
            }else{
                currentPic = pic1;
            }
            timer = 0;
        }
        double dx = Math.cos(degrees)*500*dt;
        double dy = Math.sin(degrees)*500*dt;
        x += dx;
        y += dy;
    }
}
