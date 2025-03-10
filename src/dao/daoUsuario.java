package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import modelo.Usuario;

public class daoUsuario {
	
	Conexion cx;
	
	public daoUsuario() {
		cx=new Conexion();
	}
	
	public boolean insertarUsuario(Usuario user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO productos VALUES(null,?,?,?,?,?)");
			ps.setString(1, user.getCodigo());
			ps.setString(2, user.getProducto());
			ps.setString(3, user.getPrecio());
			ps.setString(4, user.getCantidad());
			ps.setString(5, convertirSHA256(user.getPreciop()));
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ArrayList<Usuario> consultaUsuarios(){
		ArrayList<Usuario> lista=new ArrayList<Usuario>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM productos");
			rs=ps.executeQuery();
			while(rs.next()) {
				Usuario user=new Usuario();
				user.setNumero(rs.getInt("numero"));
				user.setCodigo(rs.getString("codigo"));
				user.setProducto(rs.getString("producto"));
				user.setPrecio(rs.getString("precio"));
				user.setCantidad(rs.getString("cantidad"));
				user.setPreciop(rs.getString("preciop"));
				lista.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public boolean eliminarUsuario(int Numero) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM productos WHERE numero=?");
			ps.setInt(1, Numero);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarUsuario(Usuario user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE productos SET codigo=?,producto=?,precio=?,cantidad=?,preciop=? WHERE numero=?");
			ps.setString(1, user.getCodigo());
			ps.setString(2, user.getProducto());
			ps.setString(3, user.getPrecio());
			ps.setString(4, user.getCantidad());
			ps.setString(5, convertirSHA256(user.getPreciop()));
			ps.setInt(6, user.getNumero());
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public String convertirSHA256(String preciop) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {		
			e.printStackTrace();
			return null;
		}
		    
		byte[] hash = md.digest(preciop.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}

}
