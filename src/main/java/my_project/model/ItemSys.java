package my_project.model;

import my_project.Config;
import my_project.control.ProgramController;
import my_project.model.passives.Passive;
import my_project.model.weapons.Weapon;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ItemSys {
    private Player player;
    private int levelIndex = 0;
    private int bread;
    private HashMap<String,String[]> types = new HashMap<>();
    private HashMap<String,String> selections = new HashMap<>();
    //Weapons
    private ArrayList<String> weaponTypes =  new ArrayList<>(Arrays.asList("RocketLauncher","Gyro","Forcefield"));
    private String selectedWeapon;
    //Passives
    private ArrayList<String> passiveTypes = new ArrayList<>(Arrays.asList("PickupRange","BreadDroprate"));
    private String selectedPassive;
    //Player
    private ArrayList<String> playerUpgradeTypes = new ArrayList<>(Arrays.asList("Shield","Speed","AttackSpeed"));
    private String selectedPlayerUpgrade;

    public ItemSys(Player player) {
        this.player = player;

        types.put("Weapon", new String[]{"RocketLauncher", "Gyro", "Forcefield"});
        types.put("Passive", new String[]{"PickupRange","BreadDroprate"});
        types.put("PlayerUpgrades", new String[]{"Shield","Speed","AttackSpeed"});

    }
    public String newRandomWeapon() {
        if (weaponTypes.size() == 0){
            selectedWeapon = "Not Available";
            return selectedWeapon;
        }
        int rand = (int) (Math.random() * weaponTypes.size());
        selectedWeapon = weaponTypes.get(rand);
        try {
            Class<?> clazz = Class.forName("my_project.model.weapons." + selectedWeapon);
            if (player.weapons.get(clazz) != null && player.weapons.get(clazz).getLevel() >= Config.UPGRADE_LIMIT ){
                return selectedWeapon + "Ultimate";
            }
        } catch (ClassNotFoundException e) {
            return selectedWeapon;
        }
        return selectedWeapon;
    }
    public String newRandomPassive(){
        int rand = (int) (Math.random() * passiveTypes.size());
        selectedPassive = passiveTypes.get(rand);
        return selectedPassive;
    }
    public String newRandomPlayerUpgrade(){
        int rand = (int) (Math.random() * playerUpgradeTypes.size());
        selectedPlayerUpgrade = playerUpgradeTypes.get(rand);
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
            if(player.weapons.get(clazz) != null && player.weapons.get(clazz).getLevel() == Config.UPGRADE_LIMIT){
                weaponTypes.remove(weaponName);
            }
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
        applyPlayerUpgrade(selectedPlayerUpgrade);
        //TODO Implement player Upgrades
    }
    private void applyPlayerUpgrade(String playerUpgradeName){
        switch (playerUpgradeName){
            case "Shield":{
                Player.shield = true;
                playerUpgradeTypes.remove("Shield");
            }
            case "Speed":{
                Player.speed++;
                playerUpgradeTypes.remove("Shield");
            }
            case "AttackSpeed":{
                Player.shootCooldown /= 1.5;
            }
        }
    }
    public boolean receiveBread(int amount){
        bread += amount;
        if (levelIndex > Config.BREAD_PER_LEVEL.length-1)
            levelIndex = Config.BREAD_PER_LEVEL.length-1;
        //TODO Real max level handling
        if (bread > Config.BREAD_PER_LEVEL[levelIndex]){
            bread = 0;
            levelIndex += 1;
            levelUp();
            if (levelIndex > Config.BREAD_PER_LEVEL.length-1)
                levelIndex = Config.BREAD_PER_LEVEL.length-1;
            return true;
        }
        return false;
    }

    public int getBread(){
        return bread;
    }

    public int getBreadNeeded(){
        return Config.BREAD_PER_LEVEL[levelIndex];
    }

    public void levelUp(){
        if (!UpgradeWindow.isActive)
            new UpgradeWindow(this);
        ProgramController.viewController.showScene(1);
    }

}
