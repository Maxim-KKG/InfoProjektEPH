package my_project.model.enemies;

import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;
import my_project.model.Bread;
import my_project.model.Explosion;
import my_project.model.Player;
import my_project.model.Statics;

import java.awt.image.BufferedImage;

public class Fly extends Enemy{

    private double speed = 100;
    private double degrees = 0;
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage currentImage;
    private double timer;


    public Fly(double x, double y, Player p){
        super(x,y,p);
        setNewImage("src/main/resources/graphic/fly/Fly1.png");
        image1 = getMyImage();
        setNewImage("src/main/resources/graphic/fly/Fly2.png");
        image2 = getMyImage();
        currentImage = image1;
    }


    public void draw(DrawTool drawTool) {
        drawTool.drawImage(currentImage,x-15,y-12.5);
    }


    public void update(double dt) {
        degrees = Math.atan2(p.getY()-y,p.getX()-x);
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
