package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;

import java.awt.*;

public class Explosion extends GraphicalObject {

    private double timer = 0.5;
    private Color color = null;

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

    @Override
    public void draw(DrawTool drawTool) {
        if(timer > 0.4)
            drawTool.setCurrentColor(0,0,0,255);
        else
            if(color == null)
                drawTool.setCurrentColor(255,255,255,(int)(timer*500));
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
