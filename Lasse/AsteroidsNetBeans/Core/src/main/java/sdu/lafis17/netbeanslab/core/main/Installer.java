/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.lafis17.netbeanslab.core.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Asteroids";
		cfg.width = 800;
		cfg.height = 600;
		cfg.useGL30 = false;
		cfg.resizable = false;
                System.out.println("Restored()");

		new LwjglApplication(new Game(), cfg);
    }

}
