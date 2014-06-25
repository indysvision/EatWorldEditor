package com.indyvision.eatworld.editor;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.indyvision.eatworld.editor.handlers.BorderHandler;
import com.indyvision.eatworld.editor.handlers.BorderHandler1;
import com.indyvision.eatworld.editor.handlers.ObjectHandler;
import com.indyvision.eatworld.editor.handlers.SelectHandler;
import com.indyvision.eatworld.editor.pojo.EatWorldMap;
import com.indyvision.eatworld.editor.pojo.MapObject;
import com.indyvision.eatworld.editor.pojo.objects.LineSaw;
import com.indyvision.eatworld.editor.pojo.objects.Meteor;
import com.indyvision.eatworld.editor.pojo.objects.Zoomer;

public class Main extends Application {
	public enum ActionType {
		SELECT, METEOR, ZOOMER, LINESAW, BORDER, WALL
	};

	private GraphicsContext gc;
	private EatWorldMap currentMap;
	public Stage mainStage;
	private BorderPane root;
	public ActionType currentAction;
	Canvas canvas;
	EventHandler<MouseEvent> currentMouseHandler;

	public double leftSpace, topSpace;

	TextArea statusText;

	static BorderHandler1 bHandler;
	static ObjectHandler oHandler;
	static SelectHandler sHandler;

	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Eat World Editor!");
			mainStage = primaryStage;

			Button btn = new Button();
			btn.setText("Say 'Hello World'");
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					EatWorldMap testMap = new EatWorldMap();
					testMap.setFileName("testMap.emap");
					ArrayList<Point2D> verts = new ArrayList<>();
					verts.add(new Point2D(0, 0));
					verts.add(new Point2D(0, 100));
					verts.add(new Point2D(100, 100));
					verts.add(new Point2D(100, 0));
					testMap.setVertices(verts);
					ArrayList<MapObject> objs = new ArrayList<>();
					objs.add(new Meteor(40, 50, 3, 80));
					objs.add(new LineSaw(30, 10, 2, 40));
					objs.add(new Zoomer(60, 60, 1, 20, 33, 44));
					testMap.setObjects(objs);
					testMap.setName("test");

