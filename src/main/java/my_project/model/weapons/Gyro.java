package my_project.model.weapons;

import KAGO_framework.view.DrawTool;
import my_project.model.Player;

import java.awt.*;
import java.util.ArrayList;

public class Gyro extends Weapon {
    private ArrayList<GyroProj> gyros = new ArrayList<>();
    private double rotation;
    private double playerDistance = 100;

    public Gyro(double x, double y, Player player) {
        super(x, y, player);
        hasPierce = false;
        this.radius = 25;
        upgrade();
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(203, 202, 47));

        for (GyroProj g : gyros){
            g.draw(drawTool);
        }
    }


    public void update(double dt) {
        rotation += dt;
        double distance = (Math.PI * 2) / level;
        int i = 0;
        for (GyroProj g : gyros) {
            g.setX( player.getX() + Math.cos(rotation + i * distance) * playerDistance);
            g.setY( player.getY() + Math.sin(rotation + i * distance) * playerDistance);
            checkAndHandelCollision(g);
            g.update(dt);
            i++;
        }
    }

    @Override
    public void upgrade() {
        super.upgrade();
        gyros.add(new GyroProj(x, y,radius));
    }
}
