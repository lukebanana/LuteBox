package ch.labegg.lutebox.model;

import ch.labegg.lutebox.model.api.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel implements DataModel {
    private ObservableList<Lute> list = FXCollections.observableArrayList();

	@Override
	public ObservableList<Lute> getList() {
		return list;
	}

	
}
