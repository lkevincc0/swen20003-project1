package rooms;

import bagel.*;
import bagel.util.Point;
import entities.Door;
import entities.Player;

import java.util.Properties;


public class PrepRoom {
    private final Image background;
    private final Font titleFont;
    private final Font promptFont;

    private final String titleMessage;
    private final String moveMessage;

    private final double titleY;
    private final double moveMessageY;

    private final Door doorToA;

    public PrepRoom(Properties gameProps, Properties messageProps) {
        // background
        background = new Image("res/background.png");

        // font
        String fontFile = gameProps.getProperty("font");
        int titleSize = Integer.parseInt(gameProps.getProperty("title.fontSize"));
        int promptSize = Integer.parseInt(gameProps.getProperty("prompt.fontSize"));

        titleFont = new Font(fontFile, titleSize);
        promptFont = new Font(fontFile, promptSize);

        // text content
        titleMessage = messageProps.getProperty("title");
        moveMessage = messageProps.getProperty("moveMessage");

        // text pos
        titleY = Double.parseDouble(gameProps.getProperty("title.y"));
        moveMessageY = Double.parseDouble(gameProps.getProperty("moveMessage.y"));

        // door to A
        doorToA = new Door(gameProps, "door.prep");
    }

    // get all doors in room
    public Door[] getDoors() {
        Door[] doors = {doorToA};
        return doors;
    }


    public void update(Input input) {
        // draw background
        background.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        // title
        double titleX = (Window.getWidth() - titleFont.getWidth(titleMessage)) / 2.0;
        titleFont.drawString(titleMessage, titleX, titleY);

        // prompt
        double promptX = (Window.getWidth() - promptFont.getWidth(moveMessage)) / 2.0;
        promptFont.drawString(moveMessage, promptX, moveMessageY);


        if (input.wasPressed(Keys.R)) {
            doorToA.doDoor();
        }
        doorToA.draw();
    }
}