package my_project.model.weapons;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ProgramController;
import my_project.model.Enemy;
import my_project.model.Explosion;
import my_project.model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Iterator;

public class Rocket extends Weapon {
    private Graphics2D g2d;
    private double explosionRadius = 100;
    private Enemy enemy;
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private int currentIndex = 1;
    private double timer = 0;
    private int enemyIndex;
    private boolean noEnemy;

    public Rocket(double x, double y, Player player, int enemyIndex) {
        super(x, y, player);
        radius = 20;
        enemy = randomEnemy();
        setPictures();
    }

    public void draw(DrawTool drawTool) {
        g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();
        drawTool.setCurrentColor(Color.CYAN);
        g2d.rotate(degrees + Math.PI * 0.5, x, y);
        drawTool.drawImage(images.get(currentIndex - 1), x - 9.5, y - 13);
        g2d.setTransform(old);//PAh Pah Pah
    }

    private void setPictures() {
        for (int i = 1; i <= 3; i++) {
            addPicturesToList("src/main/resources/graphic/rocket/Rocket" + i + ".png");
        }
    }

    private void addPicturesToList(String pathToImage) {
        try {
            images.add(ImageIO.read(new File(pathToImage)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(double dt) {
        super.update(dt);
            for (Enemy e : ProgramController.enemies) {
                if (collidesWith(e)) {
                    Iterator<Enemy> i = ProgramController.enemies.iterator();
                    while (i.hasNext()) {
                        Enemy e2 = i.next();
                        if (e2.getDistanceTo(this) < explosionRadius) {
                            e2.die(damage);
                            i.remove();
                        }
                    }
                    ProgramController.viewController.draw(new Explosion(x, y, explosionRadius, new Color(180, 32, 42)));
                    ProgramController.viewController.removeDrawable(this);
                    break;
                }
            }
        if (enemy != null && !enemy.isDead) {
            moveTowardsTarget(dt, enemy.getX(), enemy.getY());
            degrees = Math.atan2(enemy.getY() - y, enemy.getX() - x);
        } else {
            enemy = randomEnemy();
            noEnemy = enemy == null;
            double dx = Math.cos(degrees)*200*dt;
            double dy = Math.sin(degrees)*200*dt;
            x += dx;
            y += dy;
        }

        timer += dt;
        if (timer > 0.05) {
            timer = 0;
            if (currentIndex < 3) {
                currentIndex += 1;
            } else {
                currentIndex = 1;
            }
        }
    }

    public double calculateDistance(double x2, double y2) {
        return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
    }
}
