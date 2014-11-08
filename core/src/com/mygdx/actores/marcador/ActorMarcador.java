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
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.utilidades.Manager;
import com.mygdx.utilidades.Settings;

public class ActorMarcador extends Actor {
	private Rectangle zona;
	@SuppressWarnings("unused")
	private final BalonmanoApp app;
	private boolean estado;
	private BitmapFont fuenteMarcador;
	private int marcador1;
	private int marcador2;
	private TextureRegion add;
	private TextureRegion remove;
	private Texture textura;
	private Rectangle zonaAddIz,zonaRemIz,zonaAddDer,zonaRemDer;
	private float x;
	private float y;
	private final float anchuraNumero = 48;
	private TextureRegion fondo;
	private TextureRegion fondoJugadores;
	
	public ActorMarcador(BalonmanoApp app){
		this.app = app;
		
		setSize(100,90);
		
		x = Gdx.graphics.getWidth()/9;
		y = Gdx.graphics.getHeight()-90/6;
		setPosition(x,y);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Settings.skin+"/Fuentes/marcador.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 100;
		fuenteMarcador = generator.generateFont(parameter);
		generator.dispose();
		
		String imagenURL = Settings.skin+"/Marcador/add.png";
		textura = (Texture)Manager.manager.get(imagenURL);
		add = new TextureRegion(textura, 0, 0, 24, 24);
		
		imagenURL = Settings.skin+"/Marcador/remove.png";
		textura = (Texture)Manager.manager.get(imagenURL);
		remove = new TextureRegion(textura, 0, 0, 24, 24);
		
		imagenURL = Settings.skin+"/Marcador/fondoMarcador.png";
		textura = (Texture)Manager.manager.get(imagenURL);
		fondo = new TextureRegion(textura);
		
		imagenURL = Settings.skin+"/Marcador/fondoJugadores.png";
		textura = (Texture)Manager.manager.get(imagenURL);
		fondoJugadores = new TextureRegion(textura);
		
		marcador1 = 0;
		marcador2 = 0;

		estado=false;
		
		asignarZonas();
	}
	
	public Rectangle getZona() {
		return zona;
	}
	
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(fondo, 0, Gdx.graphics.getHeight()-fondo.getRegionHeight(), getOriginX(), getOriginY(), Gdx.graphics.getWidth()/2-5, fondo.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
		batch.draw(fondo, Gdx.graphics.getWidth()/2+5, Gdx.graphics.getHeight()-fondo.getRegionHeight(), getOriginX(), getOriginY(), Gdx.graphics.getWidth()/2, fondo.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
	    
		batch.draw(fondoJugadores, 0, 0, getOriginX(), getOriginY(), Gdx.graphics.getWidth()/2-5, fondoJugadores.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
		batch.draw(fondoJugadores, Gdx.graphics.getWidth()/2+5, 0, getOriginX(), getOriginY(), Gdx.graphics.getWidth()/2, fondoJugadores.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
	    
		
		if(estado==true){
		    batch.draw(add, zonaAddIz.x, zonaAddIz.y, getOriginX(), getOriginY(), 24, 24, getScaleX(), getScaleY(), getRotation());
		    batch.draw(remove, zonaRemIz.x, zonaRemIz.y, getOriginX(), getOriginY(), 24, 24, getScaleX(), getScaleY(), getRotation());
		    
		    batch.draw(add, zonaAddDer.x, zonaAddDer.y, getOriginX(), getOriginY(), 24, 24, getScaleX(), getScaleY(), getRotation());
		    batch.draw(remove, zonaRemDer.x, zonaRemDer.y, getOriginX(), getOriginY(), 24, 24, getScaleX(), getScaleY(), getRotation());
		}
		
		fuenteMarcador.draw(batch, marcador1+":"+marcador2, getX(), getY());
	}
	
	public void mostrar(){
		if(estado==false){
			estado=true;
		}else{
			estado=false;
		}
	}

	public boolean getEstado() {
		return estado;
	}

	public void cambiarMarcador(float x, float y) {
		if(zonaAddIz.contains(x,y)){
			if(marcador1==9){
				setX(getX()-anchuraNumero);
				setWidth(getWidth()+anchuraNumero);
				asignarZonas();
			}
			marcador1++;
		}
		if(zonaRemIz.contains(x,y)){
			if(marcador1>0){
				if(marcador1==10){
					setX(getX()+anchuraNumero);
					setWidth(getWidth()-anchuraNumero);
					asignarZonas();
				}
				marcador1--;
			}
		}
		if(zonaAddDer.contains(x,y)){
			if(marcador2==9){
				setWidth(getWidth()+anchuraNumero);
				asignarZonas();
			}
			marcador2++;
		}
		if(zonaRemDer.contains(x,y)){
			if(marcador2>0){
				if(marcador2==10){
					setWidth(getWidth()-anchuraNumero);
					asignarZonas();
				}
				marcador2--;
			}
		}
	}

	private void asignarZonas(){
		zona = new Rectangle();
		zona.x = getX();
		zona.y = getY()-getHeight();
		zona.width = getWidth();
		zona.height = getHeight();
		
		zonaAddIz = new Rectangle(getX()-(10+24),350,24,24);
		zonaRemIz = new Rectangle(getX()-(10+24),320,24,24);
		zonaAddDer = new Rectangle(getX()+getWidth()+10,350,24,24);
		zonaRemDer = new Rectangle(getX()+getWidth()+10,320,24,24);
	}
}
