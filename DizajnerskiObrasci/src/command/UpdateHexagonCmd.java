package command;

import adapter.HexagonAdapter;
import hexagon.Hexagon;

public class UpdateHexagonCmd implements CommandShape{

	private HexagonAdapter oldHexagon;
	private HexagonAdapter newHexagon;
	private HexagonAdapter original = new HexagonAdapter();
	
	public UpdateHexagonCmd(HexagonAdapter oldHexagon, HexagonAdapter newHexagon) {
		
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original=oldHexagon.clone();
		/*original.setX(oldHexagon.getX());
	    original.setY(oldHexagon.getY());		
	    original.setR(oldHexagon.getR());
	    original.setColor(oldHexagon.getColor());
	    original.setInnerColor(oldHexagon.getInnerColor());*/
	

	    oldHexagon.setX(newHexagon.getX());
	    oldHexagon.setY(newHexagon.getY());
	    oldHexagon.setR(newHexagon.getR());
		oldHexagon.setColor(newHexagon.getColor());
		oldHexagon.setInnerColor(newHexagon.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldHexagon.setX(original.getX());
		oldHexagon.setY(original.getY());
		oldHexagon.setR(original.getR());
		oldHexagon.setColor(original.getColor());
		oldHexagon.setInnerColor(original.getInnerColor());
		
	}

}
