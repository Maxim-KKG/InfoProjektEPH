package my_project.model.enemies.spiders;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ProgramController;
import my_project.model.Player;
import my_project.model.enemies.Spider;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Web extends GraphicalObject {
    private double degrees;
    private Player player;
    private Graphics2D g2d;
    private Spider spider;
    private double speed;

    public Web(double x, double y, Player player) {
        degrees =  Math.atan2(player.getY() - y, player.getX() - x);
        this.x = x;
        this.y = y;
        this.player = player;
        speed = 500;
    }

    public void draw(DrawTool drawTool){
        g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();//Hihihaw
        g2d.rotate(degrees+Math.PI*0.5,x,y);
        drawTool.setCurrentColor(Color.WHITE);
        drawTool.drawFilledCircle(x,y,5);
        g2d.setTransform(old);//PAh Pah Pah
    }

    public void update(double dt){
        if(x > Config.WINDOW_WIDTH + 200){
            ProgramController.viewController.removeDrawable(this);
        }else if(x < -200){
            ProgramController.viewController.removeDrawable(this);
        }
        if(y > Config.WINDOW_HEIGHT + 200){
            ProgramController.viewController.removeDrawable(this);
        }else if(y < -200){
            ProgramController.viewController.removeDrawable(this);
        }

        double dx = Math.cos(degrees)*speed*dt;
        double dy = Math.sin(degrees)*speed*dt;
        x += dx;
        y += dy;
    }

}
