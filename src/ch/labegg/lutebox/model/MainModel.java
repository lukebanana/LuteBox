package ch.labegg.lutebox.model;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import ch.labegg.lutebox.db.DerbyDB;
import ch.labegg.lutebox.db.DerbyHelper;
import ch.labegg.lutebox.db.DerbySerializer;
import ch.labegg.lutebox.db.api.LBDatabaseHandler;
import ch.labegg.lutebox.model.api.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel implements DataModel {
    private ObservableList<Lute> list = FXCollections.observableArrayList();

    
    public MainModel(){
    	
    	
    		this.list.add(new Lute("First TestLute"));
   		this.list.add(new Lute("Second TestLute"));
   		this.list.add(new Lute("Third TestLute"));
   		this.list.add(new Lute("Fourth TestLute"));

   		
   		LBDatabaseHandler db = new DerbyDB();
   		
   		
		db.createTable(DerbyDB.TABLE_NAME);
		db.insertData(this.list);
		

	
		try {
			ResultSet resultSet = db.getAllEntries();

			/*
			list = DerbySerializer.deserialize(resultSet);
			*/
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			
			for(int x = 1; x<=columnCount; x++) {
				System.out.format("%20s", resultSetMetaData.getColumnName(x) + " | ");
				
			}
			
			// Print out results
			if (resultSet.next() == false) { 
				System.out.println("ResultSet in empty in Java"); 
			
			}else {
				while (resultSet.next()) { 

					System.out.println();
					for(int x = 1; x<=columnCount; x++) {
						System.out.format("%20s", resultSet.getString(x) + " | ");
					}
					
				}				
			}
			
			/*
			for(Lute l : list) {
				System.out.println(l.getName());
			}
			*/
			
			
			System.out.println();

		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.closeConnection();
    }
    
	@Override
	public ObservableList<Lute> getList() {
		return list;
	}

	
}
