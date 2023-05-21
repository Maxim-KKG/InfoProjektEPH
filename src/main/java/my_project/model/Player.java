package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

/**
 * Repräsentiert eine Kugel (einen Kreis), der in eine Schlange eingefügt werden soll. Dabei muss jeder QueueBall immer
 * seinen Vorgänger kennen, damit er zu ihm Abstand halten kann.
 */
public class Player extends InteractiveGraphicalObject {

    private double mouseX;
    private double mouseY;
    private Graphics2D g2d;
    private double speed = 300;
    private double degrees = 0;
    private ProgramController p;

    public Player(double x, double y, ProgramController p){
        this.x = x;
        this.y = y;
        this.p = p;
        this.setNewImage("src/main/resources/graphic/Tank.png");
    }


    /**
     * Selbsterklärend: zeichnet den die optische Repräsentation eines Ball-Objekts. Wird vom Framework automatisch aufgerufen (jeden Frame 1x).
     */
    @Override
    public void draw(DrawTool drawTool) {
        g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();
        degrees = Math.atan2(mouseY-y,mouseX-x);
        g2d.rotate(degrees,x,y);
        drawTool.drawImage(getMyImage(),x-15,y-12.5);
        g2d.setTransform(old);
    }

    /**
     * Wird mit jedem Frame vom Framework aufgerufen und dient zur Manipulation des Objekts im Verlauf
     * der Zeit.
     * @param dt die Sekunden, die seit dem letzten Aufruf von update vergangen sind
     */
    @Override
    public void update(double dt){
        if(ViewController.isKeyDown(65)){
            x -= speed * dt;
        } else if (ViewController.isKeyDown(68)) {
            x += speed * dt;
        }
        if(ViewController.isKeyDown(87)){
            y -= speed * dt;
        } else if (ViewController.isKeyDown(83)) {
            y += speed * dt;
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1){
            p.spawnBullet(x,y,degrees);
        }
    }
}
