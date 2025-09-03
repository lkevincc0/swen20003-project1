package entities;

import bagel.Image;
import bagel.util.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TreasureBox {
    private final Image body;
    private final Map<Point, Integer> boxMap = new HashMap<>();
    private final Map<Point, Boolean> openedMap = new HashMap<>();

    public TreasureBox(Properties gameProps, String boxConfig) {
        this.body = new Image("res/treasure_box.png");

        String raw = gameProps.getProperty(boxConfig);
        if (raw != null && !raw.equals("0")) {
            // split by ';'
            String[] pairs = raw.split(";");
            for (String p : pairs) {
                String[] xyz = p.split(",");
                double x = Double.parseDouble(xyz[0]);
                double y = Double.parseDouble(xyz[1]);
                int coins = Integer.parseInt(xyz[2]);
                Point pos = new Point(x, y);
                boxMap.put(pos, coins);
                openedMap.put(pos, false);
            }
        }
    }

    // get all positions
    public Point[] getPositions() {
        return boxMap.keySet().toArray(new Point[0]);
    }

    // get hashmaps
    public Map<Point, Integer> getBoxMap() {
        return boxMap;
    }

    public Map<Point, Boolean> getOpenedMap() {
        return openedMap;
    }

    // get coin value by position
    public int getCoins(Point pos) {
        return boxMap.getOrDefault(pos, 0);
    }

    // get isOpened
    public boolean isOpened(Point pos) {
        return openedMap.getOrDefault(pos, true);
    }

    // if open, mark
    public int openBox(Point pos) {
        if (boxMap.containsKey(pos) && !openedMap.get(pos)) {
            openedMap.put(pos, true);
            return boxMap.get(pos);
        }
        return 0;
    }

    public void draw() {
        for (Map.Entry<Point, Integer> entry : boxMap.entrySet()) {
            Point position = entry.getKey();
            // only draw it not opened
            if (!openedMap.get(position)) {
                body.draw(position.x, position.y);
            }
        }
    }
}