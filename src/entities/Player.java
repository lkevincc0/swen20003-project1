package entities;

import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Player {
    private Point position;
    private int health;
    private int coins;
    private int speed;
    private Image playerLeft;
    private Image playerRight;
    private boolean facingRight;
    // others
    private Image wallImage;

    public Player(Point startPos, int health, int coins, int speed) {
        this.position = startPos;
        this.health = health;
        this.coins = coins;
        this.speed = speed;
        this.playerLeft = new Image("res/player_left.png");
        this.playerRight = new Image("res/player_right.png");
        this.facingRight = true;
        // others
        this.wallImage = new Image("res/wall.png");
    }

    private void move(Input input, Point[] boundaries, boolean isWall) {
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
            for (Point wp : boundaries) {
                Rectangle wallBox = wallImage.getBoundingBoxAt(wp);
                if (playerBox.intersects(wallBox)) {
                    x = position.x;
                    y = position.y;
                    break;
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

    // update function
    public void update(Input input, Point[] boundaries, boolean isWall) {
        /* Regular update during game */
        move(input, boundaries, isWall);
        Point mouse = input.getMousePosition();
        if (mouse.x >= position.x) {
            facingRight = true;
        } else {
            facingRight = false;
        }
        draw();
    }
}
