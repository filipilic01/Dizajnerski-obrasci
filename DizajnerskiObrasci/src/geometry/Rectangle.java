package geometry;

import java.awt.Graphics;
import java.awt.Color;

public class Rectangle extends ShapeShape implements Cloneable{

	private Point upperLeftPoint;
	private int width;
	private int height;

	public Rectangle() {
	}
	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}
	public Rectangle(Point upperLeftPoint, int width, int height, Color color) {
		this(upperLeftPoint, width, height);
		setColor(color);
	}
	public Rectangle(Point upperLeftPoint, int width, int height, Color color, Color colorInner) {
		this(upperLeftPoint, width, height, color);
		setColorInner(colorInner);
	}
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {

		this(upperLeftPoint, width, height);
		setSelected(selected);
		//menja se nakon uvodjenja klase Shape
		//this.selected = selected;
	}

	public int area() {
		return this.width * this.height;
	}
	public int circumference() {
		return this.width * 2 + this.height * 2;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) && 
					this.width==pomocna.width && this.height==pomocna.height)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public boolean contains(int x, int y) {
		if (upperLeftPoint.getX()<=x && x<=upperLeftPoint.getX()+width 
				&& upperLeftPoint.getY()<=y && y<=upperLeftPoint.getY()+height)
			return true;
		return false;
	}
	
	public boolean contains(Point p) {
		if (upperLeftPoint.getX()<=p.getX() && p.getX()<=upperLeftPoint.getX()+width 
				&& upperLeftPoint.getY()<=p.getY() && p.getY()<=upperLeftPoint.getY()+height)
			return true;
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		this.fill(g);

		if (selected) {
			g.setColor(Color.black);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width  - 2, upperLeftPoint.getY() + height - 2, 4, 4);
		}
	}
	
	public void fill(Graphics g) {
	g.setColor(getColorInner());
		g.fillRect(this.getUpperLeftPoint().getX()+1, this.getUpperLeftPoint().getY()+1, this.getWidth()-1, this.getHeight()-1);
	}

	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
	}
	
	@Override
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x,y);
		
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle) {
			return this.area()-((Rectangle)o).area();
		}
		return 0;
	}
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public String toString() {
		return "Upper left point: ["+ upperLeftPoint.getX() + "," + upperLeftPoint.getY() + "], width=" + width + ", height="+height + ", borderColor= " + getColor().getRGB() + ", innerColor= " + getColorInner().getRGB();
	}
	
	public Rectangle clone() {
		Rectangle rectangle = new Rectangle();
		
		rectangle.setUpperLeftPoint(new Point());
		
		rectangle.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
		rectangle.setHeight(this.getHeight());
		rectangle.setWidth(this.getWidth());
		rectangle.setColor(this.getColor());
		rectangle.setColorInner(this.getColorInner());
		
		return rectangle;
	}
}
	

