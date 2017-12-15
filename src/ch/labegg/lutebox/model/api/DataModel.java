package ch.labegg.lutebox.model.api;

import ch.labegg.lutebox.model.Lute;
import javafx.collections.ObservableList;

public interface DataModel {    
	public ObservableList<Lute> getList();

	public void addItem(Lute lute);
	void removeItem(Lute lute);

	void closeDB();
}
