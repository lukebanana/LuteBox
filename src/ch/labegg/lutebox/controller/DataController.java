package ch.labegg.lutebox.controller;

import ch.labegg.lutebox.model.Lute;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public interface DataController {

	public ObservableList<Lute> getList();

	public Stage getStage();
}
