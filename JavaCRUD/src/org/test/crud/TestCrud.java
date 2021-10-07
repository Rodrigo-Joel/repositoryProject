package org.test.crud;
/*
 * CRUD: CREATE, READ, UPDATE, DELETE
 */

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestCrud {
	
	private static Connection connection = null;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectDataBaseOracle() throws IOException,SQLException {
		
		try {
			Class.forName(driver).newInstance();
			System.out.println("CARGO DRIVER: ojdbc6.jar");
		} catch (Exception e) {
			System.out.println("Exception driver: "+e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(URL,"System","Joel1234");
			System.out.println("CONEXION EXITOSA: Oracle 11g");
		} catch (Exception e) {
			System.out.println("Exception connectio:"+e.getMessage());
		}
		
	}
	
	public static void agregarS_Region(int id, String name) throws IOException,SQLException {
		try {
			connectDataBaseOracle();
			
			//QUERIE
			String sql = "INSERT INTO S_REGION (ID,NAME) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ps.setString(2, name);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("AGREGO EL REGISTRO = "+id+", "+name);
			
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}
	}
	
	public static void modificarS_Region(String name, int id) throws IOException,SQLException {
		try {
			connectDataBaseOracle();
			
			//QUERIE
			String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,name);
			ps.setInt(2, id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("MODIFICO EL REGISTRO = "+id+", "+name);
			
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}
	}
	
	public static void eliminarS_Region(int id) throws IOException,SQLException {
		try {
			connectDataBaseOracle();
			
			//QUERIE
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("ELIMINO EL REGISTRO = "+id);
			
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}
	}
	
	public static void consultarS_Region() throws IOException,SQLException {
		try {
			connectDataBaseOracle();
			
			//QUERIE
			String sql = "SELECT * FROM S_REGION";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				System.out.println(rSet.getInt("id")+","+rSet.getString("name"));
				
			}
			ps.close();
			connection.close();
			
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}
	}
	
	public static void invocarProcedure(int id, String name) throws IOException,SQLException {
		try {
			connectDataBaseOracle();
			
			//QUERIE
			CallableStatement cs= connection.prepareCall("CALL proc(?,?)");
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.executeQuery();
			cs.close();
			connection.close();
			System.out.println("EJECUDATO CORRECTAMENTE EL PROCEDURE PROC");
			
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}
	}
	
	public static void main(String[] args) throws IOException,SQLException {
		//agregarS_Region(15, "EDO. MEX");
		
		//modificarS_Region("UPDATEREG", 1);
		
		//eliminarS_Region(14);
		
		//consultarS_Region();
		
		invocarProcedure(16, "REYNOSA");
	}

}
