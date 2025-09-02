package rooms;

import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import entities.Door;

import java.util.Properties;

public class BattleRoomB {
    private final Image background;
    private final Door doorToA;
    private final Door doorToEnd;

    public BattleRoomB(Properties gameProps, Properties messageProps) {
        // background
        background = new Image("res/background.png");

        // door to prep
        doorToA = new Door(gameProps.getProperty("primarydoor.B"));
        doorToEnd = new Door(gameProps.getProperty("secondarydoor.B"));
    }

    public void update(Input input) {
        // draw background
        background.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        // door
        if (input.wasPressed(Keys.R)) {
            doorToA.doDoor();
            doorToEnd.doDoor();
        }
        doorToA.draw();
        doorToEnd.draw();
    }
}
