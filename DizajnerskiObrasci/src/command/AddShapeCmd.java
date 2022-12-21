package command;

import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCmd implements CommandShape{
	
	private DrawingModel model;
	private Shape shape;

	public AddShapeCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		this.model.add(shape);
		
	}

	@Override
	public void unexecute() {
		this.model.remove(shape);
		
	}

}
