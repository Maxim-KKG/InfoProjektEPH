package my_project.model;

import KAGO_framework.model.GraphicalObject;
import my_project.control.ProgramController;

public class EnemySpawner extends GraphicalObject {

    private Player p;
    private ProgramController programController;
    private double timer, waveTimer = 0;
    private double spawnRadius = 600;
    private int waveIndex = 0;

    public EnemySpawner(Player p, ProgramController programController){
        this.p = p;
        this.programController = programController;
    }

    @Override
    public void update(double dt) {
        if(timer > 200) {
            waveIndex += 1;
            timer = 0;
        }
        waveTimer += dt;
        if(waveTimer < 5)
            return;
        waveTimer = 0;
        timer += 1;
        switch (waveIndex){
            case 0:
                if(Math.random() < 0.7)
                    spawnFly();
                else
                    spawnWasp();
                return;
            case 1:
                if(Math.random() < 0.7)
                    spawnWasp();
                else
                    spawnSpider();
                return;
            case 2:
                spawnSpider();
                return;
        }
        spawnSpider();
    }

    private void spawnFly(){
        double degrees = Math.random()*360;
        double xPos = Math.cos(degrees)*spawnRadius + 400;
        double yPos = Math.sin(degrees)*spawnRadius + 400;
        programController.spawnEnemy(xPos,yPos,p,1);
    }
    private void spawnWasp(){
        double degrees = Math.random()*360;
        double xPos = Math.cos(degrees)*spawnRadius + 400;
        double yPos = Math.sin(degrees)*spawnRadius + 400;
        programController.spawnEnemy(xPos,yPos,p,2);
    }
    private void spawnSpider(){
        double degrees = Math.random()*360;
        double xPos = Math.cos(degrees)*spawnRadius + 400;
        double yPos = Math.sin(degrees)*spawnRadius + 400;
        programController.spawnEnemy(xPos,yPos,p,3);
    }
}
