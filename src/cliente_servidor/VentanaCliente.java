package cliente_servidor;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;

import json.LecturaDatos;
import modelo.Municipios;
import modelo.Usuarios;

public class VentanaCliente extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	Socket socket = null;

	Usuarios usuario;
	static JTextField mensaje = new JTextField();

	private JScrollPane scrollpane1;
	static JTextArea textarea1;
	JButton botonEnviar = new JButton("Enviar");
	JButton botonSalir = new JButton("Salir");
	Cliente cliente  = null;


	// constructor
	public VentanaCliente(Socket s, Usuarios usuario) throws IOException {
		super(" BIENVENIDO " + usuario.getNombre());
		getContentPane().setLayout(null);

		mensaje.setBounds(10, 10, 400, 30);
		getContentPane().add(mensaje);
		scrollpane1 = new JScrollPane();
		scrollpane1.setBounds(10, 50, 448, 436);
		getContentPane().add(scrollpane1);
		
				textarea1 = new JTextArea();
				scrollpane1.setViewportView(textarea1);
				
						textarea1.setEditable(false);
						
						cliente = new Cliente(socket,textarea1, mensaje,botonEnviar);

		botonEnviar.setBounds(557, 10, 100, 30);
		getContentPane().add(botonEnviar);
		botonSalir.setBounds(612, 456, 100, 30);
		getContentPane().add(botonSalir);
		botonEnviar.addActionListener(this);
		botonSalir.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		socket = s;
		this.usuario = usuario;
		
		cliente = new Cliente();
		
		socket = new Socket(cliente.getHost(),cliente.getPuerto());
		
		JButton btnMunicipios = new JButton("Municipios");
		btnMunicipios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				LecturaDatos ld = new LecturaDatos();
				ArrayList<Municipios> listaMunicipios = LecturaDatos.listaMunicipios;
				for (Municipios municipio: listaMunicipios) {
					textarea1.append(municipio.getNombre());
				}
				
			}
		});
		btnMunicipios.setBounds(468, 62, 208, 54);
		getContentPane().add(btnMunicipios);
		
		JButton btnEspaciosNaturales = new JButton("Espacios Naturales");
		btnEspaciosNaturales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEspaciosNaturales.setBounds(468, 192, 208, 54);
		getContentPane().add(btnEspaciosNaturales);
		
		JButton btnTopRankingEspacios = new JButton("Top 5 Espacios Naturales");
		btnTopRankingEspacios.setBounds(468, 257, 208, 54);
		getContentPane().add(btnTopRankingEspacios);
		
		JButton btnTopMunicipios = new JButton("Top 5 Municipios");
		btnTopMunicipios.setBounds(468, 127, 208, 54);
		getContentPane().add(btnTopMunicipios);
		
		JButton btnFavoritos = new JButton("Favoritos");
		btnFavoritos.setBounds(468, 322, 208, 54);
		getContentPane().add(btnFavoritos);
		
		JComboBox comboBoxFiltro = new JComboBox();
		comboBoxFiltro.setBounds(432, 14, 30, 22);
		getContentPane().add(comboBoxFiltro);
		cliente.enviarMensaje("> " +usuario.getNombre() +" se ha conectado\n");
		
		
		
	}// fin constructor

	// accion cuando pulsamos botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonEnviar) { // SE PULSA EL ENVIAR

			if (mensaje.getText().trim().length() == 0)
				return;
			String texto = usuario.getNombre() + "> " + mensaje.getText();

			cliente.enviarMensaje(texto+"\n");
			mensaje.setText("");
				
			
		}
		if (e.getSource() == botonSalir) { // SE PULSA BOTON SALIR
			String texto = usuario.getNombre()  + " > Abandona la app ... \n" ;
			cliente.enviarMensaje(texto);
			cliente.enviarMensaje("*");
			System.exit(0);
		}
		
	}
}




