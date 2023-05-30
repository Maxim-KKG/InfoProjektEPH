package my_project.model.enemies;

import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;
import my_project.model.Player;

import java.awt.*;

public class Wasp extends Enemy{
    public Wasp(double x, double y, Player p) {
        super(x, y, p);
        radius = 10;
        health = 5;
        bread = false;
        honeycomb = true;
        programController = p.getProgrammController();
    }

    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(148, 126, 23));
        drawTool.drawFilledCircle(x,y,radius);
    }

    public void update(double dt){
        super.update(dt);
        moveTowardsPlayer(dt);
    }
}
