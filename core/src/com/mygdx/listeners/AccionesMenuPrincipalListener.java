package com.mygdx.listeners;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.actores.menu.BotonLinkActor;
import com.mygdx.actores.menu.BotonPantallaActor;
import com.mygdx.pantallas.PantallaOpciones;
import com.mygdx.pantallas.PantallaTemas.GuardarActor;
import com.mygdx.utilidades.Camara;
import com.mygdx.utilidades.Settings;

public class AccionesMenuPrincipalListener extends InputListener {
	private Camara camara;
	private ArrayList<Actor> botonesMenu;
	 
	public AccionesMenuPrincipalListener(Camara camara,ArrayList<Actor> botonesMenu) {
		this.camara=camara;
		this.botonesMenu=botonesMenu;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		
		x = x * camara.zoom + camara.position.x - (camara.viewportWidth/2 * camara.zoom);
		y = y * camara.zoom + camara.position.y - (camara.viewportHeight/2 * camara.zoom);
		
		for(Actor boton : botonesMenu){
			if(boton instanceof BotonPantallaActor){	
				if(((BotonPantallaActor)boton).getZona().contains(x,y)){
					((BotonPantallaActor)boton).abrirSeccion();
					break;
				}
			}
			if(boton instanceof BotonLinkActor){
				if(((BotonLinkActor)boton).getZona().contains(x,y)){
					((BotonLinkActor)boton).abrirURL();
					break;
				}
			}
			if(boton instanceof GuardarActor){
				if(((GuardarActor)boton).getZona().contains(x,y)){
					((GuardarActor)boton).app.setScreen((Screen) new PantallaOpciones(((GuardarActor)boton).app));
					Settings.save();
					break;
				}
			}
		}
		
		return false;
	}
}
