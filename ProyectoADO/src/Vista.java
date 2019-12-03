import java.awt.Window;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Vista {

	private Principal controlador;
	private VentanaPpal ventanaPpal;
	private DialogoPpal dialogoPpal;
	private DialogoAgregarClave dialogoAgregarClave;
	private DialogoModificarClave dialogoModificarClave;
	private DialogoCambiarClave dialogoCambiarClave;
	private static SecretKeySpec secretKey;
	private static byte[] key;

	public Vista(Principal controlador) {
		super();
		this.controlador = controlador;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.ventanaPpal = new VentanaPpal(this);
		this.dialogoPpal = new DialogoPpal(this);
		this.dialogoAgregarClave = new DialogoAgregarClave(this);
		this.dialogoModificarClave = new DialogoModificarClave(this);
		this.dialogoCambiarClave = new DialogoCambiarClave(this);
		this.dialogoPpal.mostrar();
	}

	public void msg(Window padre, String mensaje) {
		JOptionPane.showMessageDialog(padre, mensaje, "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
	}

	public void error(Window padre, String mensaje) {
		JOptionPane.showMessageDialog(padre, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	public int pregunta(Window padre, String mensaje) {
		return JOptionPane.showConfirmDialog(padre, mensaje, "PREGUNTA", JOptionPane.YES_NO_OPTION);
	}

	public String getClave() {
		// TODO Auto-generated method stub
		return this.controlador.getClave();
	}

	public void mostrarVentanaPpal() {
		// TODO Auto-generated method stub
		this.ventanaPpal.mostar();
	}

	public ResultSet getEntradas() {
		// TODO Auto-generated method stub
		return this.controlador.getEntradas();
	}

	public void mostrarDialogoAgregarClave() {
		// TODO Auto-generated method stub
		this.dialogoAgregarClave.mostrar();
	}

	public boolean agregarClave(String titulo, String usuario, String pass, String url, String notas) {
		// TODO Auto-generated method stub
		return this.controlador.agregarClave(titulo, usuario, pass, url, notas);
	}

	public boolean agregarClave(String titulo, String usuario, String pass, String url, String notas, String id) {
		// TODO Auto-generated method stub
		return this.controlador.agregarClave(titulo, usuario, pass, url, notas, id);
	}

	public void mostrarDialogoModificarClave(String titulo, String usuario, String pass, String url, String notas, String id) {
		// TODO Auto-generated method stub
		this.dialogoModificarClave.mostrar(titulo, usuario, pass, url, notas, id);
	}

	public boolean eliminarClave(String id) {
		// TODO Auto-generated method stub
		return this.controlador.eliminarClave(id);
	}

	public void bloquear() {
		// TODO Auto-generated method stub
		this.ventanaPpal.setVisible(false);
		this.dialogoPpal.mostrar();
	}

	public void mostrarDialogoCambiarClave() {
		// TODO Auto-generated method stub
		this.dialogoCambiarClave.mostrar();
	}

	public boolean cambiarClave(String pass) {
		// TODO Auto-generated method stub
		String pass2 = md5(pass);
		return this.controlador.cambiarClave(pass2);
	}

	public static String getHash(String txt, String hashType) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance(hashType);
			byte[] array = md.digest(txt.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/* Retorna un hash MD5 a partir de un texto */
	public static String md5(String txt) {
		return Vista.getHash(txt, "MD5");
	}

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return strToEncrypt;
	}

	public static String decrypt(String strToDecrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return strToDecrypt;
	}
}
