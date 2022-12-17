package command;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd extends CommandShape{
	private DrawingModel model;
	private Shape shape;

	public RemoveShapeCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		this.model.remove(shape);
		
	}

	@Override
	public void unexecute() {
		this.model.add(shape);
		
	}
	

}
