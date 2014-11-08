package com.mygdx.pantallas;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.actores.menu.BotonLinkActor;
import com.mygdx.actores.menu.BotonPantallaActor;
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.listeners.AccionesMenuPrincipalListener;
import com.mygdx.utilidades.Camara;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;

public class PantallaOpciones implements Screen {
	@SuppressWarnings("unused")
	private final BalonmanoApp app;
	private Camara camara;
	private Stage stageListener;
	private Stage stageBotones;
	private Stage stageFondo;
	private Image fondo;
	private Image icono;
	private Image titulo;
	
	public PantallaOpciones(final BalonmanoApp app){
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
		String imgBotonDes = Settings.skin+"/PantallaMenu/boton_des.png";
		
		BotonPantallaActor tema = new BotonPantallaActor(xBotones,250, "Tema","Temas",imgBoton, app);
		
		BotonLinkActor link = new BotonLinkActor(xBotones,190, "Visitamos", "www.google.es",imgBotonDes, app);
		BotonLinkActor donar = new BotonLinkActor(xBotones,130, "Donar","www.paypal.es",imgBotonDes, app);
	
		BotonPantallaActor atras = new BotonPantallaActor(xBotones,70, "Atras","Menu",imgBoton, app);
		
		ArrayList<Actor> listaBotones = new ArrayList<Actor>();
		listaBotones.add(tema);
		listaBotones.add(link);
		listaBotones.add(atras);
		listaBotones.add(donar);
		
		stageBotones.addActor(tema);
		stageBotones.addActor(link);
		stageBotones.addActor(atras);
		stageBotones.addActor(donar);
		
		stageListener.addListener(new AccionesMenuPrincipalListener(camara,listaBotones ));
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
