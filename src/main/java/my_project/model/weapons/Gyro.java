package my_project.model.weapons;

import KAGO_framework.view.DrawTool;
import my_project.model.Player;

import java.awt.*;
import java.util.ArrayList;

public class Gyro extends Weapon{

    public static ArrayList<double[]> gyros = new ArrayList<>();
    private double rotation;
    private double timer;
    private double playerDistance = 100;
    private double xR;
    private double yR;
    private double speed;

    public Gyro(double x, double y,Player player){
        super(x,y,player);
        this.radius = 50;
        double[] gyro = new double[2];
        gyro[0] = x;
        gyro[1] = y;
        gyros.add(gyro);
    }

    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(203, 202, 47));
        drawTool.drawFilledCircle(x, y, radius);


    }


    @Override
    public void update(double dt) {
        rotation += 0.01 * dt;
        speed = playerDistance *  Math.cos(rotation);
        double old = speed;
        x = player.getX() +  speed;
        speed = old;
        //y = player.getY() +  playerDistance;
    }

    @Override
    public void upgrade() {
        super.upgrade();
        double[] gyro = new double[2];
        gyro[0] = x;
        gyro[1] = y;
        gyros.add(gyro);
    }
}
