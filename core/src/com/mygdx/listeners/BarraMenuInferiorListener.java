package com.mygdx.listeners;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.actores.campo.ActoresCampoActorGroup;
import com.mygdx.actores.campo.JugadorAbstractActor;
import com.mygdx.actores.pizarra.MenuActorGroup;
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.pantallas.PantallaMenu;
import com.mygdx.utilidades.Camara;

public class BarraMenuInferiorListener extends InputListener {
	
	private MenuActorGroup menu;
	private Camara camara;
	private ActoresCampoActorGroup actoresCampo;
	private final BalonmanoApp app;
	
	private float mouseY;
	
	public BarraMenuInferiorListener(final BalonmanoApp app,MenuActorGroup menu, Camara camara, ActoresCampoActorGroup actoresCampo) {
		this.app=app;
		this.menu = menu;
		this.camara = camara;
		this.actoresCampo = actoresCampo;
	}
	
	// Evento que sucede al pulsar la pantalla:
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		
		mouseY = y;
		
		// Si el menu esta mostrado del todo:
		if (menu.barraInferior.esTotalMostrado()) {
			
			// Si se pulsa en la barra del menu:
			if (menu.barraInferior.getZona().contains(x, y)) {
				
				// Si se pulsa el icono de eliminar jugador:
				if (menu.barraInferior.iconoEliminar.getZona().contains(x, y)) {
					
					if (!menu.barraInferior.iconoEliminar.esSeleccionado() && menu.barraInferior.iconoEliminar.esActivado()) {
						
						menu.barraInferior.iconoEliminar.seleccionar();
						
						if (menu.barraInferior.iconoDefensa.esSeleccionado()) {
							menu.barraInferior.iconoDefensa.desseleccionar();
							menu.barraSuperior.ocultar(); 
						}
						else if (menu.barraInferior.iconoAtaque.esSeleccionado()) {
								 menu.barraInferior.iconoAtaque.desseleccionar();
								 //menu.barraSuperior.mostrar(); 
								 menu.barraSuperior.ocultar(); 
						}
						else if (menu.barraInferior.iconoBalon.esSeleccionado()) {
							menu.barraInferior.iconoBalon.desseleccionar();
							actoresCampo.setModoBalon();
							actoresCampo.setModoBloqueado(true);
						}
						else actoresCampo.setModoBloqueado(true);
						
						actoresCampo.setModoEliminable(true);
						
					} else {
						menu.barraInferior.iconoEliminar.desseleccionar();
						actoresCampo.setModoEliminable(false);
						actoresCampo.setModoBloqueado(false);
					}
				}
				
				// Si se pulsa el icono de jugador de defensa:
				if (menu.barraInferior.iconoDefensa.getZona().contains(x, y)) {
					
					if (!menu.barraInferior.iconoDefensa.esSeleccionado() && menu.barraInferior.iconoDefensa.esActivado()) {
						
						menu.barraInferior.iconoDefensa.seleccionar();
						menu.barraSuperior.setMenuDefensa();
						
						if (menu.barraInferior.iconoEliminar.esSeleccionado()) {
							menu.barraInferior.iconoEliminar.desseleccionar();
							actoresCampo.setModoEliminable(false);
							menu.barraSuperior.ocultar(); 
						} 
						else if (menu.barraInferior.iconoAtaque.esSeleccionado()) menu.barraInferior.iconoAtaque.desseleccionar();
						else if (menu.barraInferior.iconoBalon.esSeleccionado()) {
							menu.barraInferior.iconoBalon.desseleccionar();
							actoresCampo.setModoBalon();
							actoresCampo.setModoBloqueado(true);
							menu.barraSuperior.ocultar(); 
						}
						else actoresCampo.setModoBloqueado(true);
						
					} else {
						menu.barraInferior.iconoDefensa.desseleccionar();	
						actoresCampo.setModoBloqueado(false);
						menu.barraInferior.ocultar();
						menu.barraSuperior.ocultar(); 
					}
				}
				
				// Si se pulsa el icono de jugador de ataque:
				else if (menu.barraInferior.iconoAtaque.getZona().contains(x, y)) {
					
					if (!menu.barraInferior.iconoAtaque.esSeleccionado() && menu.barraInferior.iconoAtaque.esActivado()) {
						
						menu.barraInferior.iconoAtaque.seleccionar();
						menu.barraSuperior.setMenuAtaque();
						
						if (menu.barraInferior.iconoEliminar.esSeleccionado()) {
							menu.barraInferior.iconoEliminar.desseleccionar();
							actoresCampo.setModoEliminable(false);
						} 
						else if (menu.barraInferior.iconoDefensa.esSeleccionado()) {
							menu.barraInferior.iconoDefensa.desseleccionar();
						}
						else if (menu.barraInferior.iconoBalon.esSeleccionado()) {
							menu.barraInferior.iconoBalon.desseleccionar();
							actoresCampo.setModoBalon();
							actoresCampo.setModoBloqueado(true);
						}
						else actoresCampo.setModoBloqueado(true);
						
					} else {
						menu.barraInferior.iconoAtaque.desseleccionar();	
						actoresCampo.setModoBloqueado(false);
						menu.barraInferior.ocultar();
						menu.barraSuperior.ocultar();
					}
				}
				
				// Si se pulsa el icono de balon:
				else if (menu.barraInferior.iconoBalon.getZona().contains(x, y)) {
					
					if (!menu.barraInferior.iconoBalon.esSeleccionado() && menu.barraInferior.iconoBalon.esActivado()) {
						
						menu.barraInferior.iconoBalon.seleccionar();
						actoresCampo.setModoBalon();
						actoresCampo.setModoBloqueado(false);
						
						if (menu.barraInferior.iconoEliminar.esSeleccionado()) {
							menu.barraInferior.iconoEliminar.desseleccionar();
							actoresCampo.setModoEliminable(false);
						} 
						else if (menu.barraInferior.iconoAtaque.esSeleccionado()) {
							menu.barraInferior.iconoAtaque.desseleccionar();
							menu.barraSuperior.ocultar();
						}
						else if (menu.barraInferior.iconoDefensa.esSeleccionado()) {
							menu.barraInferior.iconoDefensa.desseleccionar();
							menu.barraSuperior.ocultar();
						}
						else actoresCampo.setModoBloqueado(false);
						
					} else {
						menu.barraInferior.iconoBalon.desseleccionar();
						actoresCampo.setModoBalon();
						actoresCampo.setModoBloqueado(false);
						menu.barraInferior.ocultar();
					}
				}
				// Si se pulsa el icono de salir:
				else if (menu.barraInferior.iconoSalir.getZona().contains(x, y)) {
					app.setScreen(new PantallaMenu(app));
					//app.setScreen(new PantallaCarga());
				}
				
			} else if (!menu.barraSuperior.getZona().contains(x, y)) { // Si se pulsa fuera:
				
				// Si esta seleccionado el icono de jugador defensa:
				if (menu.barraInferior.iconoDefensa.esSeleccionado()) {
					
					actoresCampo.anadirJugadorDefensa(menu.barraSuperior.getNumSeleccionado(), (x * camara.zoom + camara.position.x - (camara.viewportWidth/2 * camara.zoom)),
													   y * camara.zoom + camara.position.y - (camara.viewportHeight/2 * camara.zoom));
					////////////////////////////7
					menu.barraSuperior.desactivarJugador(menu.barraSuperior.getNumSeleccionado());
				
				// si esta seleccionado el icono de jugador ataque:
				} else if (menu.barraInferior.iconoAtaque.esSeleccionado()) {
					
					actoresCampo.anadirJugadorAtaque(menu.barraSuperior.getNumSeleccionado(), (x * camara.zoom + camara.position.x - (camara.viewportWidth/2 * camara.zoom)),
													  y * camara.zoom + camara.position.y - (camara.viewportHeight/2 * camara.zoom));
					menu.barraSuperior.desactivarJugador(menu.barraSuperior.getNumSeleccionado());
					
				// Si esta seleccionado el icono del balon:
				} else if (menu.barraInferior.iconoBalon.esSeleccionado()) {
					if (!actoresCampo.getBalon().getZona().contains(x, y))
							menu.barraInferior.ocultar();
					
				// Si esta seleccionado el icono eliminar:
				} else if (menu.barraInferior.iconoEliminar.esSeleccionado()) {
					
					if (!ComprobarJugadorEliminado(x, y)) {
						
						menu.barraInferior.ocultar();
					}
					
				// Si no hay nada seleccionado:
				} else {
					
					actoresCampo.setModoBloqueado(false);
					menu.barraInferior.ocultar();
				}
				
				return false;
			}
		}
		
		return true;	
	}
	
	// Evento que sucede mientras se pulsa la pantalla:
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		
	}
	
	// Evento que sucede cuando se deja de pulsar la pantalla:
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		
		if ((!menu.barraInferior.esMostrado()) && mouseY < Gdx.graphics.getHeight()*0.05
			&& y > Gdx.graphics.getHeight()*0.15) {
			
			menu.barraInferior.mostrar();
		}
	}
	
	
	private boolean ComprobarJugadorEliminado(float x, float y) {
		
		ArrayList<JugadorAbstractActor> actoresEnCampo;
		boolean jugadorEliminado = false;
		
		// Obtener jugadores en el campo:
		actoresEnCampo = actoresCampo.getJugadoresEnCampo();
		for(JugadorAbstractActor jugador : actoresEnCampo) {
			
			// Si el jugador esta en modo eliminable y no se pulsa el icono de eliminar:
			if (jugador.esModoEliminable() && jugador.getZonaEliminar().contains(x, y)) {
				
				jugadorEliminado = true;
				break;
			}
		}
		
		return jugadorEliminado;
	}

}
