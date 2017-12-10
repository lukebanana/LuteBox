package ch.labegg.lutebox.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import ch.labegg.lutebox.db.api.LBDatabaseHandler;


public class DerbyDB implements LBDatabaseHandler {

	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:zadb;create=true";
	public static final String TABLE_NAME = "personen";
	public static final String SQL_STATEMENT = "SELECT * FROM "+TABLE_NAME;
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
	
	public static void main(String[] args) throws SQLException {
		LBDatabaseHandler db = new DerbyDB();
	
		//db.createTable(TABLE_NAME);
		//db.insertData(TABLE_NAME);
		ResultSet resultSet = db.query(SQL_STATEMENT);
		
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();
		
		for(int x = 1; x<=columnCount; x++) {
			System.out.format("%20s", resultSetMetaData.getColumnName(x) + " | ");
		}
		
		System.out.println();

		while(resultSet.next()) {
			for(int x = 1; x<=columnCount; x++) {
				System.out.format("%20s", resultSet.getString(x) + " | ");
			}
			System.out.println();
		}
		
		db.closeConnection();

	}
	
	public ResultSet query(String sqlquery) {		
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
			return this.connection.createStatement().execute("CREATE TABLE "+tableName+"(anrede VARCHAR(20), name VARCHAR(20), vorname VARCHAR(20))");
		} catch(SQLException e) {
			if(DerbyHelper.tableAlreadyExists(e)) {
				System.out.println("Table already exists");
			}
		}		
		
		return false;
	}
	
	public boolean insertData(String tableName) {

		try {
			return this.connection.createStatement().execute("INSERT INTO "+tableName+" VALUES "
												+ "('Frau', 'Hartmeier', 'Maya'),"
												+ "('Herr', 'Abegg', 'Lukas'),"
												+ "('Herr', 'Schmid', 'Lukas'),"
												+ "('Herr', 'Wayne', 'John'),"
												+ "('Frau', 'Ryder', 'Winona'),"
												+ "('Herr', 'Wettach', 'Marco')");
		} catch (SQLException e) {
			if(DerbyHelper.tableDoesntExist(e)) {
				System.out.println("Cannot insert data into table "+tableName+": Table doesn't exist");
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
