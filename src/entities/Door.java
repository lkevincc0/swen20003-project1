package entities;

import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.util.Properties;

public class Door {
    private boolean locked = true;
    private final Image doorLocked;
    private final Image doorUnlocked;
    private final Point position;
    private final String targetRoom;
    private final String doorConfig;
    /*
    * accept a string to set up the config
    * @param is gmaeProps and doorConfig,
    * e.g. "doorToEnd = new Door(gameProps, "secondarydoor.B");"
    */
    public Door(Properties gameProps, String doorConfig) {
        this.doorConfig = doorConfig;
        String raw = gameProps.getProperty(doorConfig);
        String[] parts = raw.split(",");
        this.position = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        this.targetRoom = parts[2];
        this.doorLocked = new Image("res/locked_door.png");
        this.doorUnlocked = new Image("res/unlocked_door.png");
    }

    public String getDoorConfig() {
        return doorConfig;
    }
    // get door position
    public Point getPosition() { return position; }
    // get target room
    public String getTargetRoom() { return targetRoom; }
    // unlock or lock door
    public void lock() { locked = true; }
    public void unlock() { locked = false; }

    // animation of open or close the door
    public void doDoor() {
        // open, close; close, open;
        if (locked) {
            locked = false;
        } else {
            locked = true;
        }
    }

    public Rectangle getBoundingBox() {
        return (locked ? doorLocked : doorUnlocked).getBoundingBoxAt(position);
    }

    // get the door status
    public boolean getIsLocked() { return locked; }

    // draw function
    public void draw() {
        // door
        if (locked) {
            doorLocked.draw(position.x, position.y);
        } else {
            doorUnlocked.draw(position.x, position.y);
        }
    }
}
