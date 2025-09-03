package entities;

import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.Properties;

public class Enemies {
    private boolean alive = true;
    private final Image body;
    private final Point position;

    public Enemies(Properties gameProps, String enemiesConfig) {
        // Enemies body
        this.body = new Image("res/key_bullet_kin.png");

        // Enemies position
        String raw = gameProps.getProperty(enemiesConfig);
        String[] parts = raw.split(",");
        this.position = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
    }

    public Rectangle getBoundingBox() {
        return body.getBoundingBoxAt(position);
    }

    public void kill() {
        alive = false;
    }

    public void draw() {
        if (alive) {
            body.draw(position.x, position.y);
        }
    }
}
