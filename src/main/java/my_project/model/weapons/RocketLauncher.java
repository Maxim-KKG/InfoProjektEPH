package my_project.model.weapons;

import my_project.model.Player;

public class RocketLauncher extends Weapon{
    private double timer;
    private double rocketCount = 1;
    private double shootCooldown = 6;
    public RocketLauncher(double x, double y, Player player) {
        super(x, y, player);
    }

    @Override
    public void update(double dt) {
        x = player.getX();
        y = player.getY();
        timer += dt;
        if (timer > shootCooldown){
            for (int i = 0; i < rocketCount; i++) {
                player.getProgrammController().spawnRocket(x,y,player,i);
            }
            timer = 0;
        }

    }

    @Override
    public void upgrade() {
        super.upgrade();
        if (level % 2 != 0){
            rocketCount++;
        }else {
            shootCooldown--;
        }
    }
}
