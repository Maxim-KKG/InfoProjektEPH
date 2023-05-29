package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Enemy extends GraphicalObject {

    private Player p;
    private double speed = 100;
    private double degrees = 0;
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage currentImage;
    private double timer;
    public boolean isDead = false;

    public Enemy(double x, double y, Player p){
        this.x = x;
        this.y = y;
        this.p = p;
        setNewImage("src/main/resources/graphic/fly/Fly1.png");
        image1 = getMyImage();
        setNewImage("src/main/resources/graphic/fly/Fly2.png");
        image2 = getMyImage();
        currentImage = image1;
    }

    public void die(){
        Statics.cameraShake(200,0.1);
        ProgramController.viewController.draw(new Explosion(x,y,10));
        if(Math.random() > 0.5) // TODO: die Wahrscheinlichkeit ist gerade festgeschrieben, aber es soll so sein, dass man die Drop-Rate steigern kann mit einem Upgrade
            ProgramController.viewController.draw(new Bread(x,y,p));
        ProgramController.viewController.removeDrawable(this);
        isDead = true;
    }
    @Override
    public void draw(DrawTool drawTool) {
        degrees = Math.atan2(p.getY()-y,p.getX()-x);
        drawTool.drawImage(currentImage,x-15,y-12.5);
    }

    @Override
    public void update(double dt) {
        double dx = Math.cos(degrees)*speed*dt;
        double dy = Math.sin(degrees)*speed*dt;
        x += dx;
        y += dy;
        timer += dt;
        if(timer > 0.1){
            if(currentImage == image1){
                currentImage = image2;
            }else{
                currentImage = image1;
            }
            timer = 0;
        }
    }

}
