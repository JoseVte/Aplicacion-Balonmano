package com.mygdx.actores.marcador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.utilidades.Settings;
import com.mygdx.utilidades.Tiempo;

public class ActorTiempo extends Actor {
	@SuppressWarnings("unused")
	private final BalonmanoApp app;
	private Object tiempo;
	private String parte;
	private BitmapFont fuente;
	
	public ActorTiempo(Tiempo tiempo, BalonmanoApp app){
		this.app = app;
		this.tiempo = tiempo;
		setPosition(300, 375);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Settings.skin+"/Fuentes/NovaSquare.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 25;
		fuente = generator.generateFont(parameter);
		generator.dispose();
		
		parte = "1º parte";
	}
	
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
	    fuente.draw(batch,tiempo.toString(),275,375);
	    fuente.draw(batch,parte,290,345);
	}
	
	public void cambiarParte(){
		if(parte.equals("1º parte")){
			parte = "2º parte";
		}else{
			parte = "1º parte";
		}
	}
}
