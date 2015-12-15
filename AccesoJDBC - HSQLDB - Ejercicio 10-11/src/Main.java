import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Cargar el driver
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			// Establecer conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");

			// Ejercicio 10

			// Empleado emp = new Empleado(2, "Ortega", "Informatico", 1, 1000,
			// 10, 2);
			// Empleado emp = new Empleado(3, "Mancera", "Informatico", 1, 1000,
			// 10, 3); //Caso de fallo: No existe el departamento
			// Empleado emp = new Empleado(2, "Mancera", "Informatico", 1, 1000,
			// 10, 2); //Caso de fallo: El empleado existe
			// Empleado emp = new Empleado(3, "Mancera", "Informatico", 1, 0,
			// 10, 2); //Caso de fallo: Salario 0
			// Empleado emp = new Empleado(4, "Mancera", "Informatico", 4, 1000,
			// 10, 2); //Caso de fallo: El director no existe
			// insertarComprobando(conexion, emp);

			// Ejercicio 11
			
			
			mostrarDepartamento(conexion, 2);
			System.out.println();
			infoDepartamento(conexion, 2);

			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertarComprobando(Connection conexion, Empleado emp) {
		ArrayList<Integer> departamentos = numDepartamentos(conexion);
		ArrayList<Integer> empleados = numEmpleados(conexion);
		if (departamentos.contains(emp.getDept_no())) {
			if (empleados.contains(emp.getEmp_no())) {
				System.out.println("No puede insertarse ese empleado, ya existe uno con ese número");
			} else {
				if (emp.getSalario() <= 0) {
					System.out.println("El salario no puede ser 0 o inferior");
				} else {
					if (empleados.contains(emp.getDir())) {
						insertarEmpleado(conexion, emp);
					} else {
						System.out.println("No existe el id de ese director");
					}
				}
			}
		} else {
			System.out.println("No existe el departamento al que pertenece el empleado");
		}
	}

	private static void insertarEmpleado(Connection conexion, Empleado emp) {

		PreparedStatement insertar = null;
		try {
			if (insertar == null) {
				insertar = conexion.prepareStatement("INSERT INTO EMPLEADOS VALUES (?, ?, ?, ?, ?, ?, ?)");
				insertar.setInt(1, emp.getEmp_no());
				insertar.setString(2, emp.getApellido());
				insertar.setString(3, emp.getOficio());
				insertar.setInt(4, emp.getDir());
				insertar.setInt(5, emp.getSalario());
				insertar.setInt(6, emp.getSalario());
				insertar.setInt(7, emp.getDept_no());
				insertar.executeUpdate();
				System.out.println("Insertado correctamente");
				conexion.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static ArrayList<Integer> numEmpleados(Connection conexion) {

		ArrayList<Integer> empleados = new ArrayList<Integer>();
		try {
			// Preparar la consulta
			Statement sentencia = conexion.createStatement();
			ResultSet resul = sentencia.executeQuery("SELECT EMP_NO FROM EMPLEADOS");
			while (resul.next()) {
				empleados.add(resul.getInt(1));
			}
			resul.close();
			sentencia.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empleados;
	}

	private static ArrayList<Integer> numDepartamentos(Connection conexion) {

		ArrayList<Integer> departamentos = new ArrayList<Integer>();
		try {

			// Preparar la consulta
			Statement sentencia = conexion.createStatement();
			ResultSet resul = sentencia.executeQuery("SELECT ID FROM departamentos");
			while (resul.next()) {
				departamentos.add(resul.getInt(1));
			}
			resul.close();
			sentencia.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return departamentos;
	}

	private static void infoDepartamento(Connection conexion, int numdep) {

		PreparedStatement mostrar = null;
		try {
			if (mostrar == null) {

				String query = "SELECT DEPARTAMENTOS.NOMBRE, AVG(EMPLEADOS.SALARIO) AS SALARIO_MEDIO, COUNT(EMPLEADOS.EMP_NO) AS NUM_EMPLEADOS"
						+ " FROM EMPLEADOS, DEPARTAMENTOS" + " WHERE EMPLEADOS.DEPT_NO = DEPARTAMENTOS.ID"
						+ " AND DEPARTAMENTOS.ID = ?" + " GROUP BY DEPARTAMENTOS.NOMBRE";

				mostrar = conexion.prepareStatement(query);
				mostrar.setInt(1, numdep);

				ResultSet rs = mostrar.executeQuery();

				System.out.println("Nombre   " + "\t" + "Salario Medio" + "   \t" + "Nº Empleados");
				System.out.println("--------   " + "\t" + "-------------" + "      \t" + "------------");
				
				while(rs.next()){
					System.out.println(rs.getString(1) + "   \t" + rs.getInt(2) + "      \t        " + rs.getInt(3));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void mostrarDepartamento(Connection conexion, int numdep) {
		PreparedStatement mostrar = null;
		try {
			if (mostrar == null) {
				String query = "SELECT EMPLEADOS.APELLIDO, EMPLEADOS.SALARIO, EMPLEADOS.OFICIO"
						+ " FROM EMPLEADOS, DEPARTAMENTOS" + " WHERE EMPLEADOS.DEPT_NO = DEPARTAMENTOS.ID"
						+ " AND EMPLEADOS.DEPT_NO = ?";

				mostrar = conexion.prepareStatement(query);
				mostrar.setInt(1, numdep);

				ResultSet rs = mostrar.executeQuery();

				System.out.println("Apellido   " + "\t" + "Salario" + "   \t" + "Oficio");
				System.out.println("--------   " + "\t" + "-------" + "   \t" + "------");

				while (rs.next()) {
					System.out.println(rs.getString(1) + "   \t" + rs.getInt(2) + "    \t" + rs.getString(3));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
