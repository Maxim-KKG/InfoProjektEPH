package my_project.model.passives;

import my_project.model.Player;

public class PickupRange extends Passive{
    @Override
    public void upgrade() {
        super.upgrade();
        Player.pickupRange += 40;
    }
}
