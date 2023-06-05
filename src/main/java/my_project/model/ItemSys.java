package my_project.model;

import KAGO_framework.model.GraphicalObject;
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

public class ItemSys{
    private Player player;
    private int levelIndex;
    private int bread;
    private HashMap<String,String[]> types = new HashMap<>();
    //Weapons
    private ArrayList<String> weaponTypes =  new ArrayList<>(Arrays.asList("RocketLauncher","Gyro","Forcefield"));
    public String selectedWeapon;
    //Passives
    private ArrayList<String> passiveTypes = new ArrayList<>(Arrays.asList("PickupRange","BreadDroprate"));
    private String selectedPassive;
    //Player
    private ArrayList<String> playerUpgradeTypes = new ArrayList<>(Arrays.asList("Shield","Speed","AttackSpeed"));
    private String selectedPlayerUpgrade;

    public ItemSys(Player player) {
        this.player = player;
        levelIndex = 0;
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
                return selectedWeapon + "DX";
            }
        } catch (ClassNotFoundException e) {
            return selectedWeapon;
        }
        return selectedWeapon;
    }

    public String newRandomPassive(){
        if (passiveTypes.size() == 0){
            selectedPassive = "Not Available";
            return selectedPassive;
        }
        int rand = (int) (Math.random() * passiveTypes.size());
        selectedPassive = passiveTypes.get(rand);
        return selectedPassive;
    }

    public String newRandomPlayerUpgrade(){
        if (playerUpgradeTypes.size() == 0){
            selectedPlayerUpgrade = "Not Available";
            return selectedPlayerUpgrade;
        }
        int rand = (int) (Math.random() * playerUpgradeTypes.size());
        selectedPlayerUpgrade = playerUpgradeTypes.get(rand);
        return selectedPlayerUpgrade;
    }

    public void chooseSelectedWeapon(){
        System.out.println(selectedWeapon);
        newWeapon(selectedWeapon);
    }
    private void newWeapon(String weaponName){
        if(weaponName.equals("Not Available")){
            return;
        }
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
        if(passiveName.equals("Not Available")){
            return;
        }
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
    }
    private void applyPlayerUpgrade(String playerUpgradeName){
        if(playerUpgradeName.equals("Not Available")){
            return;
        }
        switch (playerUpgradeName){
            case "Shield":{
                Player.shield = true;
                playerUpgradeTypes.remove("Shield");
            }
            case "Speed":{
                Player.speed += 50;
                if(Player.speed >= 350)
                    playerUpgradeTypes.remove("Speed");
            }
            case "AttackSpeed":{
                Player.shootCooldown /= 1.5;
                if(Player.shootCooldown <= 0.1)
                    playerUpgradeTypes.remove("AttackSpeed");
            }
        }
    }
    public boolean receiveBread(int amount){
        bread += amount;
        if (levelIndex > Config.BREAD_PER_LEVEL.length-1)
            levelIndex = Config.BREAD_PER_LEVEL.length-1;
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
