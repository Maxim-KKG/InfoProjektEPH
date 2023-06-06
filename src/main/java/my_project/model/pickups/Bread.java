package my_project.model.pickups;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;
import my_project.model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bread extends GraphicalObject {

    private BufferedImage image;
    private double dx, dy;
    private Player player;
    protected int breadAmount = 1;


    public Bread(double x, double y, Player player) {
        double rand = (Math.random() - 0.5) * 20;
        dy = Math.cos(rand) * 5;
        dx = Math.sin(rand) * 5;
        this.x = x;
        this.y = y;
        setNewImage("src/main/resources/graphic/Bread.png");
        image = getMyImage();
        width = image.getWidth();
        height = image.getHeight();
        this.player = player;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(image, x - 8.5, y - 8);
    }

    @Override
    public void update(double dt) {
        x += dx;
        y += dy;
        dx = changeSpeed(dx, dt);
        dy = changeSpeed(dy, dt);
        if(getDistanceTo(player) < Player.pickupRange){
            moveTowardsTarget(dt, player.getX(), player.getY());
        }
        checkAndHandleCollision();
    }

    private double changeSpeed(double s, double dt) {
        if (s > 0)
            s -= dt * 10;
        else if (s < 0)
            s += dt * 10;
        if (s > -1 && s < 1)
            s = 0;
        return s;
    }

    private void checkAndHandleCollision() {
        if (getDistanceTo(player) < 16) {
            player.receiveBread(breadAmount);
            ProgramController.viewController.removeDrawable(this);
        }
    }

}
