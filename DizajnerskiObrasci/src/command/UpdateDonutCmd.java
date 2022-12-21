package command;

import geometry.Donut;

public class UpdateDonutCmd implements CommandShape{

	private Donut oldDonut;
	private Donut newDonut;
	private Donut original = new Donut();
	
	public UpdateDonutCmd(Donut oldDonut, Donut newDonut) {
		this.oldDonut=oldDonut;
		this.newDonut=newDonut;
	}
	@Override
	public void execute() {
		original.getCenter().setX(oldDonut.getCenter().getX());
		original.getCenter().setY(oldDonut.getCenter().getY());
		try {
			original.setRadius(oldDonut.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		original.setInnerRadius(oldDonut.getInnerRadius());
		
		oldDonut.getCenter().setX(newDonut.getCenter().getX());
		oldDonut.getCenter().setY(newDonut.getCenter().getY());
		try {
			oldDonut.setRadius(newDonut.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldDonut.setInnerRadius(newDonut.getInnerRadius());
		
	}

	@Override
	public void unexecute() {
		oldDonut.getCenter().setX(original.getCenter().getX());
		oldDonut.getCenter().setY(original.getCenter().getY());
		try {
			oldDonut.setRadius(original.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldDonut.setInnerRadius(original.getInnerRadius());
		
	}

}
