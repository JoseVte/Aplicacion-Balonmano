package com.mygdx.aplicacion.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.aplicacion.BalonmanoApp;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.title = "TACTIC BOARD";
	    config.width = 800;
	    config.height = 400;
		new LwjglApplication(new BalonmanoApp(), config);
	}
}