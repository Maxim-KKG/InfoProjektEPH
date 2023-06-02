package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ProgramController;

import java.awt.image.BufferedImage;

public class UI extends GraphicalObject {

    private Player p;
    private BufferedImage noHealth;

    public UI(Player p){
        x = 20;
        y = 20;
        setNewImage("src/main/resources/graphic/NoHealth.png");
        noHealth = getMyImage();
        setNewImage("src/main/resources/graphic/Health.png");
        this.p = p;
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
    }
}
