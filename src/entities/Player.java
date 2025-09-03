package entities;

import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Player {
    private Point position;
    private double health;
    private int coins;
    private int speed;
    private Image playerLeft;
    private Image playerRight;
    private boolean facingRight;

    // const damage values
    public final double riverDamagePerFrame = 0.2;

    // others
    private final Image wallImage;
    private final Image riverImage;

    public Player(Point startPos, double health, int coins, int speed) {
        this.position = startPos;
        this.health = health;
        this.coins = coins;
        this.speed = speed;
        this.playerLeft = new Image("res/player_left.png");
        this.playerRight = new Image("res/player_right.png");
        this.facingRight = true;
        // others
        this.wallImage = new Image("res/wall.png");
        this.riverImage = new Image("res/river.png");
    }

    private void move(Input input,
                      Point[] wallBoundaries,
                      boolean isWall,
                      Point[] riverBoundaries,
                      boolean isRiver) {
        /* according to input change position */
        double x = position.x;
        double y = position.y;
        // up
        if (input.isDown(Keys.W)) {
            y -= speed;
        }

        // down
        if (input.isDown(Keys.S)) {
            y += speed;
        }

        // left
        if (input.isDown(Keys.A)) {
            x -= speed;
        }

        // right
        if (input.isDown(Keys.D)) {
            x += speed;
        }

        // boundaries
        Rectangle playerBox = playerLeft.getBoundingBoxAt(new Point(x, y));

        if (playerBox.left() < 0) {
            x = position.x;
        }

        if (playerBox.right() > Window.getWidth()) {
            x = position.x;
        }

        if (playerBox.top() < 0) {
            y = position.y;
        }

        if (playerBox.bottom() > Window.getHeight()) {
            y = position.y;
        }

        // boundaries of wall
        // get rectangle of wall
        if (isWall) {
            for (Point wp : wallBoundaries) {
                Rectangle wallBox = wallImage.getBoundingBoxAt(wp);
                if (playerBox.intersects(wallBox)) {
                    x = position.x;
                    y = position.y;
                    break;
                }
            }
        }
        // river logic
        if (isRiver) {
            for (Point wp : riverBoundaries) {
                Rectangle riverBox = riverImage.getBoundingBoxAt(wp);
                if (playerBox.intersects(riverBox)) {
                    health -= riverDamagePerFrame;
                    System.out.println(health);
                }
            }
        }

        position = new Point(x, y);
    }

    public void setPosition(Point newPosition) {
        position = newPosition;
    }

    // judge whether touch door or not
    private Image getCurrentImage() {
        return facingRight ? playerRight : playerLeft;
    }

    public Rectangle getBoundingBox() {
        return getCurrentImage().getBoundingBoxAt(position);
    }

    // draw
    public void draw() {
        /* draw player according to direction and position */
        if (facingRight) {
            playerRight.draw(position.x, position.y);
        } else {
            playerLeft.draw(position.x, position.y);
        }
    }

    // get values
    public double getHealth() { return health; }
    public double getCoins() { return coins; }

    // update function
    public void update(
            Input input,
            Point[] wallBoundaries,
            boolean isWall,
            Point[] riverBoundaries,
            boolean isRiver) {
        /* Regular update during game */
        move(input, wallBoundaries, isWall, riverBoundaries, isRiver);
        Point mouse = input.getMousePosition();
        if (mouse.x >= position.x) {
            facingRight = true;
        } else {
            facingRight = false;
        }
        draw();
    }
}
