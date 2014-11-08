package com.mygdx.listeners;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.actores.campo.ActoresCampoActorGroup;
import com.mygdx.actores.pizarra.IconoMenuActor;
import com.mygdx.actores.pizarra.MenuActorGroup;
import com.mygdx.utilidades.Camara;
@SuppressWarnings("unused")
public class BarraMenuSuperiorListener extends InputListener {
	
	
	private MenuActorGroup menu;
	private Camara camara;
	private ActoresCampoActorGroup actoresCampo;
	
	private float mouseY;
	
	public BarraMenuSuperiorListener(MenuActorGroup menu, Camara camara, ActoresCampoActorGroup actoresCampo) {
		
		this.menu = menu;
		this.camara = camara;
		this.actoresCampo = actoresCampo;
	}
	
	// Evento que sucede al pulsar la pantalla:
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		
		mouseY = y;
		
		// Si el menu esta mostrado del todo:
		if (menu.barraSuperior.esTotalMostrado()) {
			
			// Si se pulsa en la barra del menu:
			if (menu.barraSuperior.getZona().contains(x, y)) {
				
				ArrayList<IconoMenuActor> listaIconosJugadores = null;
				
				if (menu.barraSuperior.esMenuDefensa()) 
					listaIconosJugadores = menu.barraSuperior.getIconosDefensa();
				else if (menu.barraSuperior.esMenuAtaque()) 
					listaIconosJugadores = menu.barraSuperior.getIconosAtaque();
				
				for (IconoMenuActor icono : listaIconosJugadores) {
					
					// si se pulsa el icono y esta activado:
					if (icono.esActivado() && icono.getZona().contains(x, y)) {
						
						menu.barraSuperior.getIconoSeleccionado().desseleccionar();
						icono.seleccionar();
						break;
					}
				}
			}
		}
		
		return true;	
	}
	
	// Evento que sucede mientras se pulsa la pantalla:
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		
	}
	
	// Evento que sucede cuando se deja de pulsar la pantalla:
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {		
		
	}
}