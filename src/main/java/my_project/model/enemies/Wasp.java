package my_project.model.enemies;

import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;
import my_project.model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Wasp extends Enemy{

    private ArrayList<BufferedImage> images = new ArrayList<>();
    private int usedPictureIndex = 1;
    private int pictureIndex = 1;
    private double moveTimer = 0;

    public Wasp(double x, double y, Player p) {
        super(x, y, p);
        radius = 10;
        health = 5;
        dropRarity = 1;
        programController = p.getProgrammController();
        setPictures();
    }

    public void draw(DrawTool drawTool){
        drawTool.drawImage(images.get(usedPictureIndex-1),x-10,y-10);
    }

    public void update(double dt){
        super.update(dt);
        moveTowardsPlayer(dt);
        moveTimer += dt;
        if (moveTimer > 0.1) {
            moveTimer = 0;
            if (pictureIndex < 4)
                pictureIndex += 1;
            else
                pictureIndex = 1;
        }
        if (p.getX() > x) {
            usedPictureIndex = pictureIndex;
        } else {
            usedPictureIndex = pictureIndex + 4;
        }
    }

    private void setPictures() {
        for (int i = 1; i <= 4; i++) {
            addPicturesToList("src/main/resources/graphic/wasp/WaspRight" + i + ".png");
        }
        for (int i = 1; i <= 4; i++) {
            addPicturesToList("src/main/resources/graphic/wasp/WaspLeft" + i + ".png");
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
