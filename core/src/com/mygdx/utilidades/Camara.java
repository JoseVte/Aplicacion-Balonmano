package com.mygdx.utilidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/*
 * Camara que emula el comportamiento de un actor, 
 * para asi poder controlarla.
 */

public class Camara extends OrthographicCamera {
	
	// Variables de la camara:
	private float velocidadX;
	private float velocidadY;
	private float velocidadZoom;
	
	// Zona representada:
	
	
	public Camara(int width, int height) {
		
		super(width, height);
		
		velocidadX = 0f;
		velocidadY = 0f;
		
		velocidadZoom = 0f;
	}

	// Cambia la velocidad de la camara en el eje X:
	public void setVelocidadX(float x) {
		
		velocidadX = x * zoom;
	}
	
	// Cambia la velocidad de la camara en el eje Y:
	public void setVelocidadY(float y) {
			
		velocidadY = y * zoom;
	}
	
	// Cambia la velocidad de acercamiento o alejamiento:
	public void setVelocidadZoom(float zoom) {
		
		velocidadZoom = zoom;
	}
	
	// Emula el metodo act() de los actores:
	public void act(float delta) {
		
		// Comprueba que el zoom no se salga de los limites antes de modificarlo:
		if ((velocidadZoom < 0) && (zoom + velocidadZoom * delta > 0.2)
			|| ((velocidadZoom > 0) && (zoom + velocidadZoom * delta < 1))) {
			
			zoom += velocidadZoom * delta;
		}
		
		// Comprueba que la camara no se salga del campo antes de moverla (eje x): 
		if (velocidadX > 0 || velocidadZoom > 0) {
			if ((position.x + velocidadX + Gdx.graphics.getWidth() * zoom / 2 < Gdx.graphics.getWidth())) {
				translate(velocidadX, 0, 0);
				
			} else {
				position.set(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * zoom / 2), position.y, 0);
			}
		}
		
		if (velocidadX < 0 || velocidadZoom > 0) {
			if (position.x + velocidadX - Gdx.graphics.getWidth() * zoom / 2 > 0) {
				translate(velocidadX, 0, 0);
				
			} else {
				position.set(Gdx.graphics.getWidth() * zoom / 2, position.y, 0);
			}
		}
		
		// Comprueba que la camara no se salga del campo antes de moverla (eje y): 
		if (velocidadY > 0 || velocidadZoom > 0) {
			if ((position.y + velocidadY + Gdx.graphics.getHeight() * zoom / 2 < Gdx.graphics.getHeight())) {
				translate(0, velocidadY, 0);
				
			} else {
				position.set(position.x, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * zoom / 2), 0);
			}	
		} 
		
		if (velocidadY < 0 || velocidadZoom > 0) {
			if (position.y + velocidadY - Gdx.graphics.getHeight() * zoom / 2 > 0) {
				translate(0, velocidadY, 0);
				
			} else {
				position.set(position.x, Gdx.graphics.getHeight() * zoom / 2, 0);
			}
		}
		
	}
}
