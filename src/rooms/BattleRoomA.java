package rooms;

import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import entities.Door;
import entities.Enemies;

import java.util.Properties;

public class BattleRoomA {
    private final Image background;
    private final Door doorToPrep;
    private final Door doorToB;
    private final Enemies enemy;

    public BattleRoomA(Properties gameProps, Properties messageProps) {
        // background
        background = new Image("res/background.png");

        // door to prep
        doorToPrep = new Door(gameProps, "primarydoor.A");
        doorToB = new Door(gameProps, "secondarydoor.A");

        // enemies
        enemy = new Enemies(gameProps, "keyBulletKin.A");
    }

    public Door[] getDoors() {
        Door[] doors = {doorToPrep, doorToB};
        return doors;
    }

    public Enemies getEnemies() { return enemy; }


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

        // enemies
        enemy.draw();
    }
}
