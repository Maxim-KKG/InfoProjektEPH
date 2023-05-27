package my_project.model.weapons;

import KAGO_framework.view.DrawTool;
import my_project.model.Weapon;

import java.awt.image.BufferedImage;

public class Egg extends Weapon {

    private double degrees;
    private BufferedImage pic;

    public Egg(double x, double y, double degrees){
        this.x = x;
        this.y = y;
        this. degrees = degrees;
        setNewImage("src/main/resources/graphic/bullets/Egg.png");
        pic = getMyImage();
    }

    public void draw(DrawTool drawTool){
        drawTool.drawImage(pic,x,y);
    }
    public void update(double dt){
        double dx = Math.cos(degrees)*500*dt;
        double dy = Math.sin(degrees)*500*dt;
        x += dx;
        y += dy;
    }

}
