package com.indyvision.eatworld.editor.pojo.objects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

	@Override
	public VBox loadObjectProperties() {
		VBox objectsPanel = new VBox(8);
		objectsPanel.setPadding(new Insets(10));

		final Label l = new Label("Properties meteor");

		Label label1 = new Label("x:");
		TextField textField1 = new TextField(String.valueOf(x));
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(label1, textField1);
		hb1.setSpacing(10);
		Label label2 = new Label("y:");
		TextField textField2 = new TextField(String.valueOf(y));
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(label2, textField2);
		hb2.setSpacing(10);
		Label label3 = new Label("speed:");
		TextField textField3 = new TextField(String.valueOf(speed));
		HBox hb3 = new HBox();
		hb3.getChildren().addAll(label3, textField3);
		hb3.setSpacing(10);
		Label label4 = new Label("angle:");
		TextField textField4 = new TextField(String.valueOf(angle));
		HBox hb4 = new HBox();
		hb4.getChildren().addAll(label4, textField4);
		hb4.setSpacing(10);

		Button updateBtn = new Button("Update");
		updateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		objectsPanel.getChildren().addAll(hb1, hb2, hb3, hb4, updateBtn);
		objectsPanel.setMinWidth(230);
		objectsPanel.setMaxWidth(230);
		return objectsPanel;	}	

}
