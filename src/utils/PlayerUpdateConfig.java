package utils;

import bagel.util.Point;

import java.util.HashMap;
import java.util.Map;

/* Player config used in main func */
public record PlayerUpdateConfig(
        Point[] wallBoundaries,
        boolean isWall,
        Point[] riverBoundaries,
        boolean isRiver,
        Point[] restartBoundaries,
        boolean isRestart,
        boolean isBox,
        Map<Point, Integer> boxMap,
        Map<Point, Boolean> openedMap
) {

    // used in prep room
    public static PlayerUpdateConfig forPrep(Point[] restartBoundaries) {
        return new PlayerUpdateConfig(
                new Point[0], false,
                new Point[0], false,
                restartBoundaries, true,
                false,
                new HashMap<>(),
                new HashMap<>()
        );
    }

    // used in battle room
    public static PlayerUpdateConfig forBattle(
            Point[] wallBoundaries, boolean isWall,
            Point[] riverBoundaries, boolean isRiver,
            Map<Point, Integer> boxMap,
            Map<Point, Boolean> openedMap
    ) {
        return new PlayerUpdateConfig(
                wallBoundaries, isWall,
                riverBoundaries, isRiver,
                new Point[0], false,
                true,
                boxMap,
                openedMap
        );
    }

    // end room
    public static PlayerUpdateConfig forEnd(Point[] restartBoundaries) {
        return forPrep(restartBoundaries);
    }
}
