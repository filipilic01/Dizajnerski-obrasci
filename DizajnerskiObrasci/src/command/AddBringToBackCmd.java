package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class AddBringToBackCmd implements CommandShape{

	private DrawingModel model;
	private Shape shape;
	private DrawingFrame frame;

	public AddBringToBackCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}
	@Override
	public void execute() {
		
		for(int i=0;i<model.getShapes().size();i++) {
			if(model.getShapes().get(i).isSelected()) {
				Shape s = model.getShapes().get(i);
				model.remove(s);
				model.getShapes().add(0, s);
				break;
			}
			
		}
		
		frame.repaint();
		
	}

	@Override
	public void unexecute() {
	for(int i=0;i<model.getShapes().size();i++) {
			
			if(model.getShapes().get(i).isSelected()) {
				Shape s = model.getShapes().get(i);
				model.getShapes().remove(i);
				model.add(s);
				break;
			}
			
			
		}
		//model.getShapes().remove(s);
		frame.repaint();
		
	}

}
