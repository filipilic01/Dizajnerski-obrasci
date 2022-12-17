package command;

import geometry.Circle;

public class UpdateCircleCmd {

	private Circle oldCircle;
	private Circle newCircle;
	private Circle original = new Circle();
	
	public UpdateCircleCmd(Circle oldCircle, Circle newCircle) {
		this.oldCircle=oldCircle;
		this.newCircle=newCircle;
	}
	
	public void execute(){
		original.getCenter().setX(oldCircle.getCenter().getX());
		original.getCenter().setY(oldCircle.getCenter().getY());
		//original.setRadius(oldCircle.getRadius());
		
		oldCircle.getCenter().setX(newCircle.getCenter().getX());
		oldCircle.getCenter().setY(newCircle.getCenter().getY());
		//oldCircle.setRadius(newCircle.getRadius());
		
	}
}
