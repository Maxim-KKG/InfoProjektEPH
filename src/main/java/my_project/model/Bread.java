package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Bread extends GraphicalObject {
    public Bread(double x,double y) {
        width = 10;
        height = 10;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(140, 96, 22));
        drawTool.drawFilledRectangle(x,y,width,height);
    }
}
