package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class Bread extends GraphicalObject {
    public Bread(double x,double y) {
        width = 10;
        height = 10;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawFilledRectangle(x,y,width,height);
    }
}
