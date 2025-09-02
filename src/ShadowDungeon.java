import bagel.*;
import java.util.Properties;

import rooms.BattleRoomA;
import rooms.BattleRoomB;
import rooms.EndRoom;
import rooms.PrepRoom;

public class ShadowDungeon extends AbstractGame {
    private final Properties GAME_PROPS;
    private final Properties MESSAGE_PROPS;
    private String currRoom = "end";
    private final PrepRoom prepRoom;
    private final BattleRoomA battleRoomA;
    private final BattleRoomB battleRoomB;
    private final EndRoom endRoom;

    public ShadowDungeon(Properties gameProps, Properties messageProps) {
        super(Integer.parseInt(gameProps.getProperty("window.width")),
                Integer.parseInt(gameProps.getProperty("window.height")),
                messageProps.getProperty("title"));

        this.GAME_PROPS = gameProps;
        this.MESSAGE_PROPS = messageProps;
        // rooms
        this.prepRoom = new PrepRoom(gameProps, messageProps);
        this.battleRoomA = new BattleRoomA(gameProps, messageProps);
        this.battleRoomB = new BattleRoomB(gameProps,messageProps);
        this.endRoom = new EndRoom(gameProps, messageProps);
    }


    /**
     * Render the relevant screen based on the keyboard input given by the user and the status of the gameplay.
     * @param input The current mouse/keyboard input.
     */
    @Override
    protected void update(Input input) {
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        if (currRoom == "prep") {
            prepRoom.update(input);
        }

        if (currRoom == "A") {
            battleRoomA.update(input);
        }

        if (currRoom == "B") {
            battleRoomB.update(input);
        }

        if (currRoom == "end") {
            endRoom.update(input);
        }
    }


    /**
     * The main entry point of the Shadow Dungeon game.
     *
     * This method loads the game properties and message files, initializes the game,
     * and starts the game loop.
     *
     * @param args Command-line arguments (not used in this game).
     */
    public static void main(String[] args) {
        Properties gameProps = IOUtils.readPropertiesFile("res/app.properties");
        Properties messageProps = IOUtils.readPropertiesFile("res/message.properties");
        ShadowDungeon game = new ShadowDungeon(gameProps, messageProps);
        game.run();
    }
}
