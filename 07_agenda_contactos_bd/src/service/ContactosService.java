package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.Contacto;

public class ContactosService {
	String url = "jdbc:mysql://localhost:3306/agenda";
	String usuario = "root";
	String password = "root";
	
	
	public boolean guardarContacto(Integer numero, String nombre, String email, int edad) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)){
//			String sql ="insert into contactos (telefono, nombre, email, edad)";
//			sql += "values ("+ numero + ",'" + nombre +"','" + email + "'," + edad +")";
//			Statement st = con.createStatement();
//			st.execute(sql);
			String sql = "insert into contactos(telefono, nombre, email, edad) values (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, numero);
			ps.setString(2, nombre);
			ps.setString(3, email);
			ps.setInt(4, edad);
			ps.execute();			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
			
		
	}
	public Contacto buscarContacto(Integer numero) {
		Contacto cont = null;
		String sql ="select * from contactos where telefono=?";
		try (Connection con = DriverManager.getConnection(url, usuario, password)){			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, numero);
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()) {
				cont = new Contacto(rs.getInt("telefono"), rs.getString("nombre"), rs.getString("email"), rs.getInt("edad"));
				
				
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return cont;
		
		
	}
	public void eliminarContacto(Integer numero) {
		
		try (Connection con = DriverManager.getConnection(url, usuario, password)){
//			String sql ="delete from contactos";
//			sql += "where telefono=" + numero;
//			Statement st = con.createStatement();
//			st.execute(sql);
			String sql = "delete from contactos where telefono=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, numero);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
        
    }
	
	public List<Contacto> contactos() {
		List <Contacto> lista = new ArrayList <>();
		try (Connection con = DriverManager.getConnection(url, usuario, password)){
			String sql ="select * from contactos";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				lista.add(new Contacto (rs.getInt("telefono"), rs.getString("nombre"), rs.getString("email"), rs.getInt("edad")));
				//getInt("telefono"), rs.getString("nombre"), rs.getString("email"), rs.getInt("edad")
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}	
	
}
