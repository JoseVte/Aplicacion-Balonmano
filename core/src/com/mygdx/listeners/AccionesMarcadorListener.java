package com.mygdx.listeners;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.actores.marcador.ActorMarcador;
import com.mygdx.utilidades.Camara;

public class AccionesMarcadorListener extends InputListener {
	private Camara camara;
	private ArrayList<Actor> botones;
	 
	public AccionesMarcadorListener(Camara camara,ArrayList<Actor> botones) {
		this.camara=camara;
		this.botones=botones;
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		x = x * camara.zoom + camara.position.x - (camara.viewportWidth/2 * camara.zoom);
		y = y * camara.zoom + camara.position.y - (camara.viewportHeight/2 * camara.zoom);
		
		for(Actor boton : botones){
			if(boton instanceof ActorMarcador){
				if(((ActorMarcador)boton).getZona().contains(x, y)){
					((ActorMarcador)boton).mostrar();
					break;
				}
				if(((ActorMarcador)boton).getEstado()){
					((ActorMarcador)boton).cambiarMarcador(x,y);
				}
			}
		}
		
		return false;
	}
}
