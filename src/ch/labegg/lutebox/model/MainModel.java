package ch.labegg.lutebox.model;

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
    }
    
	@Override
	public ObservableList<Lute> getList() {
		return list;
	}

	
}
