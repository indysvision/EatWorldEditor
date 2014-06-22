package com.indyvision.eatworld.editor.pojo.objects;

import com.indyvision.eatworld.editor.pojo.MapObject;

public class LineSaw extends MapObject{
	int speed;
	int torque;
	
	public LineSaw(int x, int y, int speed, int torque) {
		obj = ObjectType.LINESAW;
		this.x = x;
		this.y = y;
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
