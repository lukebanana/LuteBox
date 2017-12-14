package ch.labegg.lutebox.model;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import ch.labegg.lutebox.db.ObjectDB;
import ch.labegg.lutebox.db.api.LBDatabaseHandler;
import ch.labegg.lutebox.model.api.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel implements DataModel {
    private ObservableList<Lute> list = FXCollections.observableArrayList();

    
    public MainModel(){   		
   		LBDatabaseHandler db = new ObjectDB();
   		
   		
		db.createDB("lutebox");
		db.insertData(this.list);

		list = FXCollections.observableList(db.getAllEntries());
		
		db.closeConnection();
    }
    
	@Override
	public ObservableList<Lute> getList() {
		return list;
	}

	
}
