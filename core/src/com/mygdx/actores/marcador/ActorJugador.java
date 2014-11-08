package com.mygdx.actores.marcador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;

public class ActorJugador extends Actor{
	public String nombre;
	public String apellidos;
	public int numero;
	private BitmapFont fuenteJugadores;
	private float x;
	private float y;
	private String apodo;
	private Rectangle zona;
	private static TextureRegion gol;
	private static TextureRegion fondo;
	
	public ActorJugador(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Settings.skin+"/Fuentes/NovaSquare.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		fuenteJugadores = generator.generateFont(parameter);
		generator.dispose();
		
		String imagenURL = Settings.skin+"/Marcador/fondoJugador.png";
		Texture textura = (Texture)Manager.manager.get(imagenURL);
		fondo = new TextureRegion(textura);
		
		imagenURL = Settings.skin+"/Marcador/golAdd.png";
		textura = (Texture)Manager.manager.get(imagenURL);
		gol = new TextureRegion(textura);
	}
	
	public String toString(){
		return nombre+"_"+
				apellidos+"_"+
				apodo+"_"+
				numero;
	}
	
	public static ActorJugador stringToJugador(String value) {
		ActorJugador j = new ActorJugador();
		String[] valores = value.split("_");
		
		j.nombre=valores[0];
		j.apellidos=valores[1];
		j.apodo=valores[2];
		if(j.apodo.equals("null")) j.apodo=null;
		j.numero=Integer.parseInt(valores[3]);
		
		return j;
	}

	public void setXY(float x, float y){
		this.x=x;
		this.y=y;
		
		setZona(new Rectangle(x,y,fondo.getRegionWidth(),fondo.getRegionHeight()));
	}
	
	public Rectangle getZona() {
		return zona;
	}

	public void setZona(Rectangle zona) {
		this.zona = zona;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
	    batch.draw(fondo, x, y-fondo.getRegionHeight()+2.5f);
	    batch.draw(gol, fondo.getRegionWidth()/2, y/2-gol.getRegionHeight()/2);
	    
	    fuenteJugadores.draw(batch, ""+numero, x+10, y);
		if(apodo==null || apodo==""){
			fuenteJugadores.drawMultiLine(batch, apellidos+"\n"+nombre, x+30, y);
		}else{
			fuenteJugadores.drawMultiLine(batch, apodo+"\n"+apellidos+", "+nombre, x+30, y);
		}
	}
	
	
}
