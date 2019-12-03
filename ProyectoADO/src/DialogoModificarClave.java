import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DialogoModificarClave extends JDialog {
	private Vista vista;
	private JTextField campoModificarTitulo;
	private JPasswordField campoModificarClave;
	private JTextField campoModificarUsuario;
	private JTextField campoModificarURL;
	private JTextArea textAreaModificar;
	private JTextField campoId;
	private JButton botonVer;

	public DialogoModificarClave(Vista vista) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogoModificarClave.class.getResource("/img/logo.png")));
		setTitle("Modificar entrada");
		setModal(true);
		setResizable(false);
		this.vista = vista;
		setBounds(100, 100, 550, 351);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(panel, BorderLayout.CENTER);
			JLabel label = new JLabel("Contrase\u00F1a");
			JLabel label_1 = new JLabel("T\u00EDtulo");
			JLabel label_2 = new JLabel("Usuario");
			JLabel label_3 = new JLabel("Notas");
			JLabel label_4 = new JLabel("URL");
			textAreaModificar = new JTextArea();
			campoModificarTitulo = new JTextField();
			campoModificarTitulo.setColumns(10);
			campoModificarClave = new JPasswordField();
			campoModificarUsuario = new JTextField();
			campoModificarUsuario.setColumns(10);
			campoModificarURL = new JTextField();
			campoModificarURL.setColumns(10);
			
			campoId = new JTextField();
			campoId.setVisible(false);
			campoId.setColumns(10);
			
			botonVer = new JButton("");
			URL url = VentanaPpal.class.getResource("/img/ojo.png");
			ImageIcon iconoOriginal = new ImageIcon(url);
			botonVer.setIcon(new ImageIcon(iconoOriginal.getImage().getScaledInstance(19, 15, java.awt.Image.SCALE_DEFAULT)));
			botonVer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					botonVerPulsado();
				}
			});
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(label)
									.addComponent(label_1)
									.addComponent(label_2)
									.addComponent(label_3)
									.addComponent(label_4)))
							.addComponent(campoId, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(textAreaModificar, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
							.addComponent(campoModificarUsuario, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
							.addComponent(campoModificarURL, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(campoModificarClave, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(botonVer))
							.addComponent(campoModificarTitulo, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_1)
							.addComponent(campoModificarTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_2)
							.addComponent(campoModificarUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(campoModificarClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(botonVer))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_4)
							.addComponent(campoModificarURL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(label_3)
								.addGap(18)
								.addComponent(campoId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(textAreaModificar, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
						.addContainerGap())
			);
			panel.setLayout(gl_panel);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton botonModificar = new JButton("Modificar");
				botonModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						botonAceptarPulsado();
					}
				});
				panel.add(botonModificar);
			}
			{
				JButton botonCancelar = new JButton("Cancelar");
				botonCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						botonCancelarPulsado();
					}
				});
				panel.add(botonCancelar);
			}
		}
	}
	
	protected void botonVerPulsado() {
		// TODO Auto-generated method stub
		if (this.campoModificarClave.getEchoChar() != (char)0) {
			this.campoModificarClave.setEchoChar((char) 0);
		}
		else {
			this.campoModificarClave.setEchoChar('•');
		}
	}

	protected void botonCancelarPulsado() {
		// TODO Auto-generated method stub
		limpiarCampos();
		this.campoModificarClave.setEchoChar('•');
		this.setVisible(false);
	}
	
	protected void botonAceptarPulsado() {
		// TODO Auto-generated method stub
		boolean valido = true;
		this.campoModificarClave.setEchoChar('•');

		String titulo = this.campoModificarTitulo.getText();
		String usuario = this.campoModificarUsuario.getText();
		char[] arrayC = this.campoModificarClave.getPassword();
		String pass = new String(arrayC);
		String url = this.campoModificarURL.getText();
		String notas = this.textAreaModificar.getText();
		String id = this.campoId.getText();

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
			if (this.vista.agregarClave(titulo, usuario, pass, url, notas, id)) {
				this.vista.mostrarVentanaPpal();
				
				this.vista.msg(this, "Entrada actualizada con éxito");
				
				this.setVisible(false);
				limpiarCampos();
			}
		}
	}
	
	private void limpiarCampos() {
		this.campoModificarClave.setText("");
		this.campoModificarTitulo.setText("");
		this.campoModificarURL.setText("");
		this.campoModificarUsuario.setText("");
		this.textAreaModificar.setText("");
	}

	public void mostrar(String titulo, String usuario, String pass, String url, String notas, String id) {
		// TODO Auto-generated method stub
		pass = Vista.decrypt(pass, "ADO");
		this.campoModificarClave.setText(pass);
		this.campoModificarTitulo.setText(titulo);
		this.campoModificarURL.setText(url);
		this.campoModificarUsuario.setText(usuario);
		this.textAreaModificar.setText(notas);
		this.campoId.setText(id);
		this.setVisible(true);
	}
}
