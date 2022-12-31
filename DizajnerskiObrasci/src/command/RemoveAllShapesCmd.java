package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveAllShapesCmd implements CommandShape{

	private DrawingModel model;
	private Shape shape;

	public RemoveAllShapesCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		ArrayList<Shape> temp = new ArrayList();
		for(int i=0;i<model.getShapes().size();i++) {
			if(!model.getShapes().get(i).isSelected()){
				
				//model.getShapes().remove(i);
				//frame.repaint();
				temp.add(model.getShapes().get(i));
			}
			
		}
		model.getShapes().clear();
	
		for(int i=0;i<temp.size();i++) {
			model.getShapes().add(temp.get(i));
			
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

}
