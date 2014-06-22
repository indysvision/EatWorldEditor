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

//	double oldX = -1, oldY = -1;
//	double stopX, stopY;

	ArrayList<Double> xPoints;
	ArrayList<Double> yPoints;
	int noOfPoints;

	boolean resetFlag;

	public BorderHandler1(Canvas canvas, Main main) {
		this.canvas = canvas;
		this.mainController = main;
		path = new Polyline();
		xPoints = new ArrayList<>();
		yPoints = new ArrayList<>();
//		oldX = -1;
//		oldY = -1;
		resetFlag = false;
	}
	
	public void clear(){
		path = new Polyline();
		xPoints = new ArrayList<>();
		yPoints = new ArrayList<>();
		noOfPoints = 0;
		resetFlag = false;
		GraphicsContext gc = canvas.getGraphicsContext2D();
		mainController.drawRulers(gc);
	}

	@Override
	public void handle(MouseEvent event) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
		} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			if (resetFlag){
				return;
			}
			currentX = event.getSceneX() - mainController.mainStage.getX();
			currentY = event.getSceneY() - mainController.mainStage.getY();
			currentX = ((int) (currentX / 20)) * 20;
			currentY = ((int) (currentY / 20)) * 20;
			
			
			if (xPoints.contains(currentX) && yPoints.contains(currentY)) {
				resetFlag = true;
//				noOfPoints = 0;
//				xPoints.clear();
//				yPoints.clear();
//				xPoints = new ArrayList<>();
//				yPoints = new ArrayList<>();
			}
			noOfPoints++;
			xPoints.add(currentX);
			yPoints.add(currentY);

			mainController.drawRulers(gc);
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
		} else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
			if ( resetFlag) {
				return;
			}
			tempX = event.getSceneX() - mainController.mainStage.getX();
			tempY = event.getSceneY() - mainController.mainStage.getY();
			tempX = ((int) (tempX / 20)) * 20;
			tempY = ((int) (tempY / 20)) * 20;

			xPoints.add(tempX);
			yPoints.add(tempY);

			mainController.drawRulers(gc);
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
			gc.strokePolyline(targetX, targetY, noOfPoints + 1);
			xPoints.remove(xPoints.size() - 1 );
			yPoints.remove(yPoints.size() - 1 );

		} else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
		}

	}

}
