package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;

public class Background extends GraphicalObject{

    private BufferedImage[] images = new BufferedImage[6];
    public Background(){
        setNewImage("src/main/resources/graphic/Background.png");
        setPictures();
    }

    private void setPictures() {
        for (int i = 1; i <= 5; i++) {
            addPicturesToList("src/main/resources/graphic/keys/key" + i + ".png",i);
        }
        addPicturesToList("src/main/resources/graphic/Target.png",6);
    }

    private void addPicturesToList(String pathToImage, int index) {
        try {
            images[index-1] = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(getMyImage(),-50,-50);
        if(!ProgramController.gameActive){
            drawTool.drawImage(images[0],350-16,350);
            drawTool.drawImage(images[1],400-166,450);
            drawTool.drawImage(images[2],350-16,550);
            drawTool.drawImage(images[3],450-16,450);
            drawTool.drawImage(images[4],450-16,50);
            drawTool.drawImage(images[5],375-16,75);
        }
    }

}
