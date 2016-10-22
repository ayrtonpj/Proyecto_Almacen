package cjava.prueba;

import java.sql.Connection;

import cjava.database.AccesoDB;

public class Prueba {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// prueba de coexion a la base de datos
		 Connection cn;
	        try {
	            cn= AccesoDB.getConnection();
	            System.out.println("Conexion conforme...");
	        } catch (Exception e) {
	            System.out.println("Error :" + e.getMessage());
	        }

	}

}
