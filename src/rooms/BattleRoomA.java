package rooms;

import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import entities.Door;

import java.util.Properties;

public class BattleRoomA {
    private final Image background;
    private final Door doorToPrep;
    private final Door doorToB;

    public BattleRoomA(Properties gameProps, Properties messageProps) {
        // background
        background = new Image("res/background.png");

        // door to prep
        doorToPrep = new Door(gameProps.getProperty("primarydoor.A"));
        doorToB = new Door(gameProps.getProperty("secondarydoor.A"));
    }

    public void update(Input input) {
        // draw background
        background.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        // door
        if (input.wasPressed(Keys.R)) {
            doorToPrep.doDoor();
            doorToB.doDoor();
        }
        doorToPrep.draw();
        doorToB.draw();
    }
}
