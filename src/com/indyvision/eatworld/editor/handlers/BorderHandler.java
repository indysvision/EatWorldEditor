package com.indyvision.eatworld.editor.handlers;

import com.indyvision.eatworld.editor.Main;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BorderHandler implements EventHandler<MouseEvent> {
	Canvas canvas;
	Main mainController;

	double startX, startY;
	double stopX, stopY;

	public BorderHandler(Canvas canvas, Main main) {
		this.canvas = canvas;
		this.mainController = main;
	}

	@Override
	public void handle(MouseEvent event) {
		GraphicsContext gc = canvas.getGraphicsContext2D();

		if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			System.out
					.println("border pressed " + event.getSceneX() + ","
							+ event.getSceneY() + " "
							+ mainController.mainStage.getX());
			mainController.drawRulers(gc);

			startX = event.getSceneX() - mainController.mainStage.getX();
			startY = event.getSceneY() - mainController.mainStage.getY();

		} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			System.out.println("released " + event.getSceneX() + ","
					+ event.getSceneY());
		} else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
			// System.out.println("moved " + event.getSceneX() + ","
			// + event.getSceneY());
		} else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
			mainController.drawRulers(canvas.getGraphicsContext2D());

			gc.setStroke(Color.BLUE);
			gc.setLineWidth(5);

			gc.strokeLine(startX, startY, event.getSceneX()
					- mainController.mainStage.getX(), event.getSceneY()
					- mainController.mainStage.getY());
		}

	}

}
