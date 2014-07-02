package com.indyvision.eatworld.editor.pojo.objects;

import com.indyvision.eatworld.editor.Main;
import com.indyvision.eatworld.editor.pojo.MapObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Swarm extends MapObject {

	private int speed = -99999;
	private int angle = -99999;
    private int connectRadius = -99999;

	public Swarm(int x, int y, int speed, int angle, int connectRadius) {
		obj = ObjectType.swarm;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.angle = angle;
        this.connectRadius = connectRadius;
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

    public int getConnectRadius() {
        return connectRadius;
    }

    public void setConnectRadius(int connectRadius) {
        this.connectRadius = connectRadius;
    }

    @Override
	public VBox loadObjectProperties(final Main mainContext) {
		VBox objectsPanel = new VBox(8);
		objectsPanel.setPadding(new Insets(10));

		final Label l = new Label("Properties swarm");

		Label label1 = new Label("x:");
		final TextField textField1 = new TextField(String.valueOf(x));
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(label1, textField1);
		hb1.setSpacing(10);
		Label label2 = new Label("y:");
		final TextField textField2 = new TextField(String.valueOf(y));
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(label2, textField2);
		hb2.setSpacing(10);
		Label label3 = new Label("speed:");
		final TextField textField3 = new TextField(String.valueOf(speed));
		HBox hb3 = new HBox();
		hb3.getChildren().addAll(label3, textField3);
		hb3.setSpacing(10);
		Label label4 = new Label("angle:");
		final TextField textField4 = new TextField(String.valueOf(angle));
		HBox hb4 = new HBox();
		hb4.getChildren().addAll(label4, textField4);
		hb4.setSpacing(10);
        Label label5 = new Label("connectRadius:");
        final TextField textField5 = new TextField(String.valueOf(connectRadius));
        HBox hb5 = new HBox();
        hb5.getChildren().addAll(label5, textField5);
        hb5.setSpacing(10);

		Button updateBtn = new Button("Update");
		updateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				x = Integer.parseInt(textField1.getText());
				y = Integer.parseInt(textField2.getText());
				speed = Integer.parseInt(textField3.getText());
                angle = Integer.parseInt(textField4.getText());
                connectRadius = Integer.parseInt(textField5.getText());
                mainContext.drawAll();
			}
		});
		hb1.setMaxWidth(Double.MAX_VALUE);
		hb2.setMaxWidth(Double.MAX_VALUE);
		hb3.setMaxWidth(Double.MAX_VALUE);
        hb4.setMaxWidth(Double.MAX_VALUE);
        hb5.setMaxWidth(Double.MAX_VALUE);

		objectsPanel.getChildren().addAll(l, hb1, hb2, hb3, hb4, hb5, updateBtn);
//		objectsPanel.setMinWidth(250);
//		objectsPanel.setMaxWidth(250);
		return objectsPanel;	}	

}
