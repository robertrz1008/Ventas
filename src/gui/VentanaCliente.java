package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import utilidades.Clientes;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Componentes.TextField;
import dao.ClientesDAO;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaCliente extends JInternalFrame {
	private TextField tfCedula;
	private TextField tfNombre;
	private TextField tfApellido;
	private TextField tfTelefono;
	private TextField tfID;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JTable tbClientes;
	private DefaultTableModel modeloTabla = new DefaultTableModel(new Object[][] {},
			new String[] { "ID", "C�DULA", "NOMBRE", "APELLIDO", "TEL�FONO" });
	private JScrollPane scrollPane;
	private TextField tfBuscar;
	private JButton btnModificar;
	private JButton btnEliminar;

	private ArrayList<Clientes> lista = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCliente frame = new VentanaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaCliente() {
		setTitle("Registro de clientes");
		setVisible(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);

		tfCedula = new TextField();
		tfCedula.setLabelText("C�dula");
		tfCedula.setBounds(10, 11, 255, 45);
		getContentPane().add(tfCedula);

		tfNombre = new TextField();
		tfNombre.setLabelText("Nombre");
		tfNombre.setBounds(10, 71, 360, 45);
		getContentPane().add(tfNombre);

		tfApellido = new TextField();
		tfApellido.setLabelText("Apellido");
		tfApellido.setBounds(10, 135, 360, 45);
		getContentPane().add(tfApellido);

		tfTelefono = new TextField();
		tfTelefono.setLabelText("Tel�fono");
		tfTelefono.setBounds(10, 189, 360, 45);
		getContentPane().add(tfTelefono);

		tfID = new TextField();
		tfID.setLabelText("ID");
		tfID.setBounds(275, 11, 95, 45);
		getContentPane().add(tfID);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadoComponentes(true);
			}
		});
		btnNuevo.setBounds(9, 244, 115, 60);
		getContentPane().add(btnNuevo);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadoComponentes(false);
			}
		});
		btnCancelar.setBounds(255, 245, 115, 60);
		getContentPane().add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnGuardar.setBounds(130, 245, 115, 60);
		getContentPane().add(btnGuardar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(381, 71, 403, 401);
		getContentPane().add(scrollPane);

		tbClientes = new JTable();
		tbClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarFormulario();
			}
		});
		scrollPane.setViewportView(tbClientes);
		tbClientes.setModel(modeloTabla);

		tfBuscar = new TextField();
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscar();
			}
		});
		tfBuscar.setLabelText("Buscar");
		tfBuscar.setBounds(380, 11, 404, 45);
		getContentPane().add(tfBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadoComponentes(true);
			}
		});
		btnModificar.setBounds(381, 483, 115, 60);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnEliminar.setBounds(669, 483, 115, 60);
		getContentPane().add(btnEliminar);

		estadoComponentes(false);
	}

	private void eliminar() {
		int respuesta = JOptionPane.showConfirmDialog(null, "�Est� seguro que desea eliminar este registro?",
				"Pregunta", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {// pregunta si hizo click en 'SI'
			Clientes cli = lista.get(tbClientes.getSelectedRow());
			ClientesDAO.eliminarCliente(cli.getId());
		 	btnCancelar.doClick();
		}
	}

	private void cargarFormulario() {
		if (tbClientes.getSelectedRow() > -1) { // PREGUNTA SI
			Clientes cli = lista.get(tbClientes.getSelectedRow()); // SI EL USUARIO SELECCION�
			tfID.setText(cli.getId() + "");
			tfNombre.setText(cli.getNombre()); // AL MENOS UNA FILA
			tfApellido.setText(cli.getApellido());
			tfTelefono.setText(cli.getTelefono());
			tfCedula.setText(cli.getCedula());

			btnModificar.setEnabled(true);
			btnEliminar.setEnabled(true);
			btnCancelar.setEnabled(true);

			btnNuevo.setEnabled(false);
		}
	}

	private void buscar() {
		lista = ClientesDAO.buscarCliente(tfBuscar.getText());
		// "ID", "C�DULA", "NOMBRE", "APELLIDO", "TEL�FONO"
		while (modeloTabla.getRowCount() > 0) {
			modeloTabla.removeRow(0);
		}
		for (int i = 0; i < lista.size(); i++) {
			Clientes clie = lista.get(i);
			modeloTabla.addRow(new Object[] { clie.getId(), clie.getCedula(), clie.getNombre(), clie.getApellido(),
					clie.getTelefono() });
		}
	}

	private boolean validarDatos() {

		if (tfCedula.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo c�dula es obligatorio", "Informaci�n incompleta",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private void estadoComponentes(boolean estado) {
		tfID.setEnabled(false);
		tfCedula.setEnabled(estado);
		tfApellido.setEnabled(estado);
		tfNombre.setEnabled(estado);
		tfTelefono.setEnabled(estado);

		if (estado) {// CUANDO SE HABILITA COMPONENTES
			tfBuscar.setEnabled(false);
			tbClientes.setEnabled(false);

			btnNuevo.setEnabled(false);
			btnGuardar.setEnabled(true);
			btnCancelar.setEnabled(true);
		} else {// RESPONDE A FALSE (CUANDO SE DESHAB. COMPONENTES.)
			buscar();
			tfID.setText("");
			tfCedula.setText("");
			tfNombre.setText("");
			tfApellido.setText("");
			tfTelefono.setText("");

			btnNuevo.setEnabled(true);
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);

			btnModificar.setEnabled(false);
			btnEliminar.setEnabled(false);

			tfBuscar.setEnabled(true);
			tbClientes.setEnabled(true);
		}

	}

	private void guardar() {
		Clientes cli = new Clientes();
		cli.setApellido(tfApellido.getText());
		cli.setNombre(tfNombre.getText());
		cli.setCedula(tfCedula.getText());
		cli.setTelefono(tfTelefono.getText());

		if (validarDatos()) {
			if (tfID.getText().isEmpty()) {
				ClientesDAO.insertarCliente(cli);
			} else {
				cli.setId(Integer.parseInt(tfID.getText()));
				ClientesDAO.modificarCliente(cli);// modifica
			}
			btnCancelar.doClick();
		}

	}
}
