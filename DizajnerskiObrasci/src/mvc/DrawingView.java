package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class DrawingView extends JPanel{
	
	private DrawingModel model = new DrawingModel();

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}
	
	
	
}
