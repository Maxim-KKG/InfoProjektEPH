package my_project.model;

import my_project.Config;
import my_project.control.ProgramController;
import my_project.model.passives.Passive;
import my_project.model.weapons.Weapon;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ItemSys {
    private Player player;
    private int levelIndex = 0;
    private int bread;
    private HashMap<String,String[]> types = new HashMap<>();
    private HashMap<String,String> selections = new HashMap<>();
    //Weapons
    private String[] weaponTypes =  {"RocketLauncher","Gyro","Forcefield"};
    private String selectedWeapon;
    //Passives
    private String[] passiveTypes = {"PickupRange","BreadDroprate"};
    private String selectedPassive;
    //Player
    private String[] playerUpgradeTypes = {"HEhehehaw"};
    private String selectedPlayerUpgrade;

    public ItemSys(Player player) {
        this.player = player;

        types.put("Weapon", new String[]{"RocketLauncher", "Gyro", "Forcefield"});
        types.put("Passive", new String[]{"PickupRange","BreadDroprate"});
        types.put("PlayerUpgrades", new String[]{"HEhehehaw"});

    }
    public String newRandomWeapon() {
        int rand = (int) (Math.random() * weaponTypes.length);
        selectedWeapon = weaponTypes[rand];
        try {
            Class<?> clazz = Class.forName("my_project.model.weapons." + selectedWeapon);
            if (player.weapons.get(clazz) != null && player.weapons.get(clazz).getLevel() >= Config.UPGRADE_LIMIT ){
                return selectedWeapon + " Ultimate";
            }
        } catch (ClassNotFoundException e) {
            return selectedWeapon;
        }
        return selectedWeapon;
    }
    public String newRandomPassive(){
        int rand = (int) (Math.random() * passiveTypes.length);
        selectedPassive = passiveTypes[rand];
        return selectedPassive;
    }
    public String newRandomPlayerUpgrade(){
        int rand = (int) (Math.random() * playerUpgradeTypes.length);
        selectedPlayerUpgrade = playerUpgradeTypes[rand];
        return selectedPlayerUpgrade;
    }
    public String newRandomUpgrade(String type){
        int rand = (int) (Math.random() * types.get(type).length);
        selections.put(type,types.get(type)[rand]);
        return selections.get(type);
        //TODO Make RandomUpgrade and newUpgrades usable
    }
    public void chooseSelectedWeapon(){
        newWeapon(selectedWeapon);
    }
    private void newWeapon(String weaponName){
        try {
            Class<?> clazz = Class.forName("my_project.model.weapons." + weaponName);
            Constructor<?> constructor = clazz.getConstructor(double.class, double.class, Player.class);
            Object instance = constructor.newInstance(player.getX(),player.getY(),player);
            player.receiveWeapon((Weapon) instance);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public void chooseSelectedPassive(){
        newPassive(selectedPassive);
    }
    private void newPassive(String passiveName){
        try {
            Class<?> clazz = Class.forName("my_project.model.passives." + passiveName);
            Constructor<?> constructor = clazz.getConstructor();
            Object instance = constructor.newInstance();
            player.receivePassive((Passive) instance);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public void chooseSelectedPlayerUpgrade(){
        //TODO Implement player Upgrades
    }
    public void receiveBread(int amount){
        bread += amount;
        if (levelIndex > Config.BREAD_PER_LEVEL.length-1)
            return;
        if (bread > Config.BREAD_PER_LEVEL[levelIndex]){
            bread -= Config.BREAD_PER_LEVEL[levelIndex];
            levelIndex += 1;
            levelUp();
        }
    }
    public void levelUp(){
        new UpgradeWindow(this);
        ProgramController.viewController.showScene(1);
        //TODO Handle Button Spawning and game Pausing

    }

}
