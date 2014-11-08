package com.mygdx.pantallas;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.actores.marcador.ActorMarcador;
import com.mygdx.actores.marcador.ActorTiempo;
import com.mygdx.actores.marcador.ActorOpcion;
import com.mygdx.actores.marcador.JugadoresCampoGroup;
import com.mygdx.actores.marcador.JugadoresSuplentesGroup;
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.listeners.AccionesMarcadorListener;
import com.mygdx.listeners.AccionesOpcionesListener;
import com.mygdx.utilidades.Camara;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;
import com.mygdx.utilidades.Tiempo;

public class PantallaMarcador implements Screen {
	@SuppressWarnings("unused")
	private final BalonmanoApp app;
	private Camara camara;
	private Stage stageListener;
	private Stage stageMarcador;
	private Stage stageJugadores;
	private Stage stageFondo;
	private Stage stageOpciones;
	private Image fondo;
	private ActorOpcion playPause;
	private Actor salir;
	private Actor cambiarParte;
	private Actor cambiarJugador;
	private Tiempo tiempoPartido;
	private ActorTiempo tiempo;
	private ActorMarcador marcador;
	private JugadoresCampoGroup jugadoresCampo;
	private JugadoresSuplentesGroup jugadoresSuplentes;
	
	
	public PantallaMarcador(final BalonmanoApp app){
		this.app=app;
		camara = new Camara(800,400);
		camara.setToOrtho(false,800,400);
		
		stageListener = new Stage();
		stageFondo = new Stage();
		stageMarcador = new Stage();
		stageOpciones = new Stage();
		stageJugadores = new Stage();
		Gdx.input.setInputProcessor(stageListener);
		
		fondo = new Image((Texture)Manager.manager.get(Settings.skin+"/Marcador/fondo.png"));
		fondo.setFillParent(true);
		
		stageFondo.addActor(fondo);
		
		//Marcador
		tiempoPartido = new Tiempo();
		marcador = new ActorMarcador(app);
		tiempo = new ActorTiempo(tiempoPartido,app);
		
		ArrayList<Actor> actoresMarcador = new ArrayList<Actor>();
		actoresMarcador.add(marcador);
		actoresMarcador.add(tiempo);
		
		stageMarcador.addActor(marcador);
		stageMarcador.addActor(tiempo);
		
		//Opciones
		String imagenURL = Settings.skin+"/Marcador/botonPause.png";
		Texture textura = (Texture)Manager.manager.get(imagenURL);
		TextureRegion imagen_pause = new TextureRegion(textura, 0, 0, textura.getWidth(), 48);
		
		imagenURL = Settings.skin+"/Marcador/botonPlay.png";
		textura = (Texture)Manager.manager.get(imagenURL);
		TextureRegion imagen_play = new TextureRegion(textura, 0, 0, textura.getWidth(), 48);
		
		imagenURL = Settings.skin+"/Marcador/botonSalir.png";
		textura = (Texture)Manager.manager.get(imagenURL);
		TextureRegion imagen_salir = new TextureRegion(textura, 0, 0, textura.getWidth(), 48);
		
		imagenURL = Settings.skin+"/Marcador/botonJugadorDes.png";
		textura = (Texture)Manager.manager.get(imagenURL);
		TextureRegion imagen_jcd= new TextureRegion(textura, 0, 0, textura.getWidth(), 48);
		
		imagenURL = Settings.skin+"/Marcador/botonJugadorAct.png";
		textura = (Texture)Manager.manager.get(imagenURL);
		TextureRegion imagen_jca= new TextureRegion(textura, 0, 0, textura.getWidth(), 48);
		
		imagenURL = Settings.skin+"/Marcador/botonCambioParte.png";
		textura = (Texture)Manager.manager.get(imagenURL);
		TextureRegion imagen_parte= new TextureRegion(textura, 0, 0, textura.getWidth(), 48);
		
		float x = 430;
		float x2 = 90;
		float y = 330;
		
		playPause = new ActorOpcion(app,"playPause",x,y,imagen_pause,imagen_play);
		cambiarJugador = new ActorOpcion(app,"cambiarJugador", x+x2, y, imagen_jcd, imagen_jca);
		cambiarParte = new ActorOpcion(app,"cambiarParte", x+2*x2, y, imagen_parte, null);
		salir = new ActorOpcion(app,"salir",x+3*x2,y,imagen_salir,null);
		
		ArrayList<Actor> actoresOpciones = new ArrayList<Actor>();
		actoresOpciones.add(playPause);
		actoresOpciones.add(cambiarParte);
		actoresOpciones.add(cambiarJugador);
		actoresOpciones.add(salir);
		
		stageOpciones.addActor(playPause);
		stageOpciones.addActor(cambiarParte);
		stageOpciones.addActor(cambiarJugador);
		stageOpciones.addActor(salir);
		
		//Jugadores
		jugadoresCampo = new JugadoresCampoGroup();
		jugadoresSuplentes = new JugadoresSuplentesGroup();
		
		stageJugadores.addActor(jugadoresCampo);
		stageJugadores.addActor(jugadoresSuplentes);
		
		
		//Listeners
		stageListener.addListener(new AccionesMarcadorListener(camara,actoresMarcador));
		stageListener.addListener(new AccionesOpcionesListener(camara,actoresOpciones));
		
		//stageListener.addListener(new AccionesJugadoresListener(camara,...);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camara.act(delta);
        camara.update();
        
        if(playPause.getEstado() && !tiempoPartido.getFinal())
        	tiempoPartido.addMiliSeg();
        
        stageFondo.draw();
        stageMarcador.draw();
        stageOpciones.draw();
        stageJugadores.draw();
	}
	
	public void cambiarParte(){
		tiempo.cambiarParte();
	}
	
	public void cambiarJugador(){
		if(jugadoresCampo.isModoCambio()){
			jugadoresCampo.setModoCambio(false);
			jugadoresSuplentes.setModoCambio(false);
		}else{
			jugadoresCampo.setModoCambio(true);
			jugadoresSuplentes.setModoCambio(true);
		}
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
