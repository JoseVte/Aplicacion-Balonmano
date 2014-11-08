package com.mygdx.actores.menu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.utilidades.Manager;

public class BotonPantallaActor extends Actor {
	private Rectangle zona;
	private String clase,texto;
	private Texture textura;
	private TextureRegion imagen;
	private final BalonmanoApp app;
	private float x_texto;

	public BotonPantallaActor(int x,int y,String texto,String clase ,String imagenURL, final BalonmanoApp app){
		this.clase = clase;
		this.texto = texto;
		this.app = app;
		textura = (Texture)Manager.manager.get(imagenURL);
		imagen = new TextureRegion(textura, 0, 0, textura.getWidth(), 48);
		
		x_texto=x+24;
		
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
	    app.font.draw(batch,texto,x_texto,getY()+31);
	    app.font.setScale(0.8f);
	}
	
	public void abrirSeccion() {
		try {
			Class<?> clasePantalla = Class.forName("com.mygdx.pantallas.Pantalla"+clase);
			Constructor<?> constructor = clasePantalla.getConstructor(app.getClass());
			Screen pantalla = (Screen)constructor.newInstance(app);
			
			app.setScreen(pantalla);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("Aun no se ha implementado: "+e.getMessage());
			e.printStackTrace();
		}
		
	}

}
