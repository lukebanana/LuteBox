package ch.labegg.lutebox.db.api;

import java.util.List;

import ch.labegg.lutebox.model.Lute;

public interface LBDatabaseHandler {
	
	public boolean createDB(String dbName);
	public boolean insertData();
	public void closeConnection();
	public List<Lute> getAllEntries();
	void insertSingle(Lute lute);
	void delete(Lute lute);
	void update(Lute lute);
}
