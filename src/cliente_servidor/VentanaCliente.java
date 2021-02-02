package cliente_servidor;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.List;

import dao.BaseDatos;
import dao.BaseDatos.HibernateUtil;
import json.LecturaDatos;
import modelo.Espacios;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincias;
import modelo.Ubicaciones;
import modelo.Usuarios;

import java.awt.Component;
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
	private JComboBox<String> comboBoxMunicipios;
	private Label label;
	private Provincias provinciaSeleccionada;
	private Ubicaciones ubicacionesSeleccionadas;
	BaseDatos bd = new BaseDatos();
	private ArrayList<Provincias> listaProvincias = bd.obtenerProvincias();
	private java.util.List<Municipios> listaMunicipios = bd.obtenerMunicipios(null, null);
	private java.util.List<Espacios> listaEspacios = bd.obtenerEspacios(null, null);
	private ArrayList<Ubicaciones> listaUbicaciones = bd.obtenerUbicaciones();
	private boolean municipiosPulsado = false;
	private boolean espaciosPulsado = false;

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
// PULSAMOS BOTON MUNICIPIOS
			public void actionPerformed(ActionEvent arg0) {
				municipiosPulsado = true;
				espaciosPulsado = false;
				mostrarMunicipios(listaMunicipios);
			}
		});
///////////////////////////////////////////
		btnMunicipios.setBounds(468, 62, 208, 54);
		getContentPane().add(btnMunicipios);

		btnEspaciosNaturales = new JButton("Espacios Naturales");
		btnEspaciosNaturales.addActionListener(new ActionListener() {
// PULSAMOS EN BOTON ESPACIOS NATURALES
			public void actionPerformed(ActionEvent arg0) {

				espaciosPulsado = true;
				municipiosPulsado = false;
				comboBoxMunicipios.setVisible(true);
				mostrarEspacios(listaEspacios);
			}
		});
///////////////////////////////////////////////
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

		comboBoxMunicipios = new JComboBox<String>();
		comboBoxMunicipios.setBounds(729, 127, 146, 22);
		getContentPane().add(comboBoxMunicipios);

		label = new Label("Seleccionar Provincia:");
		label.setBounds(729, 62, 115, 22);
		getContentPane().add(label);

		label_1 = new Label("Seleccionar Municipio");
		label_1.setBounds(729, 127, 115, 22);
		getContentPane().add(label_1);

		for (Provincias provincia : listaProvincias) {
			comboBoxProvincias.addItem(provincia.getNombre());
		}
		comboBoxProvincias.setVisible(true);

//SELECCIONAMOS ALGO EN EL COMBOBOX PROVINCIAS

		comboBoxProvincias.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				
				for (Provincias provincia : listaProvincias) {
					if (provincia.getNombre()
							.equals(comboBoxProvincias.getItemAt(comboBoxProvincias.getSelectedIndex()))) {
						provinciaSeleccionada = provincia;
					}
				}
				ArrayList<Integer> codProv = new ArrayList<>();
				codProv.add(provinciaSeleccionada.getCodProv());
				java.util.List<Municipios> municipiosProvincia = new ArrayList<>();
				municipiosProvincia = bd.obtenerMunicipios(null, codProv);
				if (municipiosPulsado) {
					mostrarMunicipios(municipiosProvincia);
				} else if (espaciosPulsado) {
					
					java.util.List<Espacios> espaciosProvincia = bd.obtenerEspacios(null,
							provinciaSeleccionada.getCodProv());
					mostrarEspacios(espaciosProvincia);
					
					comboBoxMunicipios.setVisible(true);
				}
			}
		});
////////////////////////////////////
// SELECCIONAMOS ALGO EN EL COMBOBOX MUNICIPIOS

		comboBoxMunicipios.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ArrayList<Espacios> espaciosSeleccionados = new ArrayList<>();

				ArrayList<Municipios> municipiosSeleccionados = new ArrayList<>();
				for (Municipios municipio : listaMunicipios) {

					if (municipio.getProvincias().getCodProv() == provinciaSeleccionada.getCodProv()) {

						for (Ubicaciones ubicacion : listaUbicaciones) {
							if (ubicacion.getMunicipios().getCodMuni() == municipio.getCodMuni()) {								
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
////////////////////////////////

		cliente.enviarMensaje("> " + usuario + " se ha conectado\n");

	}// fin constructor

	public void comboBoxMunicipios(java.util.List<Municipios> municipios) {
		comboBoxMunicipios.removeAllItems();
		

		for (Municipios municipio : municipios) {
			comboBoxMunicipios.addItem(municipio.getNombre());
		}

	}

	public boolean mostrarMunicipios(java.util.List<Municipios> municipiosProvincia) {
		textarea1.setText("");
		for (Municipios municipio : municipiosProvincia) {

			textarea1.append(municipio.getNombre() + "\n");

		}
		return true;
	}

	public boolean mostrarEspacios(java.util.List<Espacios> espaciosProvincia) {
		textarea1.setText("");
		for (Espacios espacio : espaciosProvincia) {

			textarea1.append(espacio.getNombre() + "\n");

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
