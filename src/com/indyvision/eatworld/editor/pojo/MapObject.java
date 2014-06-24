package com.indyvision.eatworld.editor.pojo;

import javafx.scene.layout.VBox;

import com.indyvision.eatworld.editor.Main;

public abstract class MapObject {
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

	protected Main mainContext;
	public MapObject(Main context) {
		this.mainContext = context;
	}
	
	public abstract VBox loadObjectProperties();
	protected int x, y;
	protected ObjectType obj;

	public int getxLoc() {
		return x;
	}

	public void setxLoc(int xLoc) {
		this.x = xLoc;
	}
	

	public int getyLoc() {
		return y;
	}

	public void setyLoc(int yLoc) {
		this.y = yLoc;
	}

	public ObjectType getCurrentType() {
		return obj;
	}

	public void setCurrentType(ObjectType currentType) {
		this.obj = currentType;
	}

}
