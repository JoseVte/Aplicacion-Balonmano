package com.mygdx.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.utilidades.Camara;

/*
 *  Listener para aplicacion de escritorio.
 *  Se encarga de recibir los eventos relacionados 
 *  con el control de la camara (movimento y zoom)
 */

public class MovCamaraAndroidListener extends InputListener {
	
	private Camara camara;
	
	private float mouseMediaX;
	private float mouseMediaY;
	
	private float distancia;
	
	final float CONST_VEL_ZOOM = 0.35f;
	final int CONST_VEL_CAMARA = 25;
	
	Float[] mouseX;
	Float[] mouseY;
	
	public MovCamaraAndroidListener(Camara camara) {
		
		this.camara = camara;
		this.mouseX = new Float[3];
		this.mouseY = new Float[3];
		
		for(int i=0; i < 3; i++) {
			
			mouseX[i] = -1f;
			mouseY[i] = -1f;
		}
	}
	
    ////////////////////////////////////////////
	///// Añadir aqui el control del zoom
	///////////////////////////////////////////
	
	
	// Evento que sucede al pulsar la pantalla:
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		
		// Pulsado con 3 dedos: Movimiento camara
		if (Gdx.input.isTouched(1) && !Gdx.input.isTouched(2) && Gdx.input.isTouched(3)) {
			
			//mouseX = x;
			//mouseY = y;
		}
		
		
		// Si se pusa con 2 dedos: Zoom
		else if (Gdx.input.isTouched(1) && Gdx.input.isTouched(1)) {
			
			
		}
		
		if (pointer <= 3) {
			
			mouseX[pointer] = x;
			mouseY[pointer] = y;
			
			distancia = (float)Math.sqrt(Math.pow((mouseX[0] - mouseX[1]), 2) 
								  + Math.pow((mouseY[0] - mouseY[1]), 2));
		}
		System.out.println("tocadoooo");
		
		return true;	
	}
	
	// Evento que sucede mientras se pulsa la pantalla:
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		
		/*if (x - mouseX != 0) {
			camara.setVelocidadX(mouseX - x);
		}
		
		if (y - mouseY != 0) {
			camara.setVelocidadY(mouseY - y);
		}*/
		
		if (mouseX[2] != -1 && mouseY[2] != -1) { // 3 dedos
			
			if (mouseMediaY*camara.zoom >= Gdx.graphics.getHeight()*0.05) { // Para no interferir con el listener del menu
				if (x - mouseMediaX != 0) {
					camara.setVelocidadX((mouseMediaX - x) * camara.zoom / 2);
				}
				
				if (y - mouseMediaY != 0) {
					camara.setVelocidadY((mouseMediaY - y) * camara.zoom / 2);
				}
			}
			
		} else if (mouseX[1] != -1 && mouseY[1] != -1) { // 2 dedos
			
			// Para el puntero 1:
			if (pointer == 0) {
				mouseX[0] = x;
				mouseY[0] = y;
				
			} else if (pointer == 1) {
				mouseX[1] = x;
				mouseY[1] = y;
				camara.setVelocidadZoom(distancia - ((float)Math.sqrt(Math.pow((mouseX[0] - mouseX[1]), 2) 
						  + Math.pow((mouseY[0] - mouseY[1]), 2))));
			}
			
		} else System.out.println("tocado");
	}
	
	// Evento que sucede cuando se deja de pulsar la pantalla:
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		
		camara.setVelocidadX(0);
		camara.setVelocidadY(0);
		
		for(int i=0; i < 3; i++) {
			
			mouseX[i] = -1f;
			mouseY[i] = -1f;
		}
	}

}
