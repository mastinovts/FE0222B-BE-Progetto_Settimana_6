package it.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static final String URL = "jdbc:postgresql://localhost:5432/bancadb";
	public static final String USER = "postgres";
	public static final String PASS = "postgres";
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("connessione stabilita correttamente");
		}
		catch(SQLException ex){
			System.out.println("connessione NON stabilita");
			ex.printStackTrace();
		}
		return conn;
	}
}
