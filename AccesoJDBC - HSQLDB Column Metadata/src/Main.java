import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Cargar el driver
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			// Establecer conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");

			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM departamentos");
			ResultSetMetaData rsmd= rs.getMetaData();
			int nColumnas = rsmd.getColumnCount();
			String nula;
			System.out.println("Número de columnas recuperadas: " + nColumnas);
			for(int i = 1; i<= nColumnas; i++){
				System.out.println("Columna " + i + ":");
				System.out.println("  Nombre : " + rsmd.getColumnName(i));
				System.out.println("  Tipo : " + rsmd.getColumnTypeName(i));
				if(rsmd.isNullable(i)==0) nula="NO"; else nula="SI";
				System.out.println("  ¿Puede ser nula? : " + nula);
				System.out.println("  Máximo ancho de la columna : " + rsmd.getColumnDisplaySize(i));
			}
			
			
			// insertarDepartamento(conexion, "Ciencias", 1);
			// mostrarDatos(conexion);
			//
			// mostrarDatos(conexion);

			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertarDepartamento(Connection conexion, String nombredep, int numdep) throws SQLException {

		PreparedStatement insertar = null;
		if (insertar == null) {
			insertar = conexion.prepareStatement("INSERT INTO departamentos (id, nombre) VALUES (?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			insertar.setInt(1, numdep);
			insertar.setString(2, nombredep);
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
