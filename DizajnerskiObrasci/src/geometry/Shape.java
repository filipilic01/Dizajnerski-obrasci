package geometry;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Moveable, Comparable {

	//private boolean selected;//moze biti definisana kao protected da se ne bi menjao ostatak koda
	protected boolean selected;
	private Color color;
	
	public Shape() {

	}
	public Shape(Color color) {
		this.setColor(color);
	}

	public Shape(boolean selected) {
		this.selected = selected;
	}

	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
