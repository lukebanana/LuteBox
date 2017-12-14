package ch.labegg.lutebox.db.api;

import java.sql.ResultSet;

import ch.labegg.lutebox.model.Lute;
import javafx.collections.ObservableList;

public interface LBDatabaseHandler {

	public boolean createTable(String tableName);
	public boolean insertData(ObservableList<Lute> list);
	public void closeConnection();
	public ObservableList<Lute> getAllEntries();
}
