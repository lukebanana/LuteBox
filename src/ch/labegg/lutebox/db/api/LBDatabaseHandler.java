package ch.labegg.lutebox.db.api;

import java.sql.ResultSet;

public interface LBDatabaseHandler {

	public ResultSet query(String sqlquery);
	public boolean createTable(String tableName);
	public boolean insertData(String tableName);
	public void closeConnection();
}
