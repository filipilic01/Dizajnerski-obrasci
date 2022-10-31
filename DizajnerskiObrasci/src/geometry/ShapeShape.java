package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class ShapeShape extends Shape {

	private Color colorInner;
	
	//public abstract boolean contains(Point p);
	// abstract void draw(Graphics g);
	public abstract void fill(Graphics g);

	public Color getColorInner() {
		return colorInner;
	}

	public void setColorInner(Color colorInner) {
		this.colorInner = colorInner;
	}
}
