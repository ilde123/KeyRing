import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DialogoPpal extends JDialog {

	private Vista vista;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField campoClave;
	private JLabel lblContrasea;
	private String claveMaestra;
	private boolean cambiarPass = false;

	public DialogoPpal(Vista vista) {
		setAlwaysOnTop(true);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogoPpal.class.getResource("/img/logo.png")));
		setTitle("KeyRing");
		this.vista = vista;
		setResizable(false);
		setBounds(100, 100, 470, 110);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		campoClave = new JPasswordField();
		
		lblContrasea = new JLabel("Contrase\u00F1a");
		JButton botonMostrar = new JButton("");
		URL url = VentanaPpal.class.getResource("/img/ojo.png");
		ImageIcon iconoOriginal = new ImageIcon(url);
		botonMostrar.setIcon(new ImageIcon(iconoOriginal.getImage().getScaledInstance(19, 15, java.awt.Image.SCALE_DEFAULT)));
		botonMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonMostrarPulsado();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblContrasea)
					.addGap(18)
					.addComponent(campoClave, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(botonMostrar))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasea)
						.addComponent(campoClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(botonMostrar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton botonAceptar = new JButton("Aceptar");
				botonAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						botonAceptarPulsado();
					}
				});
				buttonPane.add(botonAceptar);
				getRootPane().setDefaultButton(botonAceptar);
			}
			{
				JButton botonCancelar = new JButton("Cancelar");
				botonCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						botonCancelarPulsado();
					}
				});
				buttonPane.add(botonCancelar);
			}
		}
		cargarClaveMaestra();
	}

	private void cargarClaveMaestra() {
		// TODO Auto-generated method stub
		try {
			String claveMaestra = this.vista.getClave();
			
			if (claveMaestra.equals("")) {
				this.vista.msg(this, "Debe introducir una clave maestra");
				this.cambiarPass = true;
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.vista.msg(this, "Debe introducir una clave maestra");
			this.cambiarPass = true;
		}
	}

	protected void botonMostrarPulsado() {
		// TODO Auto-generated method stub
		if (this.campoClave.getEchoChar() != (char)0) {
			this.campoClave.setEchoChar((char) 0);
		}
		else {
			this.campoClave.setEchoChar('•');
		}
	}

	protected void botonAceptarPulsado() {
		// TODO Auto-generated method stub
		char[] arrayC = campoClave.getPassword();
		String pass = new String(arrayC);

		if (pass.equals("")) {
			this.vista.error(this, "Debe introducir una contraseña");
		}
		else {
			if (this.cambiarPass) {
				if (this.vista.cambiarClave(pass)) {
					this.vista.msg(this, "Contraseña maestra creada con éxito");
					this.vista.mostrarVentanaPpal();
					this.cambiarPass = false;
					this.setVisible(false);
				}
				else {
					this.vista.error(this, "Error al crear la contraseña maestra");
				}
			}
			else {
				String claveMaestra = this.vista.getClave();

				if (Vista.md5(pass).equals(claveMaestra)) {
					this.vista.mostrarVentanaPpal();
					this.setVisible(false);
				}
				else {
					this.vista.error(this, "Contraseña incorrecta");
				}
			}
			this.campoClave.setText("");
		}
	}

	protected void botonCancelarPulsado() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	public void mostrar() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}
}
