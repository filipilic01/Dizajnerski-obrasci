package command;

import java.util.ArrayList;
import java.util.List;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingModel;
import strategy.Logger;

public class SelectCmd implements CommandShape{
	
	private ArrayList<Shape> selectedShapes;
	private Shape shape;


	public SelectCmd(ArrayList<Shape> selectedshapes, Shape shape) {
		
		this.selectedShapes=selectedshapes;
		this.shape=shape;
		
	}
	@Override
	public void execute() {
		selectedShapes.add(shape);
		shape.setSelected(true);
		
	}

	@Override
	public void unexecute() {
		
		selectedShapes.remove(shape);
		shape.setSelected(false);
		
	}

}
