package my_project.model;

import KAGO_framework.model.GraphicalObject;
import my_project.control.ProgramController;

public class EnemySpawner extends GraphicalObject {

    private Player p;
    private ProgramController programController;
    private double timer, waveTimer = 0;
    private double spawnRadius = 600;
    private int waveIndex = 0;
    private int waveLength = 200;
    private double waveEnemySpawnTimer = 1;

    public EnemySpawner(Player p, ProgramController programController){
        this.p = p;
        this.programController = programController;
    }

    @Override
    public void update(double dt) {
        if(!ProgramController.gameActive)
            return;
        if(timer > waveLength) {
            waveIndex += 1;
            timer = 0;
        }
        waveTimer += dt;
        if(waveTimer < waveEnemySpawnTimer)
            return;
        waveTimer = 0;
        timer += 1;
        switch (waveIndex){
            case 0:
                spawnFly();
                waveEnemySpawnTimer = 0.5;
                waveLength = 100;
                return;
            case 1:
                if(Math.random() > 0.7)
                    spawnWasp();
                else
                    spawnFly();
                return;
            case 2:
                spawnFly();
                waveEnemySpawnTimer = 0.1;
                waveLength = 100;
                return;
            case 3:
                spawnSpider();
                waveEnemySpawnTimer = 15;
                waveLength = 2;
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
