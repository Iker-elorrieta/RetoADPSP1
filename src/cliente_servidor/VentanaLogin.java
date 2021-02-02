package cliente_servidor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BaseDatos;
import json.LecturaDatos;
import modelo.Usuarios;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Label;

public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_NombreUsuario;
	private JTextField textField_ContraUsuario;
	private JTextField textField_RegistroNombre;
	private JTextField textField_RegistroContra;
	private JTextField textField_RegistroRepetirContra;
	private BaseDatos bd = new BaseDatos();
	Usuarios usuario;
	/**
	 * Launch the application.
	 * @return 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textField_NombreUsuario = new JTextField();
		textField_NombreUsuario.setBounds(25, 84, 149, 19);
		contentPane.add(textField_NombreUsuario);
		textField_NombreUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre de Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(25, 59, 128, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblLoginError = new JLabel("");
		lblLoginError.setForeground(Color.RED);
		lblLoginError.setBounds(25, 219, 165, 19);
		contentPane.add(lblLoginError);
		lblLoginError.setVisible(false);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblLoginError.setVisible(false);
				BaseDatos bd = new BaseDatos();
				Usuarios usuario = bd.obtenerUsuario(textField_NombreUsuario.getText(), textField_ContraUsuario.getText());
				
				if(!textField_NombreUsuario.getText().equals("") && usuario!=null) 
				{
					
					Socket s = null;
					VentanaCliente cl;
					try {
						cl = new VentanaCliente(s,usuario);
						cl.setBounds(0, 0, 540, 400);
						cl.setVisible(true);
						setVisible(false);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						lblLoginError.setText("Error al conectarse");
					}
				
				}
				else
				{
					lblLoginError.setVisible(true);
					lblLoginError.setText("Debe introducir un nick");
				}
					
			}
		});
		btnLogin.setBounds(72, 263, 102, 21);
		contentPane.add(btnLogin);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContrasea.setBounds(25, 128, 128, 25);
		contentPane.add(lblContrasea);
		
		textField_ContraUsuario = new JTextField();
		textField_ContraUsuario.setColumns(10);
		textField_ContraUsuario.setBounds(25, 153, 149, 19);
		contentPane.add(textField_ContraUsuario);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre de Usuario");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(278, 59, 128, 25);
		contentPane.add(lblNewLabel_2);
		
		textField_RegistroNombre = new JTextField();
		textField_RegistroNombre.setColumns(10);
		textField_RegistroNombre.setBounds(278, 84, 149, 19);
		contentPane.add(textField_RegistroNombre);
		
		JLabel lblContrasea_1 = new JLabel("Contrase\u00F1a");
		lblContrasea_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContrasea_1.setBounds(278, 114, 128, 25);
		contentPane.add(lblContrasea_1);
		
		textField_RegistroContra = new JTextField();
		textField_RegistroContra.setColumns(10);
		textField_RegistroContra.setBounds(278, 139, 149, 19);
		contentPane.add(textField_RegistroContra);
		
		JLabel lblContrasea_2 = new JLabel("Repetir Contrase\u00F1a");
		lblContrasea_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContrasea_2.setBounds(278, 169, 149, 25);
		contentPane.add(lblContrasea_2);
		
		textField_RegistroRepetirContra = new JTextField();
		textField_RegistroRepetirContra.setColumns(10);
		textField_RegistroRepetirContra.setBounds(278, 194, 149, 19);
		contentPane.add(textField_RegistroRepetirContra);
		
		Label llblRegistro = new Label("");
		llblRegistro.setBounds(278, 219, 149, 22);
		contentPane.add(llblRegistro);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				llblRegistro.setVisible(false);
				if (textField_RegistroContra.getText().equals(textField_RegistroRepetirContra.getText()) && !textField_RegistroNombre.getText().equals("")){
					usuario = new Usuarios(textField_RegistroNombre.getText(),textField_RegistroContra.getText());		}
				
				
				if(bd.insertUsuarios(usuario)){
				
					
					Socket s = null;
					VentanaCliente cl;
					try {
						cl = new VentanaCliente(s,usuario);
						cl.setBounds(0, 0, 540, 400);						
						cl.setVisible(true);
						setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						lblLoginError.setVisible(true);
						lblLoginError.setText("Error al conectarse");
					}
				
				}
				else
				{
					lblLoginError.setVisible(true);
					lblLoginError.setText("Debe introducir un nick");
				}
				
				
			}
		});
		btnRegistrarse.setBounds(278, 263, 102, 21);
		contentPane.add(btnRegistrarse);
		
		Label label = new Label("INICIAR SESION");
		label.setFont(new Font("Dialog", Font.BOLD, 15));
		label.setBounds(25, 10, 138, 22);
		contentPane.add(label);
		
		Label label_1 = new Label("REGISTRO");
		label_1.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1.setBounds(278, 10, 138, 22);
		contentPane.add(label_1);
		
		
		
		
		
	}
}
