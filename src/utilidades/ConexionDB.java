package utilidades;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import dao.ClientesDAO;

public class ConexionDB {
	
	public static String host = "localhost";
	public static String puerto = "5432";
	public static String usuario = "postgres";
	public static String password = "horizon.20";
	public static String dataBase = "ventas";
	
	public static Connection conexion;//atributo para establecer la conectioncon la db
	public static Statement stm; 
	
	public static void conectar() {
		try {
			conexion = DriverManager. getConnection("jdbc:postgresql://"+host+"/"+dataBase, usuario, password);
			stm = conexion.createStatement();
			System.out.println("CONTECCION EXITOSA!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void desconectar() {
		try {
			conexion.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		conectar();
	}
	
	
}
