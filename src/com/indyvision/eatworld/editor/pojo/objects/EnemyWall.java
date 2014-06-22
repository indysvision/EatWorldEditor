package com.indyvision.eatworld.editor.pojo.objects;

import javafx.geometry.Point2D;

import com.indyvision.eatworld.editor.pojo.MapObject;

public class EnemyWall extends MapObject{
	
	public EnemyWall(Point2D[] points) {
		obj = ObjectType.ENEMY_WALL;
		x = (int) points[0].getX();
		y = (int) points[0].getY();
	}

}
