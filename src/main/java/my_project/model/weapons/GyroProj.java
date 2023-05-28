package my_project.model.weapons;


import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class GyroProj extends GraphicalObject {

    public GyroProj(double x, double y , double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public void draw(DrawTool drawTool){
        drawTool.drawFilledCircle(x, y, radius);

    }
}
