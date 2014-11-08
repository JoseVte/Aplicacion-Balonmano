package com.mygdx.pantallas;

//Para cargar un sonido:
// Sound sonido = Gdx.audio.newSound(Gdx.files.internal("sonido.mp3"));

//Para poner la musica en loop:
// Music musica = Gdx.audio.newMusic(Gdx.files.internal("musica.mp3"));
// musica.setLooping(true);
// musica.play();
		
//Para cargar una imagen:
// Texture imagen = new Texture(Gdx.files.internal("imagen.png"));


import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.actores.campo.ActoresCampoActorGroup;
import com.mygdx.actores.pizarra.MenuActorGroup;
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.listeners.BarraMenuInferiorListener;
import com.mygdx.listeners.BarraMenuSuperiorListener;
import com.mygdx.listeners.MovCamaraAndroidListener;
import com.mygdx.listeners.MovCamaraDesktopListener;
import com.mygdx.listeners.AccionesCampoListener;
import com.mygdx.utilidades.Camara;
import com.mygdx.utilidades.Settings;

public class PantallaPizarra implements Screen {
	
	private final BalonmanoApp app;
	private Camara camara;
	
	// Escenarios (superpuestos en capas):
	private Stage stageListener;
	private Stage stageMenu;
	private Stage stageCampo;
	
	// Grupo de actores mostrados sobre el campo:
	private ActoresCampoActorGroup actoresCampo;
	
	// Imagenes:
	private Image campoFondo;
	
	// Menu:
	private MenuActorGroup menu;
	
	
	public PantallaPizarra(final BalonmanoApp app) {
		
		this.app = app;
		
		// Crear camara:
		camara = new Camara(800, 400);
		
		// Crear los escenarios:
		stageCampo = new Stage(new ScreenViewport(camara));
		stageMenu = new Stage();
		stageListener = new Stage();
		Gdx.input.setInputProcessor(stageListener);
		
		// Cargar fondo:
		campoFondo = new Image(new Texture(Gdx.files.internal(Settings.skin+"/Campo/campo_balonmano.jpg")));
		
		// Crear menu:
		menu = new MenuActorGroup();
		
		// Crear grupo de actores del campo:
		actoresCampo = new ActoresCampoActorGroup(menu);
	}
	
	public void show() {
		
		// A�adir fondo:
		campoFondo.setFillParent(true);
		stageCampo.addActor(campoFondo);
		
		// A�adir actores al campo:
		stageCampo.addActor(actoresCampo);
		
		// A�adir barra de menu:
		stageMenu.addActor(menu);
		
		asignarListeners();
	}

	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camara.act(delta);
        camara.update();
		
        stageCampo.act(delta);
        stageCampo.draw();
        
        stageMenu.act(delta);
        stageMenu.draw();
	}

	@Override
	public void resize (int width, int height) {
	    
	    stageCampo.getViewport().update(width, height, true);
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
		
		stageCampo.dispose();
		stageMenu.dispose();
		stageListener.dispose();

	}
	
	public void asignarListeners() {
		
		// Asigna diferentes listeners dependiendo de 
		// si loejecutamos en Android o en Escritorio:
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			stageListener.addListener(new MovCamaraDesktopListener(camara));
		} else if(Gdx.app.getType() == ApplicationType.Android) { 
			stageListener.addListener(new MovCamaraAndroidListener(camara));		
		}
		
		stageListener.addListener(new BarraMenuInferiorListener(app,menu, camara, actoresCampo));
		stageListener.addListener(new BarraMenuSuperiorListener(menu, camara, actoresCampo));
		stageListener.addListener(new AccionesCampoListener(actoresCampo, camara));
	}
}
