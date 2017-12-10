package ch.labegg.lutebox.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyHelper {

	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:zadb;create=true";
	
	public static boolean tableAlreadyExists(SQLException e) {
		return (e.getSQLState().equals("X0Y32"));
	}
	
	public static boolean tableDoesntExist(SQLException e) {
		return (e.getSQLState().equals("42Y55"));
	}


}
