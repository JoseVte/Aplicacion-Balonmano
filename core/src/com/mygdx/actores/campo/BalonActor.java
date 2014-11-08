package com.mygdx.actores.campo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;

public class BalonActor extends Actor implements ActorMovil{
	private final int CONST_TAM_BALON_NORMAL = 16;
	private final int CONST_TAM_BALON_SELECCIONADO = 32;
	
	private Texture textura;
	private TextureRegion imagen_normal;
	private TextureRegion imagen_seleccionado;
	private Rectangle area;
	
	private boolean modoSeleccionado;
	
	public BalonActor(float x, float y) {
		textura = (Texture)Manager.manager.get(Settings.skin+"/Campo/balon_normal.png");
		imagen_normal = new TextureRegion(textura, 0, 0, CONST_TAM_BALON_NORMAL, CONST_TAM_BALON_NORMAL);
		
		textura = (Texture)Manager.manager.get(Settings.skin+"/Campo/balon_seleccionado.png");
		imagen_seleccionado = new TextureRegion(textura, 0, 0, CONST_TAM_BALON_SELECCIONADO, CONST_TAM_BALON_SELECCIONADO);
		
		setSize(imagen_normal.getRegionWidth(), imagen_normal.getRegionHeight());
		setPosition(x - getWidth()/2, y - getHeight()/2);
		//setScale(0.8f);
		
		modoSeleccionado = false;
		
		setVisible(true);
	
		area = new Rectangle();
		area.x = getX();
		area.y = getY();
		area.width = CONST_TAM_BALON_SELECCIONADO;
		area.height = CONST_TAM_BALON_SELECCIONADO;
	
	}
	
	public Rectangle getZona() { return area; }
	
	public void seleccionar() {
		
		if (!modoSeleccionado) {
			
			modoSeleccionado = true;
			setSize(imagen_seleccionado.getRegionWidth(), imagen_seleccionado.getRegionHeight());
		}
	}
	
	public void desseleccionar() {
		
		if (modoSeleccionado) {
			
			modoSeleccionado = false;
			setSize(imagen_normal.getRegionWidth(), imagen_normal.getRegionHeight());
		}
	}
	
	public boolean esSeleccionado() { return modoSeleccionado; }
	
	public void mover(float x, float y) {
		
		setX(getX() + x);
		setY(getY() + y);
	}
	
	public void act(float delta) {
		
		area.x = getX();
		area.y = getY();
		area.width = CONST_TAM_BALON_SELECCIONADO;
		area.height = CONST_TAM_BALON_SELECCIONADO;
		
	}
	
	public void draw(Batch batch, float parentAlpha) {
		
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		
		if (modoSeleccionado) {
			batch.draw(imagen_seleccionado, getX(), getY(), getOriginX(), getOriginY(),
					   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		} else {
			batch.draw(imagen_normal, getX(), getY(), getOriginX(), getOriginY(),
					   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			
		}
	}
}
