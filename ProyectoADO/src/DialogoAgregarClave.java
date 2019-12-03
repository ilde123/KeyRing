import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;

public class DialogoAgregarClave extends JDialog {

	private final JPanel panel = new JPanel();
	private Vista vista;
	private JPasswordField campoAgregarClave;
	private JTextField campoAgregarTitulo;
	private JTextField campoAgregarUsuario;
	private JTextField campoAgregarURL;
	private JTextArea textAreaAgregarNotas;

	public DialogoAgregarClave(Vista vista) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogoAgregarClave.class.getResource("/img/logo.png")));
		setTitle("A\u00F1adir entrada");
		setModal(true);
		setResizable(false);
		this.vista = vista;
		setBounds(100, 100, 550, 351);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		JLabel lblUsuario = new JLabel("Usuario");
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		JLabel lblUrl = new JLabel("URL");
		JLabel lblNotas = new JLabel("Notas");
		
		textAreaAgregarNotas = new JTextArea();
		
		campoAgregarClave = new JPasswordField();
		
		campoAgregarTitulo = new JTextField();
		campoAgregarTitulo.setColumns(10);
		
		campoAgregarUsuario = new JTextField();
		campoAgregarUsuario.setColumns(10);
		
		campoAgregarURL = new JTextField();
		campoAgregarURL.setColumns(10);
		
		JButton botonMostrar = new JButton("");
		URL url = VentanaPpal.class.getResource("/img/ojo.png");
		ImageIcon iconoOriginal = new ImageIcon(url);
		botonMostrar.setIcon(new ImageIcon(iconoOriginal.getImage().getScaledInstance(19, 15, java.awt.Image.SCALE_DEFAULT)));
		botonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonMostrarPulsado();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContrasea)
						.addComponent(lblTtulo)
						.addComponent(lblUsuario)
						.addComponent(lblNotas)
						.addComponent(lblUrl))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textAreaAgregarNotas, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
						.addComponent(campoAgregarTitulo, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
						.addComponent(campoAgregarUsuario, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
						.addComponent(campoAgregarURL, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(campoAgregarClave, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(botonMostrar)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTtulo)
						.addComponent(campoAgregarTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(campoAgregarUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasea)
						.addComponent(campoAgregarClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(botonMostrar))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUrl)
						.addComponent(campoAgregarURL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNotas)
						.addComponent(textAreaAgregarNotas, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton botonAgregarAceptar = new JButton("Añadir");
				botonAgregarAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						botonAceptarPulsado();
					}
				});
				buttonPane.add(botonAgregarAceptar);
				getRootPane().setDefaultButton(botonAgregarAceptar);
			}
			{
				JButton botonAgregarCancelar = new JButton("Cancelar");
				botonAgregarCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						botonCancelarPulsado();
					}
				});
				buttonPane.add(botonAgregarCancelar);
			}
		}
	}

	protected void botonMostrarPulsado() {
		// TODO Auto-generated method stub
		if (this.campoAgregarClave.getEchoChar() != (char)0) {
			this.campoAgregarClave.setEchoChar((char) 0);
		}
		else {
			this.campoAgregarClave.setEchoChar('•');
		}
	}

	protected void botonCancelarPulsado() {
		// TODO Auto-generated method stub
		limpiarCampos();
		this.setVisible(false);
	}

	protected void botonAceptarPulsado() {
		// TODO Auto-generated method stub
		boolean valido = true;

		String titulo = campoAgregarTitulo.getText();
		String usuario = campoAgregarUsuario.getText();
		char[] arrayC = campoAgregarClave.getPassword();
		String pass = new String(arrayC);
		String url = campoAgregarURL.getText();
		String notas = textAreaAgregarNotas.getText();

		if (titulo.equals("")) {
			this.vista.error(this, "Debe escribir un título");
			valido = false;
		}
		else if (usuario.equals("")) {
			this.vista.error(this, "Debe escribir un usuario");
			valido = false;
		}
		else if (pass.equals("")) {
			this.vista.error(this, "Debe escribir una contraseña");
			valido = false;
		}
		
		if (valido) {
			pass = Vista.encrypt(pass, "ADO");
			if (this.vista.agregarClave(titulo, usuario, pass, url, notas)) {
				this.vista.mostrarVentanaPpal();
				
				this.vista.msg(this, "Entrada agregada con éxito");
				
				this.setVisible(false);
				limpiarCampos();
			}
		}
	}

	private void limpiarCampos() {
		this.campoAgregarClave.setText("");
		this.campoAgregarTitulo.setText("");
		this.campoAgregarURL.setText("");
		this.campoAgregarUsuario.setText("");
		this.textAreaAgregarNotas.setText("");
	}

	public void mostrar() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}
}
