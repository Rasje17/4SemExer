package dk.sdu.mmmi.cbse.asteroid;

import static dk.sdu.mmmi.cbse.asteroid.AsteroidType.MEDIUM;
import static dk.sdu.mmmi.cbse.asteroid.AsteroidType.SMALL;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SplitterPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Random;

public class AsteroidSplitter implements IEntityProcessingService {
    
    Random rnd = new Random();

    @Override
    public void process(GameData gameData, World world) {
         for (Entity asteroid : world.getEntities(Asteroid.class)) {
            Asteroid theAsteroid = (Asteroid) asteroid;
            PositionPart posPart = asteroid.getPart(PositionPart.class);
            SplitterPart splitter = asteroid.getPart(SplitterPart.class);

            if (theAsteroid.getSize().equals("LARGE") && splitter.ShouldSplit()) {
                splitter.setHasSplit(true);
                splitter.setShouldSplit(false);
                Asteroid mediumAsteroidOne = createMediumAsteroid(posPart.getX(), posPart.getY(), splitter.getID());
                Asteroid mediumAsteroidTwo = createMediumAsteroid(posPart.getX(), posPart.getY(), splitter.getID());
                world.addEntity(mediumAsteroidOne);
                world.addEntity(mediumAsteroidTwo);
                continue;
            }

            if (theAsteroid.getSize().equals("MEDIUM") && splitter.ShouldSplit()) {
                splitter.setHasSplit(true);
                splitter.setShouldSplit(false);
                Asteroid smallAsteroidOne = createSmallAsteroid(posPart.getX(), posPart.getY(), splitter.getID());
                Asteroid smallAsteroidTwo = createSmallAsteroid(posPart.getX(), posPart.getY(), splitter.getID());
                world.addEntity(smallAsteroidOne);
                world.addEntity(smallAsteroidTwo);
                continue;
            }

            if (theAsteroid.getSize().equals("SMALL") && splitter.ShouldSplit()) {
                splitter.setHasSplit(true);
                splitter.setShouldSplit(false);
                LifePart lp = theAsteroid.getPart(LifePart.class);
                lp.setLife(0);
                continue;
            }
        }
    }

    private Asteroid createMediumAsteroid(float x, float y, String id) {
        float speed = 175;
        float radians = 3.1415f / 2 + (float) Math.random();

        Entity asteroid = new Asteroid(MEDIUM);
        float[] color = new float[4];
        color[0] = 1.0f;
        color[1] = 1.0f;
        color[2] = 1.0f;
        color[3] = 1.0f;

        asteroid.setColor(color);

        asteroid.add(new MovingPart(0, speed, speed, 0));
        asteroid.add(new PositionPart(x + rnd.nextInt(50), y + rnd.nextInt(50), radians));
        asteroid.add(new LifePart(1));
        asteroid.add(new SplitterPart(id));
        asteroid.setRadius(10);

        return (Asteroid) asteroid;
    }

    private Asteroid createSmallAsteroid(float x, float y, String id) {
        float speed = 150;
        float radians = 3.1415f / 2 + (float) Math.random();

        float[] color = new float[4];
        color[0] = 1.0f;
        color[1] = 1.0f;
        color[2] = 1.0f;
        color[3] = 1.0f;

        Entity asteroid = new Asteroid(SMALL);
        asteroid.add(new MovingPart(0, speed, speed, 0));
        asteroid.add(new PositionPart(x + rnd.nextInt(50), y + rnd.nextInt(50), radians));
        asteroid.add(new LifePart(1));
        asteroid.add(new SplitterPart(id));
        asteroid.setColor(color);
        asteroid.setRadius(5);

        return (Asteroid) asteroid;
    }
}