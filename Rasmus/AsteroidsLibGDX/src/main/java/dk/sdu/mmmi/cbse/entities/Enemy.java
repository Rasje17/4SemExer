package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Enemy extends SpaceObject {

    private final int MAX_BULLETS = 4;

    private boolean left;
    private boolean right;
    private boolean up;
    
    private ArrayList<Bullet> bullets;

    private float maxSpeed;
    private float acceleration;
    private float deceleration;

    public Enemy(ArrayList<Bullet> bullets) {
        
        this.bullets = bullets;

        x = Game.WIDTH / 3;
        y = Game.HEIGHT / 3;

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

    public void shoot() {
    if (bullets.size() == MAX_BULLETS) return;
        bullets.add(new Bullet(x, y, radians));
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

        sr.setColor(255, 255, 0, 1);

        sr.begin(ShapeType.Line);

        for (int i = 0, j = shapex.length - 1;
                i < shapex.length;
                j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }

        sr.end();

    }

    private class AI implements Runnable {

        public AI() {
        }

        @Override
        public void run() {
            while (true) {
                int r = (int) (1 + Math.random() * 9);
                switch (r) {
                    case 1:
                        setLeft(true);
                        //System.out.println(r);
                        break;
                    case 2:
                        setLeft(false);
                        //System.out.println(r);
                        break;
                    case 3:
                        setRight(true);
                        //System.out.println(r);
                        break;
                    case 4:
                        setRight(false);
                        //System.out.println(r);
                        break;
                    case 5:
                        setUp(true);
                        //System.out.println(r);
                        break;
                    case 6:
                        setUp(true);
                        //System.out.println(r); 
                        break;
                    case 7:
                        shoot();
                        //System.out.println(r); 
                        break;
                    case 8:
                        shoot();
                        //System.out.println(r);
                        break;
                    case 9:
                        shoot();
                        //System.out.println(r);  
                        break;
                    case 10:
                        shoot();
                        //System.out.println(r); 
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
