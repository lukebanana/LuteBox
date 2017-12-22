package ch.labegg.lutebox.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

import ch.labegg.lutebox.db.ObjectDB;
import ch.labegg.lutebox.db.api.LBDatabaseHandler;
import ch.labegg.lutebox.model.api.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel implements DataModel {
    private ObservableList<Lute> list = FXCollections.observableArrayList();
    private LBDatabaseHandler db = null;
	private Locale locale = null;
	private ResourceBundle bundle =  null;
	
    public MainModel(){   	
    		locale = new Locale("de");
    		bundle = ResourceBundle.getBundle("ch.labegg.lutebox.bundles.AppStrings", locale);
    	
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
		try {
			db.delete(lute);
			if(lute.getFilePath() != "") {				
				Files.deleteIfExists( Paths.get(lute.getFilePath()) );
			}			
		} catch (IOException e) {
			System.out.println("Cannot delete file. Path to file: " + lute.getFilePath() );
			e.printStackTrace();
		}
	}


	@Override
	public void closeDB() {
		db.closeConnection();
	}

	@Override
	public ResourceBundle getRessourceBundle() {
		return bundle;
	}

	
}
