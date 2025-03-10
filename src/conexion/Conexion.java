package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	String sURL = "jdbc:mysql://localhost:3306/tienda";
	Connection cx=null;
	
	public Connection conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cx=DriverManager.getConnection(sURL, "root", "");
			System.out.print("Conexion exitosa\n");
			//JOptionPane.showMessageDialog(null, "Conexion Exitosa");
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		return cx;
		
	}
	
	public void desconectar() {
		try {
			cx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	public static void main(String [] args) {
		Conexion cx=new Conexion();
		cx.conectar();
	}
}
