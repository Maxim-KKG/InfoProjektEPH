package my_project.model;

import KAGO_framework.model.GraphicalObject;
import my_project.control.ProgramController;

public class EnemySpawner extends GraphicalObject {

    private Player p;
    private ProgramController programController;
    private double timer = 0;
    private double spawnRadius = 600;

    public EnemySpawner(Player p, ProgramController programController){
        this.p = p;
        this.programController = programController;
    }

    @Override
    public void update(double dt) {

        timer += dt;
        if(timer % 0.3 <0.01 && timer % 0.3 > -0.01){
            //timer = 0;
            double degrees = Math.random()*360;
            double xPos = Math.cos(degrees)*spawnRadius + 400;
            double yPos = Math.sin(degrees)*spawnRadius + 400;
            programController.spawnEnemy(xPos,yPos,p,1);
        }
        if(timer > 3){
            timer = 0;
            double degrees = Math.random()*360;
            double xPos = Math.cos(degrees)*spawnRadius + 400;
            double yPos = Math.sin(degrees)*spawnRadius + 400;
            programController.spawnEnemy(xPos,yPos,p,2);
        }

    }
}
