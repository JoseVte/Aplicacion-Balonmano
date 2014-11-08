package com.mygdx.actores.marcador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.utilidades.Settings;

public class JugadoresCampoGroup extends Group {
	public static final int NUM_JUGADORES_CAMPO = 7;
	private boolean modoCambio;
	private boolean modoFalta;
	private boolean modoGol;
	
	private List<ActorJugador> jugadores;
	
	public JugadoresCampoGroup() {
		setModoCambio(false);
		setModoFalta(false);
		setModoGol(false);
		
		jugadores = new ArrayList<ActorJugador>();
		
		volcadoJugadores();
		
		int i=0;
		for(ActorJugador jugador : jugadores){
			jugador.setXY(10, 280-40*i);
			i++;
			addActor(jugador);
		}
		
		Settings.guardarPruebas(jugadores);
	}
	
	private void volcadoJugadores(){
		//Guardar los jugadores en la lista
		Iterator<?> it = Settings.getPreferences().get().entrySet().iterator();
		
		while(it.hasNext()){
			Entry<?, ?> e = (Entry<?, ?>) it.next();
			jugadores.add(ActorJugador.stringToJugador((String) e.getValue()));
		}
	}

	public boolean isModoCambio() {
		return modoCambio;
	}

	public void setModoCambio(boolean modoCambio) {
		this.modoCambio = modoCambio;
	}

	public boolean isModoFalta() {
		return modoFalta;
	}

	public void setModoFalta(boolean modoFalta) {
		this.modoFalta = modoFalta;
	}

	public boolean isModoGol() {
		return modoGol;
	}

	public void setModoGol(boolean modoGol) {
		this.modoGol = modoGol;
	}
	

}
