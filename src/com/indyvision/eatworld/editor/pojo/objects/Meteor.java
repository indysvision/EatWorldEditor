package com.indyvision.eatworld.editor.pojo.objects;

import com.indyvision.eatworld.editor.pojo.MapObject;

public class Meteor extends MapObject {

	private int speed;
	private int angle;	

	public Meteor(int x, int y, int speed, int angle) {
		obj = ObjectType.METEOR;
		this.x = x;
		this.y = y;
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
