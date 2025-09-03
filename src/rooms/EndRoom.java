package rooms;

import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import entities.Door;

import java.util.Properties;

public class EndRoom {
    private final Image background;
    private final Door doorToB;

    public EndRoom(Properties gameProps, Properties messageProps) {
        // background
        background = new Image("res/background.png");

        // door to prep
        doorToB = new Door(gameProps, "door.end");
    }

    public Door[] getDoors() {
        Door[] doors = {doorToB};
        return doors;
    }

    public void update(Input input) {
        // draw background
        background.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        // door
        if (input.wasPressed(Keys.R)) {
            doorToB.doDoor();
        }
        doorToB.draw();
    }
}
