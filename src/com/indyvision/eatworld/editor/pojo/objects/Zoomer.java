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

	@Override
	public VBox loadObjectProperties() {
		VBox objectsPanel = new VBox(8);
		objectsPanel.setPadding(new Insets(10));

		final Label l = new Label("Properties linesaw");

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
		Label label3 = new Label("reset dist:");
		TextField textField3 = new TextField(String.valueOf(resetDistance));
		HBox hb3 = new HBox();
		hb3.getChildren().addAll(label3, textField3);
		hb3.setSpacing(10);
		Label label4 = new Label("damping ratio:");
		TextField textField4 = new TextField(String.valueOf(dampingRatio));
		HBox hb4 = new HBox();
		hb4.getChildren().addAll(label4, textField4);
		hb4.setSpacing(10);
		Label label5 = new Label("max force:");
		TextField textField5 = new TextField(String.valueOf(maxForce));
		HBox hb5 = new HBox();
		hb5.getChildren().addAll(label5, textField5);
		hb5.setSpacing(10);
		Label label6 = new Label("freq:");
		TextField textField6 = new TextField(String.valueOf(frequency));
		HBox hb6 = new HBox();
		hb6.getChildren().addAll(label6, textField6);
		hb6.setSpacing(10);

		Button updateBtn = new Button("Update");
		updateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		objectsPanel.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb6, updateBtn);
		objectsPanel.setMinWidth(230);
		objectsPanel.setMaxWidth(230);

		return objectsPanel;
	}
    
    
    
    
}
