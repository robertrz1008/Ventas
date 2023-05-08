package main;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JDialog {
	private JTextField tfUsuario;
	private JPasswordField tfPasword;
	private JButton btnIniciarSesin;
    private String usuario = "roberto";
    private String passwor = "1331";
	

	public Login() {
		setBounds(100, 100, 300, 250);
		//indica la position que se posicionara relativo a otra ventana jframe
		setLocationRelativeTo(null);//en el centro de la ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		//titulo de ventana
		setTitle("Acceso al sistema");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		getContentPane().setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(34, 52, 58, 23);
		getContentPane().add(lblUsuario);

		tfUsuario = new JTextField();
		tfUsuario.setBounds(102, 52, 155, 23);
		getContentPane().add(tfUsuario);
		tfUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(10, 101, 82, 23);
		getContentPane().add(lblContrasea);

		tfPasword = new JPasswordField(); // txfiel como pasword
		tfPasword.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {// escucador de evento de teclado
				if(e.getKeyCode() == KeyEvent.VK_ENTER){ // si se preciona enter 
					btnIniciarSesin.doClick(); // secliquea el boton este boton
				}
			}
		});
		tfPasword.setBounds(103, 102, 154, 22);
		getContentPane().add(tfPasword);

		btnIniciarSesin = new JButton("Iniciar sesi\u00F3n");
		btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarsecion();
			}
		});
		btnIniciarSesin.setBounds(102, 151, 144, 33);
		getContentPane().add(btnIniciarSesin);

	}
	
	private void iniciarsecion() {
		
		if (tfUsuario.getText().equals(usuario) && new String(tfPasword.getPassword()).equals(passwor)) {
			dispose(); // cierra la ventana
			JOptionPane.showMessageDialog(null, "Bienvenido "+usuario+"!!");
		}else {																							//titulo    			//tipo de la joption
			JOptionPane.showMessageDialog(null, "Los datos de usuario y/o contraseña no son correctos", "Credencial incorrecta", JOptionPane.ERROR_MESSAGE);
		}

	}
}
