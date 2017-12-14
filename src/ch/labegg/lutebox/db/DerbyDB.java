package ch.labegg.lutebox.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import ch.labegg.lutebox.db.api.LBDatabaseHandler;
import ch.labegg.lutebox.model.Lute;
import javafx.collections.ObservableList;

public class DerbyDB implements LBDatabaseHandler {

	public static final String TABLE_NAME = "LUTES";
	private Statement statement = null;
	private Connection connection = null;

	public DerbyDB() {
		try {
			this.connection = DriverManager.getConnection(DerbyHelper.JDBC_URL);
			this.statement = this.connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private ResultSet query(String sqlquery) {		
		try {
			return this.statement.executeQuery(sqlquery);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean createTable(String tableName){
		try {
			this.connection.createStatement().execute("DROP TABLE " + tableName);
		} catch(SQLException e) {
			if(DerbyHelper.tableDoesntExist(e)) {
				System.out.println("Cannot drop table "+tableName+": Table doesn't exist");
			}
		}
		
		try {
			
			return this.connection.createStatement().execute("CREATE TABLE "+tableName+"(lute BLOB, anrede VARCHAR(20), name VARCHAR(20), vorname VARCHAR(20))");
		} catch(SQLException e) {
			if(DerbyHelper.tableAlreadyExists(e)) {
				System.out.println("Table already exists");
			}
		}		
		
		return false;
	}
	
	public boolean insertData(ObservableList<Lute> list) {
		try {
			/*
			
			return this.connection.createStatement().execute("INSERT INTO "+TABLE_NAME+"(lute) VALUES "
												+ "('Frau', 'Hartmeier', 'Maya'),"
												+ "('Herr', 'Abegg', 'Lukas'),"
												+ "('Herr', 'Schmid', 'Lukas'),"
												+ "('Herr', 'Wayne', 'John'),"
												+ "('Frau', 'Ryder', 'Winona'),"
												+ "('Herr', 'Wettach', 'Marco')");
												
												*/
			for(Lute l: list) {
			
				PreparedStatement pstmt = this.connection.prepareStatement("INSERT INTO "+TABLE_NAME+"(lute) VALUES (?)");

			    // set input parameters
			    pstmt.setObject(1, l);
			    pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			if(DerbyHelper.tableDoesntExist(e)) {
				System.out.println("Cannot insert data into table "+TABLE_NAME+": Table doesn't exist");
			}else {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void closeConnection() {
		try {
			if(this.statement != null) { this.statement.close(); }
			if(this.connection != null) { this.connection.close(); }
			
			try {
				DriverManager.getConnection("jdbc:derby:;shutdown=true");			
			} catch(SQLException e) {
				if(DerbyHelper.derbyShutdownError(e)) {
					System.out.println("Shutting down DerbyDB");
				}
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet getAllEntries() {
		return query("SELECT * FROM "+TABLE_NAME);
	}

	
}
