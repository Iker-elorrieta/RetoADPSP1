package PSP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class PantallaCliente extends JFrame {

	private JLabel mostrarMunicipio;

	public JLabel getMostrarMunicipio() {
		return mostrarMunicipio;
	}

	public void setMostrarMunicipio(JLabel mostrarMunicipio) {
		this.mostrarMunicipio = mostrarMunicipio;
	}

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaCliente frame = new PantallaCliente();
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
	public PantallaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mostrarMunicipio = new JLabel("El municipio seleccionado es:");
		mostrarMunicipio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mostrarMunicipio.setBounds(33, 87, 343, 45);
		contentPane.add(mostrarMunicipio);
	}
}
