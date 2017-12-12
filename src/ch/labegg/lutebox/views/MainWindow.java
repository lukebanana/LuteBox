package ch.labegg.lutebox.views;

import ch.labegg.lutebox.config.LBConfig;
import ch.labegg.lutebox.controller.api.DataController;
import ch.labegg.lutebox.model.Lute;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainWindow extends Application {

	private Button btn1 = null;
	private Button btn2 = null;
	private Stage stage = null;
	private Scene scene1, scene2 = null;
	ListView<Lute> list = new ListView<Lute>();
	
	
	public void showView()	{
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "LuteBox");
		launch();
    }

	@Override
	public void start(Stage initialStage) throws Exception {
		// Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		stage = initialStage;
		stage.setMaximized(true);
		stage.setTitle("Test Title");
		stage.setOnCloseRequest(e -> {
			e.consume(); // Stops java from closing Program
			closeProgram();
		});
		
		// Top Menu
		MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);

		
		// Left Menu
		VBox leftMenu = new VBox(10);
		leftMenu.setPrefWidth( Screen.getPrimary().getBounds().getWidth() / 100 * LBConfig.LEFT_MENU_WIDTH_PERCENT );
		leftMenu.setMaxWidth( Screen.getPrimary().getBounds().getWidth() / 100 * LBConfig.LEFT_MENU_WIDTH_PERCENT );
		Button btnFile2 = new Button("1");
		Button btnEdit2 = new Button("2");
		Button btnView2 = new Button("3");
		leftMenu.getChildren().addAll(btnFile2, btnEdit2, btnView2);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setLeft(leftMenu);
		
		
		// List Layout
		VBox listlayout = new VBox(20); 	// 20px spacing
		listlayout.setPrefHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.LIST_WINDOW_HEIGHT_PERCENT);
		listlayout.setMaxHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.LIST_WINDOW_HEIGHT_PERCENT );
		listlayout.setVgrow(list, Priority.ALWAYS);
		listlayout.getChildren().addAll(list);
		listlayout.setAlignment(Pos.TOP_CENTER);
		listlayout.setStyle("-fx-background-color: "+LBConfig.LIST_WINDOW_BG_COLOR+";");
		
		borderPane.setCenter(listlayout);
		
		
		// Bottom Layout
		Label label1 = new Label("Lute");
		btn1 = new Button("Go to scene 2");

		btn1.setOnAction(event -> {
			stage.setScene(scene2);
		});
	
		VBox bottomlayout = new VBox(20); 	// 20px spacing
		bottomlayout.setPrefHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.BOTTOM_WINDOW_HEIGHT_PERCENT);
		bottomlayout.setMaxHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.BOTTOM_WINDOW_HEIGHT_PERCENT );
		bottomlayout.getChildren().addAll(label1, btn1);
		bottomlayout.setAlignment(Pos.CENTER);
		bottomlayout.setStyle("-fx-background-color: "+LBConfig.BOTTOM_WINDOW_BG_COLOR+";");
	
		borderPane.setBottom(bottomlayout);
		
		
		scene1 = new Scene(borderPane);
		
		
		
		
		
		// Layout 2
		Label label2 = new Label("This is another label for scene 2");
		btn2 = new Button("Go to scene 1");

		btn2.setOnAction(event -> {
			boolean result = ConfirmBox.display("Alert, yoo", "Are you sure?", 200, 200);
	
			if(result) {
				stage.setScene(scene1);
			}
		});
		
		VBox layout2 = new VBox();
		layout2.setAlignment(Pos.CENTER);
		layout2.getChildren().addAll(label2, btn2);
		layout2.setStyle("-fx-background-color: "+LBConfig.LIST_WINDOW_BG_COLOR+";");
		scene2 = new Scene(layout2, 320, 400);
		
		
		
		stage.setScene(scene1);
		stage.centerOnScreen();
		stage.show();

	}
	
	  public ListView<Lute> getList() {
		  return this.list;
	  }
	 


	private void closeProgram() {
		boolean result = ConfirmBox.display("Before you go...", "Are you sure you want to exit?", 200, 120);
				
		if(result) {
			stage.close();
		}
	}

}