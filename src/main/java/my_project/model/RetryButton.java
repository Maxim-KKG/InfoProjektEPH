package my_project.model;

import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.ProgramController;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static my_project.control.ProgramController.viewController;

public class RetryButton extends InteractiveGraphicalObject {

    private ProgramController p;

    public RetryButton(double x, double y, ProgramController p){
        this.p = p;
        this.x = x;
        this.y = y;
        setNewImage("src/main/resources/graphic/Retry.png");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();
        if(mouseX > x && mouseX < x+200 && mouseY > y && mouseY < y+100){
            restartGame();
        }
    }

    public void restartGame() {
        p.viewController.removeAllDrawables();
        p.enemies = new ArrayList<>();
        p.newGame();
        p.clickCooldown = 0;
        p.viewController.showScene(0);
    }
}
