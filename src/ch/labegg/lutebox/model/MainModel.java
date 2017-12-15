package ch.labegg.lutebox.model;

import ch.labegg.lutebox.db.ObjectDB;
import ch.labegg.lutebox.db.api.LBDatabaseHandler;
import ch.labegg.lutebox.model.api.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel implements DataModel {
    private ObservableList<Lute> list = FXCollections.observableArrayList();
    private LBDatabaseHandler db = null;
    
    public MainModel(){   		
   		db = new ObjectDB();
   		
		db.createDB("lutebox");
		//db.insertData(this.list);

		list = getList();
    }
    
	@Override
	public ObservableList<Lute> getList() {
		return FXCollections.observableList(db.getAllEntries());
	}

	@Override
	public void addItem(Lute lute) {
		db.insertSingle(lute);
	}
	

	@Override
	public void removeItem(Lute lute) {
		db.delete(lute);
	}


	@Override
	public void closeDB() {
		db.closeConnection();
	}

	
}
