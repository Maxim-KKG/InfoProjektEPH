package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.simple_gui.Button;
import KAGO_framework.view.simple_gui.ButtonHandler;
import my_project.control.ProgramController;

public class UpgradeButton extends Button {
    public UpgradeButton() {
        super(new ButtonHandler() {
            @Override
            public void processButtonClick(int code) {
                System.out.println("Pipi");
            }

            @Override
            public int getSceneIndex() {
                return 0;
            }

            @Override
            public ViewController getViewController() {
                return ProgramController.viewController;
            }
        }, 0, 10, 10, "Heheheaw", 10);
    }
}

