package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import Componentes.TextField;
import dao.ProductosDAO;
import utilidades.Productos;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaProducto extends JInternalFrame {
	private TextField tfDescripcion;
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable table;
	private TextField tfCodigoBarra;
	private TextField tfPrecioVenta;
	private TextField tfBuscar;
	private TextField tfPrecioCompra;


	public VentanaProducto() {
		setTitle("Registros de Productos");
		setMaximizable(true); 
		setIconifiable(true);
		setClosable(true); 
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		
		TextField txtfldId = new TextField();
		txtfldId.setLabelText("Id");
		txtfldId.setBounds(292, 11, 50, 44);
		getContentPane().add(txtfldId);
		
		tfDescripcion = new TextField();
		tfDescripcion.setLabelText("Descripcion");
		tfDescripcion.setBounds(10, 11, 273, 44);
		getContentPane().add(tfDescripcion);
		
		tfCodigoBarra = new TextField();
		tfCodigoBarra.setLabelText("CodigoBarra");
		tfCodigoBarra.setBounds(10, 66, 332, 44);
		getContentPane().add(tfCodigoBarra);
		
		tfPrecioCompra = new TextField();
		tfPrecioCompra.setLabelText("PrecioCompra");
		tfPrecioCompra.setBounds(10, 121, 332, 44);
		getContentPane().add(tfPrecioCompra);
		
		tfPrecioVenta = new TextField();
		tfPrecioVenta.setLabelText("PrecioVenta");
		tfPrecioVenta.setBounds(10, 176, 332, 44);
		getContentPane().add(tfPrecioVenta);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadosComponentes(true);
			}
		});
		btnNuevo.setBounds(10, 231, 103, 60);
		getContentPane().add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
				estadosComponentes(false);
			}
		});
		btnGuardar.setBounds(123, 231, 106, 60);
		getContentPane().add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadosComponentes(false);
				limpiarFormulario();
			}
		});
		btnCancelar.setBounds(239, 231, 103, 60);
		getContentPane().add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(352, 66, 422, 435);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Descripcion", "CgoBarra", "PrecioCompra", "PrecioVenta"
				
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(78);
		scrollPane.setViewportView(table);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(669, 505, 105, 54);
		getContentPane().add(btnEliminar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(549, 505, 113, 54);
		getContentPane().add(btnModificar);
		
		tfBuscar = new TextField();
		tfBuscar.setLabelText("Buscar");
		tfBuscar.setBounds(352, 16, 422, 39);
		getContentPane().add(tfBuscar);
		
		estadosComponentes(false);
	}

	private void limpiarFormulario() {
		tfDescripcion.setText("");
		tfCodigoBarra.setText("");
	}
	
	private boolean validacion() {
		if(tfDescripcion.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "El campo Descripcion es obligatorio", "Informacion incompleta", JOptionPane.WARNING_MESSAGE);
			return false;
		}else if (tfCodigoBarra.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "El campo Codigo Barra es obligatorio", "Informacion incompleta", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void estadosComponentes(boolean estado) {
		tfDescripcion.setEnabled(estado);
		tfCodigoBarra.setEnabled(estado);
		
		if(estado){
			btnNuevo.setEnabled(false);
			btnGuardar.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnModificar.setEnabled(true);
			btnEliminar.setEnabled(true);
			
			tfBuscar.setEnabled(true);
		}else{
			limpiarFormulario();
			
			btnNuevo.setEnabled(true);
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
			btnModificar.setEnabled(false);
			btnEliminar.setEnabled(false);
			
			tfBuscar.setEnabled(true);
		}
	}
	
	private void guardar() {
		Productos productos = new Productos();
		productos.setDescripcion(tfDescripcion.getText());
		productos.setCodigoBarra(tfCodigoBarra.getText());
		
		if(validacion()){
			ProductosDAO.insertarProductos(productos);
			limpiarFormulario();
		}
	}
}


