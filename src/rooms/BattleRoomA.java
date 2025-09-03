package rooms;

import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import bagel.util.Point;
import entities.Door;
import entities.Enemies;
import entities.Wall;

import java.util.Properties;

public class BattleRoomA {
    private final Image background;
    // door
    private final Door doorToPrep;
    private final Door doorToB;
    // enemy
    private final Enemies enemy;
    // wall
    private final Wall wall;

    private boolean encounterStarted = false;

    public BattleRoomA(Properties gameProps, Properties messageProps) {
        // background
        background = new Image("res/background.png");

        // door to prep
        doorToPrep = new Door(gameProps, "primarydoor.A");
        doorToB = new Door(gameProps, "secondarydoor.A");

        // enemies
        enemy = new Enemies(gameProps, "keyBulletKin.A");

        // wall
        wall = new Wall(gameProps, "wall.A");
    }

    // get entities in room
    public Door[] getDoors() {
        Door[] doors = {doorToPrep, doorToB};
        return doors;
    }
    public Enemies getEnemies() { return enemy; }

    // war status
    public void setEncounterStarted() {
        if (encounterStarted == false) {
            encounterStarted = true;
        }
    }

    // wall status
    public boolean isWall() { return wall.isWall(); }

    // get all positions of wall, exposed to players
    public Point[] getWallPositions() { return wall.getPositions(); }

    public void update(Input input) {
        // draw background
        background.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        // door
        if (input.wasPressed(Keys.R) && !enemy.getStatus()) {
            doorToPrep.doDoor();
            doorToB.doDoor();
        }

        if (encounterStarted) {
            doorToPrep.lock();
            doorToB.lock();
        }

        // enemies and doors animation
        if (enemy.getStatus() && encounterStarted) {
            enemy.draw();
        } else {
            if (!encounterStarted) {
                doorToPrep.unlock();
            }
        }

        if (!enemy.getStatus()) {
            encounterStarted = false;
            doorToPrep.unlock();
            doorToB.unlock();
        }

        doorToPrep.draw();
        doorToB.draw();

        // walls
        wall.draw();
    }
}
