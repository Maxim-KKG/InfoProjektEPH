package my_project.model;

import KAGO_framework.model.GraphicalObject;
import my_project.control.ProgramController;

public class EnemySpawner extends GraphicalObject {

    private Player p;
    private ProgramController programController;
    private double timer = 0;

    public EnemySpawner(Player p, ProgramController programController){
        this.p = p;
        this.programController = programController;
    }

    @Override
    public void update(double dt) {

        timer += dt;
        if(timer > 0.5){
            timer = 0;
            double degrees = Math.random()*360;
            double xPos = Math.cos(degrees)*360 + 300;
            double yPos = Math.sin(degrees)*360 + 300;
            programController.spawnEnemy(xPos,yPos,p);
        }


    }
}
