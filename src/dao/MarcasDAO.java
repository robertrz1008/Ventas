package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utilidades.ConexionDB;
import utilidades.Marcas;

public class MarcasDAO {
	public static void insertarMarcas(Marcas mar){
		String sentencia = "INSERT INTO marcas(mar_descripcion, mar_estado)" + "	VALUES ('"
				+ mar.getDescripcion() + "', '" + mar.isEstado() + "');";
		try {
			ConexionDB.conectar();
			ConexionDB.stm.execute(sentencia);
			ConexionDB.desconectar();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}
	public static void modificarMarcas(Marcas mar) {
		String sentencia = "UPDATE marcas SET  mar_descripcion='" + mar.getDescripcion() + "', mar_estado='" + mar.isEstado() + "'"
				+ "	WHERE mar_id='" + mar.getId() + "'";
		try {
			ConexionDB.conectar();
			ConexionDB.stm.execute(sentencia);
			ConexionDB.desconectar();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}
	public static void EliminarrMarcas(int id) {
		String sentencia = "DELETE FROM marcas WHERE mar_id="+id;
		try {
			ConexionDB.conectar(); 
			ConexionDB.stm.execute(sentencia);
			ConexionDB.desconectar();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}
	
	public static ArrayList<Marcas> buscarMarcas(String pista) {
		String sentencia = "SELECT * FROM marcas WHERE mar_descripcion ILIKE '%"+pista+"%'";
		ConexionDB.conectar();
		ArrayList<Marcas> lista = new ArrayList<>();
		Marcas marca = null;
		try {
			ResultSet res = ConexionDB.stm.executeQuery(sentencia);
			while (res.next()) {
				marca = new Marcas();
				marca.setId(res.getInt("mar_id"));
				marca.setDescripcion(res.getString("mar_descripcion"));
				marca.setEstado(res.getBoolean("mar_estado"));

				lista.add(marca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConexionDB.desconectar();
		return lista;
	}
	
}
