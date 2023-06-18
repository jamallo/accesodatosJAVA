package servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;

public class PedidosService {
	
	final static String URL = "jdbc:mysql://localhost:3306/pedidos";
	final static String USUARIO = "root";
	final static String PASSWORD = "root";
	
	public static List<Pedido> filtroTiendaDB (String tienda) {
		List <Pedido> pedidos = new ArrayList<>();
		String sql = "select * from pedidos where tienda=?";
		//Localizo la tienda en el fichero
		try {
			Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD );
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tienda);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pedidos.add(new Pedido(rs.getInt("idPedido"), 
						rs.getString("producto"), 
						rs.getInt("unidades"), 
						rs.getDate("fecha").toLocalDate(), 
						rs.getString("tienda")));
				
			} return pedidos;
		} catch (SQLException ex) {
				ex.printStackTrace();
				return pedidos;
		}
	
	}

}
