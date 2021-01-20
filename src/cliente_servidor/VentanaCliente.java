package cliente_servidor;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;

import dao.BaseDatos;
import json.LecturaDatos;
import modelo.Espacios;
import modelo.Municipios;
import modelo.Provincias;
import modelo.Ubicaciones;
import modelo.Usuarios;
import java.awt.Label;

public class VentanaCliente extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	Socket socket = null;

	Usuarios usuario;
	static JTextField mensaje = new JTextField();

	private JScrollPane scrollpane1;
	static JTextArea textarea1;
	JButton botonEnviar = new JButton("Enviar");
	JButton botonSalir = new JButton("Salir");
	Cliente cliente = null;
	private JButton btnMunicipios;
	private JButton btnEspaciosNaturales;
	private JButton btnTopRankingEspacios;
	private JButton btnTopMunicipios;
	private JButton btnFavoritos;
	private JComboBox<String> comboBoxProvincias;
	private Label label_1;
	private JComboBox comboBoxMunicipios;
	private Label label;
	private Provincias provinciaSeleccionada;
	private Ubicaciones ubicacionesSeleccionadas;
	BaseDatos bd = new BaseDatos();
	private ArrayList<Municipios> listaMunicipios;
	private ArrayList<Espacios> listaEspacios;
	private ArrayList<Ubicaciones> listaUbicaciones;

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

		botonEnviar.setBounds(557, 10, 100, 30);
		getContentPane().add(botonEnviar);
		botonSalir.setBounds(612, 456, 208, 69);
		getContentPane().add(botonSalir);
		botonEnviar.addActionListener(this);
		botonSalir.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		socket = s;
		this.usuario = usuario;

		cliente = new Cliente();

		socket = new Socket(cliente.getHost(), cliente.getPuerto());
		cliente = new Cliente(socket, textarea1, mensaje, botonEnviar);
		btnMunicipios = new JButton("Municipios");
		btnMunicipios.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				comboBoxMunicipios.setEnabled(false);
				listaMunicipios = LecturaDatos.listaMunicipios;
				mostrarMunicipios(listaMunicipios);
			}
		});
		btnMunicipios.setBounds(468, 62, 208, 54);
		getContentPane().add(btnMunicipios);

		btnEspaciosNaturales = new JButton("Espacios Naturales");
		btnEspaciosNaturales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				listaEspacios = LecturaDatos.listaEspacios;

			}
		});
		btnEspaciosNaturales.setBounds(468, 192, 208, 54);
		getContentPane().add(btnEspaciosNaturales);

		btnTopRankingEspacios = new JButton("Top 5 Espacios Naturales");
		btnTopRankingEspacios.setBounds(468, 257, 208, 54);
		getContentPane().add(btnTopRankingEspacios);

		btnTopMunicipios = new JButton("Top 5 Municipios");
		btnTopMunicipios.setBounds(468, 127, 208, 54);
		getContentPane().add(btnTopMunicipios);

		btnFavoritos = new JButton("Favoritos");
		btnFavoritos.setBounds(468, 322, 208, 54);
		getContentPane().add(btnFavoritos);

		comboBoxProvincias = new JComboBox<String>();

		comboBoxProvincias.setBounds(729, 62, 146, 22);
		getContentPane().add(comboBoxProvincias);
		for (Provincias provincia : LecturaDatos.listProvincias) {
			comboBoxProvincias.addItem(provincia.getNombre());
		}
		comboBoxProvincias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Provincias provincia : LecturaDatos.listProvincias) {
					if (provincia.getNombre()
							.equals(comboBoxProvincias.getItemAt(comboBoxProvincias.getSelectedIndex()))) {
						provinciaSeleccionada = provincia;

						ArrayList<Municipios> municipiosSeleccionados = new ArrayList<>();
						for (Municipios municipio : LecturaDatos.listaMunicipios) {

							if (municipio.getProvincias().getCodProv() == provinciaSeleccionada.getCodProv()) {

								municipiosSeleccionados.add(municipio);
							}
						}
						mostrarMunicipios(municipiosSeleccionados);
					}

				}

			}
		});

		label = new Label("Seleccionar Provincia:");
		label.setBounds(729, 62, 115, 22);
		getContentPane().add(label);

		label_1 = new Label("Seleccionar Municipio");
		label_1.setBounds(729, 127, 115, 22);
		getContentPane().add(label_1);

		comboBoxMunicipios = new JComboBox();
		comboBoxMunicipios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ArrayList<Espacios> espaciosSeleccionados = new ArrayList<>();
				listaUbicaciones = LecturaDatos.listaUbicaciones;
				listaEspacios = LecturaDatos.listaEspacios;
				ArrayList<Municipios> municipiosSeleccionados = new ArrayList<>();
				for (Municipios municipio : LecturaDatos.listaMunicipios) {

					if (municipio.getProvincias().getCodProv() == provinciaSeleccionada.getCodProv()) {

						for (Ubicaciones ubicacion : listaUbicaciones) {
							if (ubicacion.getMunicipios().getCodMuni() == municipio.getCodMuni()) {
								// ubicacionesSeleccionadas.add(ubicacion);
								for (Espacios espacio : listaEspacios) {
									if (espacio.getCodEspacio() == ubicacion.getId().getCodEspacio()) {
										espaciosSeleccionados.add(espacio);
									}
								}
							}
							municipiosSeleccionados.add(municipio);
						}
					}

				}
				

				mostrarEspacios(espaciosSeleccionados);
			}
		});
		comboBoxMunicipios.setBounds(729, 127, 146, 22);
		getContentPane().add(comboBoxMunicipios);

		cliente.enviarMensaje("> " + usuario + " se ha conectado\n");

	}// fin constructor

	public boolean mostrarMunicipios(ArrayList<Municipios> municipios) {
		textarea1.setText("");
		for (Municipios municipio : municipios) {
			String texto = municipio.getNombre() + " | " + municipio.getDescripcion()
					+ municipio.getProvincias().getNombre();

			cliente.enviarMensaje(texto + "\n");

		}
		return true;
	}

	public boolean mostrarEspacios(ArrayList<Espacios> espacios) {
		textarea1.setText("");
		for (Espacios espacio : espacios) {
			String texto = espacio.getNombre() + " | " + espacio.getDescripcion();

			cliente.enviarMensaje(texto + "\n");

		}
		return true;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonEnviar) { // SE PULSA EL ENVIAR

			if (mensaje.getText().trim().length() == 0)
				return;
			String texto = usuario + "> " + mensaje.getText();

			cliente.enviarMensaje(texto + "\n");
			mensaje.setText("");

		}
		if (e.getSource() == botonSalir) { // SE PULSA BOTON SALIR
			String texto = usuario + " > Abandona la app ... \n";
			cliente.enviarMensaje(texto);
			cliente.enviarMensaje("*");
			System.exit(0);
		}

	}
}
