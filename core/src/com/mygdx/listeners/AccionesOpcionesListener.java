package com.mygdx.listeners;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.actores.marcador.ActorOpcion;
import com.mygdx.utilidades.Camara;

public class AccionesOpcionesListener extends InputListener {
	private Camara camara;
	private ArrayList<Actor> botones;
	
	public AccionesOpcionesListener(Camara camara, ArrayList<Actor> actoresOpciones) {
		this.camara=camara;
		botones=actoresOpciones;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		x = x * camara.zoom + camara.position.x - (camara.viewportWidth/2 * camara.zoom);
		y = y * camara.zoom + camara.position.y - (camara.viewportHeight/2 * camara.zoom);
		
		for(Actor boton : botones){
			if(boton instanceof ActorOpcion){
				if(((ActorOpcion)boton).getZona().contains(x,y)){
					((ActorOpcion)boton).doAccion();
					break;
				}
			}
		}
		
		return false;
	}
}
