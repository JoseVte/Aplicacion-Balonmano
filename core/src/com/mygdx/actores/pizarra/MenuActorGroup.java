package com.mygdx.actores.pizarra;

import com.badlogic.gdx.scenes.scene2d.Group;

public class MenuActorGroup extends Group {
	
	public BarraMenuInferiorActorGroup barraInferior;
	public BarraMenuSuperiorActorGroup barraSuperior;
	
	public MenuActorGroup() {
		barraInferior = new BarraMenuInferiorActorGroup();
		barraSuperior = new BarraMenuSuperiorActorGroup();
		
		addActor(barraInferior);
		addActor(barraSuperior);
	}
	
	/*
	public void draw(Batch batch, float parentAlpha) {
		
		barraInferior.draw(batch, parentAlpha);
		//barraSuperior.draw(batch, parentAlpha);
	}*/

}
