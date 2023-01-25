package geometry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.Color;

public class Donut extends Circle implements Cloneable{

	private int innerRadius;
	public Donut() {
	}
	public Donut(Point center, int radius, int innerRadius) {
		// this.center - ne moze jer je private u Circle klasi
		/*
		 * this.setCenter(center); this.setRadius(radius);
		 */
		super(center, radius);// prva naredba
		this.innerRadius = innerRadius;
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		//this.setSelected(selected);//jer je selected u Circle private

		setSelected(selected);
		//menja se nakon uvodjenja klase Shape
		//this.selected = selected;//mogu ako je selected u Circle protected
	}
	
	public Donut(Point center, int radius, int innerRadius, Color color) {
		this(center, radius, innerRadius);
		setColor(color);
		
	}
	
	public Donut(Point center, int radius, int innerRadius, Color color, Color colorInner) {
		this(center, radius, innerRadius, color);
		setColorInner(colorInner);
		}

	public double area() {
		return super.area() - innerRadius*innerRadius*Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			if (getCenter().equals(pomocni.getCenter()) && getRadius() == pomocni.getRadius() && innerRadius == pomocni.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double dFromCenter = getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}	

	@Override
	public void draw(Graphics g) {
		super.draw(g);//crtanje spoljasnjeg kruga
		g.setColor(getColor());
		g.drawOval(center.getX()-innerRadius, center.getY()-innerRadius, innerRadius*2, innerRadius*2);
	
		if(selected == true) {
			g.setColor(Color.black);
			g.drawRect(this.getCenter().getX() - innerRadius - 2, this.getCenter().getY() - 2, 4, 4);
			g.drawRect(this.getCenter().getX() + innerRadius - 2, this.getCenter().getY() - 2, 4, 4);
			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() - innerRadius - 2, 4, 4);
			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() + innerRadius - 2, 4, 4);
	}
	}
	
	public void fill(Graphics g) {
		Ellipse2D eIner=new Ellipse2D.Float(center.getX()-innerRadius,center.getY()- innerRadius,2* innerRadius,2* innerRadius);
		Ellipse2D eOuter=new Ellipse2D.Float(center.getX()-getRadius(),center.getY()- getRadius(),2* getRadius(),2* getRadius());
		Area outer=new Area(eOuter);
		Area iner=new Area(eIner);
		outer.subtract(iner);
		
		g.setColor(getColorInner());
		((Graphics2D) g).fill(outer);
		
		//g.setColor(getColorInner());
		/*super.fill(g);
	    g.setColor(Color.white);
		g.fillOval(center.getX()-innerRadius+1, center.getY()-innerRadius+1, innerRadius*2-1, innerRadius*2-1);*/
	}

	public int compareTo(Object o) {
		if(o instanceof Donut) {
			return (int)(this.area()-((Donut)o).area());
		}
		return 0;
	}
	public String toString() {
		return super.toString()+", innerRadius="+innerRadius;
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public Donut clone() {
		Donut donut =new Donut();
		
		donut.setCenter(new Point());
		
		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());
		try {
			donut.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setInnerRadius(this.getInnerRadius());
		donut.setColor(this.getColor());
		donut.setColorInner(this.getColorInner());
		return donut;
	}
}

