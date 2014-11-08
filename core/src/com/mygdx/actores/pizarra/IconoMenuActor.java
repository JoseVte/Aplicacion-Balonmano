package com.mygdx.actores.pizarra;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.utilidades.Manager;

public class IconoMenuActor extends Actor {
	
	public int tamIcono;
	
	private Texture textura;
	private TextureRegion imagen_activo;
	private TextureRegion imagen_desactivado;
	private TextureRegion imagen_seleccionado;
	private Rectangle area;
	
	private boolean activado;
	private boolean desactivado;
	private boolean seleccionado;
	
	public IconoMenuActor(int tamIcono, String imagenURLActivo, String imagenURLDesactivado,
								  String imagenURLSeleccionado ) {
		this.tamIcono = tamIcono;
		
		textura = (Texture)Manager.manager.get(imagenURLActivo);
		imagen_activo = new TextureRegion(textura, 0, 0, tamIcono, tamIcono);
		
		textura = (Texture)Manager.manager.get(imagenURLDesactivado);
		imagen_desactivado = new TextureRegion(textura, 0, 0, tamIcono, tamIcono);
		
		textura = (Texture)Manager.manager.get(imagenURLSeleccionado);
		imagen_seleccionado = new TextureRegion(textura, 0, 0, tamIcono, tamIcono);
		
		
		setSize(tamIcono, tamIcono);
		//setPosition(x, y);
		//setScale(0.8f);
		
		setVisible(true);
		
		activado = true;
		desactivado = false;
		seleccionado = false;
	
		area = new Rectangle(getX(), getY(), getWidth(), getHeight());
		area.x = getX();
		area.y = getY();
		area.width = getWidth();
		area.height = getHeight();
	}
	
	public boolean esActivado() { return activado; }
	
	public boolean esSeleccionado() { return seleccionado; }
	
	public void activar() {
		
		if (desactivado) {
			
			activado = true;
			desactivado = false;
		}
	}
	
	public void desactivar() {
		
		if (activado) {
			
			desactivado = true;
			activado = false;
			seleccionado = false;
		}
	}
	
	public void seleccionar() {
		
		if (activado && !seleccionado) {
			
			seleccionado = true;
			
		}
	}
	
	public void desseleccionar() {
		
		if (seleccionado) {
			
			seleccionado = false;
		}
	}
	
	public Rectangle getZona() { return area; }
	
	public void act(float delta) {
		
		area.x = getX();
		area.y = getY();
		area.width = getWidth();
		area.height = getHeight();
	}
	
	public void draw(float x, float y, Batch batch, float parentAlpha) {
		
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
	    
		if (activado && !seleccionado) {
			batch.draw(imagen_activo, x, y, getOriginX(), getOriginY(),
					   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		
		} else if (seleccionado) {
			batch.draw(imagen_seleccionado, x, y, getOriginX(), getOriginY(),
					   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			
		} else  {
			batch.draw(imagen_desactivado, x, y, getOriginX(), getOriginY(),
					   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
	}

}
