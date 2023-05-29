package my_project.model;

import my_project.model.weapons.Gyro;
import my_project.model.weapons.Weapon;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ItemSys {
    private Player player;
    private final String[] weaponTypes = {"RocketLauncher","Gyro","Forcefield"};
    private String selectedWeapon;
    private int bread;

    public ItemSys(Player player) {
        this.player = player;
    }
    public String newRandomWeapon(){
        int rand = (int) (Math.random() * weaponTypes.length);
        selectedWeapon = weaponTypes[rand];
        return selectedWeapon;
    }
    public void chooseSelectedWeapon(){
        newWeapon(selectedWeapon);
        System.out.println("Hehehehaw");
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
    public void receiveBread(int amount){

    }

}
