package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.ProgramController;
import my_project.model.passives.Passive;
import my_project.model.weapons.Gyro;
import my_project.model.weapons.Rocket;
import my_project.model.weapons.Weapon;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends InteractiveGraphicalObject {

    // These variables can be changed as upgrades
    private double speed = 150;
    private double shootCooldown = 0.2;
    private boolean rocket = false;
    private double rocketCooldown = 5;
    //Statics for Passives
    public static double pickupRange = 100;
    private int health;
    // These variables are here for functionality
    private double timer = 0;
    private double mouseX;
    private double mouseY;
    private double degrees = 0;
    private ProgramController p;
    private boolean facingRight = true;
    private int pictureIndex = 1;
    private int usedPictureIndex = 1;
    private double moveTimer = 0;
    private boolean mouseDown;
    private double shootingTimer = 0;
    private ArrayList<BufferedImage> images = new ArrayList<>();
    public HashMap<Class<?>, Weapon> weapons = new HashMap<>();
    private HashMap<Class<?>, Passive> passives = new HashMap<>();
    private double rocketTimer;
    private ItemSys itemSys;
    private int bread;
    private boolean gyro;

    public Player(double x, double y, ProgramController p) {
        this.x = x;
        this.y = y;
        this.p = p;
        this.setNewImage("src/main/resources/graphic/duck/DuckRight1.png");
        setPictures();
        health = 20;
    }


    public void receiveWeapon(Weapon weapon) {
        if (weapons.get(weapon.getClass()) != null) {
            if (weapons.get(weapon.getClass()).getLevel() < Config.UPGRADE_LIMIT) {
                weapons.get(weapon.getClass()).upgrade();
            }
        } else {
            weapons.put(weapon.getClass(),weapon);
            ProgramController.viewController.draw(weapon,0);
        }
    }
    public void receivePassive(Passive passive) {
        if (passives.get(passive.getClass()) != null) {
            if (passives.get(passive.getClass()).getLevel() < Config.UPGRADE_LIMIT) {
                passives.get(passive.getClass()).upgrade();
            }
        } else {
            passives.put(passive.getClass(),passive);
            ProgramController.viewController.draw(passive);
        }
        //TODO fuse receive Methods to one
    }

    private void setPictures() {
        for (int i = 1; i <= 6; i++) {
            addPicturesToList("src/main/resources/graphic/duck/DuckRight" + i + ".png");
        }
        for (int i = 1; i <= 6; i++) {
            addPicturesToList("src/main/resources/graphic/duck/DuckLeft" + i + ".png");
        }
    }

    private void addPicturesToList(String pathToImage) {
        try {
            images.add(ImageIO.read(new File(pathToImage)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(156,219,67,(int)Math.abs(Math.sin(timer)*20));
        drawTool.drawFilledCircle(x,y,pickupRange);
        drawTool.drawImage(images.get(usedPictureIndex-1),x-16,y-17);
    }

    /**
     * Wird mit jedem Frame vom Framework aufgerufen und dient zur Manipulation des Objekts im Verlauf
     * der Zeit.
     * @param dt die Sekunden, die seit dem letzten Aufruf von update vergangen sind
     */
    @Override
    public void update(double dt) {
        timer += dt;
        degrees = Math.atan2(mouseY - y, mouseX - x);
        boolean movedX = true;
        boolean movedY = true;
        double currentSpeed = speed;
        if(shootingTimer < shootCooldown)
            currentSpeed /= 2;
        if (ViewController.isKeyDown(65)) {
            facingRight = false;
            x -= currentSpeed * dt;
        } else if (ViewController.isKeyDown(68)) {
            facingRight = true;
            x += currentSpeed * dt;
        } else {
            movedX = false;
        }
        if (ViewController.isKeyDown(87)) {
            y -= currentSpeed * dt;
        } else if (ViewController.isKeyDown(83)) {
            y += currentSpeed * dt;
        } else {
            movedY = false;
        }
        if (!movedX && !movedY) {
            moveTimer = 0;
            pictureIndex = 1;
        }else{
            moveTimer += dt;
            if (moveTimer > 0.1) {
                moveTimer = 0;
                if (pictureIndex < 6)
                    pictureIndex += 1;
                else
                    pictureIndex = 1;
            }
        }
        if (facingRight) {
            usedPictureIndex = pictureIndex;
        } else {
            usedPictureIndex = pictureIndex + 6;
        }
        shootingTimer += dt;
        if (mouseDown && shootingTimer > shootCooldown) {
            shootingTimer = 0;
            p.spawnEgg(x, y, degrees);
        }
        die();
    }
    public void receiveBread(int amount){
        bread += amount;
        if(itemSys.receiveBread(amount))
            mouseDown = false;
    }

    public void setItemSys(ItemSys itemSys){
        this.itemSys = itemSys;
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseDown = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }
    public ProgramController getProgrammController(){
        return p;
    }
    public void setHealth(int damage){
        health -= damage;
    }
    private void die(){
        if(health <= 0){
            System.out.println("YOU DIED");
            //TODO player soll sterben
        }
    }

}
