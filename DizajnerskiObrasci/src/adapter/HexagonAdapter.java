package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Shape;
import geometry.ShapeShape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape{
	
	private Hexagon hexagon;
	
	
	


	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public HexagonAdapter(Hexagon hex) {
		this.hexagon=hex;
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);
		
	}
	
	


	

	

	
	

	

	
}
