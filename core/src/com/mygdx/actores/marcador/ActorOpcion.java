package com.mygdx.actores.marcador;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.aplicacion.BalonmanoApp;
import com.mygdx.pantallas.PantallaMarcador;
import com.mygdx.pantallas.PantallaMenu;

public class ActorOpcion extends Actor {
	private final BalonmanoApp app;
	private boolean estado;
	private String modo;
	private Rectangle zona;
	private TextureRegion imagen;
	private TextureRegion imagen_1;
	private TextureRegion imagen_2;
	
	public ActorOpcion(BalonmanoApp app, String modo, float x, float y, TextureRegion imagenPrincipal, TextureRegion imagenSecundaria){
		this.app = app;
		this.modo = modo;
		
		imagen_1=imagenPrincipal;
		imagen_2=imagenSecundaria;
		
		imagen=imagenPrincipal;
		
		setSize(imagen.getRegionWidth(), imagen.getRegionHeight());
		setPosition(x, y);
		
		estado=false;
		
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
	}
	
	public void doAccion(){
		switch(modo){
			case "playPause":
				if(estado==false){
					imagen=imagen_2;
					estado=true;
				}else{
					imagen=imagen_1;
					estado=false;
				}
			break;
			case "cambiarParte":
				((PantallaMarcador)app.getScreen()).cambiarParte();
			break;
			case "cambiarJugador":
				if(estado==false){
					imagen=imagen_2;
					estado=true;
				}else{
					imagen=imagen_1;
					estado=false;
				}
				((PantallaMarcador)app.getScreen()).cambiarJugador();
			break;
			case "salir":
				app.setScreen(new PantallaMenu(app));
			break;
			default:
				System.err.println("Error: "+modo+" aun no se ha implementado");
			break;
		}
		
		
	}

	public boolean getEstado() {
		return estado;
	}

}
