package ch.labegg.lutebox.controller;

import ch.labegg.lutebox.model.Lute;
import ch.labegg.lutebox.model.api.DataModel;
import ch.labegg.lutebox.views.ConfirmBox;
import ch.labegg.lutebox.views.MainView;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class MainController implements DataController {
	private DataModel model = null;
	private MainView view = null;
	private Stage stage = null;

	public MainController(DataModel model, Stage primaryStage) {
		this.model = model;
		
		stage = primaryStage;
		stage.setMaximized(true);
		stage.setTitle("Test Title");
		stage.setOnCloseRequest(e -> {
			e.consume(); // Stops java from closing Program
			closeProgram();
		});
		
		view = new MainView(this);
		
		stage.centerOnScreen();
		view.show(stage);
		
	}
	
	public ObservableList<Lute> getList(){
		return this.model.getList();
	}
	
	private void closeProgram() {
		boolean result = ConfirmBox.display("Before you go...", "Are you sure you want to exit?", 200, 120);
				
		if(result) {
			stage.close();
		}
	}

	@Override
	public Stage getStage() {
		return stage;
	}
}
