package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gui.VentanaCliente;
import utilidades.Clientes;
import utilidades.ConexionDB;

public class ClientesDAO {

	public static void modificarCliente(Clientes cli) {
		String sentencia = "UPDATE clientes SET cli_cedula='" + cli.getCedula() + "', cli_nombre='" + cli.getNombre()
				+ "', cli_apellido='" + cli.getApellido() + "', cli_telefono='" + cli.getTelefono() + "' WHERE cli_id="
				+ cli.getId();
		System.out.println(sentencia);
		ConexionDB.conectar();
		try {
			ConexionDB.stm.execute(sentencia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConexionDB.desconectar();
	}

	public static void eliminarCliente(int id) {
		String sentencia = "DELETE FROM clientes WHERE cli_id=" + id;
		ConexionDB.conectar();
		try {
			ConexionDB.stm.execute(sentencia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConexionDB.desconectar();
	}

	public static void insertarCliente(Clientes cliente) {
		String sentencia = "INSERT INTO clientes(cli_cedula, cli_nombre, " + "cli_apellido, cli_telefono) VALUES"
				+ " ('" + cliente.getCedula() + "', '" + cliente.getNombre() + "'," + " '" + cliente.getApellido()
				+ "', '" + cliente.getTelefono() + "');";
		ConexionDB.conectar();// establece conexi�n con la bd
		try {
			ConexionDB.stm.execute(sentencia);// ejecuta la sentencia
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConexionDB.desconectar();// Vuelve a desconectarse de la bd
	}

	public static ArrayList<Clientes> buscarCliente(String pista) {
		String sql = "SELECT * FROM clientes WHERE cli_cedula ILIKE '%"+pista+"%' OR cli_nombre ILIKE '%"+pista+"%' OR cli_apellido ILIKE '%"+pista+"%'";
		Clientes cliente = null;
		ArrayList<Clientes> lista = new ArrayList<>();
		ConexionDB.conectar();
		try {
			ResultSet resultadoBD = ConexionDB.stm.executeQuery(sql);
			while (resultadoBD.next()) {
				cliente = new Clientes();
				cliente.setId(resultadoBD.getInt("cli_id"));
				cliente.setCedula(resultadoBD.getString("cli_cedula"));
				cliente.setNombre(resultadoBD.getString("cli_nombre"));
				cliente.setApellido(resultadoBD.getString("cli_apellido"));
				cliente.setTelefono(resultadoBD.getString("cli_telefono"));

				lista.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConexionDB.desconectar();
		return lista;
	}
}