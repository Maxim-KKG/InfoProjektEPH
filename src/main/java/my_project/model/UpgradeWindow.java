package my_project.model;

import my_project.control.ProgramController;

public class UpgradeWindow {
    public UpgradeButton[] upgradeButtons;
    public UpgradeWindow(ItemSys itemSys){
        ProgramController.viewController.showScene(1);
        upgradeButtons = new UpgradeButton[]{
        new UpgradeButton(0, 10, "Weapon", itemSys, this),
        new UpgradeButton(0, 300, "Passive", itemSys, this),
        new UpgradeButton(0, 600, "PlayerUpgrade", itemSys, this)
        };
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
