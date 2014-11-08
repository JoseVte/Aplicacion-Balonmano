package com.mygdx.actores.campo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.utilidades.Settings;

public class JugadorAtaqueActor extends JugadorAbstractActor {
	
	public JugadorAtaqueActor(int num, float x, float y) {
		
		super(num, x, y, Settings.skin+"/Campo/jugador_ataque.png");
		
	}
	
	public void draw(Batch batch, float parentAlpha) {
		
		super.draw(batch, parentAlpha);
		
		super.fuenteNumeros.draw(batch, ""+getNumJugador(), getX() + getWidth()/3 + 3, getY()+ getHeight()/2 + 8);
	    
	    if (esModoEliminable()) batch.draw(super.icono_eliminar, super.area_eliminar.x, super.area_eliminar.y, getOriginX(), getOriginY(),
	    		super.CONST_TAM_ICONO_ELIMINAR, super.CONST_TAM_ICONO_ELIMINAR, getScaleX(), getScaleY(), getRotation());
	    
	}

}
