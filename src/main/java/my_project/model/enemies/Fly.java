package my_project.model.enemies;

import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;
import my_project.model.Bread;
import my_project.model.Explosion;
import my_project.model.Player;
import my_project.model.Statics;

import java.awt.image.BufferedImage;

public class Fly extends Enemy{

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
        speed = 100;
        health = 2;
        programController = p.getProgrammController();
    }


    public void draw(DrawTool drawTool) {
        drawTool.drawImage(currentImage,x-15,y-12.5);
    }


    public void update(double dt) {
        super.update(dt);
        moveTowardsPlayer(dt);
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
