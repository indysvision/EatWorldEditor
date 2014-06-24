package com.indyvision.eatworld.editor.handlers;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import com.indyvision.eatworld.editor.Main;
import com.indyvision.eatworld.editor.Main.ActionType;
import com.indyvision.eatworld.editor.pojo.MapObject;
import com.indyvision.eatworld.editor.pojo.MapObject.ObjectType;
import com.indyvision.eatworld.editor.pojo.objects.LineSaw;
import com.indyvision.eatworld.editor.pojo.objects.Meteor;
import com.indyvision.eatworld.editor.pojo.objects.Zoomer;

public class ObjectHandler implements EventHandler<MouseEvent> {
	Canvas canvas;
	Main mainController;
	double currentX, currentY;
	double tempX, tempY;
	Image imgMeteor;
	Image imgLineSaw;
	Image imgZoomer;

	MapObject currentObject;

	public static ArrayList<MapObject> objects;

	public ObjectHandler(Canvas canvas, Main main) {
		this.canvas = canvas;
		this.mainController = main;
//		if (objects == null) {
			objects = new ArrayList<>();
//		}
		imgMeteor = new Image("file:meteorGrey_big1.png", 30, 30, true, true);
		imgLineSaw = new Image("file:ufoBlue.png", 30, 30, true, true);
		imgZoomer = new Image("file:ufoRed.png", 30, 30, true, true);

	}

	public void drawAll() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		for (MapObject obj : objects) {
			if (obj.getCurrentType() == ObjectType.METEOR) {
				gc.drawImage(imgMeteor, obj.getxLoc(), obj.getyLoc());
			} else if (obj.getCurrentType() == ObjectType.LINESAW) {
				gc.drawImage(imgLineSaw, obj.getxLoc(), obj.getyLoc());
			} else if (obj.getCurrentType() == ObjectType.ZOOMER) {
				gc.drawImage(imgZoomer, obj.getxLoc(), obj.getyLoc());
			}
		}

	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			// System.out.println("pressed " + event.getSceneX() + ","
			// + event.getSceneY());
			currentX = event.getSceneX() - mainController.leftSpace;
			currentY = event.getSceneY() - mainController.topSpace;
			currentX = ((int) (currentX / 20)) * 20 - 15;
			currentY = ((int) (currentY / 20)) * 20 - 10;

			boolean isAlreadyInList = false;

			if (mainController.currentAction != ActionType.METEOR
					&& mainController.currentAction != ActionType.LINESAW
					&& mainController.currentAction != ActionType.ZOOMER) {
				return;
			}

			for (MapObject obj : objects) {
				if ((obj.getxLoc() == ((int) currentX))
						&& (obj.getyLoc() == ((int) currentY))) {
					isAlreadyInList = true;
					currentObject = obj;
				}
			}

			if (event.getButton().equals(MouseButton.MIDDLE)) {
				objects.remove(currentObject);
			}
			else if (event.getButton().equals(MouseButton.SECONDARY)){
				mainController.showProperties(currentObject);
				return;
			}
			if (!isAlreadyInList) {
				currentObject = null;
				// new object
				if (mainController.currentAction == ActionType.METEOR)
					objects.add(new Meteor(mainController, ((int) currentX), ((int) currentY),
							0, 0));
				else if (mainController.currentAction == ActionType.ZOOMER)
					objects.add(new Zoomer(mainController, ((int) currentX), ((int) currentY),
							0, 0, 0, 0));
				else if (mainController.currentAction == ActionType.LINESAW)
					objects.add(new LineSaw(mainController, ((int) currentX), ((int) currentY),
							0, 0));
			}

			mainController.drawAll();
		} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			// System.out.println("released " + event.getSceneX() + ","
			// + event.getSceneY());
		} else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
			// System.out.println("moved " + event.getSceneX() + ","
			// + event.getSceneY());
		} else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
			// System.out.println("dragged " + event.getSceneX() + ","
			// + event.getSceneY());
			if (currentObject != null) {
				tempX = event.getSceneX() - mainController.leftSpace;
				tempY = event.getSceneY() - mainController.topSpace;
				tempX = ((int) (tempX / 20)) * 20 - 15;
				tempY = ((int) (tempY / 20)) * 20 - 10;
				currentObject.setxLoc((int) tempX);
				currentObject.setyLoc((int) tempY);
				mainController.drawAll();
			}
		}
		// if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
		// if(event.getButton().equals(MouseButton.PRIMARY)){
		// if(event.getClickCount() == 2){
		// System.out.println("Double clicked");
		// tempX = event.getSceneX() - mainController.leftSpace;
		// tempY = event.getSceneY() - mainController.topSpace;
		// tempX = ((int) (tempX / 20)) * 20 - 15;
		// tempY = ((int) (tempY / 20)) * 20 - 10;
		// for (MapObject obj : objects) {
		// if ((obj.getxLoc() == ((int) tempX))
		// && (obj.getyLoc() == ((int) tempY))) {
		// currentObject = obj;
		// }
		// }
		// objects.remove(currentObject);
		// }
		// }
		// }

	}

}
