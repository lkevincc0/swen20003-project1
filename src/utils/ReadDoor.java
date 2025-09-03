package utils;

import bagel.util.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadDoor {
    // entry - exit
    private static final Map<String, Point> exitPoints = new HashMap<>();

    public ReadDoor(Properties gameProps) {
        // read x y
        Map<String, String> rawMap = new HashMap<>();
        for (String key : gameProps.stringPropertyNames()) {
            rawMap.put(key, gameProps.getProperty(key));
        }

        link("door.prep", "primarydoor.A", rawMap);
        link("primarydoor.A", "door.prep", rawMap);

        link("secondarydoor.A", "primarydoor.B", rawMap);
        link("primarydoor.B", "secondarydoor.A", rawMap);

        link("secondarydoor.B", "door.end", rawMap);
        link("door.end", "secondarydoor.B", rawMap);
    }

    private void link(String a, String b, Map<String, String> rawMap) {
        exitPoints.put(a, parsePoint(rawMap.get(b)));
        exitPoints.put(b, parsePoint(rawMap.get(a)));
    }

    private Point parsePoint(String raw) {
        if (raw == null) return null;
        String[] parts = raw.split(",");
        if (parts.length < 2) return null;
        double x = Double.parseDouble(parts[0].trim());
        double y = Double.parseDouble(parts[1].trim());
        return new Point(x, y);
    }

    public static Point getExit(String entry) {
        return exitPoints.get(entry);
    }
}