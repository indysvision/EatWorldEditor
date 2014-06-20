package com.indyvision.eatworld.editor.pojo.objects;

import javafx.geometry.Point2D;

import com.indyvision.eatworld.editor.pojo.MapObject;

public class LineSaw extends MapObject{
	int speed;
	int torque;
	
	public LineSaw(Point2D x, Point2D y, int speed, int torque) {
		currentType = ObjectType.LINESAW;
		xLoc = x;
		yLoc = y;
		this.speed = speed;
		this.torque = torque;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getTorque() {
		return torque;
	}

	public void setTorque(int torque) {
		this.torque = torque;
	}

	
	
}
