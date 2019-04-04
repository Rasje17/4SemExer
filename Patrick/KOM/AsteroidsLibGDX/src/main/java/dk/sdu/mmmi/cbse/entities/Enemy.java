package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Enemy extends SpaceObject {

    private boolean left;
    private boolean right;
    private boolean up;

    private float maxSpeed;
    private float acceleration;
    private float deceleration;

    private final int MAXBULLETS = 4;
    private ArrayList<Bullet> bullets;

    public Enemy() {

        bullets = new ArrayList<Bullet>();

        Random rand = new Random();
        float rx = (float) rand.nextDouble();
        float ry = (float) rand.nextDouble();
        x = Game.WIDTH * rx;
        y = Game.HEIGHT * ry;

        maxSpeed = 300;
        acceleration = 200;
        deceleration = 10;

        shapex = new float[4];
        shapey = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 3;

        AI ai = new AI();
        Thread t = new Thread(ai);
        t.start();
    }

    private void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 8;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    private void shoot() {
        if (MAXBULLETS > bullets.size()) {
            bullets.add(new Bullet(shapex[0], shapey[0], radians));
        }
    }

    public void update(float dt) {

        // turning
        if (left) {
            radians += rotationSpeed * dt;
        } else if (right) {
            radians -= rotationSpeed * dt;
        }

        // accelerating
        if (up) {
            dx += MathUtils.cos(radians) * acceleration * dt;
            dy += MathUtils.sin(radians) * acceleration * dt;
        }

        // deceleration
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if (vec > 0) {
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
        }
        if (vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape();

        // screen wrap
        wrap();

    }

    public void draw(ShapeRenderer sr) {

        sr.setColor(1, 1, 1, 1);

        sr.begin(ShapeType.Line);

        for (int i = 0, j = shapex.length - 1;
                i < shapex.length;
                j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }

        sr.end();

    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    private class AI implements Runnable {

        public AI() {
        }

        @Override
        public void run() {
            while (true) {
                int r = (int) (1 + Math.random() * 9);
                System.out.println(r);
                switch (r) {
                    case 1:
                        setLeft(true);
                        break;
                    case 2:
                        setLeft(false);
                        break;
                    case 3:
                        setRight(true);
                        break;
                    case 4:
                        setRight(false);
                        break;
                    case 5:
                        setUp(true);
                        break;
                    case 6:
                        setUp(true);
                        break;
                    case 7:
                        shoot();
                        break;
                    case 8:
                        shoot();
                        break;
                    case 9:
                        shoot();
                        break;
                    case 10:
                        setUp(false);
                        break;
                }
                try {
                    Thread.sleep(600);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
