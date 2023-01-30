package command;

import java.util.ArrayList;

import geometry.Shape;

public class DeselectCmd implements CommandShape {
	
	private ArrayList<Shape> selectedShapes;
	private Shape shape;
		
	public DeselectCmd(ArrayList<Shape> selectedshapes, Shape shape) {
		
		this.selectedShapes=selectedshapes;
		this.shape=shape;
		
	}
		
	public void execute() {
		shape.setSelected(false);
		selectedShapes.remove(shape);
		
		
	}

	@Override
	public void unexecute() {
		selectedShapes.add(shape);
		shape.setSelected(true);
		
	}

}
