package vista;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.daoUsuario;
import modelo.Usuario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtProducto;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JTextField txtPrecioProveedor;
	private JLabel lblNumero;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnLimpiar;
	private JTable tblUsuarios;
	daoUsuario dao=new daoUsuario();
	DefaultTableModel modelo=new DefaultTableModel();
	ArrayList<Usuario> lista;
	int fila=-1;
	Usuario usuario=new Usuario();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vUsuario frame = new vUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void actualizarTabla() {
		
		while(modelo.getRowCount()>0) {
			modelo.removeRow(0);
		}
		
		lista=dao.consultaUsuarios();
		for(Usuario u:lista) {
			Object user[]=new Object[6];
			user[0]=u.getNumero();
			user[1]=u.getCodigo();
			user[2]=u.getProducto();
			user[3]=u.getPrecio();
			user[4]=u.getCantidad();
			user[5]=u.getPreciop();
			modelo.addRow(user);
		}
		tblUsuarios.setModel(modelo);
	}
	
	public void limpiar() {
		lblNumero.setText("");
		txtCodigo.setText("");
		txtProducto.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
		txtPrecioProveedor.setText("");
		
		
	}


	public vUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/tienda.png")));
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("TIENDA");
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Numero:");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel.setBounds(24, 39, 130, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblCodigo.setBounds(24, 98, 130, 38);
		contentPane.add(lblCodigo);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblProducto.setBounds(24, 152, 130, 38);
		contentPane.add(lblProducto);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblPrecio.setBounds(24, 207, 130, 38);
		contentPane.add(lblPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblCantidad.setBounds(24, 262, 130, 38);
		contentPane.add(lblCantidad);
		
		JLabel lblPrecioProveedor = new JLabel("Precio Proveedor:");
		lblPrecioProveedor.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblPrecioProveedor.setBounds(24, 320, 130, 38);
		contentPane.add(lblPrecioProveedor);
		
		lblNumero = new JLabel("0");
		lblNumero.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNumero.setBounds(164, 39, 130, 38);
		contentPane.add(lblNumero);
		
		txtCodigo = new JTextField();
		txtCodigo.setBackground(new Color(235, 243, 231));
		txtCodigo.setBounds(164, 107, 190, 19);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtProducto = new JTextField();
		txtProducto.setBackground(new Color(235, 243, 231));
		txtProducto.setColumns(10);
		txtProducto.setBounds(164, 161, 190, 19);
		contentPane.add(txtProducto);
		
		txtPrecio = new JTextField();
		txtPrecio.setBackground(new Color(235, 243, 231));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(164, 216, 190, 19);
		contentPane.add(txtPrecio);
		
		txtCantidad = new JTextField();
		txtCantidad.setBackground(new Color(235, 243, 231));
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(164, 271, 190, 19);
		contentPane.add(txtCantidad);
		
		txtPrecioProveedor = new JTextField();
		txtPrecioProveedor.setBackground(new Color(235, 243, 231));
		txtPrecioProveedor.setColumns(10);
		txtPrecioProveedor.setBounds(164, 329, 190, 19);
		contentPane.add(txtPrecioProveedor);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(255, 255, 255));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtCodigo.getText().equals("")||txtProducto.getText().equals("")||txtPrecio.getText().equals("")||txtCantidad.getText().equals("")||txtPrecioProveedor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
						return;
					}
					Usuario user=new Usuario();
					user.setCodigo(txtCodigo.getText());
					user.setProducto(txtProducto.getText());
					user.setPrecio(txtPrecio.getText());
					user.setCantidad(txtCantidad.getText());
					user.setPreciop(txtPrecioProveedor.getText());
					if(dao.insertarUsuario(user)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "FELICIDADES\n"+"Se Agrego Exitosamente El Producto");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR\n"+"No Se Pudo Agregar El Producto");
					}
				} catch (Exception e2){
					JOptionPane.showMessageDialog(null, "ERROR\n"+"No Se Pudo Agregar El Producto");
				}
			}
		});
		btnAgregar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnAgregar.setBounds(186, 471, 145, 32);
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion=JOptionPane.showConfirmDialog(null, "¿Seguro que deseas Eliminar este Producto?", "ELIMINAR PRODUCTO", JOptionPane.YES_NO_OPTION);
					if(opcion==0) {
						if(dao.eliminarUsuario(usuario.getNumero())&&usuario.getNumero()>0) {
							actualizarTabla();
							limpiar();
							JOptionPane.showMessageDialog(null, "FELICIDADES\n"+"Se Elimino El Producto");
						}else {
							JOptionPane.showMessageDialog(null, "ERROR\n"+"No se Pudo Eliminar El Producto");
						}
					}
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR\n"+"No se Pudo Eliminar El Producto");
				}
			}
		});
		btnEliminar.setBackground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnEliminar.setBounds(451, 471, 145, 32);
		contentPane.add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtCodigo.getText().equals("")||txtProducto.getText().equals("")||txtPrecio.getText().equals("")||txtCantidad.getText().equals("")||txtPrecioProveedor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
						return;
					}
					usuario.setCodigo(txtCodigo.getText());
					usuario.setProducto(txtProducto.getText());
					usuario.setPrecio(txtPrecio.getText());
					usuario.setCantidad(txtCantidad.getText());
					usuario.setPreciop(txtPrecioProveedor.getText());
					if(dao.editarUsuario(usuario)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "FELICIDADES\n"+"Se Actualizo Exitosamente El Producto");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR\n"+"No Se Pudo Actualizar El Producto");
					}
				} catch (Exception e2){
					JOptionPane.showMessageDialog(null, "ERROR\n"+"No se Pudo Actualizar El Producto");
				}
			}
		});
		btnActualizar.setBackground(new Color(255, 255, 255));
		btnActualizar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnActualizar.setBounds(720, 471, 145, 32);
		contentPane.add(btnActualizar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBackground(new Color(255, 255, 255));
		btnLimpiar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnLimpiar.setBounds(956, 471, 145, 32);
		contentPane.add(btnLimpiar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(395, 10, 881, 410);
		contentPane.add(scrollPane);
		
		tblUsuarios = new JTable();
		tblUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblUsuarios.getSelectedRow();
				usuario=lista.get(fila);
				lblNumero.setText(""+usuario.getNumero());
				txtCodigo.setText(usuario.getCodigo());
				txtProducto.setText(usuario.getProducto());
				txtPrecio.setText(usuario.getPrecio());
				txtCantidad.setText(usuario.getCantidad());
				txtPrecioProveedor.setText(usuario.getPreciop());
			}
		});
		tblUsuarios.setBackground(new Color(192, 192, 192));
		tblUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblUsuarios);
		modelo.addColumn("NUMERO");
		modelo.addColumn("CODIGO");
		modelo.addColumn("PRODUCTO");
		modelo.addColumn("PRECIO");
		modelo.addColumn("CANTIDAD");
		modelo.addColumn("PRECIO PROVEEDOR");
		actualizarTabla();
	}
}
