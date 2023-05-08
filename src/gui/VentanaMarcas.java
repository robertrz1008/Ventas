package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Componentes.TextField;
import dao.ClientesDAO;
import dao.MarcasDAO;
import utilidades.Clientes;
import utilidades.Marcas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class VentanaMarcas extends JInternalFrame {
	private TextField tfDescripcion;
	private TextField tfId;
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private TextField tfBuscar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private DefaultTableModel modeloTabla = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Descripcion", "Estado"
			}
		);
	private JScrollPane scrollPane;
	private JTable tbMoarcas;
	private JRadioButton rActivo;
	private JRadioButton rInactivo;
	private ArrayList<Marcas> lista = null; 
	
	
	//CONSTRUCTOR
	public VentanaMarcas() {
		setTitle("Registros de Marcas");
		setMaximizable(true); // PANTALLA COMPLETA
		setIconifiable(true);
		setClosable(true); //SE PEEDE CERRAR
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		
		tfDescripcion = new TextField();
		tfDescripcion.setBounds(13, 66, 342, 45);
		tfDescripcion.setLabelText("Descripcion");
		getContentPane().add(tfDescripcion);
		
		tfId = new TextField();
		tfId.setBounds(13, 11, 342, 44);
		tfId.setLabelText("Id");
		tfId.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfId);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(13, 199, 103, 60);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadoComponentes(true);
			}
		});
		getContentPane().add(btnNuevo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(242, 199, 103, 60);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadoComponentes(false);
			}
		});
		getContentPane().add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(126, 199, 106, 60);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		getContentPane().add(btnGuardar);
		
		tfBuscar = new TextField();
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscar();
			}
		});
		tfBuscar.setBounds(365, 11, 409, 44);
		tfBuscar.setLabelText("Buscar por descripcion");
		getContentPane().add(tfBuscar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadoComponentes(true);
			}
		});
		btnModificar.setBounds(555, 462, 106, 60);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnEliminar.setBounds(671, 462, 103, 60);
		getContentPane().add(btnEliminar);
		
		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setBounds(13, 145, 46, 14);
		getContentPane().add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(375, 58, 409, 393);
		getContentPane().add(scrollPane);
		
		tbMoarcas = new JTable();
		tbMoarcas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarFormulario();
			}
		});		tbMoarcas.setModel(modeloTabla);
		scrollPane.setViewportView(tbMoarcas);
		
		rActivo = new JRadioButton("Activo");
		rActivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rActivo.isSelected()){
					rActivo.setSelected(true);
					rInactivo.setSelected(false);
				}else{
					rActivo.setSelected(false);
					rActivo.setSelected(true);
				}
			}
		});
		rActivo.setBounds(66, 141, 109, 23);
		getContentPane().add(rActivo);
		
		rInactivo = new JRadioButton("Inactivo");
		rInactivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rInactivo.isSelected()){
					rActivo.setSelected(false);
					rInactivo.setSelected(true);
				}else{
					rActivo.setSelected(true);
					rInactivo.setSelected(false);
				}
			}
		});
		rInactivo.setBounds(66, 167, 109, 23);
		getContentPane().add(rInactivo);
		
		estadoComponentes(false);
	}
	
	private boolean validacion() {
		if(tfDescripcion.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "El campo Descripcion es obligatorio", "Informacion incompleta", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void guardar() { 
		Marcas mar = new Marcas();
		mar.setDescripcion(tfDescripcion.getText());
		mar.setEstado(rActivo.isSelected());
		
		if(validacion() && tfId.getText().isEmpty()){
			MarcasDAO.insertarMarcas(mar);
			btnCancelar.doClick();
		} else{
			mar.setId(Integer.parseInt(tfId.getText()));
			MarcasDAO.modificarMarcas(mar);
		}
		btnCancelar.doClick();
	}
	
	private void estadoComponentes(boolean estado) {
		tfId.setEnabled(false);
		tfDescripcion.setEnabled(estado);
		rActivo.setEnabled(estado);
		rInactivo.setEnabled(estado);
		
		if(estado){
			tfBuscar.setEnabled(false);
			tbMoarcas.setEnabled(false);
			
			btnNuevo.setEnabled(false);
			btnGuardar.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnModificar.setEnabled(true);
			btnEliminar.setEnabled(true);
		}else{
			 buscar();
			tfId.setText("");
			tfDescripcion.setText("");
			rActivo.setSelected(false);
			rInactivo.setSelected(false);
			
			btnNuevo.setEnabled(true);
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
			btnModificar.setEnabled(false);
			btnEliminar.setEnabled(false);
			
			tfBuscar.setEnabled(true);
			tbMoarcas.setEnabled(true);
		}
	}

	private void cargarFormulario() {
		if(tbMoarcas.getSelectedColumn() > -1){
			Marcas mar = lista.get(tbMoarcas.getSelectedRow());
			tfId.setText(mar.getId() + "");
			tfDescripcion.setText(mar.getDescripcion());
			if (mar.isEstado()) {
				rActivo.setSelected(true);
				rInactivo.setSelected(false);
			} else {
				rActivo.setSelected(false);
				rInactivo.setSelected(true);
			}
			
			btnModificar.setEnabled(true);
			btnEliminar.setEnabled(true);
			btnCancelar.setEnabled(true);

			btnNuevo.setEnabled(false);
			System.out.println("cargar formulaio");
		}
	}
	
	private void eliminar() {
		int respuesta = JOptionPane.showConfirmDialog(null, "�Est� seguro que desea eliminar este registro?",
				"Pregunta", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {// pregunta si hizo click en 'SI'
			Marcas mar = lista.get(tbMoarcas.getSelectedRow());
			MarcasDAO.EliminarrMarcas(mar.getId());
			btnCancelar.doClick();
		}
	}

	private void buscar() {
		lista = MarcasDAO.buscarMarcas(tfBuscar.getText());
		// "ID", "C�DULA", "NOMBRE", "APELLIDO", "TEL�FONO"
		while (modeloTabla.getRowCount() > 0) {
			modeloTabla.removeRow(0);
		}
		for (int i = 0; i < lista.size(); i++) {
			String estado = "";
			Marcas mar = lista.get(i);
			if(mar.isEstado()){
				estado = "activo";
			}else{
				estado = "Inactivo";
			}
			modeloTabla.addRow(new Object[] { mar.getId(), mar.getDescripcion(), estado});
		}
	}
	
	private void stringEstado() {

	}
}

