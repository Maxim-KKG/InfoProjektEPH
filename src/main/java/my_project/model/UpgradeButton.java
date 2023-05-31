package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

import java.awt.*;

public class UpgradeButton {
    private String selectedUpgrade;
    private String upgradeType;
    private ItemSys itemSys;
    private Button button;
    private boolean chosen;

    public UpgradeButton(double x, double y, String upgradeType, ItemSys itemSys, UpgradeWindow upgradeWindow) {

        this.upgradeType = upgradeType;
        this.itemSys = itemSys;
        switch (upgradeType) {
            case "Weapon" -> selectedUpgrade = itemSys.newRandomWeapon();
            case "Passive" -> selectedUpgrade = itemSys.newRandomPassive();
            case "PlayerUpgrade" -> selectedUpgrade = itemSys.newRandomPlayerUpgrade();
        }
        ButtonHandler buttonHandler = new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                if (!chosen) {
                    switch (upgradeType) {
                        case "Weapon" -> itemSys.chooseSelectedWeapon();
                        case "Passive" -> itemSys.chooseSelectedPassive();
                        case "PlayerUpgrade" -> itemSys.chooseSelectedPlayerUpgrade();
                    }
                    chosen = true;
                }
                upgradeWindow.destroy();
            }

            @Override
            public int getSceneIndex() {
                return 1;
            }

            @Override
            public ViewController getViewController() {
                return ProgramController.viewController;
            }
        };
        button = new Button(buttonHandler, 0, 10, y, selectedUpgrade, 50);
        if (selectedUpgrade.contains("Ultimate")) {
            button.setColor(78, 18, 80);
        }
    }

    public void removeButton() {
        ProgramController.viewController.removeDrawable(button, 1);
    }
}

