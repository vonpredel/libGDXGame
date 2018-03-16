package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Game;
import com.mygdx.game.Utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "2DGAME !";
		config.width = Constants.DEFAULT_RESOLUTION_WIDTH;
//		config.width = 1920;
		config.height = Constants.DEFAULT_RESOLUTION_HEIGHT;
//		config.height = 1080;
		config.resizable = false;
//		config.fullscreen = true;
		new LwjglApplication(new Game(), config);
	}
}
