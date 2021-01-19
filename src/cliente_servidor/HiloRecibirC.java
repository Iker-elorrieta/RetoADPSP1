package cliente_servidor;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HiloRecibirC extends Thread {

	JTextArea textArea = null;
	JTextField texto = null;
	ObjectInputStream oentrada = null;
	JButton botonEnviar = null;

	public HiloRecibirC(JTextArea textArea, JTextField texto, ObjectInputStream oentrada, JButton botonEnviar) {
		this.textArea = textArea;
		this.texto = texto;
		this.oentrada = oentrada;
		this.botonEnviar = botonEnviar;
	}

	public HiloRecibirC() {
		// TODO Auto-generated constructor stub
	}

	public void run() {


		String texto = "";

		while (!texto.equals("*")) {
			try {
				texto = (String) oentrada.readObject();
				if(!texto.equals("*"))
					textArea.append(texto);
				else
					textArea.append("Servidor desconectado");

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Termino recibir cliente");
		botonEnviar.setEnabled(false);
	}

}
