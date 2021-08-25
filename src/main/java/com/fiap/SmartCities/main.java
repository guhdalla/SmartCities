package com.fiap.SmartCities;

import com.fiap.SmartCities.model.Temperatura;

public class main {

	public static void main(String[] args) {
		
		Temperatura t = new Temperatura();
		
		t.setTemperatura(25.0);
		t.setDATASINAL(t.getDATASINAL());
		System.out.println(t.getDATASINAL());
		
	}
}
