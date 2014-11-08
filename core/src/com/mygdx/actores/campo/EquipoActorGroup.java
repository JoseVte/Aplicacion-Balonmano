package com.mygdx.actores.campo;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

public class EquipoActorGroup extends Group {
	
	private final int CONST_MAX_JUGADORES = 7;
	
	private int numJugadores;
	
	public EquipoActorGroup() {
		
		numJugadores = 0;
	}
	
	public int getHuecos() {
		
		return CONST_MAX_JUGADORES - numJugadores;
	}
	
	public boolean esVacio() { 
		if (numJugadores == 0) return true;
		else return false;
	}
	
	public void anadirJugador(JugadorAbstractActor jugador) {
		
		if (getHuecos() > 0) {
			
			addActor(jugador);
			numJugadores++;
		} 
	}
	
	public void eliminarJugador(JugadorAbstractActor jugador) {
		
		removeActor(jugador);
		numJugadores--;
	}
	
	public boolean pertenece(JugadorAbstractActor jugador) {
		
		SnapshotArray<Actor> array = getChildren();
		Object jugadores[] = array.begin();
		for (int i = 0, n = array.size; i < n; i++) {
		     
			if (jugador == (JugadorAbstractActor) jugadores[i]) {
				
				return true;
			}
		}
		array.end();
		
		return false;
	}
	
	public void setModoEliminable(boolean eliminable) {
			
		SnapshotArray<Actor> array = getChildren();
		Object jugadores[] = array.begin();
		for (int i = 0, n = array.size; i < n; i++) {
		     
			((JugadorAbstractActor) jugadores[i]).setModoEliminable(eliminable);
		}
		array.end();	
	}
}
