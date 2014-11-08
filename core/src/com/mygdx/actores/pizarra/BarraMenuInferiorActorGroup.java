package com.mygdx.actores.pizarra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.utilidades.Settings;

public class BarraMenuInferiorActorGroup extends Group {
	
	private final int CONST_SEPARACION_ICONOS = 5;
	private final int CONST_TAM_ICONOS = 64;
	
	public BarraMenuActor barraMenu;
	
	public IconoMenuActor iconoEliminar;
	public IconoMenuActor iconoDefensa;
	public IconoMenuActor iconoAtaque;
	public IconoMenuActor iconoBalon; 
	
	public IconoMenuActor iconoRec;
	public IconoMenuActor iconoPlay;
	
	public IconoMenuActor iconoSalir;
	
	private boolean ocultar;  // true cuando la barra se esta ocultado, pero aun no lo esta del todo.
	private boolean mostrar;  // true cuando la barra se esta mostrando, pero aun no lo esta del todo.
	private boolean mostrado; // true cuando la barra se ha mostrado del todo.
	
	private final float CONST_VEL_APARICION = 160;
	
	
	// Se le pasan las dimeniones de la pantalla como argumento:
	public BarraMenuInferiorActorGroup() {
		barraMenu = new BarraMenuActor(Settings.skin+"/MenuInferior/barra_menu.png", 0, 45, 512, 83);
		
		iconoEliminar = new IconoMenuActor(CONST_TAM_ICONOS,
				Settings.skin+"/MenuInferior/icono_eliminar_jugador_activado.png", 
				Settings.skin+"/MenuInferior/icono_eliminar_jugador_desactivado.png",
				Settings.skin+"/MenuInferior/icono_eliminar_jugador_seleccionado.png");
		iconoDefensa = new IconoMenuActor(CONST_TAM_ICONOS,
				Settings.skin+"/MenuInferior/icono_jugador_defensa_activado.png", 
				Settings.skin+"/MenuInferior/icono_jugador_defensa_desactivado.png",
				Settings.skin+"/MenuInferior/icono_jugador_defensa_seleccionado.png");
		iconoAtaque = new IconoMenuActor(CONST_TAM_ICONOS,
				Settings.skin+"/MenuInferior/icono_jugador_ataque_activado.png", 
				Settings.skin+"/MenuInferior/icono_jugador_ataque_desactivado.png",
				Settings.skin+"/MenuInferior/icono_jugador_ataque_seleccionado.png");
		iconoBalon = new IconoMenuActor(CONST_TAM_ICONOS,
				Settings.skin+"/MenuInferior/icono_balon_activado.png", 
				Settings.skin+"/MenuInferior/icono_balon_seleccionado.png",
				Settings.skin+"/MenuInferior/icono_balon_seleccionado.png");
		iconoRec = new IconoMenuActor(CONST_TAM_ICONOS,
				Settings.skin+"/MenuInferior/icono_rec.png", 
				Settings.skin+"/MenuInferior/icono_rec.png",
				Settings.skin+"/MenuInferior/icono_rec.png");
		iconoPlay = new IconoMenuActor(CONST_TAM_ICONOS,
				Settings.skin+"/MenuInferior/icono_play.png", 
				Settings.skin+"/MenuInferior/icono_play.png",
				Settings.skin+"/MenuInferior/icono_play.png");
		iconoSalir = new IconoMenuActor(CONST_TAM_ICONOS,
				Settings.skin+"/MenuInferior/icono_salir.png", 
				Settings.skin+"/MenuInferior/icono_salir.png",
				Settings.skin+"/MenuInferior/icono_salir.png");
		
		
		setPosition(Gdx.graphics.getWidth()/2 - getZona().width/2, -getZona().height);
		
		iconoEliminar.desactivar();
		
		addActor(barraMenu);
		addActor(iconoEliminar);
		addActor(iconoDefensa);
		addActor(iconoAtaque);
		addActor(iconoBalon);
		
		addActor(iconoSalir);
		
		mostrar = false;
		ocultar = false;
	}
	
	public Rectangle getZona() { return barraMenu.getZona(); }
	
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
		
		if (mostrado) {
			
			barraMenu.draw(batch, parentAlpha);
			
			iconoEliminar.draw(iconoEliminar.getX(), iconoEliminar.getY(), batch, parentAlpha);
			iconoDefensa.draw(iconoDefensa.getX(), iconoDefensa.getY(), batch, parentAlpha);
			iconoAtaque.draw(iconoAtaque.getX(), iconoAtaque.getY(), batch, parentAlpha);
			iconoBalon.draw(iconoBalon.getX(), iconoBalon.getY(), batch, parentAlpha);
			
			iconoRec.draw(iconoRec.getX(), iconoRec.getY(), batch, parentAlpha);
			iconoPlay.draw(iconoPlay.getX(), iconoPlay.getY(), batch, parentAlpha);
			
			iconoSalir.draw(iconoSalir.getX(), iconoSalir.getY(), batch, parentAlpha);
		}
	}
	
	public void act(float delta) {
		
		int separacion = CONST_SEPARACION_ICONOS*2;
		
		barraMenu.setPosition(getX(), getY());
		
		iconoEliminar.setPosition(getX() + separacion, getY());
		
		separacion += CONST_TAM_ICONOS + CONST_SEPARACION_ICONOS*2;
		iconoAtaque.setPosition(getX() + separacion, getY());
		separacion += CONST_TAM_ICONOS + CONST_SEPARACION_ICONOS;
		iconoDefensa.setPosition(getX() + separacion, getY());
		separacion += CONST_TAM_ICONOS + CONST_SEPARACION_ICONOS;
		iconoBalon.setPosition(getX() + separacion, getY());
		
		separacion += CONST_TAM_ICONOS + CONST_SEPARACION_ICONOS*3;
		iconoRec.setPosition(getX() + separacion, getY());
		separacion += CONST_TAM_ICONOS;
		iconoPlay.setPosition(getX() + separacion, getY());
		
		separacion += CONST_TAM_ICONOS + CONST_SEPARACION_ICONOS*2;
		iconoSalir.setPosition(getX() + separacion, getY());
		
		super.act(delta);
		
		controlMostrar(delta);
	}
	
	private void controlMostrar(float delta) {
		
		// Si se tiene que mostrar y aun no lo ha hecho del todo:
		if (mostrar && getY() < 0) {
			
			setY(getY() + delta*CONST_VEL_APARICION);
		
		// Si ha acabado de mostrarse:
		} else if (mostrar && getY() >= 0) {
			
			setY(0);
			mostrar = false;
		}
				
		// Si se tiene que ocultar y aun no lo ha hecho del todo:
		if (ocultar && getY() > -getZona().height) {
			
			setY(getY() - delta*CONST_VEL_APARICION);
		
		// Si ha acabado de mostrarse:
		} else if (ocultar && getY() <= -getZona().height) {
			
			setY(-getZona().height);
			ocultar = false;
			mostrado = false;
		}
	}

}
