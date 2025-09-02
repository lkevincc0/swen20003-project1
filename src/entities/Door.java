package entities;

import bagel.Image;
import bagel.util.Point;

public class Door {
    private boolean locked = true;
    private final Image doorLocked;
    private final Image doorUnlocked;
    private final Point position;
    private final String targetRoom;

    public Door(String doorConfig) {
        String[] parts = doorConfig.split(",");
        this.position = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        this.targetRoom = parts[2];
        this.doorLocked = new Image("res/locked_door.png");
        this.doorUnlocked = new Image("res/unlocked_door.png");
    }

    public void doDoor() {
        if (locked) {
            locked = false;
        } else {
            locked = true;
        }
    }
    public boolean isUnlocked() { return !locked; }
    public String getTargetRoom() { return targetRoom; }
    public Point getPosition() { return position; }

    public void draw() {
        // door
        if (locked) {
            doorLocked.draw(position.x, position.y);
        } else {
            doorUnlocked.draw(position.x, position.y);
        }

    }
}
