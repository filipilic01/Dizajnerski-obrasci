package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements CommandShape{

	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private Rectangle original = new Rectangle();
	
	public UpdateRectangleCmd(Rectangle oldRectangle, Rectangle newRectangle) {
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
	}

	@Override
	public void execute() {
		original=oldRectangle.clone();
		/*
		original.getUpperLeftPoint().setX(oldRectangle.getUpperLeftPoint().getX());
		original.getUpperLeftPoint().setY(oldRectangle.getUpperLeftPoint().getY());
		original.setHeight(oldRectangle.getHeight());
		original.setWidth(oldRectangle.getWidth());
		original.setColor(oldRectangle.getColor());
		original.setColorInner(oldRectangle.getColorInner());
		*/
		
		oldRectangle.getUpperLeftPoint().setX(newRectangle.getUpperLeftPoint().getX());
		oldRectangle.getUpperLeftPoint().setY(newRectangle.getUpperLeftPoint().getY());
		oldRectangle.setHeight(newRectangle.getHeight());
		oldRectangle.setWidth(newRectangle.getWidth());
		oldRectangle.setColor(newRectangle.getColor());
		oldRectangle.setColorInner(newRectangle.getColorInner());
		
	}

	@Override
	public void unexecute() {
		oldRectangle.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX());
		oldRectangle.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		oldRectangle.setHeight(original.getHeight());
		oldRectangle.setWidth(original.getWidth());
		oldRectangle.setColor(original.getColor());
		oldRectangle.setColorInner(original.getColorInner());
		
	}

}
