package ch.labegg.lutebox.db.api;

import java.sql.ResultSet;
import java.util.List;

import ch.labegg.lutebox.model.Lute;
import javafx.collections.ObservableList;

public interface LBDerbyDatabaseHandler {

	public boolean createDB(String dbName);
	public boolean insertData(ObservableList<Lute> list);
	public void closeConnection();
	public ResultSet getAllEntries();
}
