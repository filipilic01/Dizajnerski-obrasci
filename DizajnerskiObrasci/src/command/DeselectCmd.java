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
		
		selectedShapes.remove(shape);
		shape.setSelected(false);
		
	}

	@Override
	public void unexecute() {
		selectedShapes.add(shape);
		shape.setSelected(true);
		
	}

}
