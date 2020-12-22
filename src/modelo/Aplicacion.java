package modelo;

import dao.baseDatos;

public class Aplicacion {

	public static void main(String[] args) {
		
		baseDatos bd  = new baseDatos();
		
		
		boolean s = bd.insert();

		System.out.println(s);
	}

}
