package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ProgramController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI extends GraphicalObject {

    private Player p;
    private ItemSys i;
    private BufferedImage noHealth;
    private BufferedImage levelMeter;

    public UI(Player p, ItemSys i){
        x = 20;
        y = 20;
        setNewImage("src/main/resources/graphic/NoHealth.png");
        noHealth = getMyImage();
        setNewImage("src/main/resources/graphic/LevelMeter.png");
        levelMeter = getMyImage();
        setNewImage("src/main/resources/graphic/Health.png");
        this.p = p;
        this.i = i;
    }

    @Override
    public void draw(DrawTool drawTool) {
        if(!ProgramController.gameActive)
            return;
        for(int i = 0; i < p.getMaxHealth(); i++){
            drawTool.drawImage(noHealth,x + (i * 30) - Statics.cameraX,y  - Statics.cameraY);
        }
        for(int i = 0; i < p.getHealth(); i++){
            drawTool.drawImage(getMyImage(),x + (i * 30) - Statics.cameraX,y  - Statics.cameraY);
        }
        drawTool.drawImage(levelMeter,x + 200 - Statics.cameraX,y - Statics.cameraY);
        double bread = i.getBread();
        double breadNeeded = i.getBreadNeeded() + 1;
        drawTool.setCurrentColor(new Color(166, 252, 219));
        drawTool.drawFilledRectangle(x + 202 - Statics.cameraX,y + 6 - Statics.cameraY,(bread/breadNeeded)*500,5);
        drawTool.setCurrentColor(new Color(36, 159, 222));
        drawTool.drawFilledRectangle(x + 202 - Statics.cameraX,y + 11 - Statics.cameraY,(bread/breadNeeded)*500,26);
        drawTool.setCurrentColor(new Color(32, 214, 199));
        drawTool.drawFilledRectangle(x + 202 - Statics.cameraX,y + 37 - Statics.cameraY,(bread/breadNeeded)*500,5);
    }
}
