import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Modelo {
	private Connection conexion;
	private Statement sql;

	public Modelo() {
		super();
		// TODO Auto-generated constructor stub

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getClave() {
		// TODO Auto-generated method stub

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/keyring", "root" ,"");
			Statement sql = conexion.createStatement();
			ResultSet resultado = sql.executeQuery("SELECT Password FROM password");
			String clave;

			if (resultado.next()) {
				clave = resultado.getString("Password");
			}
			else {
				clave = "";
			}

			conexion.close();
			return clave;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public ResultSet getEntradas() {
		// TODO Auto-generated method stub

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/keyring", "root" ,"");
			Statement sql = conexion.createStatement();
			ResultSet resultado = sql.executeQuery("SELECT Id, Titulo, Usuario, Password, URL, Nota FROM entrada");

			return resultado;
			/*			if (resultado.next()) {
				return resultado;
			}
			else {
				return null;
			}
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean agregarClave(String titulo, String usuario, String pass, String url, String notas) {
		// TODO Auto-generated method stub
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/keyring", "root" ,"");
			Statement sql = conexion.createStatement();
			int resultado = sql.executeUpdate("INSERT INTO entrada(Titulo, Usuario, Password, URL, Nota) VALUES ('" + titulo + "', '" + usuario + "', '" + pass + "', '" + url + "', '" + notas + "')");

			if (resultado > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean agregarClave(String titulo, String usuario, String pass, String url, String notas, String id) {
		// TODO Auto-generated method stub
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/keyring", "root" ,"");
			Statement sql = conexion.createStatement();
			int resultado = sql.executeUpdate("UPDATE entrada SET Id=" + id + ", Titulo='" + titulo + "', Usuario='" + usuario + "', Password='" + pass + "', URL='" + url + "', Nota='" + notas + "' WHERE Id='" + id + "'");

			if (resultado > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean eliminarClave(String id) {
		// TODO Auto-generated method stub
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/keyring", "root" ,"");
			Statement sql = conexion.createStatement();
			int resultado = sql.executeUpdate("DELETE FROM entrada WHERE Id="+id);

			if (resultado > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean cambiarClave(String pass) {
		// TODO Auto-generated method stub
		Connection conexion;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/keyring", "root" ,"");
			Statement sql = conexion.createStatement();
			int resultado;
			String clave = getClave();

			if (clave.length() > 0) {
				resultado = sql.executeUpdate("UPDATE password SET Password='" + pass + "'");
			}
			else {
				resultado = sql.executeUpdate("INSERT INTO password (Password) VALUES ('" + pass + "')");
			}

			if (resultado > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
