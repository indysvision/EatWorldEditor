package com.indyvision.eatworld.editor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Hello World!");

			Button btn = new Button();
			btn.setText("Say 'Hello World'");
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					System.out.println("Hello World!");
				}
			});
			
			// file menu
			MenuBar menuBar = new MenuBar();
			Menu menuFile = new Menu("File");
			MenuItem menuNew = new MenuItem("New");
			menuNew.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					onNewClicked();
				}
			});
			MenuItem menuLoad = new MenuItem("Load");
			menuLoad.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					onLoadClicked();
				}
			});
			MenuItem menuSave = new MenuItem("Save");
			menuSave.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					onSaveClicked();
				}
			});
			MenuItem menuExit = new MenuItem("Exit");
			menuExit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					onExitClicked();
				}
			});
			menuFile.getItems().add(menuNew);
			menuFile.getItems().add(menuLoad);
			menuFile.getItems().add(menuSave);
			menuFile.getItems().add(menuExit);

			// edit menu
			Menu menuEdit = new Menu("Edit");
			MenuItem menuRename = new MenuItem("Rename");
			menuRename.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					onRenameClicked();
				}
			});
			menuEdit.getItems().add(menuRename);
			// view menu
			Menu menuView = new Menu("View");
			

			menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
//			StackPane root = new StackPane();
//			root.getChildren().add(btn);
			Scene mainScene = new Scene(new VBox(), 300, 250);
			((VBox)mainScene.getRoot()).getChildren().add(menuBar);
			((VBox)mainScene.getRoot()).getChildren().add(btn);

			primaryStage.setScene(mainScene);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void onNewClicked(){
		
	}
	
	private void onLoadClicked(){
		
	}
	
	private void onExitClicked(){
		Platform.exit();
	}
	
	private void onSaveClicked(){
	}

	private void onRenameClicked(){

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

/*
 * primaryStage.setTitle("Hello World!"); Button btn = new Button();
 * btn.setText("Say 'Hello World'"); btn.setOnAction(new
 * EventHandler<ActionEvent>() {
 * 
 * @Override public void handle(ActionEvent event) {
 * System.out.println("Hello World!"); } });
 * 
 * BorderPane root = new BorderPane(); root.getChildren().add(btn); Scene scene
 * = new Scene(root, 400, 400); //scene.getStylesheets().add( //
 * getClass().getResource("application.css").toExternalForm());
 * primaryStage.setScene(scene); primaryStage.show();
 */