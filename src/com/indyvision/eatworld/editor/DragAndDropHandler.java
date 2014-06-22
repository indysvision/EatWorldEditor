package com.indyvision.eatworld.editor;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class DragAndDropHandler implements EventHandler<MouseEvent> {
	boolean isDragging;
	Canvas canvas;

	public DragAndDropHandler(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			System.out.println("pressed " + event.getSceneX() + ","
					+ event.getSceneY());
			isDragging = true;
		} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			System.out.println("released " + event.getSceneX() + ","
					+ event.getSceneY());
			isDragging = false;
		} else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
			// System.out.println("moved " + event.getSceneX() + ","
			// + event.getSceneY());
		} else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
			System.out.println("dragged " + event.getSceneX() + ","
					+ event.getSceneY());
		}

	}

}
