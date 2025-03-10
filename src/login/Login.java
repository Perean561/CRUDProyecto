package login;

import conexion.Conexion;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vista.vUsuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField Contraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Omen\\eclipse-workspace\\CRUDProyecto\\src\\img\\password.png")); //icono de ventana
		setTitle("Inicio de Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 281);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(235, 243, 231));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Imagen de sesion
		JLabel lblFoto = new JLabel("");
		Image imagenes = new ImageIcon(this.getClass().getResource("/img/storet.png")).getImage(); //Icono grande de sesion
		lblFoto.setIcon(new ImageIcon(imagenes));
		lblFoto.setBounds(20, 42, 135, 161);
		contentPane.add(lblFoto);
		
		//Usuario
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblUsuario.setBounds(179, 86, 80, 17);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(269, 84, 206, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		//contraseña
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblContrasea.setBounds(179, 136, 93, 14);
		contentPane.add(lblContrasea);
		
		Contraseña = new JPasswordField();
		Contraseña.setBounds(270, 133, 205, 20);
		contentPane.add(Contraseña);
		
		JButton btnIniciarSesion = new JButton("Iniciar");
		btnIniciarSesion.setBackground(new Color(255, 255, 255));
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Para el boton que nos llevara a la ventana 2
				if(validar(txtUsuario.getText(),Contraseña.getText())){//los nombres que tengan son nuestras variables
					vUsuario usuario= new vUsuario();
					usuario.setVisible(true);
					//usuario.setExtendedState(JFrame.MAXIMIZED_BOTH);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Usuario o Contraseña erronea intenta de nuevo");
					txtUsuario.setText(null);
					Contraseña.setText(null);
				}
			}
		});
		btnIniciarSesion.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		//Image imagenes1 = new ImageIcon(this.getClass().getResource("/bien.png")).getImage(); //Mini icono para el boton
		//btnIniciarSesion.setIcon(new ImageIcon(imagenes1));
		btnIniciarSesion.setBounds(280, 172, 185, 41);
		contentPane.add(btnIniciarSesion);
	}
	
	private boolean validar(String usuario, String contra) {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = conexion.conectar();
            String sql = "SELECT * FROM empleados WHERE vendedor = ? AND contraseña = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contra);
            rs = ps.executeQuery();

            // Si la consulta encuentra un registro, las credenciales son correctas
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) conexion.desconectar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

	//Metodo para que funcione el password
//	private boolean validar(String usuario, String contra){
//		if(usuario.equals("Brian") && contra.equals("lobos123"))
//			return true;
//		else if(usuario.equals("Luis") && contra.equals("lobos456"))
//			return true;
//		else if(usuario.equals("Grelty") && contra.equals("lobos789"))
//			return true;
//		else 
//			return false;
//	}

}
