package my_project.model.passives;

import my_project.model.enemies.Enemy;

public class BreadDroprate extends Passive{

    @Override
    public void upgrade() {
        super.upgrade();
        Enemy.breadDroprate += 0.5;
    }
}
