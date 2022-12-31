package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class AddToFrontCmd implements CommandShape{

	private DrawingModel model;
	private Shape shape;
	private DrawingFrame frame;

	public AddToFrontCmd(DrawingModel model, Shape shape) {
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
				if(i>=model.getShapes().size()) {
					model.getShapes().add(s);
					break;
				}
				else {
				model.getShapes().add(i+1, s);
				break;
			}
			
			
		}
		
	}
		frame.repaint();
	}

	@Override
	public void unexecute() {
		for(int i=0;i<model.getShapes().size();i++) {
			
			if(model.getShapes().get(i).isSelected()) {
				Shape s = model.getShapes().get(i);
				model.remove(s);
				if(i<=1) {
					model.getShapes().add(0,s);
					break;
				}
				else {
				model.getShapes().add(i-1, s);
				break;
			}
			
			
		}
		
	}
	frame.repaint();
		
	}

}
