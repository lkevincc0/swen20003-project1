package rooms;

import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import entities.Door;
import entities.Enemies;

import java.util.Properties;

public class BattleRoomB {
    private final Image background;
    private final Door doorToA;
    private final Door doorToEnd;
    private final Enemies enemy;

    public BattleRoomB(Properties gameProps, Properties messageProps) {
        // background
        background = new Image("res/background.png");

        // door to prep
        doorToA = new Door(gameProps, "primarydoor.B");
        doorToEnd = new Door(gameProps, "secondarydoor.B");

        // enemies
        enemy = new Enemies(gameProps, "keyBulletKin.B");
    }

    /* Fetch the entities */
    public Door[] getDoors() {
        Door[] doors = {doorToA, doorToEnd};
        return doors;
    }

    public Enemies getEnemies() { return enemy; }

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

        // enemies
        enemy.draw();
    }
}
