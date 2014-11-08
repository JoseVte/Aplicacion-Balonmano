package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.utilidades.Camara;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;

public class PantallaCarga implements Screen {
	
	private final BalonmanoApp app;
	private Camara camara;
	
	private Texture img_fondo;
	
	public PantallaCarga(final BalonmanoApp app) {
		
		this.app = app;
		
		img_fondo = new Texture(Gdx.files.internal(Settings.skin+"/PantallaCarga/pantalla_carga.png"));
		
		camara = new Camara(800, 400);
		camara.setToOrtho(false, 800, 400);
		
		app.assets.loadAssets(Settings.skin);
	}

	public void render(float delta) {
		String progreso = (int)(Manager.manager.getProgress()*100)+" %";
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camara.act(delta);
        camara.update();
        app.SB.setProjectionMatrix(camara.combined);

        app.SB.begin();
        
        app.SB.draw(img_fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Texture icono = new Texture(Gdx.files.internal(Settings.skin+"/PantallaCarga/logo.png"));
        TextureRegion region = new TextureRegion(icono);
        app.SB.draw(region, 0, 0, 550, 256,512, 512, 0.5f, 0.5f, 00);
        
        if(!Manager.manager.update()){
        	app.font.drawMultiLine(app.SB, "Cargando", Gdx.graphics.getWidth()/2, 95,0,BitmapFont.HAlignment.CENTER);
		}
        
        if (Manager.manager.update()){
        	app.font.drawMultiLine(app.SB, "Toca la pantalla para continuar", Gdx.graphics.getWidth()/2, 95,0,BitmapFont.HAlignment.CENTER);
        }
        
        app.font.drawMultiLine(app.SB, progreso, Gdx.graphics.getWidth()/2, 65,0,BitmapFont.HAlignment.CENTER);
        
        app.SB.end();

        if (Manager.manager.update() && Gdx.input.isTouched()) {	
        	app.setScreen(new PantallaMenu(app));
        	dispose();
        }

        app.font.setColor(1, 1, 1, 1);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
