package my_project.model;

import KAGO_framework.model.GraphicalObject;

public class Statics extends GraphicalObject {

    public static double cameraY;
    public static double cameraX;
    private static double strengh;
    private static double timer;

    public static void cameraShake(double amount, double duration){
        if(amount < strengh && timer > 0)
            return;
        timer = duration;
        strengh = amount;
    }

    public static void reset(){
        timer = 0;
        strengh = 0;
    }

    @Override
    public void update(double dt) {
        if(timer > 0){
            cameraY = (Math.random()-0.5) * strengh * timer;
            cameraX = (Math.random()-0.5) * strengh * timer;
            timer -= dt;
        }else{
            cameraX = 0;
            cameraY = 0;
        }
    }
}
