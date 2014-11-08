package com.mygdx.actores.pizarra;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;

public class BarraMenuSuperiorActorGroup extends Group{
	
	private final int CONST_SEPARACION_ICONOS = 1;
	private final int CONST_TAM_ICONOS = 32;
	private final int CONST_NUM_JUGADORES = 14;
	private final float CONST_VEL_APARICION = 220;
	
	public BarraMenuActor barraMenu;
	private BitmapFont fuenteNumeros;
	
	private boolean ocultar;  // true cuando la barra se esta ocultado, pero aun no lo esta del todo.
	private boolean mostrar;  // true cuando la barra se esta mostrando, pero aun no lo esta del todo.
	private boolean mostrado; // true cuando la barra se ha mostrado del todo.
	
	// Iconos:
	private ArrayList<IconoMenuActor> iconosDefensa;
	private ArrayList<IconoMenuActor> iconosAtaque;
	
	// Distintos modos de menu:
	private boolean modoDefensa = true;
	private boolean modoAtaque = false;
	
	private boolean modoTransicionDefensa = false;
	private boolean modoTransicionAtaque = false;
	
	
	// Se le pasan las dimeniones de la pantalla como argumento:
	public BarraMenuSuperiorActorGroup() {
		
		barraMenu = new BarraMenuActor(Settings.skin+"/MenuSuperior/barra_menu.png", 0, 0, 512, 64);
		
		// Crear funte para los numeros:
		fuenteNumeros = (BitmapFont)Manager.manager.get(Settings.skin+"/Fuentes/numeroJugadorFont.fnt");
				
		iconosDefensa = new ArrayList<IconoMenuActor>();
		iconosAtaque = new ArrayList<IconoMenuActor>();
		
		setPosition(Gdx.graphics.getWidth()/2 - getZona().width/2, Gdx.graphics.getHeight());
		
		addActor(barraMenu);
		
		for(int i=0; i < CONST_NUM_JUGADORES; i++) {
			
			IconoMenuActor iconoDefensa = new IconoMenuActor(CONST_TAM_ICONOS,
					Settings.skin+"/MenuSuperior/icono_defensa_activo.png",
					Settings.skin+"/MenuSuperior/icono_defensa_desactivado.png",
					Settings.skin+"/MenuSuperior/icono_defensa_seleccionado.png");
			IconoMenuActor iconoAtaque = new IconoMenuActor(CONST_TAM_ICONOS,
					Settings.skin+"/MenuSuperior/icono_ataque_activo.png",
					Settings.skin+"/MenuSuperior/icono_ataque_desactivado.png",
					Settings.skin+"/MenuSuperior/icono_ataque_seleccionado.png");
			iconosDefensa.add(iconoDefensa);
			iconosAtaque.add(iconoAtaque);
			addActor(iconoDefensa);
			addActor(iconoAtaque);
		}
		
		(iconosDefensa.get(0)).seleccionar();
		(iconosAtaque.get(0)).seleccionar();
		
		mostrar = false;
		ocultar = false;
	}
	
	public Rectangle getZona() { return barraMenu.getZona(); }
	
	public void activarJugadorDefensa(int num) {
		
		if (!iconosDefensa.get(num-1).esActivado())
				iconosDefensa.get(num-1).activar();
	}
	
	public void activarJugadorAtaque(int num) {
		
		if (!iconosAtaque.get(num-1).esActivado())
				iconosAtaque.get(num-1).activar();
	}
	
	public IconoMenuActor getIconoSeleccionado() {
		
		if (modoDefensa) {
			for(IconoMenuActor icono: iconosDefensa) {
				
				if (icono.esSeleccionado()) {
					
					return icono;
				}
			}
		}
		else if (modoAtaque) {
			for(IconoMenuActor icono: iconosAtaque) {
				
				if (icono.esSeleccionado()) {
					
					return icono;
				}
			}
		}
		
		return null;
	}
	
	public int getNumSeleccionado() {
		
		int numJugador = 0;
		
		if (modoDefensa) {
			for(IconoMenuActor icono: iconosDefensa) {
				
				numJugador++;
				if (icono.esSeleccionado()) {
					
					return numJugador;
				}
			}
		}
		else if (modoAtaque) {
			for(IconoMenuActor icono: iconosAtaque) {
				
				numJugador++;
				if (icono.esSeleccionado()) {
					
					return numJugador;
				}
			}
		}
		
		return -1;
	}
	
	public void desactivarJugador(int num) {
		
		num -= 1;
		
		if (modoDefensa) {
			
			if (iconosDefensa.get(num).esSeleccionado()) {
				
				iconosDefensa.get(num).desseleccionar();
				iconosDefensa.get(num).desactivar();
				seleccionarSiguiente(num);
			}
		}
		else if (modoAtaque) {
			
			if (iconosAtaque.get(num).esSeleccionado()) {
				
				iconosAtaque.get(num).desseleccionar();
				iconosAtaque.get(num).desactivar();
				seleccionarSiguiente(num);
			}
		}
	}
	
