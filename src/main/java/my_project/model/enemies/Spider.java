package my_project.model.enemies;


import KAGO_framework.view.DrawTool;

import my_project.model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Spider extends Enemy{
    private double timer = 0;
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private int pictureIndex = 1;
    private double moveTimer = 0;

    public Spider(double x, double y, Player p) {
        super(x, y, p);
        programController = p.getProgrammController();
        health = 10;
        speed = 100;
        radius = 10;
        dropRarity = 1;
        hitRadius = 20;
        setPictures();
    }
    public void draw(DrawTool drawTool){
        Graphics2D g2d = drawTool.getGraphics2D();
        AffineTransform old = g2d.getTransform();
        drawTool.setCurrentColor(Color.CYAN);
        g2d.rotate(degrees + Math.PI * 0.5, x, y);
        drawTool.drawImage(images.get(pictureIndex - 1), x - 32, y - 32);
        g2d.setTransform(old);
    }
    public void update(double dt){
        super.update(dt);
        if(timer < 2.5) {
            moveTowardsPlayer(dt);
            moveTimer += dt;
            if (moveTimer > 0.1) {
                moveTimer = 0;
                if (pictureIndex < 3)
                    pictureIndex += 1;
                else
                    pictureIndex = 1;
            }
        }
        timer += dt;
        if(timer > 3){
            timer = 0;
            programController.shootNet(x,y,p);
        }
    }

    private void setPictures() {
        for (int i = 1; i <= 3; i++) {
            addPicturesToList("src/main/resources/graphic/spider/Spider" + i + ".png");
        }
    }

    private void addPicturesToList(String pathToImage) {
        try {
            images.add(ImageIO.read(new File(pathToImage)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
