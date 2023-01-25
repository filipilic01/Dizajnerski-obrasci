package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import strategy.Logger;

public class BringToBackCmd implements CommandShape{

	private DrawingModel model;
	private Shape shape;
	private DrawingFrame frame;
	private Logger logger;

	public BringToBackCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}
	@Override
	public void execute() {
	/*	if(model.getShapes().indexOf(shape)==0) {
			JOptionPane.showMessageDialog(null, "Shape is located at the bottom");
		}
		else {
			*/
		
		model.remove(shape);
		model.getShapes().add(0,shape);
		
		/*for(int i=0;i<model.getShapes().size();i++) {
			if(model.getShapes().get(i).isSelected()) {
				Shape s = model.getShapes().get(i);
				model.remove(s);
				model.getShapes().add(0, s);
				break;
			}
			
		}
		
		frame.repaint();*/
		}
		
	//}

	@Override
	public void unexecute() {
	/*	if(model.getShapes().indexOf(shape)==model.getShapes().size()-1) {
			JOptionPane.showMessageDialog(null, "Shape is located at the top");
		}
		else {*/
			model.getShapes().remove(shape);
			model.add(shape);
		//}
		
		
/*	for(int i=0;i<model.getShapes().size();i++) {
			
			if(model.getShapes().get(i).isSelected()) {
				Shape s = model.getShapes().get(i);
				model.getShapes().remove(i);
				model.add(s);
				break;
			}
			
			
		}
		//model.getShapes().remove(s);
		frame.repaint();
		*/
	}

}