					testMap.writeData();
					testMap = testMap.loadData1(Main.this, testMap.getFileName());
					// System.out.println("Hello World!");
				}
			});

			// file menu
			MenuBar menuBar = new MenuBar();
			initMenu(menuBar);

			// left menu
			VBox objectsPanel = new VBox(8);
			initObjectsPanel(objectsPanel);
			HBox hbox = new HBox();
			hbox.getChildren().addAll(objectsPanel,
					new Separator(Orientation.VERTICAL));

			// canvas
			canvas = new Canvas(1400, 800);

			bHandler = new BorderHandler1(canvas, this);
			oHandler = new ObjectHandler(canvas, this);
			sHandler = new SelectHandler(canvas, this);

			initCanvas();
			ScrollPane sp = new ScrollPane();
			sp.setContent(canvas);

			// status
			statusText = new TextArea();
			initStatusText();
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.BOTTOM_CENTER);
			vbox.getChildren().addAll(new Separator(Orientation.HORIZONTAL),
					statusText);

			root = new BorderPane();
			root.setTop(menuBar);
			root.setRight(btn);
			root.setLeft(hbox);
			root.setBottom(vbox);
			root.setCenter(sp);

			Scene mainScene = new Scene(root, 1200, 800);
			primaryStage.setScene(mainScene);
			primaryStage.show();
			leftSpace = sp.getLayoutX();
			topSpace = sp.getLayoutY();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initStatusText() {
		statusText.setPrefRowCount(5);
		statusText.setPrefColumnCount(100);
		statusText.setWrapText(true);
		statusText.setPrefWidth(150);
		statusText.setText("Current loaded map: Not available");

		// statusText.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 20; -fx-text-fill: darkred;");

	}

	private void initCanvas() {
		gc = canvas.getGraphicsContext2D();
		drawRulers();

		// canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
		// @Override
		// public void handle(MouseEvent event) {
		// System.out.println("mouse press detected! " + event.getSceneX()
		// + "," + event.getSceneY());
		// }
		// });
		// canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
		// @Override
		// public void handle(MouseEvent event) {
		// System.out.println("mouse unpress detected! "
		// + event.getSceneX() + "," + event.getSceneY());
		// }
		// });
	}

	private void initObjectsPanel(VBox objectsPanel) {
		objectsPanel.setPadding(new Insets(10));
		final Label l = new Label("Select mode");

		Button btnSelect = new Button();
		btnSelect.setText("Select");
		btnSelect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentAction = ActionType.SELECT;
				l.setText("Select mode");
				canvas.setOnMouseMoved(sHandler);
				canvas.setOnMousePressed(sHandler);
				canvas.setOnMouseReleased(sHandler);
				canvas.setOnMouseDragged(sHandler);
			}
		});
		Button btnBorder = new Button();
		btnBorder.setText("Border");
		btnBorder.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentAction = ActionType.BORDER;
				l.setText("Border mode");
				bHandler.clear();
				canvas.setOnMouseMoved(bHandler);
				canvas.setOnMousePressed(bHandler);
				canvas.setOnMouseReleased(bHandler);
				canvas.setOnMouseDragged(bHandler);

			}
		});
		Button btnMeteor = new Button();
		btnMeteor.setText("Meteor");
		btnMeteor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentAction = ActionType.METEOR;
				l.setText("Meteor mode");
				canvas.setOnMouseMoved(oHandler);
				canvas.setOnMousePressed(oHandler);
				canvas.setOnMouseReleased(oHandler);
				canvas.setOnMouseDragged(oHandler);
			}
		});
		Button btnZoomer = new Button();
		btnZoomer.setText("Zoomer");
		btnZoomer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentAction = ActionType.ZOOMER;
				l.setText("Zoomer mode");
				canvas.setOnMouseMoved(oHandler);
				canvas.setOnMousePressed(oHandler);
				canvas.setOnMouseReleased(oHandler);
				canvas.setOnMouseDragged(oHandler);
			}
		});
		Button btnLinesaw = new Button();
		btnLinesaw.setText("Linesaw");
		btnLinesaw.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentAction = ActionType.LINESAW;
				l.setText("Linesaw mode");
				canvas.setOnMouseMoved(oHandler);
				canvas.setOnMousePressed(oHandler);
				canvas.setOnMouseReleased(oHandler);
				canvas.setOnMouseDragged(oHandler);
			}
		});
		Button btnWall = new Button();
		btnWall.setText("Enemy Wall");
		btnWall.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentAction = ActionType.WALL;
				l.setText("Wall mode");
			}
		});

		btnSelect.setMaxWidth(Double.MAX_VALUE);
		btnBorder.setMaxWidth(Double.MAX_VALUE);
		btnWall.setMaxWidth(Double.MAX_VALUE);
		btnMeteor.setMaxWidth(Double.MAX_VALUE);
		btnZoomer.setMaxWidth(Double.MAX_VALUE);
		btnLinesaw.setMaxWidth(Double.MAX_VALUE);

		objectsPanel.getChildren().addAll(btnSelect, btnBorder, btnWall,
				btnLinesaw, btnMeteor, btnZoomer, l);

		currentAction = ActionType.SELECT;
	}

	private void initMenu(MenuBar menuBar) {

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
		MenuItem menuSave = new MenuItem("Save As");
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

	}

	public void drawAll() {
		drawRulers();
		bHandler.drawAll();
		oHandler.drawAll();
	}

	public void drawRulers() {
		gc.setFill(Color.AZURE);
		double canvasWidth = gc.getCanvas().getWidth();
		double canvasHeight = gc.getCanvas().getHeight();

		gc.fillRect(0, 0, canvasWidth, canvasHeight);
		gc.setFill(Color.CORAL);
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(1);
		// gc.strokeLine(40, 10, 10, 40);
		int noOfRulersWidth = (int) (canvasWidth / 20);
		int noOfRulersHeight = (int) (canvasHeight / 20);
		for (int i = 1; i < noOfRulersWidth; i++) {
			for (int j = 1; j < noOfRulersHeight; j++) {
				gc.fillOval(20 * i, 20 * j, ((i % 5) == 0 && (j % 5) == 0) ? 4
						: 2, ((i % 5) == 0 && (j % 5) == 0) ? 4 : 2);
			}
		}

		// gc.fillOval(10, 60, 30, 30);
		// gc.strokeOval(60, 60, 30, 30);
		// gc.fillRoundRect(110, 60, 30, 30, 10, 10);
		// gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
		// gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
		// gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
		// gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
		// gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
		// gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
		// gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
		// gc.fillPolygon(new double[] { 10, 40, 10, 40 }, new double[] { 210,
		// 210, 240, 240 }, 4);
		// gc.strokePolygon(new double[] { 60, 90, 60, 90 }, new double[] { 210,
		// 210, 240, 240 }, 4);
		// gc.strokePolyline(new double[] { 110, 140, 110, 140 }, new double[] {
		// 210, 210, 240, 240 }, 4);
	}

	private void onNewClicked() {
		System.out.println("new");
		bHandler = new BorderHandler1(canvas, this);
		oHandler = new ObjectHandler(canvas, this);
		sHandler = new SelectHandler(canvas, this);

		initCanvas();
	}

	private void onLoadClicked() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load map");
		// fileChooser.setInitialDirectory(new File(System
		// .getProperty("user.home")));

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"EatWorldMap files (*.emap)", "*.emap");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);
		// System.out.println("path " + file.getPath() + ", filename "
		// + file.getName());

		if (file != null) {
			currentMap = EatWorldMap.loadData1(Main.this, file.getPath());
			statusText.setText(currentMap.toJson());
			bHandler.setPoints(currentMap.getVertices());
			oHandler.objects = currentMap.getObjects();
			drawAll();
		}
	}

	private void onExitClicked() {
		Platform.exit();
	}

	private void onSaveClicked() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load map");
		// fileChooser.setInitialDirectory(new File(System
		// .getProperty("user.home")));

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"EatWorldMap files (*.emap)", "*.emap");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show open file dialog
		File file = fileChooser.showSaveDialog(mainStage);
		if (file != null) {
			System.out.println("path " + file.getPath() + ", filename "
					+ file.getName());
			if (currentMap == null){
				currentMap = new EatWorldMap();
			}
			currentMap.setFileName(file.getPath());
			updateCurrentMap();			
			currentMap.writeData();
		}

	}
	
	private void updateCurrentMap(){
		currentMap.setVertices(bHandler.getPoints());
		currentMap.setObjects(ObjectHandler.objects);
	}

	private void onRenameClicked() {

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showProperties(MapObject currentObject) {
		if (currentObject != null){
			root.setRight(currentObject.loadObjectProperties(Main.this));
		}
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