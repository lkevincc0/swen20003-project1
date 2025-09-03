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

public class BattleRoomB {
    private final Image background;
    // door
    private final Door doorToA;
    private final Door doorToEnd;
    // enemy
    private final Enemies enemy;
    // wall
    private final Wall wall;

    private boolean encounterStarted = false;

    public BattleRoomB(Properties gameProps, Properties messageProps) {
        // background
        background = new Image("res/background.png");

        // door to prep
        doorToA = new Door(gameProps, "primarydoor.B");
        doorToEnd = new Door(gameProps, "secondarydoor.B");

        // enemies
        enemy = new Enemies(gameProps, "keyBulletKin.B");

        // wall
        wall = new Wall(gameProps, "wall.B");
    }

    /* Fetch the entities */
    public Door[] getDoors() {
        Door[] doors = {doorToA, doorToEnd};
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
            doorToA.doDoor();
            doorToEnd.doDoor();
        }

        if (encounterStarted) {
            doorToA.lock();
            doorToEnd.lock();
        }

        // enemies and doors animation
        if (enemy.getStatus() && encounterStarted) {
            enemy.draw();
        } else {
            if (!encounterStarted) {
                doorToA.unlock();
            }
        }

        if (!enemy.getStatus()) {
            encounterStarted = false;
            doorToA.unlock();
            doorToEnd.unlock();
        }

        doorToA.draw();
        doorToEnd.draw();

        // wall
        wall.draw();
    }
}
