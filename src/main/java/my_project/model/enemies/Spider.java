package my_project.model.enemies;


import KAGO_framework.view.DrawTool;

import my_project.model.Player;

import java.awt.*;

public class Spider extends Enemy{
    private double timer = 0;
    public Spider(double x, double y, Player p) {
        super(x, y, p);
        programController = p.getProgrammController();
        health = 10;
        bread = false;
        honeycomb = true;
        speed = 100;
        radius = 10;

    }
    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(Color.GRAY);
        drawTool.drawFilledCircle(x,y,radius);
    }
    public void update(double dt){
        super.update(dt);
        moveTowardsPlayer(dt);
        timer += dt;
        if(timer > 3){
            timer = 0;
            programController.shootNet(x,y,p);
        }
    }
}
