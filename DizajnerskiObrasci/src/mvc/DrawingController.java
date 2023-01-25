package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import adapter.HexagonAdapter;

import command.AddShapeCmd;

import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.CommandShape;
import command.RemoveShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
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
import strategy.Logger;
import strategy.Save;


public class DrawingController {
	private boolean select;
    private boolean selectModify;
    private Shape shape;
    private ArrayList<CommandShape> commands = new ArrayList();
	private Point sP;
	private DrawingModel model;
	private int num = 0;
	private CommandShape command;
	private AddShapeCmd addShapeCmd;
	private RemoveShapeCmd removeShapeCmd;
	private UpdatePointCmd updatePointCmd;
	private UpdateLineCmd updateLineCmd;
	private UpdateRectangleCmd updateRectCmd;
	private UpdateCircleCmd updateCircleCmd;
	private UpdateDonutCmd updateDonutCmd;
	private UpdateHexagonCmd updateHexagonCmd;
	private BringToBackCmd bringToBackCmd;
	private BringToFrontCmd bringToFrontCmd;
	private ToFrontCmd toFrontCmd;
	private ToBackCmd toBackCmd;
	private int indexUndo;
	private int numOfSelectedShapes=0;
	private ArrayList<CommandShape> temp = new ArrayList();
	private ArrayList<Shape> selectedShapes = new ArrayList();
	private Color border;
	private Color inner;
	private DlgDonut dlgDonut; 
	private DlgRectangle dlgRec;
	private DlgHexagon dlgHexagon; 
	private DlgCircle dlgCircle;
	private DlgPoint dlgPo;
	private DlgLine dlgLi;
	private DrawingFrame frame;
	private PropertyChangeSupport propertyChangeSupport;
	private Logger logger;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		propertyChangeSupport = new PropertyChangeSupport(this);
		logger = new Logger(this.frame.getTextArea_1());
	}

	

	public void colorTheBorder() {
		border = JColorChooser.showDialog(null, "Choose a color!", border);
		frame.getBtnBorder().getBackground();
		if(border!=null) {
			frame.getBtnBorder().setBackground(border);
		}
	}
	
	public void colorTheInner() {
		inner = JColorChooser.showDialog(null, "Choose a color!", inner);
		frame.getBtnInner().getBackground();
		if(inner!=null) {
			frame.getBtnInner().setBackground(inner);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		//CommandShape command=null;

		Shape sh;
		Point clickPoint=new Point(e.getX(),e.getY());
		

		if(frame.getTglbtnPoint().isSelected()) {
			
			sP=null;
			sh=new Point(e.getX(),e.getY(), frame.getBtnBorder().getBackground());
			addShapeCmd = new AddShapeCmd(model, sh);
			//model.add(sh);
			addShapeCmd.execute();
			commands.add(addShapeCmd);
			logger.log("Added_point: " + sh.toString());
			temp.clear();
			frame.repaint();
			
		}
		
		if(frame.getTglbtnLine().isSelected()) {
			
			
			if(sP==null) {
				sP=clickPoint;
				
			}
			else {
				sh=new Line(sP, new Point(e.getX(), e.getY()), frame.getBtnBorder().getBackground());
				addShapeCmd = new AddShapeCmd(model, sh);
				//model.add(sh);
				addShapeCmd.execute();
				commands.add(addShapeCmd);
				logger.log("Added_line: " + sh.toString());
				temp.clear();
				frame.repaint();
				sP=null;
				
			}
		}
			
		if(frame.getTglbtnHexagon().isSelected()) {
			sP=null;
			dlgHexagon = new DlgHexagon();
			dlgHexagon.getTxtX().setText(Integer.toString(e.getX()));
			dlgHexagon.getTxtY().setText(Integer.toString(e.getY()));
			dlgHexagon.getTxtX().setEditable(false);
			dlgHexagon.getTxtY().setEditable(false);
			dlgHexagon.getBtnBorder().setBackground(frame.getBtnBorder().getBackground());
			dlgHexagon.getBtnInner().setBackground(frame.getBtnInner().getBackground());
			dlgHexagon.getBtnBorder().setEnabled(false);
			dlgHexagon.getBtnInner().setEnabled(false);
			dlgHexagon.setVisible(true);
			
			if(dlgHexagon.isGood()) {
				Hexagon hex = new Hexagon(e.getX(),e.getY(),Integer.parseInt(dlgHexagon.getTxtRadius().getText()));
				hex.setBorderColor(frame.getBtnBorder().getBackground());
				hex.setAreaColor(frame.getBtnInner().getBackground());
				sh = new HexagonAdapter(hex);
				addShapeCmd = new AddShapeCmd(model, sh);
				//model.add(sh);
				addShapeCmd.execute();
				commands.add(addShapeCmd);
				logger.log("Added_hexagon: " + sh.toString());
				temp.clear();
				frame.repaint();
				
				
			}
			
			
			
		}
		
		 if(frame.getTglbtnCircle().isSelected()) {
			 sP=null;
			dlgCircle=new DlgCircle();
			dlgCircle.getTxtX().setText(Integer.toString(e.getX()));
			dlgCircle.getTxtY().setText(Integer.toString(e.getY()));
			dlgCircle.getTxtX().setEditable(false);
			dlgCircle.getTxtY().setEditable(false);
			dlgCircle.getBtnCircleBorder().setBackground(frame.getBtnBorder().getBackground());
			dlgCircle.getBtnCircleColorInner().setBackground(frame.getBtnInner().getBackground());
			dlgCircle.getBtnCircleBorder().setEnabled(false);
			dlgCircle.getBtnCircleColorInner().setEnabled(false);
			dlgCircle.setVisible(true);
			
			if(dlgCircle.isGood()) {
				Circle c=new Circle(new Point(e.getX(),e.getY()),(Integer.parseInt(dlgCircle.getTxtRadius().getText())), frame.getBtnBorder().getBackground(), frame.getBtnInner().getBackground());
				addShapeCmd = new AddShapeCmd(model, c);
				//model.add(c);
				addShapeCmd.execute();
				commands.add(addShapeCmd);
				logger.log("Added_circle: " + c.toString());
				temp.clear();
				frame.repaint();
			}
			
			
			
		}
		 if(frame.getTglbtnRectangle().isSelected()) {
			 	sP=null;
			 	dlgRec=new DlgRectangle();
				dlgRec.getTxtX().setText(Integer.toString(e.getX()));
				dlgRec.getTxtY().setText(Integer.toString(e.getY()));
				dlgRec.getTxtX().setEditable(false);
				dlgRec.getTxtY().setEditable(false);
				dlgRec.getBtnBorderColor().setBackground(frame.getBtnBorder().getBackground());
				dlgRec.getBtnInnerColor().setBackground(frame.getBtnInner().getBackground());
				dlgRec.getBtnBorderColor().setEnabled(false);
				dlgRec.getBtnInnerColor().setEnabled(false);
			 	dlgRec.setVisible(true);
			 
				if (dlgRec.isOkk()){
					int w=(Integer.parseInt(dlgRec.getTxtWidth().getText()));
					int h=(Integer.parseInt(dlgRec.getTxtHeight().getText()));
					Color c1=frame.getBtnBorder().getBackground();
					Color c2=frame.getBtnInner().getBackground();
					Rectangle r=new Rectangle(new Point(e.getX(),e.getY()),w, h, c1, c2);
					addShapeCmd = new AddShapeCmd(model, r);
					//model.add(r);
					addShapeCmd.execute();
					commands.add(addShapeCmd);
					logger.log("Added_rectangle: " + r.toString());
					temp.clear();
					frame.repaint();
				}
			
			
		}
		 if(frame.getTglbtnDonut().isSelected()) {
			 sP=null;
			dlgDonut =new DlgDonut();
			dlgDonut.getTxtX().setText(Integer.toString(e.getX()));
			dlgDonut.getTxtY().setText(Integer.toString(e.getY()));
			dlgDonut.getTxtX().setEditable(false);
			dlgDonut.getTxtY().setEditable(false);
			dlgDonut.getBtnDonutBorder().setBackground(frame.getBtnBorder().getBackground());
			dlgDonut.getBtnDonutInner().setBackground(frame.getBtnInner().getBackground());
			dlgDonut.getBtnDonutBorder().setEnabled(false);
			dlgDonut.getBtnDonutInner().setEnabled(false);
		 	dlgDonut.setVisible(true);
		 	
		 	if(dlgDonut.isOkkk()) {
		 		int rad=(Integer.parseInt(dlgDonut.getTxtRadius().getText()));
		 		int inner=(Integer.parseInt(dlgDonut.getTxtInner().getText()));
		 		Donut d =new Donut(new Point(e.getX(),e.getY()), rad, inner,frame.getBtnBorder().getBackground(), frame.getBtnInner().getBackground());
		 		addShapeCmd = new AddShapeCmd(model, d);
		 		//model.add(d);
		 		addShapeCmd.execute();
		 		commands.add(addShapeCmd);
		 		logger.log("Added_donut: " + d.toString());
		 		temp.clear();
		 		frame.repaint();
		 	}
		
		}
		
		 if(frame.getTglbtnSelect().isSelected()) {
	
			 Point point=new Point(e.getX(),e.getY());
			 shapesSelect(point.getX(),point.getY());
			 //tglbtnSelect.setSelected(false);

		 }

	}
	
	public void shapesSelect(int x, int y) {
		
		/*                                      SELEKCIJA VISE OBLIKA                       */
		int counter= 0;
		shape=null;
		if(counter()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
		}
	/*	for(int i =0; i<model.getShapes().size();i++) {
			if(model.getShapes().get(i).contains(x, y)) {
				model.getShapes().get(i).setSelected(false);
				
			}
				
		}*/
		for(int i=0;i<model.getShapes().size();i++) {
			if(model.getShapes().get(i).isSelected()) {
				
			}
			else {
				Shape shp=model.getShapes().get(i);
				//shp.setSelected(false);
			
			
			if(model.getShapes().get(i).contains(x, y)) {
				shape=shp;	
				setSelect(false);
				setSelectModify(false);
		}
			
			}
	}
		if(shape != null) {
			shape.setSelected(true);
			if(shape instanceof Point) {
				logger.log("Selected_point: " + shape.toString());
			}
			else if(shape instanceof Line) {
				logger.log("Selected_line: " + shape.toString());
			}
			else if(shape instanceof Circle) {
				logger.log("Selected_circle: " + shape.toString());
			}
			else if(shape instanceof Rectangle) {
				logger.log("Selected_rectangle: " + shape.toString());
			}
			else if(shape instanceof Donut) {
				logger.log("Selected_donut: " + shape.toString());
			}
			else if(shape instanceof HexagonAdapter) {
				logger.log("Selected_hexagon: " + shape.toString());
			}
			//numOfSelectedShapes++;
			selectedShapes.add(shape);
			frame.repaint();
			
		}
		else {
			for(int i=0;i<model.getShapes().size();i++) 
			{
				if(model.getShapes().get(i).isSelected()) {
					
				
				if(model.getShapes().get(i).contains(x, y)) {
					model.getShapes().get(i).setSelected(false);
					if(model.getShapes().get(i) instanceof Point) {
						logger.log("Deselected_point: " + model.getShapes().get(i).toString());
					}
					else if(model.getShapes().get(i) instanceof Line) {
						logger.log("Deselected_line: " + model.getShapes().get(i).toString());
					}
					else if(model.getShapes().get(i) instanceof Circle) {
						logger.log("Deselected_circle: " + model.getShapes().get(i).toString());
					}
					else if(model.getShapes().get(i) instanceof Rectangle) {
						logger.log("Deselected_rectangle: " + model.getShapes().get(i).toString());
					}
					else if(model.getShapes().get(i) instanceof Donut) {
						logger.log("Deselected_donut: " + model.getShapes().get(i).toString());
					}
					else if(model.getShapes().get(i) instanceof HexagonAdapter) {
						logger.log("Deselected_hexagon: " + model.getShapes().get(i).toString());
					}
					//numOfSelectedShapes--;
					selectedShapes.remove(model.getShapes().get(i));
					frame.repaint();
					break;
				}
				else {
					counter++;
				
					//deselectAll();
					//break;
				}
			}
				else {
					counter++;
					
				}
			}
			}
			//shape.setSelected(false);
			/*for(int i=0;i<model.getShapes().size();i++) {
				if(model.getShapes().get(i).contains(x, y))
				model.getShapes().get(i).setSelected(false);
				else
					break;
			}*/
			
			
		
		//frame.repaint();
		if(counter==model.getShapes().size()) {
			deselectAll((ArrayList<Shape>)model.getShapes());
		}
		
		if(selectedShapes.size()==0) {
			propertyChangeSupport.firePropertyChange("Delete disabled", selectedShapes == null, selectedShapes != null);
			propertyChangeSupport.firePropertyChange("Modify disabled", selectedShapes == null, selectedShapes != null);
		}
		else if(selectedShapes.size()==1) {
			propertyChangeSupport.firePropertyChange("Modify enable", false, selectedShapes != null && selectedShapes.size() == 1);
			propertyChangeSupport.firePropertyChange("Delete enable", false, selectedShapes != null && selectedShapes.size() == 1);
		}
		else if(selectedShapes.size()>1){
			propertyChangeSupport.firePropertyChange("Delete enable", false, selectedShapes != null && selectedShapes.size() == 1);
			propertyChangeSupport.firePropertyChange("Modify disabled", selectedShapes == null, selectedShapes != null);
		}
		
	}
	
	public void deselectAll(ArrayList<Shape> shapes) {
		
			for(int i = 0;i<shapes.size();i++) {
				if(shapes.get(i).isSelected()) {
					if(shapes.get(i) instanceof Point) {
					logger.log("Deselected_point: " + model.getShapes().get(i).toString());
				}
				else if(shapes.get(i) instanceof Line) {
					logger.log("Deselected_line: " + model.getShapes().get(i).toString());
				}
				else if(shapes.get(i) instanceof Circle) {
					logger.log("Deselected_circle: " + model.getShapes().get(i).toString());
				}
				else if(shapes.get(i) instanceof Rectangle) {
					logger.log("Deselected_rectangle: " + model.getShapes().get(i).toString());
				}
				else if(shapes.get(i) instanceof Donut) {
					logger.log("Deselected_donut: " + model.getShapes().get(i).toString());
				}
				else if(shapes.get(i) instanceof HexagonAdapter) {
					logger.log("Deselected_hexagon: " + model.getShapes().get(i).toString());
				}
				}
				
				
			shapes.get(i).setSelected(false);
			
			
			
		}
			selectedShapes.clear();
			frame.repaint();
		//numOfSelectedShapes=0;
		
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
		
		else {
		setSelect(false);
		if(selectedShapes.size()==1) {
		for(int i=0;i<model.getShapes().size();i++) {
			
				if(model.getShapes().get(i).isSelected()) {
					setSelect(true);
					
					int selectedOption=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this shape?", "Warning message", JOptionPane.YES_NO_OPTION);
					if(selectedOption==JOptionPane.YES_OPTION) {
					RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model,model.getShapes().get(i));
					model.getShapes().get(i).setSelected(false);
					selectedShapes.remove(model.getShapes().get(i));
					//model.getShapes().remove(i);
						if(model.getShapes().get(i) instanceof Point) {
						logger.log("Removed_point: " + model.getShapes().get(i).toString());
					} else if(model.getShapes().get(i) instanceof Line) {
						logger.log("Removed_line: " + model.getShapes().get(i).toString());
					} else if(model.getShapes().get(i) instanceof Circle) {
						logger.log("Removed_circle: " + model.getShapes().get(i).toString());
					} else if(model.getShapes().get(i) instanceof Rectangle) {
						logger.log("Removed_rectangle: " + model.getShapes().get(i).toString());
					} else if(model.getShapes().get(i) instanceof Donut) {
						logger.log("Removed_donut: " + model.getShapes().get(i).toString());
					} else if(model.getShapes().get(i) instanceof HexagonAdapter) {
						logger.log("Removed_hexagon: " + model.getShapes().get(i).toString());
					}
					removeShapeCmd.execute();
					commands.add(removeShapeCmd);
				
					
					temp.clear();
					//numOfSelectedShapes--;
					frame.repaint();
					setSelect(true);
					}
					else {
						model.getShapes().get(i).setSelected(false);
						//numOfSelectedShapes=0;
						selectedShapes.clear();
						frame.repaint();
						setSelect(true);
					}
				}
				
		}
		}
		else if(selectedShapes.size() ==0) {
			JOptionPane.showMessageDialog(null, "Please, select the shape!");
			setSelect(true);
		}
		else {
			ArrayList<Shape> temporary = new ArrayList();
			int option =JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all of selected shapes?", "Warning message", JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.YES_OPTION) {
				for(int i=0;i<model.getShapes().size();i++) {
					if(!model.getShapes().get(i).isSelected()){
						
						//model.getShapes().remove(i);
						//frame.repaint();
						temporary.add(model.getShapes().get(i));
					}
					else {
						RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model,model.getShapes().get(i));
						model.getShapes().get(i).setSelected(false);
						//removeShapeCmd.execute();
						commands.add(removeShapeCmd);
						if(model.getShapes().get(i) instanceof Point) {
							logger.log("Removed_point: " + model.getShapes().get(i).toString());
						} else if(model.getShapes().get(i) instanceof Line) {
							logger.log("Removed_line: " + model.getShapes().get(i).toString());
						} else if(model.getShapes().get(i) instanceof Circle) {
							logger.log("Removed_circle: " + model.getShapes().get(i).toString());
						} else if(model.getShapes().get(i) instanceof Rectangle) {
							logger.log("Removed_rectangle: " + model.getShapes().get(i).toString());
						} else if(model.getShapes().get(i) instanceof Donut) {
							logger.log("Removed_donut: " + model.getShapes().get(i).toString());
						} else if(model.getShapes().get(i) instanceof HexagonAdapter) {
							logger.log("Removed_hexagon: " + model.getShapes().get(i).toString());
						}
					}
				}
				temp.clear();
				model.getShapes().clear();
			
				for(int i=0;i<temporary.size();i++) {
					model.getShapes().add(temporary.get(i));
					
				}
				//numOfSelectedShapes=0;
				selectedShapes.clear();
				frame.repaint();
				setSelect(true);
			}
			else {
				for(int i=0;i<model.getShapes().size();i++) {
					model.getShapes().get(i).setSelected(false);
				}
				//numOfSelectedShapes=0;
				selectedShapes.clear();
				frame.repaint();
				setSelect(true);
			}
		}
		}
		//frame.repaint();
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
						dlgPo=new DlgPoint();
						Point p2=(Point)model.getShapes().get(i);
						dlgPo.getTxtX().setText(Integer.toString(p2.getX()));
						dlgPo.getTxtY().setText(Integer.toString(p2.getY()));
						dlgPo.getBtnColorPoint().setBackground(frame.getBtnBorder().getBackground());
						dlgPo.getBtnColorPoint().setEnabled(false);
						dlgPo.setVisible(true);
						setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
						
						if(dlgPo.isOok()) {
							int cooX=(Integer.parseInt(dlgPo.getTxtX().getText()));
							int cooY=(Integer.parseInt(dlgPo.getTxtY().getText()));
							Point p=new Point(cooX,cooY, frame.getBtnBorder().getBackground());
							updatePointCmd = new UpdatePointCmd(p2,p);
							commands.add(updatePointCmd);
							updatePointCmd.execute();
							logger.log("Modify_point: " + p.toString());
							temp.clear();
							//model.getShapes().remove(i);
							
							//model.getShapes().add(p);
							
							p.setSelected(true);
							frame.repaint();
						}
						
						else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
					}
					
					}
					else if(model.getShapes().get(i) instanceof Line) {
						if(model.getShapes().get(i).isSelected()) {
						dlgLi=new DlgLine();
						Line l=(Line)model.getShapes().get(i);
						dlgLi.getTxtSPX().setText(Integer.toString(l.getStartPoint().getX()));
						dlgLi.getTxtSPY().setText(Integer.toString(l.getStartPoint().getY()));
						dlgLi.getTxtEPX().setText(Integer.toString(l.getEndPoint().getX()));
						dlgLi.getTxtEPY().setText(Integer.toString(l.getEndPoint().getY()));
						dlgLi.getBtnColorLine().setBackground(frame.getBtnBorder().getBackground());
						dlgLi.getBtnColorLine().setEnabled(false);
						dlgLi.setVisible(true);
						setSelectModify(true);
						//shapes.remove(i);
					//	repaint();
						
					
						if(dlgLi.isOkayy()) {
							int sX=(Integer.parseInt(dlgLi.getTxtSPX().getText()));
							int sY=(Integer.parseInt(dlgLi.getTxtSPY().getText()));
							int eX=(Integer.parseInt(dlgLi.getTxtEPX().getText()));
							int eY=(Integer.parseInt(dlgLi.getTxtEPY().getText()));
							Line l2=new Line(new Point(sX,sY), new Point(eX, eY), frame.getBtnBorder().getBackground());
							updateLineCmd = new UpdateLineCmd((Line)model.getShapes().get(i),l2);
							commands.add(updateLineCmd);
							//model.getShapes().remove(i);
							
						//	model.getShapes().add(l2);
						
							
							updateLineCmd.execute();
							logger.log("Modify_line: " + l2.toString());
							temp.clear();
							
							l2.setSelected(true);
							frame.repaint();
						}
						
						else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
					}
						
					}
					else if(model.getShapes().get(i) instanceof Rectangle) {
						if(model.getShapes().get(i).isSelected()) {
						dlgRec=new DlgRectangle();
						Rectangle r1=(Rectangle)model.getShapes().get(i);
						dlgRec.getTxtX().setText(Integer.toString(r1.getUpperLeftPoint().getX()));
						dlgRec.getTxtY().setText(Integer.toString(r1.getUpperLeftPoint().getY()));
						dlgRec.getTxtWidth().setText(Integer.toString(r1.getWidth()));
						dlgRec.getTxtHeight().setText(Integer.toString(r1.getHeight()));
						dlgRec.getBtnBorderColor().setBackground(frame.getBtnBorder().getBackground());
						dlgRec.getBtnInnerColor().setBackground(frame.getBtnInner().getBackground());
						dlgRec.getBtnBorderColor().setEnabled(false);
						dlgRec.getBtnInnerColor().setEnabled(false);
						dlgRec.setVisible(true);
						setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
						
						if(dlgRec.isOkk()) {
							int uX=(Integer.parseInt(dlgRec.getTxtX().getText()));
							int uY=(Integer.parseInt(dlgRec.getTxtY().getText()));
							int wid=(Integer.parseInt(dlgRec.getTxtWidth().getText()));
							int he=(Integer.parseInt(dlgRec.getTxtHeight().getText()));
							Color clr1= frame.getBtnBorder().getBackground();
							Color clr2=frame.getBtnInner().getBackground();
							
							Rectangle r2=new Rectangle(new Point(uX, uY), wid, he, clr1, clr2);
							updateRectCmd=new UpdateRectangleCmd(r1,r2);
							commands.add(updateRectCmd);
							updateRectCmd.execute();
							logger.log("Modify_rectangle: " + r2.toString());
							temp.clear();
							//model.getShapes().remove(i);
							
							//model.getShapes().add(r2);
						
							r2.setSelected(true);
							frame.repaint();
						}
						else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
						
					}
						
					}
					else if(model.getShapes().get(i) instanceof Circle==true && model.getShapes().get(i) instanceof Donut ==false) {
						if(model.getShapes().get(i).isSelected()) {
						dlgCircle=new DlgCircle();
						Circle c1=(Circle)model.getShapes().get(i);
						dlgCircle.getTxtX().setText(Integer.toString(c1.getCenter().getX()));
						dlgCircle.getTxtY().setText(Integer.toString(c1.getCenter().getY()));
						dlgCircle.getTxtRadius().setText(Integer.toString(c1.getRadius()));
						dlgCircle.getBtnCircleBorder().setBackground(frame.getBtnBorder().getBackground());
						dlgCircle.getBtnCircleColorInner().setBackground(frame.getBtnInner().getBackground());
						dlgCircle.getBtnCircleBorder().setEnabled(false);
						dlgCircle.getBtnCircleColorInner().setEnabled(false);
						dlgCircle.setVisible(true);
						setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
					
						if(dlgCircle.isGood()) {
							int xx=(Integer.parseInt(dlgCircle.getTxtX().getText()));
							int yy=(Integer.parseInt(dlgCircle.getTxtY().getText()));
							int ra=(Integer.parseInt(dlgCircle.getTxtRadius().getText()));
							Color col1=frame.getBtnBorder().getBackground();
							Color col2=frame.getBtnInner().getBackground();
							Circle c2=new Circle(new Point(xx, yy),ra, col1, col2);
							updateCircleCmd = new UpdateCircleCmd(c1,c2);
							commands.add(updateCircleCmd);
							updateCircleCmd.execute();
							logger.log("Modify_circle: " + c2.toString());
							temp.clear();
							//model.getShapes().remove(i);
							
							//model.getShapes().add(c2);
							c2.setSelected(true);
							frame.repaint();
						}
						else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
						
						
					}
						
					}
					else if(model.getShapes().get(i) instanceof Donut) {
						if(model.getShapes().get(i).isSelected()) {
						dlgDonut=new DlgDonut();
						Donut d1=(Donut) model.getShapes().get(i);
						dlgDonut.getTxtX().setText(Integer.toString(d1.getCenter().getX()));
						dlgDonut.getTxtY().setText(Integer.toString(d1.getCenter().getY()));
						dlgDonut.getTxtRadius().setText(Integer.toString(d1.getRadius()));
						dlgDonut.getTxtInner().setText(Integer.toString(d1.getInnerRadius()));
						dlgDonut.getBtnDonutBorder().setBackground(frame.getBtnBorder().getBackground());
						dlgDonut.getBtnDonutInner().setBackground(frame.getBtnInner().getBackground());
						dlgDonut.getBtnDonutBorder().setEnabled(false);
						dlgDonut.getBtnDonutInner().setEnabled(false);
						dlgDonut.setVisible(true);
						setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
						if(dlgDonut.isOkkk()) {
							int dX=(Integer.parseInt(dlgDonut.getTxtX().getText()));
							int dY=(Integer.parseInt(dlgDonut.getTxtY().getText()));
							int radi=(Integer.parseInt(dlgDonut.getTxtRadius().getText()));
							int in=(Integer.parseInt(dlgDonut.getTxtInner().getText()));
							Donut d2=new Donut(new Point(dX, dY),radi, in, frame.getBtnBorder().getBackground(),frame.getBtnInner().getBackground());
							updateDonutCmd = new UpdateDonutCmd(d1,d2);
							commands.add(updateDonutCmd);
							updateDonutCmd.execute();
							logger.log("Modify_donut: " + d2.toString());
							temp.clear();
							//model.getShapes().remove(i);
						
							
							//model.getShapes().add(d2);
							d2.setSelected(true);
							frame.repaint();
						
					}
						else {
							model.getShapes().get(i).setSelected(false);
							frame.repaint();
						}
						
				}
						
					}
					else if(model.getShapes().get(i) instanceof HexagonAdapter) {
						if(model.getShapes().get(i).isSelected()) {
							dlgHexagon = new DlgHexagon();
							HexagonAdapter h1 = (HexagonAdapter) model.getShapes().get(i);
							
							dlgHexagon.getTxtX().setText(Integer.toString(h1.getHexagon().getX()));
							dlgHexagon.getTxtY().setText(Integer.toString(h1.getHexagon().getY()));
							dlgHexagon.getTxtRadius().setText(Integer.toString(h1.getHexagon().getR()));
							dlgHexagon.getBtnBorder().setBackground(frame.getBtnBorder().getBackground());
							dlgHexagon.getBtnInner().setBackground(frame.getBtnInner().getBackground());
							dlgHexagon.getBtnBorder().setEnabled(false);
							dlgHexagon.getBtnInner().setEnabled(false);
							dlgHexagon.setVisible(true);
							setSelectModify(true);
							
							if(dlgHexagon.isGood()) {
								int dX = (Integer.parseInt(dlgHexagon.getTxtX().getText()));
								int dY = (Integer.parseInt(dlgHexagon.getTxtY().getText()));
								int r = (Integer.parseInt(dlgHexagon.getTxtRadius().getText()));
								//Color c1=frame.getBtnBorder().getBackground();
								//Color c2=frame.getBtnInner().getBackground();
								Hexagon h = new Hexagon(dX,dY,r);
								h.setBorderColor(frame.getBtnBorder().getBackground());
								h.setAreaColor(frame.getBtnInner().getBackground());
								
								HexagonAdapter ha = new HexagonAdapter(h);
								//model.getShapes().remove(i);
								
								//model.getShapes().add(ha);
								updateHexagonCmd=new UpdateHexagonCmd(h1,ha);
								commands.add(updateHexagonCmd);
								updateHexagonCmd.execute();
								logger.log("Modify_hexagon: " + ha.toString());
								temp.clear();
								ha.setSelected(true);
								frame.repaint();
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
	
	public void undo() {
		if(commands.size()!=0) {
			logger.log("Undo: " + commands.get(commands.size()-1));
			commands.get(commands.size()-1).unexecute();
			temp.add(commands.get(commands.size()-1));
			commands.remove(commands.size()-1);
			
			frame.repaint();
		

	}
	else {
		JOptionPane.showMessageDialog(null, "Nema komandi");
		//frame.getBtnUndo().setEnabled(false);
	}
		
	}
	
	public void redo() {
		if(temp.size()!=0) {
			
			commands.add(temp.get(temp.size()-1));
			logger.log("Redo: " + temp.get(temp.size()-1));
			temp.remove(temp.get(temp.size()-1));
			
			commands.get(commands.size()-1).execute();
			
			
			frame.repaint();
			}
			else {
				JOptionPane.showMessageDialog(null, "Nema komandi");
				return;
				//frame.getBtnRedo().setEnabled(false);
			}
	}

	public void toFront() {
		if(model.getShapes().size()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			return;
		}
		else if(selectedShapes.size()==0) {
			JOptionPane.showMessageDialog(null, "List of selected shapes is empty!");
			return;
		}
		else if(selectedShapes.size()>1) {
			JOptionPane.showMessageDialog(null, "List of selected shapes contains more than 1 shape!");
			return;
		}
		else if(selectedShapes.size()==1) {
			toFrontCmd = new ToFrontCmd(model,selectedShapes.get(selectedShapes.size()-1));
			commands.add(toFrontCmd);
			logger.log("To_front: " + selectedShapes.get(selectedShapes.size()-1));
			toFrontCmd.execute();
			
			
		}
		/*
		for(int i=0;i<model.getShapes().size();i++) {
			
			if(model.getShapes().get(i).isSelected()) {
				Shape s = model.getShapes().get(i);
				model.remove(s);
				if(i>=model.getShapes().size()) {
					model.getShapes().add(s);
					break;
				}
				else {
				model.getShapes().add(i+1, s);
				break;
			}
			
			
		}
		
	}
	*/
		frame.repaint();
}
	
	public void toBack() {
		if(model.getShapes().size()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			return;
		}
		else if(selectedShapes.size()==0) {
			JOptionPane.showMessageDialog(null, "List of selected shapes is empty!");
			return;
		}
		else if(selectedShapes.size()>1) {
			JOptionPane.showMessageDialog(null, "List of selected shapes contains more than 1 shape!");
			return;
		}
		else if(selectedShapes.size()==1){
			toBackCmd= new ToBackCmd(model,selectedShapes.get(selectedShapes.size()-1));
			commands.add(toBackCmd);
			logger.log("To_back: " + selectedShapes.get(selectedShapes.size()-1));
			toBackCmd.execute();
			
		}
		
	/*
		for(int i=0;i<model.getShapes().size();i++) {
			
			if(model.getShapes().get(i).isSelected()) {
				Shape s = model.getShapes().get(i);
				model.remove(s);
				if(i<=1) {
					model.getShapes().add(0,s);
					break;
				}
				else {
				model.getShapes().add(i-1, s);
				break;
			}
			
			
		}
		
	}*/
		frame.repaint();
		
	}
	
	public void bringToFront() {
		if(model.getShapes().size()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			return;
		}
		else if(selectedShapes.size()==0) {
			JOptionPane.showMessageDialog(null, "List of selected shapes is empty!");
			return;
		}
		else if(selectedShapes.size()>1) {
			JOptionPane.showMessageDialog(null, "List of selected shapes contains more than 1 shape!");
			return;
		}
		else if(selectedShapes.size()==1) {
			bringToFrontCmd = new BringToFrontCmd(model,selectedShapes.get(selectedShapes.size()-1));
			commands.add(bringToFrontCmd);
			logger.log("Bring_to_front: " + selectedShapes.get(selectedShapes.size()-1));
			bringToFrontCmd.execute();
			
		}/*
		for(int i=0;i<model.getShapes().size();i++) {
			
			if(model.getShapes().get(i).isSelected()) {
				Shape s = model.getShapes().get(i);
				model.getShapes().remove(i);
				model.add(s);
				break;
			}
			
			
		}
		//model.getShapes().remove(s);*/
		frame.repaint();
		
		
			
	}
	
	public void bringToBack() {
		/*
		for(int i=0;i<model.getShapes().size();i++) {
			if(model.getShapes().get(i).isSelected()) {
				Shape s = model.getShapes().get(i);
				model.remove(s);
				model.getShapes().add(0, s);
				break;
			}
			
		}
		*/
		if(model.getShapes().size()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			return;
		}
		else if(selectedShapes.size()==0) {
			JOptionPane.showMessageDialog(null, "List of selected shapes is empty!");
			return;
		}
		else if(selectedShapes.size()>1) {
			JOptionPane.showMessageDialog(null, "List of selected shapes contains more than 1 shape!");
			return;
		}
		else if(selectedShapes.size()==1) {
			bringToBackCmd = new BringToBackCmd(model,selectedShapes.get(selectedShapes.size()-1)); 
			commands.add(bringToBackCmd);
			logger.log("Bring_to_back: " + selectedShapes.get(selectedShapes.size()-1));
			bringToBackCmd.execute();
			
		}
		frame.repaint();
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.removePropertyChangeListener(pcl);
	}
	
	public void save() {
		Save save = new Save(logger);
		save.saveLog();
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
