package com.mygdx.actores.campo;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.actores.pizarra.MenuActorGroup;

public class ActoresCampoActorGroup extends Group {
	
	private MenuActorGroup menu;
	
	private EquipoActorGroup equipoDefensa;
	private EquipoActorGroup equipoAtaque;
	private BalonActor balon;
	
	private boolean modoEliminable;
	private boolean modoBloqueado;
	private boolean modoBalon;
	
	public ActoresCampoActorGroup(MenuActorGroup menu) {
		
		this.menu = menu;
		
		equipoDefensa = new EquipoActorGroup();
		equipoAtaque = new EquipoActorGroup();
		balon = new BalonActor(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		modoEliminable = false;
		modoBloqueado = false;
		modoBalon = false;
		
		addActor(equipoDefensa);
		addActor(equipoAtaque);
		addActor(balon);
	}
	
	public void anadirJugadorDefensa(int num, float x, float y) {
		
		if (equipoDefensa.getHuecos() > 0) {
			
			if (equipoDefensa.esVacio() && equipoAtaque.esVacio())
				menu.barraInferior.iconoEliminar.activar();
			
			equipoDefensa.anadirJugador(new JugadorDefensaActor(num, x, y));
			if (equipoDefensa.getHuecos() == 0) menu.barraInferior.iconoDefensa.desactivar();
		}	
	}
	
	public void anadirJugadorAtaque(int num, float x, float y) {
		
		if (equipoAtaque.getHuecos() > 0) {
			
			if (equipoDefensa.esVacio() && equipoAtaque.esVacio())
				menu.barraInferior.iconoEliminar.activar();
			
			equipoAtaque.anadirJugador(new JugadorAtaqueActor(num, x, y));
			if (equipoAtaque.getHuecos() == 0) menu.barraInferior.iconoAtaque.desactivar();
		}	
	}
	
	public ArrayList<JugadorAbstractActor> getJugadoresEnCampo() {
		
		Object[] jugadores;
		SnapshotArray<Actor> array;
		ArrayList<JugadorAbstractActor> lista = new ArrayList<JugadorAbstractActor>();
		
		array = equipoDefensa.getChildren();
		jugadores = array.begin();
		for (int i = 0, n = array.size; i < n; i++) {
		     
			lista.add((JugadorAbstractActor) jugadores[i]);      
		}
		array.end();
		
		array = equipoAtaque.getChildren();
		jugadores = array.begin();
		for (int i = 0, n = array.size; i < n; i++) {
		     
			lista.add((JugadorAbstractActor) jugadores[i]);      
		}
		array.end();
		
		return lista;
	}
	
	public BalonActor getBalon() { return balon; }
	
	public SnapshotArray<Actor> getActoresAtaqueEnCampo() {
		
		return equipoAtaque.getChildren();
	}
	
	public void setModoEliminable(boolean eliminable) {
		
		if (eliminable && !modoEliminable) {
			System.out.println("Eliminable");
			modoEliminable = true;
			equipoAtaque.setModoEliminable(true);
			equipoDefensa.setModoEliminable(true);
			
		} else if (!eliminable && modoEliminable) {
			System.out.println("No Eliminable");
			modoEliminable = false;
			equipoAtaque.setModoEliminable(false);
			equipoDefensa.setModoEliminable(false);
		}
	}
	
	public void setModoBloqueado(boolean bloqueado) {
		
		this.modoBloqueado = bloqueado;
	}
	
	public void setModoBalon() {
		
		if (!modoBalon) {
			
			modoBalon = true;
			balon.seleccionar();
		}
		else {
			
			modoBalon = false;
			balon.desseleccionar();
		}
	}
	
	public boolean esModoEliminable() { return modoEliminable; }
	public boolean esModoBloqueado() { return modoBloqueado; }
	public boolean esModoBalon() { return modoBalon; }
	
	public void eliminarJugadorDelCampo(JugadorAbstractActor jugador) {
		
		if (equipoDefensa.pertenece(jugador)) {
			if (equipoDefensa.getHuecos() == 0) menu.barraInferior.iconoDefensa.activar();
			equipoDefensa.eliminarJugador(jugador);
			menu.barraSuperior.activarJugadorDefensa(jugador.getNumJugador());
			
		} else if (equipoAtaque.pertenece(jugador)) {
			if (equipoAtaque.getHuecos() == 0) menu.barraInferior.iconoAtaque.activar();
			equipoAtaque.eliminarJugador(jugador);
			menu.barraSuperior.activarJugadorAtaque(jugador.getNumJugador());
		}
		
		if (equipoDefensa.esVacio() && equipoAtaque.esVacio()) {
			
			setModoEliminable(false);
			menu.barraInferior.iconoEliminar.desactivar();
		}
	}
	
}
