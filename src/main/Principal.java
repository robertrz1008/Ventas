package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import gui.VentanaCliente;
import gui.VentanaMarcas;
import gui.VentanaProducto;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class Principal extends JFrame {
	private JDesktopPane Escritorio;

	public static void main(String[] args) {
					
					try {
						UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					Principal principal = new Principal();
					principal.setVisible(true);
					
					//visulaiza el dialogo login
//					Login login = new Login();
//					login.setModal(true);
//					login.setVisible(true);
			
		};
	

	public Principal() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 1382, 721);
		
		Escritorio = new JDesktopPane();
		
		// MENU
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mnNewMenu = new JMenu("Registros");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuClientes = new JMenuItem("Clientes");
		menuClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// visualiza el internalframe de ventanaCliente
				VentanaCliente vcliente = new VentanaCliente();
				Escritorio.add(vcliente);
				vcliente.show();
			}
		});
		mnNewMenu.add(menuClientes);
		
		JMenuItem menuProductos = new JMenuItem("Productos");
		menuProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProducto vProducto = new VentanaProducto();
				Escritorio.add(vProducto);
				vProducto.show();
			}
		});
		mnNewMenu.add(menuProductos);
		
		JMenuItem menuMarcas = new JMenuItem("Marcas");
		menuMarcas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMarcas vMarcas = new VentanaMarcas();
				Escritorio.add(vMarcas);
				vMarcas.show();
			}
		});
		mnNewMenu.add(menuMarcas);
		
		JMenu mnTransaccion = new JMenu("Transaccion");
		menuBar.add(mnTransaccion);
		
		JMenuItem mntmVentas = new JMenuItem("Ventas");
		mnTransaccion.add(mntmVentas);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmACerca = new JMenuItem("A cerca del Sistema");
		mntmACerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "acerca de este sistema...");
			}
		});
		mnSistema.add(mntmACerca);
		
		// BARRA DE ATAJO
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setIcon(new ImageIcon(new ImageIcon(Principal.class.getResource("/img/clientes.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuClientes.doClick();
			}
		});
		
		JButton btnProductos = new JButton("Productos");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuProductos.doClick();
			}
		});
		
		JButton btnMarcas = new JButton("Marcas");
		btnMarcas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuMarcas.doClick();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 1350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(btnClientes, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnProductos)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnMarcas, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1093, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(Escritorio, GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnClientes, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnProductos, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMarcas, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addComponent(Escritorio, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
}
