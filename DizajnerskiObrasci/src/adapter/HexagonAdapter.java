package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;
import geometry.Shape;
import geometry.ShapeShape;
import hexagon.Hexagon;

public class HexagonAdapter extends ShapeShape implements Cloneable{
	
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

	public HexagonAdapter() {
		// TODO Auto-generated constructor stub
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
		hexagon.paint(g);

	}
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	public void setInnerColor(Color innerColor) {
		
		hexagon.setAreaColor(innerColor);
		//super.setInnerColor(innerColor);
		
	}
	public Color getColor() {
		return hexagon.getBorderColor();
	}

	public void setColor(Color color) {
		hexagon.setBorderColor(color);
		//super.setColor(color);
	}
	public int getR() {
		return hexagon.getR();
	}
	public void setR(int radius) {
		hexagon.setR(radius);
	}
	public int getX() {
		return hexagon.getX();
	}
	public void setX(int x) {
		hexagon.setX(x);
	}
	public int getY() {
		return hexagon.getY();
	}
	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return "Center: " + "[" + getX() + "," + getY() + "]" + ", radius: " + getR() + ", borderColor= " + getColor().getRGB() + ", innerColor= " + getInnerColor().getRGB(); 
	}
	
	public HexagonAdapter clone() {
		HexagonAdapter hexagonAdapter=new HexagonAdapter();
		
		hexagonAdapter.setHexagon(new Hexagon(0,0,0));
		
		hexagonAdapter.setX(this.getX());
		hexagonAdapter.setY(this.getY());		
		hexagonAdapter.setR(this.getR());
		hexagonAdapter.setColor(this.getColor());
		hexagonAdapter.setInnerColor(this.getInnerColor());
		
		return hexagonAdapter;
	}

	


	

	

	
	

	

	
}
