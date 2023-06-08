package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Curso;

public class CursosService {
	String url = "jdbc:mysql://localhost:3306/agenda";
	String usuario = "root";
	String password = "root";
	
	public boolean guardarCurso(int codigoCurso, String nombre, double precio, LocalDate fechaInicio, LocalDate fechaFin) {
		
	            try(Connection con = DriverManager.getConnection(url, usuario, password)){
	            	String sql = "insert into cursos values (?,?,?,?,?)";
	    			PreparedStatement ps = con.prepareStatement(sql);
	    			ps.setInt(1, codigoCurso);
	    			ps.setString(2, nombre);
	    			ps.setDouble(3, precio);
	    			ps.setDate(4, Date.valueOf(fechaInicio));
	    			ps.setDate(5, Date.valueOf(fechaFin));
	    			ps.execute();			
	    			return true;
	            }
	            catch(SQLException ex) {
	                ex.printStackTrace();
	                return false;
	            }
	            
	}
	public Curso buscarCurso(int codigo) {
        
        Curso res=null;
        String sql ="select * from contactos where codigoCurso=?";
        //Localizamos contacto en el fichero
		try (Connection con = DriverManager.getConnection(url, usuario, password)) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery(sql);
			if (rs.next()) {
				res = new Curso(rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getInt("precio"),
						rs.getDate("fechaIncio").toLocalDate(), rs.getDate("fechaFin").toLocalDate());

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return res;
	}
	
	public void agregarCursos(List<Curso> cursos) {
		try(Connection con = DriverManager.getConnection(url, usuario, password)){
			con.setAutoCommit(false); //desactivamos autocommit para que todos los execute estén en la misma transacción
        	String sql = "insert into cursos values (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			for (Curso c:cursos) {
			ps.setInt(1, c.getCodigoCurso());
			ps.setString(2, c.getNombre());
			ps.setDouble(3, c.getPrecio());
			ps.setDate(4, Date.valueOf(c.getFechaInicio()));
			ps.setDate(5, Date.valueOf(c.getFechaFin()));
			ps.execute();
			}
			con.commit();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
	}


	
	public void eliminarCurso(int codigo) {
		try (Connection con = DriverManager.getConnection(url, usuario, password)){
			String sql = "delete from contactos where codigoCurso=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public List<Curso> cursosPrecioMax(double max) {
		 List<Curso> cursos= new ArrayList<>();
	        
	        
	        //Buscar cursos que superen el precio dado
	        try(Connection con = DriverManager.getConnection(url, usuario, password)){
	        	String sql = "select * from contactos where precio >=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setDouble(1, max);
				ResultSet rs = ps.executeQuery();
	                    while(rs.next()) {
	                    	cursos.add(new Curso (rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getDouble("precio"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate()));
	                    }
	            return cursos;
	        }catch(SQLException ex) {
	            ex.printStackTrace();
		        return cursos;

	        }                
	        	        
	}
	public List<Curso> cursosDuracionMax(int max){
		 List<Curso> cursos= new ArrayList<>();
	        
	        
	        //Buscar cursos que superen el precio dado
	        try(Connection con = DriverManager.getConnection(url, usuario, password)){
	        	String sql = "select * from contactos where duracion >=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setDouble(1, max);
				ResultSet rs = ps.executeQuery();
	                    while(rs.next()) {
	                    	cursos.add(new Curso (rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getDouble("precio"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate()));
	                    }
	            return cursos;
	        }catch(SQLException ex) {
	            ex.printStackTrace();
		        return cursos;

	        }    
	}
	public List<Curso> cursosFinalizados(){        
		 List<Curso> cursos= new ArrayList<>();
	        
	        
	        //Buscar cursos que superen el precio dado
	        try(Connection con = DriverManager.getConnection(url, usuario, password)){
	        	String sql = "select * from contactos where fechaFin <=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setDate(1, Date.valueOf(LocalDate.now()));
				ResultSet rs = ps.executeQuery();
	                    while(rs.next()) {
	                    	cursos.add(new Curso (rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getDouble("precio"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate()));
	                    }
	            return cursos;
	        }catch(SQLException ex) {
	            ex.printStackTrace();
		        return cursos;

	        }             
  	}
	public Curso cursoLargo() {
//		Curso caux=null;
//		Period paux=Period.of(0, 0, 0);
//		try(Connection con = DriverManager.getConnection(url, usuario, password)){
//        	String sql = "select * from contactos where curso =?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setObject(1, caux);
//			ResultSet rs = ps.executeQuery();
//			Curso cr = new Curso(rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getDouble("precio"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate());
//			if(Period.between(cr.getFechaInicio(), cr.getFechaFin()).toTotalMonths()>paux.toTotalMonths()) {
//                caux=cr;
//                paux=Period.between(cr.getFechaInicio(), cr.getFechaFin());
//			}
//			return caux;
//        }catch(SQLException ex) {
//            ex.printStackTrace();
//	        return caux;
//
//        }   
		Curso curso = null;
		
		try (Connection con = DriverManager.getConnection(url, usuario, password)){
			String sql = "SELECT * FROM curso ORDER BY DATEDIFF (fechaInicio, fechaFin) LIMIT 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, curso);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return curso = new Curso (rs.getInt("codigoCurso"), rs.getString("nombre"), rs.getDouble("precio"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("fechaFin").toLocalDate());

			}
		}catch(SQLException ex) {
          ex.printStackTrace();
		}  
		return curso;
	}
}
