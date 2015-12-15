import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Cargar el driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// Establecer conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:odbc:EJEMPLO-ACCESS");

			mostrarDatos(conexion);
			// insertarDepartamento(conexion, "Ciencias");
			// mostrarDatos(conexion);

			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void insertarDepartamento(Connection conexion, String nombredep) throws SQLException {

		PreparedStatement insertar = null;
		if (insertar == null) {
			insertar = conexion.prepareStatement("INSERT INTO departamentos (nombre) VALUES (?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			insertar.setString(1, nombredep);
			insertar.executeUpdate();

			// Se obtiene la clave generada
			ResultSet rs = insertar.getGeneratedKeys();
			while (rs.next()) {
				int claveGenerada = rs.getInt(1);
				System.out.println("Insertado departamento con ID = " + claveGenerada);
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
				System.out.println(resul.getInt(1) + "\t" + resul.getString(3));
			}
			System.out.println();
			resul.close();
			sentencia.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
