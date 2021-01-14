package PSP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Servidor {
	
	private final int PUERTO = 5000;
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/retofinal";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";
    private Connection conexion = null;

	public boolean conectar(String c, String u) {
        boolean res;
        try {
            Class.forName(c);
            conexion = DriverManager.getConnection(u, USUARIO, CLAVE);
            System.out.println("Conexión OK");
            res=true;
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador");
            res=false;
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            res=false;
        }
        
        return res;
    }
	
	public boolean iniciar() {
		boolean res;
		ServerSocket servidor = null;
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;
		try {
			servidor = new ServerSocket(PUERTO);
			System.out.println("Esperando conexiones del cliente...");
			cliente = servidor.accept();
			System.out.println("Cliente conectado");
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());
			
			conectar(CONTROLADOR,URL);
			
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery ("select Nombre from municipios where CodMuni=0");
			
			if (rs.next()){
			    salida.writeObject(rs.getString(1));
			}
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (servidor != null)
					servidor.close();
				if (cliente != null)
					cliente.close();
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Fin servidor");
		}
		res=true;
		return res;
	}

	public static void main(String[] args) {
		Servidor s1 = new Servidor();
		s1.iniciar();
	}
}
