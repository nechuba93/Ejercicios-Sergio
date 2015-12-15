import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			// Establecer conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ignacio", "root", "toor");

			DatabaseMetaData dmbd = conexion.getMetaData();
			ResultSet resul = null;
			
			String nombre = dmbd.getDatabaseProductName();
			String driver = dmbd.getDriverName();
			String url    = dmbd.getURL();
			String usuario= dmbd.getUserName();
			
			System.out.println("INFORMACION SOBRE LA BASE DE DATOS:");
			System.out.println("===================================");
			System.out.println("Nombre : " + nombre);
			System.out.println("Driver : " + driver);
			System.out.println("URL    : " + url);
			System.out.println("Usuario: " + usuario);
			
			//obtener info de las tablas y vistas
			resul = dmbd.getTables(null, "ignacio", null, null);
			
			while(resul.next()){
				String catalogo = resul.getString(1);
				String esquema = resul.getString(2);
				String tabla = resul.getString(3);
				String tipo = resul.getString(4);
				System.out.println(tipo + " - Catalogo: " + catalogo + " Esquema: " + esquema + " Nombre: " +tabla);
			}
			
			// insertarDepartamento(conexion, "Ciencias");
			// mostrarDatos(conexion);

			// mostrarDatos(conexion);

			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertarDepartamento(Connection conexion, String nombredep) throws SQLException {

		PreparedStatement insertar = null;
		if (insertar == null) {
			insertar = conexion.prepareStatement("INSERT INTO departamentos (nombre) VALUES (?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			// insertar.setInt(1, numdep);
			insertar.setString(1, nombredep);
			insertar.executeUpdate();

			// Se obtiene la clave generada
			ResultSet rs = insertar.getGeneratedKeys();
			while (rs.next()) {
				int claveGenerada = rs.getInt(1);
				System.out.println("Clave generada = " + claveGenerada);
				System.out.println();
			}
		}

	}

	private static void mostrarDatos(Connection conexion) {
		try {

			// Preparar la consulta
			Statement sentencia = conexion.createStatement();
			ResultSet resul = sentencia.executeQuery("SELECT * FROM departamentos");

			System.out.println("ID" + "\t" + "Nombre");
			System.out.println("--" + "\t" + "------------------------");

			while (resul.next()) {
				System.out.println(resul.getInt(1) + "\t" + resul.getString(2));
			}
			System.out.println();
			resul.close();
			sentencia.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
