package my_project.model.weapons;

import KAGO_framework.control.SoundController;
import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ProgramController;
import my_project.model.Explosion;
import my_project.model.Statics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Egg extends Weapon {

    private double degrees;
    private double speed = 300;
    private BufferedImage pic;
    private Graphics2D g2d;
    public static int damageSetter = 2;

    public Egg(double x, double y, double degrees){
        super(x,y,null);
        this.degrees = degrees;
        setNewImage("src/main/resources/graphic/bullets/Egg.png");
        deathSound = "eggCrack";
        pic = getMyImage();
        damage = damageSetter;
    }

    public void draw(DrawTool drawTool){
        g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();//Hihihaw
        g2d.rotate(degrees+Math.PI*0.5,x,y);
        drawTool.drawImage(pic,x-8,y-8);
        g2d.setTransform(old);//PAh Pah Pah
    }
    public void update(double dt){
        super.update(dt);
        checkAndHandleCollision(200,0.1);
        double dx = Math.cos(degrees)*speed*dt;
        double dy = Math.sin(degrees)*speed*dt;
        x += dx;
        y += dy;
        if(!ProgramController.gameActive && calculateDistance(375,75) < 45){
            ProgramController.gameActive = true;
            ProgramController.viewController.draw(new Explosion(400,400,600),0);
            Statics.cameraShake(300,0.5);
            SoundController.playSound("mainTrack");
            ProgramController.viewController.removeDrawable(this);
        }
    }

}
