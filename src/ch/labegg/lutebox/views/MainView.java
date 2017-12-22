package ch.labegg.lutebox.views;

import ch.labegg.lutebox.config.LBConfig;
import ch.labegg.lutebox.controller.DataController;
import ch.labegg.lutebox.model.Lute;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainView {

	private Button btn1 = null;
	private Button btn2 = null;
	private Scene scene1, scene2 = null;
	private DataController controller = null;
	
	public MainView(DataController controller) {
		setupView(controller.getList());
	}

	public void setupView(ObservableList<Lute> list) {
		// Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		
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
	
        ListView<Lute> listView = new ListView<>(list);
        listView.setCellFactory(param -> new ListCell<Lute>() {
            @Override
            protected void updateItem(Lute item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
	        
		listlayout.setVgrow(listView, Priority.ALWAYS);
		listlayout.getChildren().add(listView);
		listlayout.setAlignment(Pos.TOP_CENTER);
		
		borderPane.setCenter(listlayout);
		
		
		// Bottom Layout
		Label label1 = new Label("Lute");
		btn1 = new Button("Go to scene 2");

		btn1.setOnAction(event -> {
			controller.getStage().setScene(scene2);
		});
	
		VBox bottomlayout = new VBox(20); 	// 20px spacing
		bottomlayout.setPrefHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.BOTTOM_WINDOW_HEIGHT_PERCENT);
		bottomlayout.setMaxHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.BOTTOM_WINDOW_HEIGHT_PERCENT );
		bottomlayout.getChildren().addAll(label1, btn1);
		bottomlayout.setAlignment(Pos.CENTER);
	
		borderPane.setBottom(bottomlayout);
		
		scene1 = new Scene(borderPane);
		
		
		// Layout 2
		Label label2 = new Label("This is another label for scene 2");
		btn2 = new Button("Go to scene 1");

		btn2.setOnAction(event -> {
			boolean result = ConfirmBox.display("Alert, yoo", "Are you sure?", 200, 200);
	
			if(result) {
				controller.getStage().setScene(scene1);
			}
		});
		
		VBox layout2 = new VBox();
		layout2.setAlignment(Pos.CENTER);
		layout2.getChildren().addAll(label2, btn2);
		scene2 = new Scene(layout2, 320, 400);
		
		
		//controller.getStage().setScene(scene1);

	}
	
	public void show(Stage stage) {
      stage.setScene(scene1);
      stage.show();
   }
	
}