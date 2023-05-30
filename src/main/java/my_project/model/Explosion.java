package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;

import java.awt.*;

public class Explosion extends GraphicalObject {

    private double timer = 0.5;
    private Color color = null;
    private double strength = 1;

    public Explosion(double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Explosion(double x, double y, double radius, Color color){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public Explosion(double x, double y, double radius, Color color, double strength){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.strength = strength;
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(timer > 0.4)
            drawTool.setCurrentColor(0,0,0,255);
        else
            if(color == null)
                if(timer * 500 * strength > 255)
                    drawTool.setCurrentColor(255,255,255,255);
                else
                    drawTool.setCurrentColor(255,255,255,(int)(timer*500));
            else
                if(timer * 500 * strength > 255)
                    drawTool.setCurrentColor(color.getRed(),color.getGreen(),color.getBlue(),255);
                else
                    drawTool.setCurrentColor(color.getRed(),color.getGreen(),color.getBlue(),(int)(timer*500));
        drawTool.drawFilledCircle(x,y,radius);
    }

    @Override
    public void update(double dt) {
        timer -= dt * 2;
        radius += dt * 100;
        if(timer < 0)
            ProgramController.viewController.removeDrawable(this);
    }
}
