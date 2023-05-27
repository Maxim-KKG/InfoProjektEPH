package my_project.model.weapons;

import KAGO_framework.view.DrawTool;
import my_project.model.Enemy;
import my_project.model.Player;

import java.awt.*;
import java.util.ArrayList;

public class Gyro extends Weapon{

    public static ArrayList<double[]> gyros = new ArrayList<>();
    private double rotation;
    private double hello;
    private double playerDistance = 100;

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
        for (double[] g: gyros) {
            drawTool.drawFilledCircle(g[0], g[1], radius);
        }

    }


    @Override
    public void update(double dt) {
        hello += 0.01 * dt;
        double distance = (Math.PI * 2) / level;
        int i = 0;
        for (double[] g: gyros) {
            g[0] = player.getX() + Math.cos(hello + i * distance) * playerDistance;
            g[1] = player.getY() + Math.sin(hello + i * distance) * playerDistance;
            i++;
        }
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
