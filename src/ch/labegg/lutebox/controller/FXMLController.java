package ch.labegg.lutebox.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import ch.labegg.lutebox.config.LBConfig;
import ch.labegg.lutebox.model.Lute;
import ch.labegg.lutebox.model.MainModel;
import ch.labegg.lutebox.model.api.DataModel;
import ch.labegg.lutebox.views.ConfirmBox;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLController extends Application implements Initializable {

	@FXML private BorderPane borderPane;

	@FXML private Menu menuFile;
	@FXML private Menu menuEdit;
	@FXML private Menu menuView;
	@FXML private MenuBar menuBar;
	
	@FXML private VBox leftMenu;
	
	@FXML private VBox listlayout;	
	@FXML private VBox bottomlayout;
	
	@FXML private TableView<Lute> tableView;
	
	private DataModel model = null;
	private ObservableList<Lute> list = null;
	

	
	public static void main(String[] args) {
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "LuteBox");
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
			    
	    try {
			primaryStage.setMaximized(true);
			primaryStage.setTitle("LuteBox");
			
		    	Locale locale = new Locale("de");
		    	ResourceBundle bundle = ResourceBundle.getBundle("ch.labegg.lutebox.bundles.AppStrings", locale);
		    	FXMLLoader loader = new FXMLLoader();
		    	loader.setResources(bundle);
		    	borderPane = new BorderPane();
		    	
		    	loader.setRoot(borderPane);
		    	loader.setLocation(getClass().getResource("/ch/labegg/lutebox/views/MainWindow.fxml"));
		    	loader.load();
	
			
			primaryStage.setOnCloseRequest(e -> {
				e.consume(); // Stops java from closing Program
				closeProgram(primaryStage);
			});
			
			Scene scene1 = new Scene(borderPane);
			primaryStage.setScene(scene1);		
			primaryStage.centerOnScreen();
			primaryStage.show();
			
		
        } catch (IOException e) {
        		e.printStackTrace();
        }
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/*
		String macMenu = System.getProperty("apple.laf.useScreenMenuBar");
	     if( macMenu != null && macMenu == "true" ) {
	    	 	borderPane.getChildren().remove(menuBar);
	     }
	     */
	     
		
		leftMenu.setPrefWidth( Screen.getPrimary().getBounds().getWidth() / 100 * LBConfig.LEFT_MENU_WIDTH_PERCENT );
		leftMenu.setMaxWidth( Screen.getPrimary().getBounds().getWidth() / 100 * LBConfig.LEFT_MENU_WIDTH_PERCENT );
		
		model = new MainModel();

		list = this.model.getList();
        
		tableView.setItems(list);
			
        
		listlayout.setPrefHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.LIST_WINDOW_HEIGHT_PERCENT);
		listlayout.setMaxHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.LIST_WINDOW_HEIGHT_PERCENT );
		listlayout.setVgrow(tableView, Priority.ALWAYS);
	

		bottomlayout.setStyle("-fx-background-color: "+LBConfig.BOTTOM_WINDOW_BG_COLOR+";");
		bottomlayout.setPrefHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.BOTTOM_WINDOW_HEIGHT_PERCENT);
		bottomlayout.setMaxHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.BOTTOM_WINDOW_HEIGHT_PERCENT );
	}	
	
	
	@FXML
	public void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
	
	
	@FXML
	private void handleKeyInput(final InputEvent event) {
		if (event instanceof KeyEvent) {
			 System.out.println("Test2");  
			final KeyEvent keyEvent = (KeyEvent) event;
			if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
				selectWholeList();
			}
		}
	}

	private void selectWholeList() {
		 System.out.println("You selected all");    
		
	}

	
	@FXML
	private void handleSaveAction(final ActionEvent event) {
		 System.out.println("Saved");   
	}
	
	@FXML
	private void handleCloseAction(final ActionEvent event) {
		closeProgram((Stage) borderPane.getScene().getWindow());
	}
	

	private void closeProgram(Stage stage) {
		boolean result = ConfirmBox.display("Before you go...", "Are you sure you want to exit?", 200, 120);
				
		if(result) {
			stage.close();
		}
	}
}
