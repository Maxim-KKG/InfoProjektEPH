package my_project.model.pickups;


import KAGO_framework.view.DrawTool;
import my_project.model.Player;

import java.awt.*;

public class Honeycomb extends Bread {
    public Honeycomb(double x, double y, Player player) {
        super(x, y, player);
        breadAmount = 10;
        setNewImage("src/main/resources/graphic/Honeycomb.png");
    }
    @Override
    public void draw(DrawTool drawTool){
        drawTool.drawImage(getMyImage(),x-11,y-10.5);
    }

    @Override
    public void update(double dt){
        super.update(dt);
    }
}
