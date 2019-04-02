package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {
    
    private float speed = 400;
    
    public Bullet(float x, float y, float heading) {
        this.x = x;
        this.y = y;
        this.radians = heading;
        
        shapex = new float[2];
	shapey = new float[2];
        
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;

    }
    
    private void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 3;
        shapey[0] = y + MathUtils.sin(radians) * 3;

        shapex[1] = x + MathUtils.cos(radians - 3.1415f) * 3;
        shapey[1] = y + MathUtils.sin(radians - 3.1145f) * 3;
    }
    
    public void update(float dt) {
        
        x += dx * dt;
        y += dy * dt;
        
        setShape();
        
        wrap();
        
    }
    
    public void draw(ShapeRenderer sr) {

        sr.setColor(1, 1, 1, 1);

        sr.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0, j = shapex.length - 1;
                i < shapex.length;
                j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }

        sr.end();

    }
}