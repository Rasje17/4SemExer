package dk.sdu.mmmi.cbse.asteroidsystem;



import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

	private Entity asteroid;

	public AsteroidPlugin() {
	}

	@Override
	public void start(GameData gameData, World world) {

		// Add entities to the world
		asteroid = createAsteroid(gameData);
		world.addEntity(asteroid);
	}

	private Entity createAsteroid(GameData gameData) {

		float deacceleration = 10;
		float acceleration = 200;
		float maxSpeed = 300;
		float rotationSpeed = 5;
		float x = gameData.getDisplayWidth() / 3;
		float y = gameData.getDisplayHeight() / 3;
		float radians = 3.1415f / 2;

		Entity asteroid = new Asteroid();
		asteroid.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
		asteroid.add(new PositionPart(x, y, radians));

		return asteroid;
	}

	@Override
	public void stop(GameData gameData, World world) {
		// Remove entities
		world.removeEntity(asteroid);
	}

}
