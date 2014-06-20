package com.indyvision.eatworld.editor.pojo.objects;

import javafx.geometry.Point2D;

import com.indyvision.eatworld.editor.pojo.MapObject;

public class Meteor extends MapObject {

	private int speed;
	private int angle;	

	public Meteor(Point2D x, Point2D y, int speed, int angle) {
		currentType = ObjectType.METEOR;
		xLoc = x;
		yLoc = y;
		this.speed = speed;
		this.angle = angle;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}	

}
