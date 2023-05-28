package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Test extends GraphicalObject {
    /**
     * Gameplay-ideas:
     * basically Ducktato
     * --Arena: Etwas Größer als der Bildschirm
     * --Player: kein Fahrzeug, vielleicht Ente, Wächst mit Brot(Kücken -> Ente)
     * --Waffen: Alles, Basic attack ist Eier werfen
     * --Setting: Sumpf
     * --Gegner: Natürliche Feinde, Droppen Brot, Spawnen in Wellen
     * --Item System: alles zählt, Shop
     */
    private double x;
    private double y;
    private double speedX;
    private double speedY;
    private double timer;
    public Test(double x, double y){
        this.x = x;
        this.y = y;
        speedX = 100;
        speedY = 100;
        timer = 0;
    }
    public void draw(DrawTool drawTool){
        drawTool.setCurrentColor(new Color(1,1,1));
        drawTool.drawFilledCircle(x,y,20);
    }
    public void update(double dt){
        timer = timer + dt;

        double old = speedX;
        speedX = speedX * Math.cos(timer);
        x = x + speedX*dt;
        speedX = old;


        double oldY = speedY;
        speedY = speedY * Math.sin(timer);
        y = y + speedY * dt;
        speedY = oldY;
        /*y = y + speedY*dt;
        if(y > 200 + 100){
            speedY = speedY * -1;
        }
        if(y < 200 - 100){
            speedY = speedY * -1;
        }

         */
    }
}
