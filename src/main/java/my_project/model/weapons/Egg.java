package my_project.model.weapons;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ProgramController;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Egg extends Weapon {

    private double degrees;
    private BufferedImage pic;
    private Graphics2D g2d;

    public Egg(double x, double y, double degrees){
        super(x,y,null);
        this.degrees = degrees;
        setNewImage("src/main/resources/graphic/bullets/Egg.png");
        pic = getMyImage();
    }

    public void draw(DrawTool drawTool){
        g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();//Hihihaw
        g2d.rotate(degrees+Math.PI*0.5,x,y);
        drawTool.drawImage(pic,x,y);
    }
    public void update(double dt){
        checkAndHandleCollision();
        double dx = Math.cos(degrees)*500*dt;
        double dy = Math.sin(degrees)*500*dt;
        x += dx;
        y += dy;
        if(x > Config.WINDOW_WIDTH + 200){
            ProgramController.viewController.removeDrawable(this);
        }else if(x < -200){
            ProgramController.viewController.removeDrawable(this);
        }
        if(y > Config.WINDOW_HEIGHT + 200){
            ProgramController.viewController.removeDrawable(this);
        }else if(y < -200){
            ProgramController.viewController.removeDrawable(this);
        }
    }

}
