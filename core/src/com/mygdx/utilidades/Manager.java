package com.mygdx.utilidades;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Manager {
	public static AssetManager manager;
	
	public Manager(){
		manager = new AssetManager();
	}
	
	public void loadAssets(String skin){
		manager.load(skin+"/Fuentes/numeroJugadorFont.fnt", BitmapFont.class);
		manager.load(skin+"/Fuentes/play.fnt", BitmapFont.class);
		manager.load(skin+"/Fuentes/square.fnt", BitmapFont.class);
		
		manager.load(skin+"/PantallaCarga/logo.png", Texture.class);
		
		manager.load(skin+"/PantallaMenu/fondo_menu.png", Texture.class);
		manager.load(skin+"/PantallaMenu/boton_des.png", Texture.class);
		manager.load(skin+"/PantallaMenu/boton.png", Texture.class);
		manager.load(skin+"/PantallaMenu/icono.png", Texture.class);
		manager.load(skin+"/PantallaMenu/informacion.png", Texture.class);
		manager.load(skin+"/PantallaMenu/titulo.png", Texture.class);
		
		manager.load(skin+"/MenuSuperior/barra_menu.png", Texture.class);
		manager.load(skin+"/MenuSuperior/icono_ataque_activo.png", Texture.class);
		manager.load(skin+"/MenuSuperior/icono_ataque_desactivado.png", Texture.class);
		manager.load(skin+"/MenuSuperior/icono_ataque_seleccionado.png", Texture.class);
		manager.load(skin+"/MenuSuperior/icono_defensa_activo.png", Texture.class);
		manager.load(skin+"/MenuSuperior/icono_defensa_desactivado.png", Texture.class);
		manager.load(skin+"/MenuSuperior/icono_defensa_seleccionado.png", Texture.class);
		
		manager.load(skin+"/MenuInferior/barra_menu.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_balon_activado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_balon_seleccionado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_eliminar_jugador_activado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_eliminar_jugador_desactivado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_eliminar_jugador_seleccionado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_jugador_ataque_activado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_jugador_ataque_desactivado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_jugador_ataque_seleccionado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_jugador_defensa_activado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_jugador_defensa_desactivado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_jugador_defensa_seleccionado.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_play.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_rec.png", Texture.class);
		manager.load(skin+"/MenuInferior/icono_salir.png", Texture.class);
		
		manager.load(skin+"/Campo/balon_normal.png", Texture.class);
		manager.load(skin+"/Campo/balon_seleccionado.png", Texture.class);
		manager.load(skin+"/Campo/balon.png", Texture.class);
		manager.load(skin+"/Campo/campo_balonmano.jpg", Texture.class);
		manager.load(skin+"/Campo/icono_jugadores_eliminar.png", Texture.class);
		manager.load(skin+"/Campo/jugador_ataque.png", Texture.class);
		manager.load(skin+"/Campo/jugador_defensa.png", Texture.class);
		
		manager.load(skin+"/Marcador/fondo.png", Texture.class);
		manager.load(skin+"/Marcador/botonPause.png", Texture.class);
		manager.load(skin+"/Marcador/botonPlay.png", Texture.class);
		manager.load(skin+"/Marcador/botonSalir.png", Texture.class);
		manager.load(skin+"/Marcador/botonCambioParte.png", Texture.class);
		manager.load(skin+"/Marcador/botonJugadorDes.png", Texture.class);
		manager.load(skin+"/Marcador/botonJugadorAct.png", Texture.class);
		manager.load(skin+"/Marcador/add.png", Texture.class);
		manager.load(skin+"/Marcador/remove.png", Texture.class);
		manager.load(skin+"/Marcador/fondoMarcador.png", Texture.class);
		manager.load(skin+"/Marcador/fondoJugadores.png", Texture.class);
		manager.load(skin+"/Marcador/fondoJugador.png", Texture.class);
		manager.load(skin+"/Marcador/golAdd.png", Texture.class);
	}
}
