package command;

import java.awt.Color;

import geometry.Line;
import geometry.Point;

public class UpdateLineCmd implements CommandShape{
	
	private Line oldLine;
	private Line newLine;
	private Line original=new Line();

	public UpdateLineCmd(Line oldLine, Line newLine) {
		
		this.oldLine = oldLine;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		
		original=oldLine.clone();
		/*original.getStartPoint().setX(oldLine.getStartPoint().getX());
		original.getStartPoint().setY(oldLine.getStartPoint().getY());
		original.getEndPoint().setX(oldLine.getEndPoint().getX());
		original.getEndPoint().setY(oldLine.getEndPoint().getY());
		//original.setColor(oldLine.getColor());*/


		oldLine.getStartPoint().setX(newLine.getStartPoint().getX());
		oldLine.getStartPoint().setY(newLine.getStartPoint().getY());
		oldLine.getEndPoint().setX(newLine.getEndPoint().getX());
		oldLine.getEndPoint().setY(newLine.getEndPoint().getY());
		oldLine.setColor(newLine.getColor());
		
		
	}

	@Override
	public void unexecute() {
		
		oldLine.getStartPoint().setX(original.getStartPoint().getX());
		oldLine.getStartPoint().setY(original.getStartPoint().getY());
		oldLine.getEndPoint().setX(original.getEndPoint().getX());
		oldLine.getEndPoint().setY(original.getEndPoint().getY());
		oldLine.setColor(original.getColor());
		
	}

}
