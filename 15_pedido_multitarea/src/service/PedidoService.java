package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;

public class PedidoService extends Thread {
	String url = "jdbc:mysql://localhost:3306/pedidos";
	String usuario = "root";
	String password = "root";
	

	public boolean guardarPedido (Pedido p) {
		try(Connection con = DriverManager.getConnection(url, usuario, password)){
        	String sql = "insert into pedidos values (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, p.getIdPedido());
			ps.setString(2, p.getProducto());
			ps.setInt(3, p.getUnidades());
			ps.setDate(4, Date.valueOf(p.getFecha()));
			ps.setString(5, p.getTienda());
			ps.execute();
			return true;
		} catch(SQLException ex) {
            ex.printStackTrace();
            return false;
		}    
	}
	
//	public List <Pedido> pedidosTienda(String tienda) { 
//        List <Pedido> pedidos = new ArrayList<>();
//        String sql ="select nombre from pedidos where tienda=?";
//        //Localizamos contacto en el fichero
//		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, tienda);
//			ResultSet rs = ps.executeQuery(sql);
//			while (rs.next()) {
//				pedidos.add(new Pedido (rs.getInt("idPedido"), rs.getString("producto"), rs.getInt("unidades"), rs.getDate("fecha").toLocalDate(), rs.getString("tienda")));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			
//		}
//		return pedidos;
//	}
}
