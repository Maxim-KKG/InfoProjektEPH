package my_project.model.passives;

import KAGO_framework.model.GraphicalObject;

public abstract class Passive extends GraphicalObject {
    protected int level;
    public void upgrade(){
        level++;
    }
    public int getLevel(){
        return level;
    }
}
