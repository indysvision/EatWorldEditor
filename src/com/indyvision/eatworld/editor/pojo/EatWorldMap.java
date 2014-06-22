package com.indyvision.eatworld.editor.pojo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javafx.geometry.Point2D;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.indyvision.eatworld.editor.pojo.objects.LineSaw;
import com.indyvision.eatworld.editor.pojo.objects.Meteor;
import com.indyvision.eatworld.editor.pojo.objects.Zoomer;

public class EatWorldMap {
	private ArrayList<MapObject> objects;
	// private ArrayList<Point2D> vertices;
	LevelInMap level;
	private String name;
	private String fileName;

	public void writeData() {
		Gson gson = new Gson();
		// System.out.println(gson.toJson(this));

		PrintWriter writer;
		try {
			writer = new PrintWriter(this.fileName, "UTF-8");
			writer.println(gson.toJson(this));
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "name - " + getName() + "/nborders "+ getVertices();
	}
	
	public String toJson(){
		return (new Gson()).toJson(this);
	}
	
	public static EatWorldMap loadData(String fileName) {
		String everything = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("pre conversed = \n" + everything);
		Gson gson = new Gson();
		EatWorldMap map = new EatWorldMap();
		map = gson.fromJson(everything, map.getClass());
		map.getObjects().clear(); // they are wrong so delete them

		JsonParser parser = new JsonParser();
		JsonArray obj = parser.parse(everything).getAsJsonObject()
				.getAsJsonArray("objects");
		for (int i = 0; i < obj.size(); i++) {
			JsonObject object = obj.get(i).getAsJsonObject();
			String type = object.get("obj").getAsString();
			if (type.equals("METEOR")) {
				map.objects.add(new Meteor(object.get("x").getAsInt(), object
						.get("y").getAsInt(), object.get("speed").getAsInt(),
						object.get("angle").getAsInt()));
			} else if (type.equals("ZOOMER")) {
				map.objects.add(new Zoomer(object.get("x").getAsInt(), object
						.get("y").getAsInt(), object.get("resetDistance").getAsInt(),
						object.get("dampingRatio").getAsInt(), object.get("maxForce")
								.getAsInt(), object.get("frequency").getAsInt()));
			}
			else if (type.equals("LINESAW")) {
				map.objects.add(new LineSaw(object.get("x").getAsInt(), object
						.get("y").getAsInt(), object.get("speed").getAsInt(),
						object.get("torque").getAsInt()));
			}
		}
		System.out.println("conversed = \n" + gson.toJson(map));
		return map;
	}

	public EatWorldMap() {
		objects = new ArrayList<>();
		// vertices = new ArrayList<>();
		level = new LevelInMap();
	}

	public ArrayList<MapObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<MapObject> objects) {
		this.objects = objects;
	}

	public ArrayList<Point2D> getVertices() {
		return null;
	}

	public void setVertices(ArrayList<Point2D> vertices) {
		int[] verticesInt = new int[2 * vertices.size()];
		for (int i = 0; i < vertices.size(); i++) {
			verticesInt[2 * i] = (int) vertices.get(i).getX();
			verticesInt[2 * i + 1] = (int) vertices.get(i).getY();
		}
		level.setBorder(verticesInt);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
