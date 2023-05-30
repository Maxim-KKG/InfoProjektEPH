package my_project.model;


import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Honeycomb extends Bread{
    public Honeycomb(double x, double y, Player player) {
        super(x, y, player);
        breadAmount = 10;
    }
    @Override
    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(Color.magenta);
        drawTool.drawFilledRectangle(x,y,width,height);
    }

    @Override
    public void update(double dt){
        super.update(dt);
    }
}
