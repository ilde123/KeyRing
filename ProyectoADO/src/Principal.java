import java.sql.ResultSet;
import java.util.LinkedList;

public class Principal {
	
	private Modelo modelo;
	private Vista vista;

	public Principal() {
		super();
		this.modelo = new Modelo();
		this.vista = new Vista(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Principal();
	}

	public String getClave() {
		// TODO Auto-generated method stub
		return this.modelo.getClave();
	}

	public ResultSet getEntradas() {
		// TODO Auto-generated method stub
		return this.modelo.getEntradas();
	}

	public boolean agregarClave(String titulo, String usuario, String pass, String url, String notas) {
		// TODO Auto-generated method stub
		return this.modelo.agregarClave(titulo, usuario, pass, url, notas);
	}

	public boolean agregarClave(String titulo, String usuario, String pass, String url, String notas, String id) {
		// TODO Auto-generated method stub
		return this.modelo.agregarClave(titulo, usuario, pass, url, notas, id);
	}

	public boolean eliminarClave(String id) {
		// TODO Auto-generated method stub
		return this.modelo.eliminarClave(id);
	}

	public boolean cambiarClave(String pass) {
		// TODO Auto-generated method stub
		return this.modelo.cambiarClave(pass);
	}

}
