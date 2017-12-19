package ch.labegg.lutebox.model.api;

import java.util.ResourceBundle;

import ch.labegg.lutebox.model.Lute;
import javafx.collections.ObservableList;

public interface DataModel {    
	public ObservableList<Lute> getList();

	public void addItem(Lute lute);
	void removeItem(Lute lute);

	public ResourceBundle getRessourceBundle();
	
	void closeDB();
}
