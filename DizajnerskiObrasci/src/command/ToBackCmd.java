package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import strategy.Logger;

public class ToBackCmd implements CommandShape{

	private DrawingModel model;
	private Shape shape;
	private DrawingFrame frame;
	private Logger logger;

	public ToBackCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}
	@Override
	public void execute() {
		/*if(model.getShapes().indexOf(shape)==0) {
			JOptionPane.showMessageDialog(null, "Shape is located at the bottom");
		}
		else {*/
			int index = model.getShapes().indexOf(shape);
			
			model.remove(shape);
				if(index==0) {
					model.getShapes().add(0, shape);
				}
				else {
					model.getShapes().add(index-1, shape);
				}
		//}
		
		/*
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
		*/
	}
	

	@Override
	public void unexecute() {
		/*if(model.getShapes().indexOf(shape)==model.getShapes().size()-1) {
			JOptionPane.showMessageDialog(null, "Shape is located at the top");
		}
		else {*/
			int index = model.getShapes().indexOf(shape);
			
			model.remove(shape);
				if(index==model.getShapes().size()) {
					model.getShapes().add(shape);
				}
				else {
					model.getShapes().add(index+1,shape);
				}
		//}
		
		/*
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
		*/
	}

}
