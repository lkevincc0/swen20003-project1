package entities;

import bagel.Image;
import bagel.util.Point;

import java.util.Properties;

public class River {
    private final Image riverImage;
    private final Point[] positions;

    public River(Properties gameProps, String riverConfig) {
        this.riverImage = new Image("res/river.png");
        String raw = gameProps.getProperty(riverConfig);
        if (raw == null || raw.equals("0")) {
            this.positions = new Point[0];
        } else {
            // first split by ';'
            String[] pairs = raw.split(";");
            this.positions = new Point[pairs.length];

            // second split by ','
            for (int i = 0; i < pairs.length; i++) {
                String[] xy = pairs[i].split(",");
                double x = Double.parseDouble(xy[0]);
                double y = Double.parseDouble(xy[1]);
                this.positions[i] = new Point(x, y);
            }
        }
    }

    // get all positions, exposed to player
    public Point[] getPositions() { return positions; }

    // get river status
    public boolean isRiver() {
        if (positions.length == 0 || positions == null) {
            return false;
        } else {
            return true;
        }
    }

    // draw
    public void draw() {
        if (positions == null || positions.length == 0) { return; }
        for (Point position: positions) {
            riverImage.draw(position.x, position.y);
        }
    }
}
