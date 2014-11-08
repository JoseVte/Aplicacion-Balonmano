package com.mygdx.pantallas;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.listeners.AccionesMenuPrincipalListener;
import com.mygdx.utilidades.Camara;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;

public class PantallaTemas implements Screen {

	public class GuardarActor extends Actor {
		private Rectangle zona;
		private String texto;
		private Texture textura;
		private TextureRegion imagen;
		private float x_texto;
		public final BalonmanoApp app;
		
		public GuardarActor(int x, int y, String texto, String imgURL, BalonmanoApp app) {
			this.app=app;
			this.texto = texto;
			textura = (Texture)Manager.manager.get(imgURL);
			imagen = new TextureRegion(textura, 0, 0, textura.getWidth(), 48);
			
			x_texto=x+24;
			
			setSize(imagen.getRegionWidth(), imagen.getRegionHeight());
			setPosition(x, y);
			
			zona = new Rectangle();
			zona.x = getX();
			zona.y = getY();
			zona.width = getWidth();
			zona.height = getHeight();
		}
		
		public Rectangle getZona() {
			return zona;
		}
		
		public void draw(Batch batch, float parentAlpha) {
			Color color = getColor();
			batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		    batch.draw(imagen, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		    app.font.draw(batch,texto,x_texto,getY()+31);
		    app.font.setScale(0.8f);
		}

	}

	@SuppressWarnings("unused")
	private final BalonmanoApp app;
	private Camara camara;
	private Stage stageListener;
	private Stage stageBotones;
	private Stage stageFondo;
	private Image fondo;
	private Image icono;
	private Image titulo;
	
	public PantallaTemas(final BalonmanoApp app){
		this.app=app;
		camara = new Camara(800,400);
		camara.setToOrtho(false,800,400);
		
		stageListener = new Stage();
		stageFondo = new Stage();
		stageBotones = new Stage();
		Gdx.input.setInputProcessor(stageListener);
		
		icono = new Image((Texture)Manager.manager.get(Settings.skin+"/PantallaCarga/logo.png"));
		icono.setFillParent(false);
		icono.setOrigin(-100, 400);
		icono.setScale(0.5f);
		
		titulo = new Image((Texture)Manager.manager.get(Settings.skin+"/PantallaMenu/informacion.png"));
		titulo.setX(230);
		titulo.setY(300);
		
		fondo = new Image((Texture)Manager.manager.get(Settings.skin+"/PantallaMenu/fondo_menu.png"));
		fondo.setFillParent(true);
		
		stageFondo.addActor(fondo);
		stageFondo.addActor(icono);
		stageFondo.addActor(titulo);
		
		int xBotones = Gdx.graphics.getWidth()/2-144;
		String imgBoton = Settings.skin+"/PantallaMenu/boton.png";
		
		Actor guardar = new GuardarActor(xBotones,130, "Guardar",imgBoton,app);
		
		stageBotones.addActor(guardar);
		
		ArrayList<Actor> listaBotones = new ArrayList<Actor>();
		listaBotones.add(guardar);
		stageListener.addListener(new AccionesMenuPrincipalListener(camara,listaBotones));
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camara.act(delta);
        camara.update();
        
        stageFondo.draw();
        stageBotones.draw();
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
