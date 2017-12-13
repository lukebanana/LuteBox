package ch.labegg.lutebox;

import ch.labegg.lutebox.controller.MainController;
import ch.labegg.lutebox.model.MainModel;
import ch.labegg.lutebox.model.api.DataModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args)	{
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "LuteBox");
		launch(args);
    }
	
	@Override
    public void start(Stage primaryStage) throws Exception {       
       DataModel model = new MainModel();
 
       // Ersten Controller aufrufen
       MainController controller = new MainController(model, primaryStage);
    }


}
