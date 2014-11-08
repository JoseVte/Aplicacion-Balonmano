package com.mygdx.actores.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.aplicacion.BalonmanoApp;

public class BotonLinkActor extends Actor {

	private Rectangle zona;
	private String texto,utilidad;
	private Texture textura;
	private TextureRegion imagen;
	private final BalonmanoApp app;
	
	public BotonLinkActor(int x,int y,String texto,String utilidad,String imagenURL, final BalonmanoApp app){
		this.texto = texto;
		this.utilidad = utilidad;
		this.app = app;
		textura = new Texture(Gdx.files.internal(imagenURL));
		imagen = new TextureRegion(textura, 0, 0, 256, 48);
		
		setSize(imagen.getRegionWidth(), imagen.getRegionHeight());
		setPosition(x, y);
		
		zona = new Rectangle();
		zona.x = getX();
		zona.y = getY();
		zona.width = getWidth();
		zona.height = getHeight();
	}
	
	public Rectangle getZona() {
		return zona;
	}
	
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
	    batch.draw(imagen, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	    app.font.draw(batch,texto,Gdx.graphics.getWidth()/2-120,getY()+31);
	    app.font.setScale(0.8f);
	}
	
	public void abrirURL(){
		Gdx.net.openURI("http://"+utilidad);
	}
}
