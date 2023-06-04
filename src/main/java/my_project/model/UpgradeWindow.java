package my_project.model;

import my_project.control.ProgramController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class UpgradeWindow {
    public ArrayList<UpgradeButton> upgradeButtons;
    public static boolean isActive = false;

    public UpgradeWindow(ItemSys itemSys){
        if(isActive)
            return;
        isActive = true;
        ProgramController.viewController.showScene(1);
        upgradeButtons = new ArrayList<>(Arrays.asList(
                new UpgradeButton(90, 40, "Weapon", itemSys, this),
                new UpgradeButton(90, 290, "Passive", itemSys, this),
                new UpgradeButton(90, 530, "PlayerUpgrade", itemSys, this)));

    }
    public void destroy(){
        Iterator<UpgradeButton> i = upgradeButtons.iterator();
        while (i.hasNext()){
            UpgradeButton b = i.next();
            b.removeButton();
            i.remove();
        }
        if (upgradeButtons.size() == 0) {
            isActive = false;
            ProgramController.viewController.showScene(0);
        }
    }
}
