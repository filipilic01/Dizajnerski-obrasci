package command;

import geometry.Circle;

public class UpdateCircleCmd implements CommandShape{

	private Circle oldCircle;
	private Circle newCircle;
	private Circle original = new Circle();
	
	public UpdateCircleCmd(Circle oldCircle, Circle newCircle) {
		this.oldCircle=oldCircle;
		this.newCircle=newCircle;
	}
	
	public void execute(){
		original=oldCircle.clone();
		/*original.getCenter().setX(oldCircle.getCenter().getX());
		original.getCenter().setY(oldCircle.getCenter().getY());
		try {
			original.setRadius(oldCircle.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		original.setColor(oldCircle.getColor());
		original.setColorInner(original.getColorInner());
		*/
		oldCircle.getCenter().setX(newCircle.getCenter().getX());
		oldCircle.getCenter().setY(newCircle.getCenter().getY());
		try {
			oldCircle.setRadius(newCircle.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldCircle.setColor(newCircle.getColor());
		oldCircle.setColorInner(newCircle.getColorInner());
		
	}
	
	public void unexecute() {
		oldCircle.getCenter().setX(original.getCenter().getX());
		oldCircle.getCenter().setY(original.getCenter().getY());
		try {
			oldCircle.setRadius(original.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldCircle.setColor(original.getColor());
		oldCircle.setColorInner(original.getColorInner());
	}
}
