package dao;

import java.sql.SQLException;

import utilidades.ConexionDB;
import utilidades.Productos;

public class ProductosDAO {
	public static void insertarProductos(Productos producto){
		String sentencia = "INSERT INTO productos(pro_description, pro_codigobarra)"
				+ "VALUES ('" + producto.getDescripcion()+"', '" + producto.getCodigoBarra()+"')";
		ConexionDB.conectar();
		try {
			ConexionDB.stm.execute(sentencia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConexionDB.desconectar();
	}

}
