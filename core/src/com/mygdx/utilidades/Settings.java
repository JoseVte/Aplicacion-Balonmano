package com.mygdx.utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.actores.marcador.ActorJugador;

public class Settings {
	//Direccion del fichero partiendo del $HOME
	public final static String file = ".tacticBoard";
	public static String skin = "Default";
	
	private static Preferences pref;
	
	public static Preferences getPreferences(){
		if(pref==null){
	         pref = Gdx.app.getPreferences("jugadoresPrueba");
	    }
		return pref;
	}
	
	public static boolean setPreferences(String nombre){
		Preferences antiguo = pref;
		
		pref = Gdx.app.getPreferences(nombre);
		
		
		return !pref.equals(antiguo);
	}
	 
	public static void guardarPruebas(List<ActorJugador> jugadores){
		getPreferences();
		
		for(int i=0;i<jugadores.size();i++)
			pref.putString(""+i, jugadores.get(i).toString());
		
		
		pref.flush();
	}
	
	public static void load() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(Gdx.files.external(file).read()));
			skin = in.readLine();
			System.out.println("Cargado "+Gdx.files.external(file).name());
		} catch (Throwable e) {
			skin = "Default";
		} finally {
			try {
				if (in != null) in.close();
			} catch (IOException e) {
			}
		}
	}
	
	public static void save() {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(Gdx.files.external(file).write(false)));
			out.write(skin);
			System.out.println("Guardado "+Gdx.files.external(file).path());
		} catch (Throwable e) {
		} finally {
			try {
				if (out != null) out.close();
			} catch (IOException e) {
			}
		}
	}
	
}