	private void seleccionarSiguiente(int num) {
		
		if (modoDefensa) {
			for(int i=0,j=num; i < CONST_NUM_JUGADORES; i++, j++) {
				
				if (j == CONST_NUM_JUGADORES) j = 0;
				
				if (iconosDefensa.get(j).esActivado()) {
					iconosDefensa.get(j).seleccionar();
					break;
				}
			}
		}
		else if (modoAtaque) {
			for(int i=0,j=num; i < CONST_NUM_JUGADORES; i++, j++) {
				
				if (j == CONST_NUM_JUGADORES) j = 0;
				
				if (iconosAtaque.get(j).esActivado()) {
					iconosAtaque.get(j).seleccionar();
					break;
				}
			}
		}
	}

	public void setMenuDefensa() {
		
		if (!mostrado) {
			
			if (modoAtaque) modoAtaque = false;
			modoDefensa = true;
			mostrar();
		}
		else if (esTotalMostrado()) {
			
			modoTransicionDefensa = true;
			ocultar();
		}
	}
	
	public void setMenuAtaque() {
		
		if (!mostrado) {
			
			if (modoDefensa) modoDefensa = false;
			modoAtaque = true;
			mostrar();
		}
		else if (esTotalMostrado()) {
			
			modoTransicionAtaque = true;
			ocultar();
		}
	}
	
	public boolean esMenuDefensa() { return modoDefensa; }
	public boolean esMenuAtaque() { return modoAtaque; }
	
	public ArrayList<IconoMenuActor> getIconosDefensa() { 
		return iconosDefensa;
	}
	
	public ArrayList<IconoMenuActor> getIconosAtaque() { 
		return iconosAtaque;
	}
	
	public void mostrar() {
		
		if (!mostrado) {
			mostrar = true;
			mostrado = true;
		}	
	}
	
	public void ocultar() {
		
		if (esTotalMostrado()) {
			ocultar = true;
		}
	}
	
	public boolean esMostrado() { return mostrado; }
	public boolean esTotalMostrado() { return mostrado && (!mostrar && !ocultar); }
	
	public void draw(Batch batch, float parentAlpha) {
		
		int numJugador = 0;
		
		if (mostrado) {
			
			barraMenu.draw(batch, parentAlpha);
			
			if (modoDefensa) {
				for(IconoMenuActor icono: iconosDefensa) {
					
					numJugador++;
					icono.draw(icono.getX(), icono.getY(), batch, parentAlpha);
					fuenteNumeros.draw(batch, ""+numJugador, icono.getX() + icono.getWidth()/3, icono.getY()+ icono.getHeight()/2 + 8);
				}
			}
			else if (modoAtaque) {
				for(IconoMenuActor icono: iconosAtaque) {
					
					numJugador++;
					icono.draw(icono.getX(), icono.getY(), batch, parentAlpha);
					fuenteNumeros.draw(batch, ""+numJugador, icono.getX() + icono.getWidth()/3 + 3, icono.getY()+ icono.getHeight()/2 + 8);
				}
			}
		}
	}
	
	public void act(float delta) {
		
		int separacion = CONST_SEPARACION_ICONOS*2;
		
		barraMenu.setPosition(getX(), getY());
		
		if (modoDefensa) {
			
			for(IconoMenuActor icono: iconosDefensa) {
				
				icono.setPosition(getX()+23 + separacion, getY()+18);
				separacion += CONST_TAM_ICONOS + CONST_SEPARACION_ICONOS;
			}
		}
		else if (modoAtaque) {
			
			for(IconoMenuActor icono: iconosAtaque) {
				
				icono.setPosition(getX()+23 + separacion, getY()+18);
				separacion += CONST_TAM_ICONOS + CONST_SEPARACION_ICONOS;
			}
		}
		
		super.act(delta);
		
		controlMostrar(delta);
	}
	
	private void controlMostrar(float delta) {
		
		// Si se tiene que mostrar y aun no lo ha hecho del todo:
		if (mostrar && getY() > Gdx.graphics.getHeight() - getZona().height) {
			
			setY(getY() - delta*CONST_VEL_APARICION);
			
		// Si ha acabado de mostrarse:
		} else if (mostrar && getY() < Gdx.graphics.getHeight() - getZona().height) {
			
			setY(Gdx.graphics.getHeight() - getZona().height);
			if (modoTransicionDefensa) modoTransicionDefensa = false;
			if (modoTransicionAtaque) modoTransicionAtaque = false;
			mostrar = false;
		}
				
		// Si se tiene que ocultar y aun no lo ha hecho del todo:
		if (ocultar && getY() < Gdx.graphics.getHeight()) {
			
			setY(getY() + delta*CONST_VEL_APARICION);
		
		// Si ha acabado de ocultarse:
		} else if (ocultar && getY() > Gdx.graphics.getHeight()) {
			
			setY(Gdx.graphics.getHeight());
			ocultar = false;
			mostrado = false;
			
			// Si sigue en modo transicion:
			if (modoTransicionDefensa) {
				
				modoDefensa = true;
				if (modoAtaque) modoAtaque = false;
				mostrar();
			} 
			else if (modoTransicionAtaque) {
				
				modoAtaque = true;
				if (modoDefensa) modoDefensa = false;
				mostrar();
			}
		}
	}
}
