package com.indyvision.eatworld.editor.pojo.objects;

import com.indyvision.eatworld.editor.pojo.MapObject;

public class Zoomer extends MapObject{
	int resetDistance;
    int dampingRatio;
    int maxForce;
    int frequency;
    
    public Zoomer(int x, int y, int resetDistance, int dampingRatio, int maxForce, int frequency) {
    	this.obj = ObjectType.ZOOMER;
    	this.x = x;
    	this.y = y;
    	this.resetDistance = resetDistance;
    	this.dampingRatio = dampingRatio;
    	this.maxForce = maxForce;
    	this.frequency = frequency;
	}

	public int getResetDistance() {
		return resetDistance;
	}

	public void setResetDistance(int resetDistance) {
		this.resetDistance = resetDistance;
	}

	public int getDampingRatio() {
		return dampingRatio;
	}

	public void setDampingRatio(int dampingRatio) {
		this.dampingRatio = dampingRatio;
	}

	public int getMaxForce() {
		return maxForce;
	}

	public void setMaxForce(int maxForce) {
		this.maxForce = maxForce;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
    
    
    
    
}
