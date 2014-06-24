package com.indyvision.eatworld.editor.handlers;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

import com.indyvision.eatworld.editor.Main;

public class BorderHandler1 implements EventHandler<MouseEvent> {
	Canvas canvas;
	Main mainController;

	Polyline path;

	double currentX, currentY;
	double tempX, tempY;

	// double oldX = -1, oldY = -1;
	// double stopX, stopY;

	ArrayList<Double> xPoints;
	ArrayList<Double> yPoints;
	int noOfPoints;
	boolean isStarted;
	boolean isFinished;
	boolean shouldShowMove;

	boolean resetFlag;

	public BorderHandler1(Canvas canvas, Main main) {
		this.canvas = canvas;
		this.mainController = main;
		path = new Polyline();
		xPoints = new ArrayList<>();
		yPoints = new ArrayList<>();
		// oldX = -1;
		// oldY = -1;
		resetFlag = false;
		System.out.println("new border " + xPoints.size());
	}

	public void clear() {
		path = new Polyline();
		xPoints = new ArrayList<>();
		yPoints = new ArrayList<>();
		noOfPoints = 0;
		resetFlag = false;
		GraphicsContext gc = canvas.getGraphicsContext2D();
		mainController.drawAll();
	}

	@Override
	public void handle(MouseEvent event) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
		} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			if (resetFlag) {
				shouldShowMove = false;
				return;
			}

			// currentX = event.getSceneX() - mainController.mainStage.getX();
			// currentY = event.getSceneY() - mainController.mainStage.getY();
			currentX = event.getSceneX() - mainController.leftSpace;
			currentY = event.getSceneY() - mainController.topSpace;
			currentX = ((int) (currentX / 20)) * 20;
			currentY = ((int) (currentY / 20)) * 20;

			for (int i = 0; i < xPoints.size(); i++) {
				if (xPoints.get(i) == currentX) {
					if (yPoints.get(i) == currentY) {
						resetFlag = true;
						shouldShowMove = false;
					}
				}
			}
			noOfPoints++;
			xPoints.add(currentX);
			yPoints.add(currentY);

			mainController.drawAll();
			shouldShowMove = true;
		} else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
			if (resetFlag || !shouldShowMove) {
				return;
			}
			tempX = event.getSceneX() - mainController.leftSpace;
			tempY = event.getSceneY() - mainController.topSpace;
			tempX = ((int) (tempX / 20)) * 20;
			tempY = ((int) (tempY / 20)) * 20;

			// xPoints.add(tempX);
			// yPoints.add(tempY);

			mainController.drawAll();
			drawAll();
			// gc.setStroke(Color.BLUE);
			// gc.setLineWidth(4);
			// double[] targetX = new double[xPoints.size()];
			// for (int i = 0; i < targetX.length; i++) {
			// targetX[i] = xPoints.get(i);
			// }
			// double[] targetY = new double[yPoints.size()];
			// for (int i = 0; i < targetY.length; i++) {
			// targetY[i] = yPoints.get(i);
			// }
			// gc.strokePolyline(targetX, targetY, noOfPoints + 1);
			// xPoints.remove(xPoints.size() - 1 );
			// yPoints.remove(yPoints.size() - 1 );

		} else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
		}

	}

	public void drawAll() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(4);
		double[] targetX = new double[xPoints.size()];
		for (int i = 0; i < targetX.length; i++) {
			targetX[i] = xPoints.get(i);
		}
		double[] targetY = new double[yPoints.size()];
		for (int i = 0; i < targetY.length; i++) {
			targetY[i] = yPoints.get(i);
		}
		gc.strokePolyline(targetX, targetY, noOfPoints);

		if (shouldShowMove) {
			gc.setStroke(Color.RED);
			gc.setLineWidth(4);
			double[] t1 = { xPoints.get(noOfPoints - 1), tempX };
			double[] t2 = { yPoints.get(noOfPoints - 1), tempY };
			if (t1[0] == 0 && t2[0] == 0) {

			} else {
				gc.strokePolyline(t1, t2, 2);
			}
		}

	}

}
