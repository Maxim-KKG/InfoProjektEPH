package my_project.model.weapons;


import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GyroProj extends GraphicalObject {

    private ArrayList<BufferedImage> images = new ArrayList<>();
    private double timer = 0;
    private int pictureIndex = 1;
    public GyroProj(double x, double y , double radius) {
        setPictures();
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public void draw(DrawTool drawTool){
        drawTool.drawImage(images.get(pictureIndex-1),x-25,y-25);
    }

    @Override
    public void update(double dt) {
        timer += dt;
        if (timer > 0.1) {
            timer = 0;
            if (pictureIndex < 4)
                pictureIndex += 1;
            else
                pictureIndex = 1;
        }
    }

    private void setPictures() {
        for (int i = 1; i <= 4; i++) {
            addPicturesToList("src/main/resources/graphic/gyro/Gyro" + i + ".png");
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
