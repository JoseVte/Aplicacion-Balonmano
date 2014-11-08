package com.mygdx.listeners;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.actores.campo.ActorMovil;
import com.mygdx.actores.campo.ActoresCampoActorGroup;
import com.mygdx.actores.campo.JugadorAbstractActor;
import com.mygdx.utilidades.Camara;

public class AccionesCampoListener extends InputListener {
	
	private ActoresCampoActorGroup actoresCampo;
	private ActorMovil actorSeleccionado;
	
	private Camara camara;
	
	private float mouseX;
	private float mouseY;
	
	public AccionesCampoListener(ActoresCampoActorGroup actoresCampo, Camara camara) {
		
		this.actoresCampo = actoresCampo;
		this.camara = camara;
		this.actorSeleccionado = null;
		
		this.mouseX = 0;
		this.mouseY = 0;
	}
	
	// Evento que sucede al pulsar la pantalla:
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		
		ArrayList<JugadorAbstractActor> jugadoresEnCampo;
		
		// Traducir las cordenadas de la pantalla a coordenadas en el escenario:
		x = x * camara.zoom + camara.position.x - (camara.viewportWidth/2 * camara.zoom);
		y = y * camara.zoom + camara.position.y - (camara.viewportHeight/2 * camara.zoom);
		
		// Si se pulsa sobre el balon y est� seleccionado:
		if (actoresCampo.esModoBalon() && actoresCampo.getBalon().getZona().contains(x, y)) {
			
			actorSeleccionado = actoresCampo.getBalon();
			 
			mouseX = x;
			mouseY = y;
						 
			return true;
			
		} else if (!actoresCampo.getBalon().esSeleccionado()) {
		
			// Obtener jugadores en el campo:
			jugadoresEnCampo = actoresCampo.getJugadoresEnCampo();
			for(JugadorAbstractActor jugador : jugadoresEnCampo) {
				
				// Si el jugador esta en modo eliminable y se pulsa el jugador o el icono de eliminar:
				if (jugador.esModoEliminable() && (jugador.getZona().contains(x, y) || jugador.getZonaEliminar().contains(x, y))) {
					
					actoresCampo.eliminarJugadorDelCampo(jugador);
					break;
				}
				// Si no esta en modo eliminable:
				else if (!actoresCampo.esModoBloqueado() && jugador.getZona().contains(x, y)) {
					 
					actorSeleccionado = jugador;
								 
					mouseX = x;
					mouseY = y;
								 
					return true;
				}
			}
		}
		
		return false;	
	}
	
	// Evento que sucede mientras se pulsa la pantalla:
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		
		if (!actoresCampo.esModoBloqueado()) {
			
			// Traducir las cordenadas de la pantalla a coordenadas en el escenario:
			x = x * camara.zoom + camara.position.x - (camara.viewportWidth/2 * camara.zoom);
			y = y * camara.zoom + camara.position.y - (camara.viewportHeight/2 * camara.zoom);
			
			if (x != mouseX) {
				
				actorSeleccionado.mover(x - mouseX, 0);
				mouseX = x;
			}
			
			if (y != mouseY) {
				
				actorSeleccionado.mover(0, y - mouseY);
				mouseY = y;
			}
		}
	}
	
	// Evento que sucede cuando se deja de pulsar la pantalla:
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		
		actorSeleccionado = null;
	}

}
