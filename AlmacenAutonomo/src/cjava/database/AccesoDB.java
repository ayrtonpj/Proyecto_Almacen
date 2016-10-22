package cjava.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {

	private static Connection cn = null;

	public static Connection getConnection() throws Exception {

		try {
			if (cn == null) {

				// carga driver en memoria
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				// url de la base de datos
				String url = "jdbc:mysql://localhost:3306/empresa";
				// obtener la conexion a la base de datos
				cn = DriverManager.getConnection(url, "root", "1234");
			}
		} catch (ClassNotFoundException | SQLException ex) {
			throw ex;
		}
		return cn;
	}
	
}
