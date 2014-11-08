package com.mygdx.aplicacion;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pantallas.PantallaCarga;
import com.mygdx.utilidades.DatabaseAbstract;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;


public class BalonmanoApp extends Game {
	public SpriteBatch SB;
	public BitmapFont font;
	public Manager assets;
	public DatabaseAbstract DB;
	
	public void create() {
		SB = new SpriteBatch();
		assets = new Manager();
		Settings.load();
		font = new BitmapFont(Gdx.files.internal(Settings.skin+"/Fuentes/cargar.fnt"),Gdx.files.internal(Settings.skin+"/Fuentes/cargar.png"),false);
		
		this.setScreen(new PantallaCarga(this));
	}
	
	public void render() {
		super.render();
	}
	
	public void dispose() {
		font.dispose();
		SB.dispose();
	}
}