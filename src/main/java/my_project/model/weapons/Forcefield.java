package my_project.model.weapons;

import KAGO_framework.view.DrawTool;
import my_project.model.Player;

import java.awt.*;

public class Forcefield extends Weapon{

    // This can be changed as an upgrade
    private double cooldown = 3;
    // This is for functionality
    private double timer = 0;
    private boolean isActive = false;
    private double currentRadius = 0;

    public Forcefield(double x, double y, Player player){
        super(x,y,player);
        radius = 50;
        hasPierce = false;
        damage = 1;
    }

    public void draw(DrawTool drawTool){
        if(!isActive)
            return;
        drawTool.setCurrentColor(new Color(24, 249, 255, 166));
        drawTool.drawCircle(x,y,currentRadius);
        drawTool.setCurrentColor(new Color(167, 253, 255, 97));
        drawTool.drawCircle(x,y,currentRadius-1);
        drawTool.setCurrentColor(new Color(217, 253, 255, 63));
        drawTool.drawCircle(x,y,currentRadius-2);
        drawTool.setCurrentColor(new Color(0, 0, 0, 255));
        drawTool.drawCircle(x,y,currentRadius+1);
        drawTool.drawCircle(x,y,currentRadius+2);
    }

    public void update(double dt){
        if(!isActive)
            timer += dt;
        if(timer > cooldown)
            isActive = true;
        if(isActive){
            checkAndHandleCollision(0,0,radius);
            currentRadius += dt * (radius-currentRadius) * 10 + 1;
            if(currentRadius > radius){
                isActive = false;
                currentRadius = 0;
                timer = 0;
            }
        }
        x = player.getX();
        y = player.getY();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        radius += 25;
    }
}
