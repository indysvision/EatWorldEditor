package com.indyvision.eatworld.editor.pojo.objects;

import javafx.geometry.Point2D;
import javafx.scene.layout.VBox;

import com.indyvision.eatworld.editor.Main;
import com.indyvision.eatworld.editor.pojo.MapObject;

public class EnemyWall extends MapObject{
	
	public EnemyWall(Point2D[] points) {
		obj = ObjectType.ENEMY_WALL;
		x = (int) points[0].getX();
		y = (int) points[0].getY();
	}

	@Override
	public VBox loadObjectProperties(Main mainContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
