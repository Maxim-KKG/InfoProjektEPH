package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static my_project.control.ProgramController.viewController;

public class RetryButton extends GraphicalObject{

    private ProgramController p;
    private Button button;
    private BufferedImage image;
    private boolean wasPressed = false;

    public RetryButton(ProgramController p){
        this.p = p;
        setPicture("src/main/resources/graphic/buttons/RedButton.png");

        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if(!wasPressed) {
                    wasPressed = true;
                    restartGame();
                }
            }

            @Override
            public int getSceneIndex() {
                return 3;
            }

            @Override
            public ViewController getViewController() {
                return ProgramController.viewController;
            }
        };

        button = new Button(buttonHandler, 0, 90, 290,"Retry", 50);
        button.setHeight(image.getHeight());
        button.setWidth(image.getWidth());
        button.setFont("Monospaced");
        viewController.draw(button,3);
    }

    @Override
    public void draw(DrawTool drawTool) {
        Statics.cameraX = 0;
        Statics.cameraY = 0;
        drawTool.drawImage(image,90-Statics.cameraX,290-Statics.cameraY);
    }

    private void setPicture(String pathToImage) {
        try {
            image = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void restartGame() {
        remove();
        p.viewController.removeAllDrawables();
        p.enemies = new ArrayList<>();
        p.newGame();
        p.clickCooldown = 0;
        p.viewController.showScene(0);
    }

    private void remove(){
        ProgramController.viewController.removeDrawable(button,3);
        ProgramController.viewController.removeDrawable(button,1);
        ProgramController.viewController.removeDrawable(this,3);
        ProgramController.viewController.removeDrawable(this,1);
    }
}
