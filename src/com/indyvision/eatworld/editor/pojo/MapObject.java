package com.indyvision.eatworld.editor.pojo;

import javafx.geometry.Point2D;

public class MapObject {
	public enum ObjectType {
		METEOR("meteor"), ZOOMER("zoomer"), LINESAW("lineSaw"), ENEMY_WALL(
				"enemyWall");

		private String jsonCode;

		private ObjectType(String jsonCode) {
			this.jsonCode = jsonCode;
		}

		@Override
		public String toString() {
			return this.jsonCode;
		}
	};

	protected Point2D xLoc, yLoc;
	protected ObjectType currentType;

	public Point2D getxLoc() {
		return xLoc;
	}

	public void setxLoc(Point2D xLoc) {
		this.xLoc = xLoc;
	}

	public Point2D getyLoc() {
		return yLoc;
	}

	public void setyLoc(Point2D yLoc) {
		this.yLoc = yLoc;
	}

	public ObjectType getCurrentType() {
		return currentType;
	}

	public void setCurrentType(ObjectType currentType) {
		this.currentType = currentType;
	}

}
