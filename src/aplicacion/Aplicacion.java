package aplicacion;



import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import cliente_servidor.Cliente;
import cliente_servidor.VentanaCliente;
import cliente_servidor.VentanaLogin;
import cliente_servidor.VentanaServidor;
import dao.BaseDatos;
import json.LecturaDatos;
import modelo.Estaciones;
import modelo.Usuarios;

public class Aplicacion {

	public static void main(String[] args) {
		LecturaDatos.main(null);
		VentanaServidor.main(null);

		
	}
	
	public static boolean PruebasServidorCliente() {
		
		
		try {
			
			LecturaDatos.main(null);
			VentanaServidor.main(null);
			
			
			String[] args = null;
			VentanaLogin.main(args);
			Cliente cliente = new Cliente();
			Socket s = new Socket();

			Usuarios usuario = new Usuarios("","");
			VentanaCliente vcl = new VentanaCliente(s,usuario);
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
}