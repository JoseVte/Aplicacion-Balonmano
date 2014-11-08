package com.mygdx.utilidades;

public class Tiempo {
	private static final long FINAL = 1800000;
	private long tiempo;
	
	public Tiempo() {
		tiempo = 0;
	}

	public boolean getFinal(){
		return tiempo == FINAL;
	}

	public void addMiliSeg() {
		tiempo++;
	}
	
	public String toString(){
		long minutes = tiempo / 3600;
		long seconds = (tiempo % 3600) / 60;
		long miliseconds = tiempo % 60;
		return twoDigitString(minutes) + ":" + twoDigitString(seconds) + ":" + twoDigitString(miliseconds);
	}
	
	private String twoDigitString(long number) {

	    if (number == 0) {
	        return "00";
	    }

	    if (number / 10 == 0) {
	        return "0" + number;
	    }

	    return String.valueOf(number);
	}
}
