package cliente_servidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HiloRecibir extends Thread {

	JTextArea textArea = null;
	JTextField textoF = null;
	ObjectOutputStream osalida = null;
	ObjectInputStream oentrada = null;
	ArrayList<ObjectOutputStream> lista = null;

	public HiloRecibir(JTextArea textArea, JTextField texto, ObjectOutputStream osalida, ObjectInputStream oentrada,
			ArrayList<ObjectOutputStream> lista) {
		this.textArea = textArea;
		this.textoF = texto;
		this.osalida = osalida;
		this.oentrada = oentrada;
		this.lista = lista;
	}

	public HiloRecibir() {
		// TODO Auto-generated constructor stub
	}

	public void run() {


		String texto = "";
		while (!texto.equals("*")) {
			try {
				texto = (String) oentrada.readObject();
				if (!texto.equals("*")) {
				textArea.append(texto);
					for (int i = 0; i < lista.size(); i++) {
						ObjectOutputStream os = lista.get(i);
						os.writeObject(texto);
					}
				}
				else
				{
					lista.remove(osalida);
					
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		this.textoF.setText("Conexiones actuales: "+lista.size());
		
		
		System.out.println("Termino recibir ser");

	}

}
