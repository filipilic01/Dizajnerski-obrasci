package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape implements Cloneable{
	private Point startPoint;
	private Point endPoint;

	public Line() {
	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint,endPoint);
		setColor(color);
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
		// menja se nakon uvodjenja klase Shape
		// this.selected = selected;
	}

	public double length() {
		// return this.startPoint.distance(this.endPoint.x, this.endPoint.y); nije
		// ispravno
		return this.startPoint.distance(this.endPoint.getX(), this.endPoint.getY());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line pomocna = (Line) obj;
			if (this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint))
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean contains(int x, int y) {
		return (this.startPoint.distance(x, y) + this.endPoint.distance(x, y)) - this.length() <= 2;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
	
		if(selected == true) {
			g.setColor(Color.black);
			g.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);
		}
	}

	@Override
	public void moveTo(int x, int y) {
		// necemo je implementirati
		}

	@Override
	public void moveBy(int x, int y) {
		startPoint.moveBy(x, y);
		endPoint.moveBy(x,y);
		
	}

	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Line) {
			return(int)(this.length()-((Line)o).length());
		}
		return 0;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getStartPoint() {
		return this.startPoint;
	}
	public Point getEndPoint() {
		return this.endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return startPoint + "-- >" + endPoint;
	}
	
	public Line clone() {
		Line line = new Line();
		
		line.setStartPoint(new Point());
		line.setEndPoint(new Point());
	
		line.getStartPoint().setX(this.getStartPoint().getX());//ovo this se odnosi na oldLine
		line.getStartPoint().setY(this.getStartPoint().getY());

		line.getEndPoint().setX(this.getEndPoint().getX());
		line.getEndPoint().setY(this.getEndPoint().getY());

		line.setColor(getColor());
		
		return line;
	}
}