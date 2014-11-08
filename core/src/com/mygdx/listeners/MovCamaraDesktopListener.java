package com.mygdx.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.utilidades.Camara;

/*
 *  Listener para aplicacion de escritorio.
 *  Se encarga de recibir los eventos relacionados 
 *  con el control de la camara (movimento y zoom)
 */
@SuppressWarnings("unused")
public class MovCamaraDesktopListener extends InputListener {
	
	private Camara camara;
	
	
	private float mouseX;
	private float mouseY;
	
	final float CONST_VEL_ZOOM = 0.35f;
	final float CONST_VEL_CAMARA = 60f;
	
	public MovCamaraDesktopListener(Camara camara) {
		
		this.camara = camara;
	}
	
    
	// Evento que ocurre cuando se pulsa una tecla:
	public boolean keyTyped(InputEvent event, char character) {
		
		switch(character) {
		
			// Zoom - Aumentar
			case 'X':
			case 'x':
				camara.setVelocidadZoom(-CONST_VEL_ZOOM);
				return true;
			
			// Zoom - Reducir:
			case 'Z':
			case 'z':
				camara.setVelocidadZoom(CONST_VEL_ZOOM);
				return true;
			
			// Camara - Movimiento derecha:
			case 'A':
			case 'a':
				camara.setVelocidadX(-CONST_VEL_CAMARA);
				return true;
			
			// Camara - Movimiento izquierda:
			case 'D':
			case 'd':
				camara.setVelocidadX(CONST_VEL_CAMARA);
				return true;
			
			// Camara - Movimiento arriba:
			case 'W':
			case 'w':
				camara.setVelocidadY(CONST_VEL_CAMARA);
				return true;
			
			// Camara - Movimiento abajo:
			case 'S':
			case 's':
				camara.setVelocidadY(-CONST_VEL_CAMARA);
				return true;
				
			default:
				return false;
		}
	}

	// Evento que ocurre cuando se levanta una tecla
	public boolean keyUp(InputEvent event, int keycode) {
		
		switch(keycode) {
		
		case Input.Keys.D:
		case Input.Keys.A:
			camara.setVelocidadX(0);
			return true;
			
		case Input.Keys.W:
		case Input.Keys.S:
			camara.setVelocidadY(0);
			return true;
			
		case Input.Keys.Z:
		case Input.Keys.X:
			camara.setVelocidadZoom(0);
			return true;
			
		default:
			return false;
		}
	}
	
	/*/ Evento que sucede al pulsar la pantalla:
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		
		mouseX = x;
		mouseY = y;
		
		return true;	
	}
	
	// Evento que sucede mientras se pulsa la pantalla:
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		
		if (mouseY*camara.zoom >= Gdx.graphics.getHeight()*0.05) { // Para no interferir con el listener del menu
			if (x - mouseX != 0) {
				camara.setVelocidadX((mouseX - x) * camara.zoom / 2);
			}
			
			if (y - mouseY != 0) {
				camara.setVelocidadY((mouseY - y) * camara.zoom / 2);
			}
		}
	}
	
	// Evento que sucede cuando se deja de pulsar la pantalla:
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		
		camara.setVelocidadX(0);
		camara.setVelocidadY(0);
	}*/

}
