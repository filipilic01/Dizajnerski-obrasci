package drawing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class PnlDrawing extends JPanel {
	
	
	private ArrayList<Shape> shapes= new ArrayList<Shape>();
    private boolean select;
    private boolean selectModify;
    private Shape shape;
  private Color pointClr;
	

	/**
	 * Create the panel.
	 */
	public PnlDrawing() {
	     
			}
    

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	
	public void shapesAdd(Shape shape) {
		this.shapes.add(shape);
		repaint();		
	}
	
	public void shapesSelect(int x, int y) {
		shape=null;
		if(counter()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
		}
		for(int i=0;i<shapes.size();i++) {
			Shape shp=shapes.get(i);
			shp.setSelected(false);
			if(shapes.get(i).contains(x, y)) {
				shape=shp;	
				setSelect(false);
				setSelectModify(false);
		}
			
	}
		if(shape != null)
			shape.setSelected(true);
		repaint();
		
	}
	
	public void delete() {
		
		if(counter()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			setSelect(true);
		}
		
		else
		setSelect(false);
		for(int i=0;i<shapes.size();i++) {
			
				if(shapes.get(i).isSelected()) {
					setSelect(true);
					int selectedOption=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this shape?", "Warning message", JOptionPane.YES_NO_OPTION);
					if(selectedOption==JOptionPane.YES_OPTION) {
					shapes.remove(i);
					repaint();
					//setSelect(true);
					}
					else {
						shapes.get(i).setSelected(false);
						repaint();
						setSelect(true);
					}
				}
				
		}
		if(isSelect()==false) {
			JOptionPane.showMessageDialog(null, "Please, select the shape!");
			setSelect(true);
		}
		}
	
	
	public void modify() {
		if(counter()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			setSelectModify(true);
		}
		else
			setSelectModify(false);
		for(int i=0;i<shapes.size();i++) {
			
					if(shapes.get(i) instanceof Point) {
						
						if(shapes.get(i).isSelected()) {
						DlgPoint dlgPo=new DlgPoint();
						Point p2=(Point)shapes.get(i);
						dlgPo.getTxtX().setText(Integer.toString(p2.getX()));
						dlgPo.getTxtY().setText(Integer.toString(p2.getY()));
						dlgPo.getBtnColorPoint().setBackground(p2.getColor());
						dlgPo.setVisible(true);
						setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
						
						if(dlgPo.isOok()) {
							int cooX=(Integer.parseInt(dlgPo.getTxtX().getText()));
							int cooY=(Integer.parseInt(dlgPo.getTxtY().getText()));
							Point p=new Point(cooX,cooY, dlgPo.getBtnColorPoint().getBackground());
							shapes.remove(i);
							repaint();
							shapes.add(p);	
							
						}
						
						else {
							shapes.get(i).setSelected(false);
							repaint();
						}
					}
					
					}
					else if(shapes.get(i) instanceof Line) {
						if(shapes.get(i).isSelected()) {
						DlgLine dlgLi=new DlgLine();
						Line l=(Line)shapes.get(i);
						dlgLi.getTxtSPX().setText(Integer.toString(l.getStartPoint().getX()));
						dlgLi.getTxtSPY().setText(Integer.toString(l.getStartPoint().getY()));
						dlgLi.getTxtEPX().setText(Integer.toString(l.getEndPoint().getX()));
						dlgLi.getTxtEPY().setText(Integer.toString(l.getEndPoint().getY()));
						dlgLi.getBtnColorLine().setBackground(l.getColor());
						dlgLi.setVisible(true);
						setSelectModify(true);
						//shapes.remove(i);
					//	repaint();
						
					
						if(dlgLi.isOkayy()) {
							int sX=(Integer.parseInt(dlgLi.getTxtSPX().getText()));
							int sY=(Integer.parseInt(dlgLi.getTxtSPY().getText()));
							int eX=(Integer.parseInt(dlgLi.getTxtEPX().getText()));
							int eY=(Integer.parseInt(dlgLi.getTxtEPY().getText()));
							Line l2=new Line(new Point(sX,sY), new Point(eX, eY), dlgLi.getBtnColorLine().getBackground());
							shapes.remove(i);
							repaint();
							shapes.add(l2);
						}
						
						else {
							shapes.get(i).setSelected(false);
							repaint();
						}
					}
						
					}
					else if(shapes.get(i) instanceof Rectangle) {
						if(shapes.get(i).isSelected()) {
						DlgRectangle dlgRe=new DlgRectangle();
						Rectangle r1=(Rectangle)shapes.get(i);
						dlgRe.getTxtX().setText(Integer.toString(r1.getUpperLeftPoint().getX()));
						dlgRe.getTxtY().setText(Integer.toString(r1.getUpperLeftPoint().getY()));
						dlgRe.getTxtWidth().setText(Integer.toString(r1.getWidth()));
						dlgRe.getTxtHeight().setText(Integer.toString(r1.getHeight()));
						dlgRe.getBtnBorderColor().setBackground(r1.getColor());
						dlgRe.getBtnInnerColor().setBackground(r1.getColorInner());
						dlgRe.setVisible(true);
						setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
						
						if(dlgRe.isOkk()) {
							int uX=(Integer.parseInt(dlgRe.getTxtX().getText()));
							int uY=(Integer.parseInt(dlgRe.getTxtY().getText()));
							int wid=(Integer.parseInt(dlgRe.getTxtWidth().getText()));
							int he=(Integer.parseInt(dlgRe.getTxtHeight().getText()));
							Color clr1= dlgRe.getBtnBorderColor().getBackground();
							Color clr2=dlgRe.getBtnInnerColor().getBackground();
							
							Rectangle r2=new Rectangle(new Point(uX, uY), wid, he, clr1, clr2);
							shapes.remove(i);
							repaint();
							shapes.add(r2);
						}
						else {
							shapes.get(i).setSelected(false);
							repaint();
						}
						
					}
						
					}
					else if(shapes.get(i) instanceof Circle==true && shapes.get(i) instanceof Donut ==false) {
						if(shapes.get(i).isSelected()) {
						DlgCircle dlgCir=new DlgCircle();
						Circle c1=(Circle)shapes.get(i);
						dlgCir.getTxtX().setText(Integer.toString(c1.getCenter().getX()));
						dlgCir.getTxtY().setText(Integer.toString(c1.getCenter().getY()));
						dlgCir.getTxtRadius().setText(Integer.toString(c1.getRadius()));
						dlgCir.getBtnCircleBorder().setBackground(c1.getColor());
						dlgCir.getBtnCircleColorInner().setBackground(c1.getColorInner());
						dlgCir.setVisible(true);
						setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
					
						if(dlgCir.isGood()) {
							int xx=(Integer.parseInt(dlgCir.getTxtX().getText()));
							int yy=(Integer.parseInt(dlgCir.getTxtY().getText()));
							int ra=(Integer.parseInt(dlgCir.getTxtRadius().getText()));
							Color col1=dlgCir.getBtnCircleBorder().getBackground();
							Color col2=dlgCir.getBtnCircleColorInner().getBackground();
							Circle c2=new Circle(new Point(xx, yy),ra, col1, col2);
							shapes.remove(i);
							repaint();
							shapes.add(c2);
						}
						else {
							shapes.get(i).setSelected(false);
							repaint();
						}
						
						
					}
						
					}
					else if(shapes.get(i) instanceof Donut) {
						if(shapes.get(i).isSelected()) {
						DlgDonut dlgDo=new DlgDonut();
						Donut d1=(Donut) shapes.get(i);
						dlgDo.getTxtX().setText(Integer.toString(d1.getCenter().getX()));
						dlgDo.getTxtY().setText(Integer.toString(d1.getCenter().getY()));
						dlgDo.getTxtRadius().setText(Integer.toString(d1.getRadius()));
						dlgDo.getTxtInner().setText(Integer.toString(d1.getInnerRadius()));
						dlgDo.getBtnDonutBorder().setBackground(d1.getColor());
						dlgDo.getBtnDonutInner().setBackground(d1.getColorInner());
						dlgDo.setVisible(true);
						setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
						if(dlgDo.isOkkk()) {
							int dX=(Integer.parseInt(dlgDo.getTxtX().getText()));
							int dY=(Integer.parseInt(dlgDo.getTxtY().getText()));
							int radi=(Integer.parseInt(dlgDo.getTxtRadius().getText()));
							int in=(Integer.parseInt(dlgDo.getTxtInner().getText()));
							Donut d2=new Donut(new Point(dX, dY),radi, in, dlgDo.getBtnDonutBorder().getBackground(),dlgDo.getBtnDonutInner().getBackground());
							shapes.remove(i);
							repaint();
							shapes.add(d2);
						
					}
						else {
							shapes.get(i).setSelected(false);
							repaint();
						}
						
				}
						
					}
			
		
			}
	if(isSelectModify()==false) {
		JOptionPane.showMessageDialog(null, "Please, select the shape!");
		setSelectModify(true);
	}
}
		
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it=shapes.iterator();
		while(it.hasNext())
			it.next().draw(g);		
		
	}
	public int counter() {
		int i;
		for(i=0;i<shapes.size();i++);
			
		return i;
	}


	public boolean isSelect() {
		return select;
	}


	public void setSelect(boolean select) {
		this.select = select;
	}


	public boolean isSelectModify() {
		return selectModify;
	}


	public void setSelectModify(boolean selectModify) {
		this.selectModify = selectModify;
	}

}

