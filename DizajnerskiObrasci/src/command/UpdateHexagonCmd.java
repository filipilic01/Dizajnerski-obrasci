package command;

import adapter.HexagonAdapter;
import hexagon.Hexagon;

public class UpdateHexagonCmd implements CommandShape{

	private HexagonAdapter oldHexagon;
	private HexagonAdapter newHexagon;
	//private HexagonAdapter original = new HexagonAdapter(new Hexagon());
	
	public UpdateHexagonCmd(HexagonAdapter oldHexagon, HexagonAdapter newHexagon) {
		
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

}
