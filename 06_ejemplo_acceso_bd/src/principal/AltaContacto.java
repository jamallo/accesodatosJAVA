package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AltaContacto {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/agenda";
		String usuario = "root";
		String password = "root";
		try (Connection con = DriverManager.getConnection(url, usuario, password)){
			String sql ="insert into contactos (nombre, email, edad)";
			sql += "values ('cNuevo','eNuevo',40)";
			Statement st = con.createStatement();
			st.execute(sql);
			System.out.println("Contacto añadido!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
