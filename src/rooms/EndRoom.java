package rooms;

import bagel.*;
import bagel.util.Point;
import entities.Door;
import entities.Player;

import java.util.Properties;

public class EndRoom {
    private final Image background;
    private final Image restartArea;
    private final Point restartPosition;

    private final Font titleFont;
    private String titleMessage;
    private final double titleY;

    private final Door doorToB;
    private boolean isAlive = true;


    public EndRoom(Properties gameProps, Properties messageProps, Player player) {
        // background
        this.background = new Image("res/background.png");

        // restart area
        this.restartArea = new Image("res/restart_area.png");
        String raw = gameProps.getProperty("restartarea.end");
        String[] parts = raw.split(",");
        this.restartPosition = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));

        // font
        String fontFile = gameProps.getProperty("font");
        int titleSize = Integer.parseInt(gameProps.getProperty("title.fontSize"));

        this.titleFont = new Font(fontFile, titleSize);

        // text pos
        this.titleY = Double.parseDouble(gameProps.getProperty("title.y"));

        // door to prep
        this.doorToB = new Door(gameProps, "door.end");
    }

    public Door[] getDoors() {
        Door[] doors = {doorToB};
        return doors;
    }

    // get restart position
    public Point[] getRestartPosition() { return new Point[]{ restartPosition }; }

    public void update(Input input, Player player, Properties messageProps) {
        // text content
        if (isAlive) {
            titleMessage = messageProps.getProperty("gameEnd.won");
        } else {
            titleMessage = messageProps.getProperty("gameEnd.lost");
        }

        // draw background
        background.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        // draw restart area
        restartArea.draw(restartPosition.x, restartPosition.y);

        // title
        double titleX = (Window.getWidth() - titleFont.getWidth(titleMessage)) / 2.0;
        titleFont.drawString(titleMessage, titleX, titleY);

        // door
        doorToB.unlock();
        if (input.wasPressed(Keys.R) && doorToB.getIsLocked()) {
            doorToB.doDoor();
        }
        doorToB.draw();

        // player status
        if (player.getHealth() > 0) {
            isAlive = true;
        } else {
            isAlive = false;
        }
    }
}
