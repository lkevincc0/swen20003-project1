package rooms;

import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import bagel.util.Point;
import entities.Door;
import entities.Enemies;
import entities.River;
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
    // river
    private final River river;

    private boolean encounterStarted = false;

    public BattleRoomA(Properties gameProps, Properties messageProps) {
        // background
        this.background = new Image("res/background.png");

        // door to prep
        this.doorToPrep = new Door(gameProps, "primarydoor.A");
        this.doorToB = new Door(gameProps, "secondarydoor.A");

        // enemies
        this.enemy = new Enemies(gameProps, "keyBulletKin.A");

        // wall
        this.wall = new Wall(gameProps, "wall.A");

        // river
        this.river = new River(gameProps, "river.A");
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

    // river status
    public boolean isRiver() { return river.isRiver(); }

    // get all positions of wall, exposed to players
    public Point[] getWallPositions() { return wall.getPositions(); }

    // get all positions of river
    public Point[] getRiverPositions() { return river.getPositions(); }

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

        // if die unlock all door
        if (!enemy.getStatus()) {
            encounterStarted = false;
            doorToPrep.unlock();
            doorToB.unlock();
        }

        doorToPrep.draw();
        doorToB.draw();

        // walls
        wall.draw();

        // rivers
        river.draw();
    }
}
