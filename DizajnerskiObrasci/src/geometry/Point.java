package geometry;

import java.awt.Graphics;
import java.awt.Color;

public class Point extends Shape implements Cloneable{
	private int x;
	private int y;

	public Point() {
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, Color color) {
		this(x,y);
		setColor(color);
	}
	public Point(int x, int y, boolean selected) {
		this(x, y);
		// this.x = x;
		// this.y = y;
		setSelected(selected);
		//menja se nakon uvodjenja klase Shape
		//this.selected = selected;
	}

	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;
			if (this.x == pomocna.x && this.y == pomocna.y)
				return true;
			else
				return false;
		} else
			return false;
	}
	public boolean contains(int x, int y) {
		/*
		 * if(this.distance(x, y) <=2) { return true; } return false;
		 */
		return this.distance(x, y) <= 2;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);
		
		if(selected==true) {
			g.setColor(Color.black);
			g.drawRect(x-2, y-2, 4, 4);
		}
	}

	
	@Override
	public void moveTo(int x, int y) {
		this.x=x;
		this.y=y;
		}
	
	@Override
	public void moveBy(int x, int y) {
	this.x=this.x+x;
	this.y=this.y+y;
		
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Point) {
			return (int)(this.distance(0, 0)-((Point)o).distance(0,0));
	}
		return 0;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}

	public String toString() {
		return "[" + x + "," + y + "]" + ", color= " + getColor().getRGB();
	}
	
	public Point clone() {
		Point point =new Point();
		
		point.setX(this.getX());
		point.setY(this.getY());
		point.setColor(this.getColor());
		
		return point;
		
	}
}