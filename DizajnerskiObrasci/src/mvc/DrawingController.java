package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import adapter.HexagonAdapter;
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
import hexagon.Hexagon;

public class DrawingController {
	private boolean select;
    private boolean selectModify;
    private Shape shape;
    private Color pointClr;
	
	private Point sP;
	private DrawingModel model;
	
	private DrawingFrame frame;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	

	public void mouseClicked(MouseEvent e) {

		Shape sh;
		Point clickPoint=new Point(e.getX(),e.getY());
		
		if(frame.getTglbtnHexagon().isSelected()) {
			Hexagon hex = new Hexagon(e.getX(),e.getY(),15);
			sh = new HexagonAdapter(hex);
			model.add(sh);
			frame.repaint();
		}
		
			
		
		if(frame.getTglbtnPoint().isSelected()) {
			sh=new Point(e.getX(),e.getY(), Color.black);
			model.add(sh);
			frame.repaint();
			
		}
		
		if(frame.getTglbtnLine().isSelected()) {
		
			if(sP==null) {
				sP=clickPoint;
				
			}
			else {
				sh=new Line(sP, new Point(e.getX(), e.getY()), Color.black);
				
				model.add(sh);
				frame.repaint();
				sP=null;
				
			}
			
			
		}
		 if(frame.getTglbtnCircle().isSelected()) {
			DlgCircle dlgCircle=new DlgCircle();
			dlgCircle.getTxtX().setText(Integer.toString(e.getX()));
			dlgCircle.getTxtY().setText(Integer.toString(e.getY()));
			dlgCircle.getTxtX().setEditable(false);
			dlgCircle.getTxtY().setEditable(false);
			dlgCircle.setVisible(true);
			
			if(dlgCircle.isGood()) {
				Circle c=new Circle(new Point(e.getX(),e.getY()),(Integer.parseInt(dlgCircle.getTxtRadius().getText())), dlgCircle.getBtnCircleBorder().getBackground(), dlgCircle.getBtnCircleColorInner().getBackground());
				model.add(c);
				frame.repaint();
			}
			
			
			
		}
		 if(frame.getTglbtnRectangle().isSelected()) {
			 	DlgRectangle dlgRec=new DlgRectangle();
				dlgRec.getTxtX().setText(Integer.toString(e.getX()));
				dlgRec.getTxtY().setText(Integer.toString(e.getY()));
				dlgRec.getTxtX().setEditable(false);
				dlgRec.getTxtY().setEditable(false);
			 	dlgRec.setVisible(true);
			 
				if (dlgRec.isOkk()){
					int w=(Integer.parseInt(dlgRec.getTxtWidth().getText()));
					int h=(Integer.parseInt(dlgRec.getTxtHeight().getText()));
					Color c1=dlgRec.getBtnBorderColor().getBackground();
					Color c2=dlgRec.getBtnInnerColor().getBackground();
					Rectangle r=new Rectangle(new Point(e.getX(),e.getY()),w, h, c1, c2);
					model.add(r);
					frame.repaint();
				}
			
			
		}
		 if(frame.getTglbtnDonut().isSelected()) {
			DlgDonut dlgDonut =new DlgDonut();
			dlgDonut.getTxtX().setText(Integer.toString(e.getX()));
			dlgDonut.getTxtY().setText(Integer.toString(e.getY()));
			dlgDonut.getTxtX().setEditable(false);
			dlgDonut.getTxtY().setEditable(false);
		 	dlgDonut.setVisible(true);
		 	
		 	if(dlgDonut.isOkkk()) {
		 		int rad=(Integer.parseInt(dlgDonut.getTxtRadius().getText()));
		 		int inner=(Integer.parseInt(dlgDonut.getTxtInner().getText()));
		 		Donut d =new Donut(new Point(e.getX(),e.getY()), rad, inner,dlgDonut.getBtnDonutBorder().getBackground(),dlgDonut.getBtnDonutInner().getBackground());
		 		model.add(d);
		 		frame.repaint();
		 	}
		
		}
		
		 if(frame.getTglbtnSelect().isSelected()) {
	
			 Point point=new Point(e.getX(),e.getY());
			 shapesSelect(point.getX(),point.getY());
		//	 tglbtnSelect.setSelected(false);
				

		 }

		 
	
		
	}
	
	public void shapesSelect(int x, int y) {
		shape=null;
		if(counter()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
		}
		for(int i=0;i<model.getShapes().size();i++) {
			Shape shp=model.getShapes().get(i);
			shp.setSelected(false);
			if(model.getShapes().get(i).contains(x, y)) {
				shape=shp;	
				setSelect(false);
				setSelectModify(false);
		}
			
	}
		if(shape != null)
			shape.setSelected(true);
		frame.repaint();
		
	}
	
	public int counter() {
		int i;
		for(i=0;i<model.getShapes().size();i++);
			
		return i;
	}
	
	public void delete() {
		
		if(counter()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			setSelect(true);
		}
		
		else
		setSelect(false);
		for(int i=0;i<model.getShapes().size();i++) {
			
				if(model.getShapes().get(i).isSelected()) {
					setSelect(true);
					int selectedOption=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this shape?", "Warning message", JOptionPane.YES_NO_OPTION);
					if(selectedOption==JOptionPane.YES_OPTION) {
					model.getShapes().remove(i);
					frame.repaint();
					//setSelect(true);
					}
					else {
						model.getShapes().get(i).setSelected(false);
						frame.repaint();
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
		for(int i=0;i<model.getShapes().size();i++) {
			
					if(model.getShapes().get(i) instanceof Point) {
						
						if(model.getShapes().get(i).isSelected()) {
						DlgPoint dlgPo=new DlgPoint();
						Point p2=(Point)model.getShapes().get(i);
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
							model.getShapes().remove(i);
							frame.repaint();
							model.getShapes().add(p);	
							
						}
						
						else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
					}
					
					}
					else if(model.getShapes().get(i) instanceof Line) {
						if(model.getShapes().get(i).isSelected()) {
						DlgLine dlgLi=new DlgLine();
						Line l=(Line)model.getShapes().get(i);
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
							model.getShapes().remove(i);
							frame.repaint();
							model.getShapes().add(l2);
						}
						
						else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
					}
						
					}
					else if(model.getShapes().get(i) instanceof Rectangle) {
						if(model.getShapes().get(i).isSelected()) {
						DlgRectangle dlgRe=new DlgRectangle();
						Rectangle r1=(Rectangle)model.getShapes().get(i);
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
							model.getShapes().remove(i);
							frame.repaint();
							model.getShapes().add(r2);
						}
						else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
						
					}
						
					}
					else if(model.getShapes().get(i) instanceof Circle==true && model.getShapes().get(i) instanceof Donut ==false) {
						if(model.getShapes().get(i).isSelected()) {
						DlgCircle dlgCir=new DlgCircle();
						Circle c1=(Circle)model.getShapes().get(i);
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
							model.getShapes().remove(i);
							frame.repaint();
							model.getShapes().add(c2);
						}
						else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
						
						
					}
						
					}
					else if(model.getShapes().get(i) instanceof Donut) {
						if(model.getShapes().get(i).isSelected()) {
						DlgDonut dlgDo=new DlgDonut();
						Donut d1=(Donut) model.getShapes().get(i);
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
							model.getShapes().remove(i);
							frame.repaint();
							model.getShapes().add(d2);
						
					}
						else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
						
				}
						
					}
			
		
			}
	if(isSelectModify()==false) {
		JOptionPane.showMessageDialog(null, "Please, select the shape!");
		setSelectModify(true);
	}
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
