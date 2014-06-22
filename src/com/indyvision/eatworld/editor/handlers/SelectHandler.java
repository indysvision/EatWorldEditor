package com.indyvision.eatworld.editor.handlers;

import com.indyvision.eatworld.editor.Main;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class SelectHandler implements EventHandler<MouseEvent> {
	Canvas canvas;
	Main mainController;

	public SelectHandler(Canvas canvas, Main main) {
		this.canvas = canvas;
		this.mainController = main;
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			System.out.println("pressed " + event.getSceneX() + ","
					+ event.getSceneY());
		} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			System.out.println("released " + event.getSceneX() + ","
					+ event.getSceneY());
		} else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
			// System.out.println("moved " + event.getSceneX() + ","
			// + event.getSceneY());
		} else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
			System.out.println("dragged " + event.getSceneX() + ","
					+ event.getSceneY());
		}

	}

}
