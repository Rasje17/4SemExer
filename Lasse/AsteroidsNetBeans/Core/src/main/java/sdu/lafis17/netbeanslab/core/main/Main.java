package sdu.lafis17.netbeanslab.core.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;



public class Main {

	public static void main(String[] args) {

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Asteroids";
		cfg.width = 800;
		cfg.height = 600;
		cfg.useGL30 = false;
		cfg.resizable = false;
                System.out.println("Main");

		new LwjglApplication(new Game(), cfg);

	}

}
