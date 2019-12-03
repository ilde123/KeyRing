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
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DialogoCambiarClave extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField campoClave;
	private JPasswordField campoClaveNueva;
	private JPasswordField campoClaveRepita;
	private Vista vista;

	public DialogoCambiarClave(Vista vista) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogoCambiarClave.class.getResource("/img/logo.png")));
		this.vista = vista;
		setTitle("Cambiar contrase\u00F1a");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblIntroduzcaContrasea = new JLabel("Introduzca contrase\u00F1a:");
		campoClave = new JPasswordField();
		JButton botonMostrar = new JButton("");
		URL url = VentanaPpal.class.getResource("/img/ojo.png");
		ImageIcon iconoOriginal = new ImageIcon(url);
		botonMostrar.setIcon(new ImageIcon(iconoOriginal.getImage().getScaledInstance(19, 15, java.awt.Image.SCALE_DEFAULT)));
		
		JLabel lblNuevaContrasea = new JLabel("Nueva contrase\u00F1a:");
		
		campoClaveNueva = new JPasswordField();
		
		JButton botonMostrarNueva = new JButton("");
		url = VentanaPpal.class.getResource("/img/ojo.png");
		iconoOriginal = new ImageIcon(url);
		botonMostrarNueva.setIcon(new ImageIcon(iconoOriginal.getImage().getScaledInstance(19, 15, java.awt.Image.SCALE_DEFAULT)));
		
		JLabel lblReptitaContrasea = new JLabel("Reptita contrase\u00F1a:");
		
		campoClaveRepita = new JPasswordField();
		
		JButton botonMostrarRepita = new JButton("");
		url = VentanaPpal.class.getResource("/img/ojo.png");
		iconoOriginal = new ImageIcon(url);
		botonMostrarRepita.setIcon(new ImageIcon(iconoOriginal.getImage().getScaledInstance(19, 15, java.awt.Image.SCALE_DEFAULT)));

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIntroduzcaContrasea)
						.addComponent(lblNuevaContrasea)
						.addComponent(lblReptitaContrasea))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(campoClaveNueva, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(campoClave, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(campoClaveRepita, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(botonMostrarRepita, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(botonMostrarNueva, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(botonMostrar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIntroduzcaContrasea)
								.addComponent(campoClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(campoClaveNueva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNuevaContrasea)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(botonMostrar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(botonMostrarNueva)))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblReptitaContrasea)
							.addComponent(campoClaveRepita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(botonMostrarRepita))
					.addContainerGap(25, Short.MAX_VALUE))
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
					public void actionPerformed(ActionEvent e) {
						botonCancelarPulsado();
					}
				});
				buttonPane.add(botonCancelar);
			}
		}
	}
	
	protected void botonAceptarPulsado() {
		// TODO Auto-generated method stub
		String pass = this.vista.getClave();
		char[] arrayC = campoClave.getPassword();
		String clave = new String(arrayC);
		clave = Vista.md5(clave);
		
		if (pass.equals(clave)) {
			arrayC = campoClaveNueva.getPassword();
			String pass1 = new String(arrayC);
			
			arrayC = campoClaveRepita.getPassword();
			String pass2 = new String(arrayC);
			
			if (pass1.equals(pass2)) {
				if (this.vista.cambiarClave(pass1)) {
					this.vista.msg(this, "Contraseña maestra cambiada con éxito");
					this.setVisible(false);
				}
				else {
					this.vista.error(this, "Error al cambiar la contraseña maestra");
				}
			}
			else {
				this.vista.error(this, "Las contraseñas no coinciden");
			}
		}
		else {
			this.vista.error(this, "Contraseña incorrecta");
		}
	}

	protected void botonCancelarPulsado() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		limpiarCampos();
	}

	public void mostrar() {
		// TODO Auto-generated method stub
		limpiarCampos();
		this.setVisible(true);
	}

	private void limpiarCampos() {
		// TODO Auto-generated method stub
		this.campoClave.setText("");
		this.campoClaveNueva.setText("");
		this.campoClaveRepita.setText("");
	}
}
