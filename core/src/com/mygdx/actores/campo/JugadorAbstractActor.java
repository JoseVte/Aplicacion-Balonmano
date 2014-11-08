package com.mygdx.actores.campo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;

public class JugadorAbstractActor extends Actor implements ActorMovil {
	
	private final int CONST_TAM_JUGADOR = 32;
	protected final int CONST_TAM_ICONO_ELIMINAR = 16;
	
	private Texture textura;
	private TextureRegion imagen;
	protected TextureRegion icono_eliminar;
	private Rectangle area;
	protected Rectangle area_eliminar;
	
	protected BitmapFont fuenteNumeros;
	
	private boolean modoEliminable;
	
	protected int numJugador;
	
	public JugadorAbstractActor(int numJugador, float x, float y, String imagenURL) {
		
		this.numJugador = numJugador;
		
		textura = (Texture)Manager.manager.get(imagenURL);
		imagen = new TextureRegion(textura, 0, 0, CONST_TAM_JUGADOR, CONST_TAM_JUGADOR);
		
		textura = (Texture)Manager.manager.get(Settings.skin+"/Campo/icono_jugadores_eliminar.png");
		icono_eliminar = new TextureRegion(textura, 0, 0, CONST_TAM_ICONO_ELIMINAR, CONST_TAM_ICONO_ELIMINAR);
		
		// Crear funte para los numeros:
		fuenteNumeros = (BitmapFont)Manager.manager.get(Settings.skin+"/Fuentes/numeroJugadorFont.fnt");
		
		setSize(imagen.getRegionWidth(), imagen.getRegionHeight());
		setPosition(x - getWidth()/2, y - getHeight()/2);
		//setScale(0.8f);
		
		modoEliminable = false;
		
		setVisible(true);
	
		area = new Rectangle();
		area.x = getX();
		area.y = getY();
		area.width = getWidth();
		area.height = getHeight();
		
		area_eliminar = new Rectangle();
		area_eliminar.x = getX() - CONST_TAM_ICONO_ELIMINAR/2;
		area_eliminar.y = getY() + getHeight() - CONST_TAM_ICONO_ELIMINAR/2;
		area_eliminar.width = CONST_TAM_ICONO_ELIMINAR;
		area_eliminar.height = CONST_TAM_ICONO_ELIMINAR;
	}
	
	public void setModoEliminable(boolean eliminable) {
		
		if (eliminable) {
			modoEliminable = true;
			
		} else modoEliminable = false;
	}
	
	public boolean esModoEliminable() { return modoEliminable; }
	
	public Rectangle getZona() { return area; }
	public Rectangle getZonaEliminar() { return area_eliminar; }
	public int getNumJugador() { return numJugador; }
	
	public void mover(float x, float y) {
		
		setX(getX() + x);
		setY(getY() + y);
	}
	
	public void act(float delta) {
		
		area.x = getX();
		area.y = getY();
		area.width = getWidth();
		area.height = getHeight();
		
		area_eliminar.x = getX() - CONST_TAM_ICONO_ELIMINAR/2;
		area_eliminar.y = getY() + getHeight() - CONST_TAM_ICONO_ELIMINAR/2;
		area_eliminar.width = CONST_TAM_ICONO_ELIMINAR;
		area_eliminar.height = CONST_TAM_ICONO_ELIMINAR;
	}
	
	public void draw(Batch batch, float parentAlpha) {
		
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
	    batch.draw(imagen, getX(), getY(), getOriginX(), getOriginY(),
	    		   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	    
	}

}
