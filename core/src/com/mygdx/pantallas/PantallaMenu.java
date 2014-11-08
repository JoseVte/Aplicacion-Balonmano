package com.mygdx.pantallas;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.actores.menu.BotonPantallaActor;
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.listeners.AccionesMenuPrincipalListener;
import com.mygdx.utilidades.Camara;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;

public class PantallaMenu implements Screen {
	@SuppressWarnings("unused")
	private final BalonmanoApp app;
	private Camara camara;
	private Stage stageListener;
	private Stage stageBotones;
	private Stage stageFondo;
	private Image fondo;
	private Image icono;
	private Image titulo;
	
	public PantallaMenu(final BalonmanoApp app){
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
		
		titulo = new Image((Texture)Manager.manager.get(Settings.skin+"/PantallaMenu/titulo.png"));
		titulo.setX(230);
		titulo.setY(300);
		
		fondo = new Image((Texture)Manager.manager.get(Settings.skin+"/PantallaMenu/fondo_menu.png"));
		fondo.setFillParent(true);
		
		int xBotones = Gdx.graphics.getWidth()/2-144;
		String imgBoton = Settings.skin+"/PantallaMenu/boton.png";
		String imgBotonDes = Settings.skin+"/PantallaMenu/boton_des.png";
		
		BotonPantallaActor pizarra = new BotonPantallaActor(xBotones,250,"Pizarra","Pizarra",imgBoton,app);
		BotonPantallaActor gestion = new BotonPantallaActor(xBotones,190,"Gestion de equipos","Gestion",imgBotonDes,app);
		BotonPantallaActor marcador = new BotonPantallaActor(xBotones,130,"Marcador","Marcador",imgBoton,app);
		BotonPantallaActor info = new BotonPantallaActor(xBotones,70,"Opciones","Opciones",imgBoton,app);
		
		ArrayList<Actor> listaBotones = new ArrayList<Actor>();
		listaBotones.add(pizarra);
		listaBotones.add(gestion);
		listaBotones.add(marcador);
		listaBotones.add(info);
		
		stageFondo.addActor(fondo);
		stageFondo.addActor(icono);
		stageFondo.addActor(titulo);
		
		stageBotones.addActor(pizarra);
		stageBotones.addActor(gestion);
		stageBotones.addActor(marcador);
		stageBotones.addActor(info);
		
		stageListener.addListener(new AccionesMenuPrincipalListener(camara,listaBotones ));
	}
	
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
