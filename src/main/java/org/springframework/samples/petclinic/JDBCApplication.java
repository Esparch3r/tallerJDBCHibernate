package org.springframework.samples.petclinic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetType;



public class JDBCApplication {

	public static void main(String[] args) {
		System.out.println("-------- Test de conexión con MySQL ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No encuentro el driver en el Classpath");
			e.printStackTrace();
			return;
		}

		System.out.println("Driver instalado y funcionando");
		Connection connection = null;
		PreparedStatement statement = null;
		String nombre = "null";
		String apellido = "null";
		String direccion = "null";
		String ciudad = "null";
		String telefono = "null";
		
		
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic","root", "Everis2017");
			if (connection != null)
				System.out.println("Conexión establecida");
			
		    //abrimos la consulta de los clientes

				System.out.println("Conexión establecida");

				Owner o = new Owner();
				o.setFirstName("yo");
				o.setLastName("yo");
				o.setCity("puerto rico");
				o.setAddress("su casa");
				o.setTelephone("951");

				Pet p = new Pet();
				Date d = new Date(8);
				p.setBirthDate(d);
				p.setName("manu");
				
				PetType t = new PetType();
				t.setId(1);
	
				p.setType(t);

				String sql = "insert into owners values ( null, ?, ?, ?, ?, ?)";
				
				statement = connection.prepareStatement(sql);
				statement.setString(1, o.getFirstName());
				statement.setString(2, o.getLastName());
				statement.setString(3, o.getAddress());
				statement.setString(4, o.getCity());
				statement.setString(5, o.getTelephone());
				
				sql = "select id from owners where first_name = '"+o.getFirstName()+"' and last_name = '"+o.getLastName()+"'";
				
				ResultSet rs = statement.executeQuery(sql);
				int id_owner = 0;
				while (rs.next()){
					 id_owner = rs.getInt("id");
				}

				statement.execute();
				
				sql = "insert into pets values (null, ?, ?, ?, ?, ?)";
				statement = connection.prepareStatement(sql);
				statement.setString(1, p.getName());
				statement.setDate(2, (Date) p.getBirthDate());
				statement.setInt(3, 1);
				statement.setInt(4, id_owner);
				statement.setInt(5, 1);
				
				statement.execute();
				
				sql = "delete * from pets where owner_id = "+id_owner;
				
				statement.execute();
				
				sql = "delete * from owner where id = "+id_owner;
				statement.execute();
				
				
				
				
				
//				while (rs.next()){
//					 int id = rs.getInt("id");
//					 String nombre = rs.getString("first_name");
//					 System.out.println(""+id);
//					 System.out.println(""+nombre);
//				}
				
				
			//cerramos la consulta de los clientes
			
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		} finally {
			try {
				if(statement != null)
					connection.close();
			} catch (SQLException se) {
		    	  
		    }
		    try {
		        if(connection != null)
		            connection.close();
		    } catch (SQLException se) {
		         	se.printStackTrace();
		    }
		}
	}

}
