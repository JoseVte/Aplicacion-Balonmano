package com.mygdx.actores.pizarra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.utilidades.Manager;

public class BarraMenuActor extends Actor {
	
	private Texture textura;
	private TextureRegion imagen;
	private Rectangle area;
	
	// Se le pasan las dimeniones de la pantalla como argumento:
	public BarraMenuActor(String imagenURL, float textureX, float textureY, float textureWidth, float textureHeight) {
		textura = (Texture)Manager.manager.get(imagenURL);
		imagen = new TextureRegion(textura, (int)textureX, (int)textureY, (int)textureWidth, (int)textureHeight);
		
		setSize(textureWidth, textureHeight);
		setPosition(Gdx.graphics.getWidth()/2 - textureWidth/2, -textureHeight);
		//setScale(0.8f);
		
		setVisible(true);
		
		area = new Rectangle(getX(), getY(), getWidth(), getHeight());
		area.x = getX();
		area.y = getY();
		area.width = getWidth();
		area.height = getHeight();
	}
	
	public Rectangle getZona() { return area; }
		
	@Override
	public void act(float delta) {
		
		area.x = getX();
		area.y = getY();
		area.width = getWidth();
		area.height = getHeight();
	}

		
	public void draw(Batch batch, float parentAlpha) {
		
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
	    batch.draw(imagen, getX(), getY(), getOriginX(), getOriginY(),
	    		   getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	public void resizePantalla(float width, float heigth) {
		
		
	}
		
}
