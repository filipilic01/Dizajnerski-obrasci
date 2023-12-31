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
import command.DeselectCmd;

import command.RemoveShapeCmd;
import command.SelectCmd;
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
import strategy.SaveLoad;


public class DrawingController {
	private boolean select;
    private boolean selectModify;
    private Shape shape;
    private ArrayList<CommandShape> commands = new ArrayList();
    private ArrayList<CommandShape> commandsC = new ArrayList();
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
	private ArrayList<CommandShape> tempT = new ArrayList();
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
	private int lineNumber;
	private String[] strLog;
	private String[] array;
	
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
				HexagonAdapter ha = new HexagonAdapter(new Hexagon(e.getX(),e.getY(),Integer.parseInt(dlgHexagon.getTxtRadius().getText())));
				//Hexagon hex = new Hexagon(e.getX(),e.getY(),Integer.parseInt(dlgHexagon.getTxtRadius().getText()));
				ha.setColor(frame.getBtnBorder().getBackground());
				ha.setColorInner(frame.getBtnInner().getBackground());
			//	sh = ha;
				addShapeCmd = new AddShapeCmd(model, ha);
				//model.add(sh);
				addShapeCmd.execute();
				commands.add(addShapeCmd);
				logger.log("Added_hexagon: " + ha.toString());
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
				Circle c=new Circle(new Point(e.getX(),e.getY()),(Integer.parseInt(dlgCircle.getTxtRadius().getText())), 
						frame.getBtnBorder().getBackground(), frame.getBtnInner().getBackground());
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
		
		//SelectCmd selectCmd = new SelectCmd(model, model.getShapes(),shape,x,y,logger);
		//selectCmd.execute();
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
				//setSelect(false);
				//setSelectModify(false);
		}
			
			}
	}
		if(shape != null) {
			//shape.setSelected(true);
			if(shape instanceof Point) {
				logger.log("Selected_point: " + shape.toString() + "," + model.getShapes().indexOf(shape));
			}
			else if(shape instanceof Line) {
				logger.log("Selected_line: " + shape.toString() + "," + model.getShapes().indexOf(shape));
			}
			else if(shape instanceof Donut) {
				logger.log("Selected_donut: " + shape.toString() + "," + model.getShapes().indexOf(shape));
			}
			else if(shape instanceof Circle) {
				logger.log("Selected_circle: " + shape.toString() + "," + model.getShapes().indexOf(shape));
			}
			else if(shape instanceof Rectangle) {
				logger.log("Selected_rectangle: " + shape.toString() + "," + model.getShapes().indexOf(shape));
			}
			else if(shape instanceof HexagonAdapter) {
				logger.log("Selected_hexagon: " + shape.toString() + "," + model.getShapes().indexOf(shape));
			}
			//numOfSelectedShapes++;
			//selectedShapes.add(shape);
			SelectCmd selectCmd = new SelectCmd(selectedShapes, shape);
			commands.add(selectCmd);
			selectCmd.execute();
			frame.repaint();
			
		}
		else {
			for(int i=0;i<model.getShapes().size();i++) 
			{
				if(model.getShapes().get(i).isSelected()) {
					
				
				if(model.getShapes().get(i).contains(x, y)) {
					
					//model.getShapes().get(i).setSelected(false);
					if(model.getShapes().get(i) instanceof Point) {
						logger.log("Deselected_point: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
					}
					else if(model.getShapes().get(i) instanceof Line) {
						logger.log("Deselected_line: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
					}
					else if(model.getShapes().get(i) instanceof Donut) {
						logger.log("Deselected_donut: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
					}
					else if(model.getShapes().get(i) instanceof Circle) {
						logger.log("Deselected_circle: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
					}
					else if(model.getShapes().get(i) instanceof Rectangle) {
						logger.log("Deselected_rectangle: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
					}
					
					else if(model.getShapes().get(i) instanceof HexagonAdapter) {
						logger.log("Deselected_hexagon: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
					}
					
					
					//numOfSelectedShapes--;
					//selectedShapes.remove(model.getShapes().get(i));
					DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, (Shape)model.getShapes().get(i));
					commands.add(deselectCmd);
					deselectCmd.execute();
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
					logger.log("Deselected_point: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
				}
				else if(shapes.get(i) instanceof Line) {
					logger.log("Deselected_line: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
				}
				else if(shapes.get(i) instanceof Donut) {
					logger.log("Deselected_donut: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
				}
				else if(shapes.get(i) instanceof Circle) {
					logger.log("Deselected_circle: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
				}
				else if(shapes.get(i) instanceof Rectangle) {
					logger.log("Deselected_rectangle: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
				}
				else if(shapes.get(i) instanceof HexagonAdapter) {
					logger.log("Deselected_hexagon: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
				}
					
				DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, shapes.get(i));
				commands.add(deselectCmd);
				deselectCmd.execute();
				}
				
				
			//shapes.get(i).setSelected(false);
				
			
			
		}
			
			//selectedShapes.clear();
			frame.repaint();
		//numOfSelectedShapes=0;
		
	}
		
	public int counter() {
		int i;
		for(i=0;i<model.getShapes().size();i++);
			
		return i;
	}
	
	public void delete() {
		
		/*if(counter()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			//setSelect(true);
		}
		
		else {*/
		//setSelect(false);
		if(selectedShapes.size()==1) {
		for(int i=0;i<model.getShapes().size();i++) {
			
				if(model.getShapes().get(i).isSelected()) {
					//etSelect(true);
					
					int selectedOption=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this shape?", "Warning message", JOptionPane.YES_NO_OPTION);
					if(selectedOption==JOptionPane.YES_OPTION) {
					RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model,model.getShapes().get(i));
					//model.getShapes().get(i).setSelected(false);
					selectedShapes.remove(model.getShapes().get(i));
					//model.getShapes().remove(i);
						if(model.getShapes().get(i) instanceof Point) {
						logger.log("Removed_point: " + model.getShapes().get(i).toString());
					} else if(model.getShapes().get(i) instanceof Line) {
						logger.log("Removed_line: " + model.getShapes().get(i).toString());
					} else if(model.getShapes().get(i) instanceof Donut) {
						logger.log("Removed_donut: " + model.getShapes().get(i).toString());
					} else if(model.getShapes().get(i) instanceof Circle) {
						logger.log("Removed_circle: " + model.getShapes().get(i).toString());
					} else if(model.getShapes().get(i) instanceof Rectangle) {
						logger.log("Removed_rectangle: " + model.getShapes().get(i).toString());
					} else if(model.getShapes().get(i) instanceof HexagonAdapter) {
						logger.log("Removed_hexagon: " + model.getShapes().get(i).toString());
					}
					removeShapeCmd.execute();
					commands.add(removeShapeCmd);
				
					
					temp.clear();
					//numOfSelectedShapes--;
					frame.repaint();
					//setSelect(true);
					}
					else {
						if(model.getShapes().get(i) instanceof Point) {
							logger.log("Deselected_point: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						} else if(model.getShapes().get(i) instanceof Line) {
							logger.log("Deselected_line: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						} else if(model.getShapes().get(i) instanceof Donut) {
							logger.log("Deselected_donut: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						} else if(model.getShapes().get(i) instanceof Circle) {
							logger.log("Deselected_circle: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						} else if(model.getShapes().get(i) instanceof Rectangle) {
							logger.log("Deselected_rectangle: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						} else if(model.getShapes().get(i) instanceof HexagonAdapter) {
							logger.log("Deselected_hexagon: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						}
						DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, model.getShapes().get(i));
						commands.add(deselectCmd);
						deselectCmd.execute();
						
						//numOfSelectedShapes=0;
						//selectedShapes.clear();
						frame.repaint();
						//setSelect(true);
					}
				}
				
		}
		}
		/*else if(selectedShapes.size() ==0) {
			JOptionPane.showMessageDialog(null, "Please, select the shape!");
			//setSelect(true);
		}*/
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
						//model.getShapes().get(i).setSelected(false);
						//removeShapeCmd.execute();
						commands.add(removeShapeCmd);
						if(model.getShapes().get(i) instanceof Point) {
							logger.log("Removed_point: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						} else if(model.getShapes().get(i) instanceof Line) {
							logger.log("Removed_line: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						} else if(model.getShapes().get(i) instanceof Donut) {
							logger.log("Removed_donut: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						} else if(model.getShapes().get(i) instanceof Circle) {
							logger.log("Removed_circle: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						} else if(model.getShapes().get(i) instanceof Rectangle) {
							logger.log("Removed_rectangle: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						} else if(model.getShapes().get(i) instanceof HexagonAdapter) {
							logger.log("Removed_hexagon: " + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(model.getShapes().get(i)));
						}
						selectedShapes.remove(model.getShapes().get(i));
					}
				}
				temp.clear();
				model.getShapes().clear();
			
				for(int i=0;i<temporary.size();i++) {
					model.getShapes().add(temporary.get(i));
					
				}
				//numOfSelectedShapes=0;
				//selectedShapes.clear();
				frame.repaint();
				//setSelect(true);
			}
			else {
				for(int i=0;i<model.getShapes().size();i++) {
					if(model.getShapes().get(i).isSelected()) {
						if(model.getShapes().get(i) instanceof Point) {
							logger.log("Deselected_point: " + model.getShapes().get(i).toString());
						} else if(model.getShapes().get(i) instanceof Line) {
							logger.log("Deselected_line: " + model.getShapes().get(i).toString());
						} else if(model.getShapes().get(i) instanceof Donut) {
							logger.log("Deselected_donut: " + model.getShapes().get(i).toString());
						} else if(model.getShapes().get(i) instanceof Circle) {
							logger.log("Deselected_circle: " + model.getShapes().get(i).toString());
						} else if(model.getShapes().get(i) instanceof Rectangle) {
							logger.log("Deselected_rectangle: " + model.getShapes().get(i).toString());
						} else if(model.getShapes().get(i) instanceof HexagonAdapter) {
							logger.log("Deselected_hexagon: " + model.getShapes().get(i).toString());
						}
						DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, model.getShapes().get(i));
						commands.add(deselectCmd);
						deselectCmd.execute();
					/*model.getShapes().get(i).setSelected(false);
					selectedShapes.remove(model.getShapes().get(i));*/
					}
				}
				//numOfSelectedShapes=0;
				//selectedShapes.clear();
				frame.repaint();
				//setSelect(true);
			}
		}
		}
		//frame.repaint();
		
		
	
	public void modify() {
		/*if(counter()==0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			setSelectModify(true);
		}
		else*/
			//setSelectModify(false);
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
					//	setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
						
						if(dlgPo.isOok()) {
							int cooX=(Integer.parseInt(dlgPo.getTxtX().getText()));
							int cooY=(Integer.parseInt(dlgPo.getTxtY().getText()));
							Point p=new Point(cooX,cooY, frame.getBtnBorder().getBackground());
							p.setSelected(true);
							updatePointCmd = new UpdatePointCmd(p2,p);
							commands.add(updatePointCmd);
						//	selectedShapes.remove(p2);
							
							updatePointCmd.execute();
							logger.log("Modify_point: " + p.toString());
							temp.clear();
							//model.getShapes().remove(i);
							
							//model.getShapes().add(p);
							
							
							//selectedShapes.add(p);
							frame.repaint();
						}
						
						else {
							//model.getShapes().get(i).setSelected(false);
							//selectedShapes.remove(model.getShapes().get(i));
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
						//setSelectModify(true);
						//shapes.remove(i);
					//	repaint();
						
					
						if(dlgLi.isOkayy()) {
							int sX=(Integer.parseInt(dlgLi.getTxtSPX().getText()));
							int sY=(Integer.parseInt(dlgLi.getTxtSPY().getText()));
							int eX=(Integer.parseInt(dlgLi.getTxtEPX().getText()));
							int eY=(Integer.parseInt(dlgLi.getTxtEPY().getText()));
							Line l2=new Line(new Point(sX,sY), new Point(eX, eY), frame.getBtnBorder().getBackground());
							l2.setSelected(true);
							updateLineCmd = new UpdateLineCmd((Line)model.getShapes().get(i),l2);
							commands.add(updateLineCmd);
							//selectedShapes.remove(l);
							
							
							updateLineCmd.execute();
							logger.log("Modify_line: " + l2.toString());
							temp.clear();
							
							
							//selectedShapes.add(l2);
							frame.repaint();
						}
						
						else {
						//	model.getShapes().get(i).setSelected(false);
						//	selectedShapes.remove(model.getShapes().get(i));
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
					//	setSelectModify(true);
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
							r2.setSelected(true);
							updateRectCmd=new UpdateRectangleCmd(r1,r2);
							//selectedShapes.remove(r1);
							commands.add(updateRectCmd);
							updateRectCmd.execute();
							logger.log("Modify_rectangle: " + r2.toString());
							temp.clear();
							//model.getShapes().remove(i);
							
							//model.getShapes().add(r2);
						
							
							//selectedShapes.add(r2);
							frame.repaint();
						}
						else {
							//model.getShapes().get(i).setSelected(false);
							//selectedShapes.remove(model.getShapes().get(i));
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
						//setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
					
						if(dlgCircle.isGood()) {
							int xx=(Integer.parseInt(dlgCircle.getTxtX().getText()));
							int yy=(Integer.parseInt(dlgCircle.getTxtY().getText()));
							int ra=(Integer.parseInt(dlgCircle.getTxtRadius().getText()));
							Color col1=frame.getBtnBorder().getBackground();
							Color col2=frame.getBtnInner().getBackground();
							Circle c2=new Circle(new Point(xx, yy),ra, col1, col2);
							c2.setSelected(true);
							updateCircleCmd = new UpdateCircleCmd(c1,c2);
							commands.add(updateCircleCmd);
							//selectedShapes.remove(c1);
							updateCircleCmd.execute();
							logger.log("Modify_circle: " + c2.toString());
							temp.clear();
							//model.getShapes().remove(i);
							
							//model.getShapes().add(c2);
							//c2.setSelected(true);
							//selectedShapes.add(c2);
							frame.repaint();
						}
						else {
							//model.getShapes().get(i).setSelected(false);
							//selectedShapes.remove(model.getShapes().get(i));
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
						//setSelectModify(true);
						//shapes.remove(i);
						//repaint();
						
						if(dlgDonut.isOkkk()) {
							int dX=(Integer.parseInt(dlgDonut.getTxtX().getText()));
							int dY=(Integer.parseInt(dlgDonut.getTxtY().getText()));
							int radi=(Integer.parseInt(dlgDonut.getTxtRadius().getText()));
							int in=(Integer.parseInt(dlgDonut.getTxtInner().getText()));
							Donut d2=new Donut(new Point(dX, dY),radi, in, frame.getBtnBorder().getBackground(),frame.getBtnInner().getBackground());
							d2.setSelected(true);
							updateDonutCmd = new UpdateDonutCmd(d1,d2);
							commands.add(updateDonutCmd);
							//selectedShapes.remove(d1);
							updateDonutCmd.execute();
							logger.log("Modify_donut: " + d2.toString());
							temp.clear();
							//model.getShapes().remove(i);
						
							
							//model.getShapes().add(d2);
							
							//selectedShapes.add(d2);
							frame.repaint();
						
					}
						else {
							//model.getShapes().get(i).setSelected(false);
							//selectedShapes.remove(model.getShapes().get(i));
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
							//setSelectModify(true);
							
							if(dlgHexagon.isGood()) {
								int dX = (Integer.parseInt(dlgHexagon.getTxtX().getText()));
								int dY = (Integer.parseInt(dlgHexagon.getTxtY().getText()));
								int r = (Integer.parseInt(dlgHexagon.getTxtRadius().getText()));
								//Color c1=frame.getBtnBorder().getBackground();
								//Color c2=frame.getBtnInner().getBackground();
								//Hexagon h = new Hexagon(dX,dY,r);
								
								HexagonAdapter ha = new HexagonAdapter(new Hexagon(dX,dY,r));
								ha.setColor(frame.getBtnBorder().getBackground());
								ha.setColorInner(frame.getBtnInner().getBackground());
								ha.setSelected(true);
								//model.getShapes().remove(i);
								
								//model.getShapes().add(ha);
								updateHexagonCmd=new UpdateHexagonCmd(h1,ha);
								//selectedShapes.remove(h1);
								commands.add(updateHexagonCmd);
								updateHexagonCmd.execute();
								logger.log("Modify_hexagon: " + ha.toString());
								temp.clear();
								
								//selectedShapes.add(ha);
								frame.repaint();
							}
							else {
								//model.getShapes().get(i).setSelected(false);
								//selectedShapes.remove(model.getShapes().get(i));
								frame.repaint();
							}
							
						}
						
					}
			
		
			}
	/*if(isSelectModify()==false) {
		JOptionPane.showMessageDialog(null, "Please, select the shape!");
		setSelectModify(true);
	}*/
}
	
	public void undo() {
		
		if(commands.size()!=0) {
			
		
			//logger.log("Undo: " + commands.get(commands.size()-1));
			//commands.get(commands.size()-1).unexecute();
			logger.log("Undo: " + commands.get(commands.size()-1));
			commands.get(commands.size()-1).unexecute();
			temp.add(commands.get(commands.size()-1));
			commands.remove(commands.size()-1);
			
			
			frame.repaint();
	
		}
	//}
	else {
	JOptionPane.showMessageDialog(null, "No active undo commands");
		//frame.getBtnUndo().setEnabled(false);
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
	
	public void redo() {
		if(temp.size()!=0) {
			
			commands.add(temp.get(temp.size()-1));
			logger.log("Redo: " + temp.get(temp.size()-1));
			temp.remove(temp.get(temp.size()-1));
			
			commands.get(commands.size()-1).execute();
			
			//temp.remove(temp.get(temp.size()-1));
			
			//commands.get(commands.size()-1).execute();
			
			
			frame.repaint();
			}
			else {
				JOptionPane.showMessageDialog(null, "No active redo commands");
				return;
				//frame.getBtnRedo().setEnarbled(false);
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
			Shape sh = selectedShapes.get(selectedShapes.size()-1);
			//selectedShapes.remove(sh);
			toFrontCmd = new ToFrontCmd(model, sh);
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
		SaveLoad save = new SaveLoad(logger);
		save.saveLog();
	}
	
	public void load() {
		SaveLoad load = new SaveLoad(logger);
		load.loadFile();
		frame.getBtnExecute().setEnabled(true);
		
		strLog= logger.getTextArea().getText().split("\n");
		lineNumber=0;
	}
	
	public void executeCommand() {
	
		if(lineNumber==strLog.length) {
			JOptionPane.showMessageDialog(null, "Loading file is done!");
		}
		else {
			
		
		String s = strLog[lineNumber];
		/*-------------------------------------------------------------------------------------------------------ADDED---------------------------------------------*/
		if(s.contains("Added_point")) {
			String [] split = s.split(",|\\[|\\]|\\=|\\ ");	
			Point p = new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]), new Color(Integer.parseInt(split[8])));
			AddShapeCmd addShape = new AddShapeCmd(model,p);
			commandsC.add(addShape);
			commands.add(addShape);
			addShape.execute();
			temp.clear();
			logger.log("Added_point: " + p.toString());
			//array[lineNumber]="Added_point: " + p.toString();
		}
		else if(s.contains("Added_line")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			Line l = new Line(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3])), new Point(Integer.parseInt(split[7]),
					Integer.parseInt(split[8])), new Color(Integer.parseInt(split[13])));
			AddShapeCmd addShape = new AddShapeCmd(model,l);
			commandsC.add(addShape);
			commands.add(addShape);
			addShape.execute();
			temp.clear();
			logger.log("Added_line: " + l.toString());
			//array[lineNumber]="Added_line: " + l.toString();
			
		}
		else if(s.contains("Added_rectangle")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
	
			Rectangle r = new Rectangle(new Point(Integer.parseInt(split[5]), Integer.parseInt(split[6])), Integer.parseInt(split[10]), 
					Integer.parseInt(split[13]), new Color(Integer.parseInt(split[17])), new Color(Integer.parseInt(split[21])));
			AddShapeCmd addShape = new AddShapeCmd(model,r);
			commandsC.add(addShape);
			commands.add(addShape);
			addShape.execute();
			temp.clear();
			logger.log("Added_rectangle: " + r.toString());
			//array[lineNumber]="Added_rectangle: " + r.toString();
		}
		else if(s.contains("Added_donut")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			Donut d = new Donut(new Point(Integer.parseInt(split[4]), Integer.parseInt(split[5])), Integer.parseInt(split[9]), Integer.parseInt(split[20]),
					new Color(Integer.parseInt(split[13])), new Color(Integer.parseInt(split[17])));
			AddShapeCmd addShape = new AddShapeCmd(model,d);
			commandsC.add(addShape);
			commands.add(addShape);
			addShape.execute();
			temp.clear();
			logger.log("Added_donut: " + d.toString());
			//array[lineNumber]="Added_donut: " + d.toString();
		}
		else if(s.contains("Added_circle")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			Circle c = new Circle(new Point(Integer.parseInt(split[4]),Integer.parseInt(split[5])), Integer.parseInt(split[9])
					,new Color(Integer.parseInt(split[13])), new Color(Integer.parseInt(split[17])));
			AddShapeCmd addShape = new AddShapeCmd(model,c);
			commandsC.add(addShape);
			commands.add(addShape);
			addShape.execute();
			temp.clear();
			logger.log("Added_circle: " + c.toString());
			//array[lineNumber]="Added_circle: " + c.toString();
		}
		else if(s.contains("Added_hexagon")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");

			HexagonAdapter ha = new HexagonAdapter(new Hexagon(Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[8])));
			ha.setColorInner(new Color(Integer.parseInt(split[16])));
			ha.setColor(new Color(Integer.parseInt(split[12])));
			
			AddShapeCmd addShape = new AddShapeCmd(model,ha);
			commandsC.add(addShape);
			commands.add(addShape);
			addShape.execute();
			temp.clear();
			logger.log("Added_hexagon: " + ha.toString());
			//array[lineNumber]="Added_hexagon: " + ha.toString();
		}
		
		/*--------------------------------------------------------------------------SELECTED-----------------------------------------------------------------------------------------------*/
		else if(s.contains("Selected_point")){
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
		
			int index = Integer.parseInt(split[9]);
			SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(selectCmd);
			commands.add(selectCmd);
			selectCmd.execute();
			//model.getShapes().get(index).setSelected(true);
			//selectedShapes.add(model.getShapes().get(index));
			logger.log("Selected_point: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Selected_point: " + model.getShapes().get(index).toString();
		}

		else if(s.contains("Selected_line")){
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
		
			int index = Integer.parseInt(split[14]);
			SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(selectCmd);
			commands.add(selectCmd);
			selectCmd.execute();
			//model.getShapes().get(index).setSelected(true);
			//selectedShapes.add(model.getShapes().get(index));
			logger.log("Selected_line: " + model.getShapes().get(index).toString());
			//[lineNumber]="Selected_line: " + model.getShapes().get(index).toString();
		}

		else if(s.contains("Selected_rectangle")){
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
		
			int index = Integer.parseInt(split[22]);
			SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(selectCmd);
			commands.add(selectCmd);
			selectCmd.execute();
			//model.getShapes().get(index).setSelected(true);
			//selectedShapes.add(model.getShapes().get(index));
			logger.log("Selected_rectangle: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Selected_rectangle: " + model.getShapes().get(index).toString();
		}
		else if(s.contains("Selected_donut")){
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
		
			int index = Integer.parseInt(split[21]);
			SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(selectCmd);
			commands.add(selectCmd);
			selectCmd.execute();
			//model.getShapes().get(index).setSelected(true);
			//selectedShapes.add(model.getShapes().get(index));
			logger.log("Selected_donut: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Selected_donut: " + model.getShapes().get(index).toString();
		}
		else if(s.contains("Selected_circle")){
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
		
			int index = Integer.parseInt(split[18]);
			SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(selectCmd);
			commands.add(selectCmd);
			selectCmd.execute();
			//model.getShapes().get(index).setSelected(true);
			////selectedShapes.add(model.getShapes().get(index));
			logger.log("Selected_circle: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Selected_circle: " + model.getShapes().get(index).toString();
		}

		else if(s.contains("Selected_hexagon")){
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
		
			int index = Integer.parseInt(split[17]);
			SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(selectCmd);
			commands.add(selectCmd);
			selectCmd.execute();
			//model.getShapes().get(index).setSelected(true);
			//selectedShapes.add(model.getShapes().get(index));
			logger.log("Selected_hexagon: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Selected_hexagon: " + model.getShapes().get(index).toString();
		}
		/*-----------------------------------------------------------------DESELECTED------------------------------------------------------------------*/
		
		else if(s.contains("Deselected_point")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			int index = Integer.parseInt(split[9]);
			DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(deselectCmd);
			commands.add(deselectCmd);
			deselectCmd.execute();
			//model.getShapes().get(index).setSelected(false);
			//selectedShapes.remove(model.getShapes().get(index));
			logger.log("Deselected_point: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Deselected_point: " + model.getShapes().get(index).toString();
		}
		else if(s.contains("Deselected_line")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			int index = Integer.parseInt(split[14]);
			DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(deselectCmd);
			commands.add(deselectCmd);
			deselectCmd.execute();
			//model.getShapes().get(index).setSelected(false);
			//selectedShapes.remove(model.getShapes().get(index));
			logger.log("Deselected_line: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Deselected_line: " + model.getShapes().get(index).toString();
		}
		else if(s.contains("Deselected_donut")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			int index = Integer.parseInt(split[21]);
			DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(deselectCmd);
			commands.add(deselectCmd);
			deselectCmd.execute();
			//model.getShapes().get(index).setSelected(false);
			//selectedShapes.remove(model.getShapes().get(index));
			logger.log("Deselected_donut: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Deselected_donut: " + model.getShapes().get(index).toString();
		}
		else if(s.contains("Deselected_rectangle")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			int index = Integer.parseInt(split[22]);
			DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(deselectCmd);
			commands.add(deselectCmd);
			deselectCmd.execute();
			//model.getShapes().get(index).setSelected(false);
			//selectedShapes.remove(model.getShapes().get(index));
			logger.log("Deselected_rectangle: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Deselected_rectangle: " + model.getShapes().get(index).toString();
		}
		else if(s.contains("Deselected_circle")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			int index = Integer.parseInt(split[18]);
			DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(deselectCmd);
			commands.add(deselectCmd);
			deselectCmd.execute();
			//model.getShapes().get(index).setSelected(false);
			//selectedShapes.remove(model.getShapes().get(index));
			logger.log("Deselected_circle: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Deselected_circle: " + model.getShapes().get(index).toString();
		}
		else if(s.contains("Deselected_hexagon")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			int index = Integer.parseInt(split[17]);
			DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
			commandsC.add(deselectCmd);
			commands.add(deselectCmd);
			deselectCmd.execute();
			//model.getShapes().get(index).setSelected(false);
			//selectedShapes.remove(model.getShapes().get(index));
			logger.log("Deselected_hexagon: " + model.getShapes().get(index).toString());
			//array[lineNumber]="Deselected_hexagon: " + model.getShapes().get(index).toString();
		}
		
		/*--------------------------------------------------------------------------REMOVED-----------------------------------------------*/
		else if(s.contains("Removed_point")) {
			//String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			//Point p = new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]), new Color(Integer.parseInt(split[8])));
			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i).isSelected()) {
					RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(i));
					logger.log("Removed_point: " + model.getShapes().get(i).toString());
					selectedShapes.remove(model.getShapes().get(i));
					removeShape.execute();
					commands.add(removeShape);
				}
				}
			
	
			temp.clear();
			
		//	array[lineNumber]="Removed_point: " + p.toString();
		}
		else if(s.contains("Removed_line")) {
			//String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			//Line l = new Line(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3])), new Point(Integer.parseInt(split[7]),
			//		Integer.parseInt(split[8])), new Color(Integer.parseInt(split[13])));
			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i).isSelected()) {
					RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(i));
					logger.log("Removed_line: " + model.getShapes().get(i).toString());
					selectedShapes.remove(model.getShapes().get(i));
					removeShape.execute();
					commands.add(removeShape);
				}
				}
			temp.clear();
		
		}
		else if(s.contains("Removed_donut")) {
			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i).isSelected()) {
					RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(i));
					logger.log("Removed_donut: " + model.getShapes().get(i).toString());
					selectedShapes.remove(model.getShapes().get(i));
					removeShape.execute();
					commands.add(removeShape);
				}
				}
			temp.clear();
			
		
		}
		else if(s.contains("Removed_circle")) {
			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i).isSelected()) {
					RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(i));
					logger.log("Removed_circle: " + model.getShapes().get(i).toString());
					selectedShapes.remove(model.getShapes().get(i));
					removeShape.execute();
					commands.add(removeShape);
				}
				}
			temp.clear();
			
		
		}
		else if(s.contains("Removed_rectangle")) {
			
			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i).isSelected()) {
					RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(i));
					logger.log("Removed_rectangle: " + model.getShapes().get(i).toString());
					selectedShapes.remove(model.getShapes().get(i));
					removeShape.execute();
					commands.add(removeShape);
				}
				}
			temp.clear();
			

		}
		else if(s.contains("Removed_hexagon")) {
			
				for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i).isSelected()) {
					RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(i));
					logger.log("Removed_hexagon: " + model.getShapes().get(i).toString());
					selectedShapes.remove(model.getShapes().get(i));
					removeShape.execute();
					commands.add(removeShape);
				}
				}
			temp.clear();
		
		}
		
		/*-----------------------------------------------------------------------MODIFY--------------------------------------------------------*/
		else if(s.contains("Modify_point")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i) instanceof Point) {
					
					if(model.getShapes().get(i).isSelected()) {
						Point p1 = (Point)model.getShapes().get(i);
						Point p2 = new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]), new Color(Integer.parseInt(split[8])));
						p2.setSelected(true);
						updatePointCmd = new UpdatePointCmd(p1,p2);
						commands.add(updatePointCmd);
						//selectedShapes.remove(p1);
						
						updatePointCmd.execute();
						logger.log("Modify_point: " + p2.toString());
						temp.clear();
						
						
						//p2.setSelected(true);
						//selectedShapes.add(p2);
					}
				}
			}
	
		}
		else if(s.contains("Modify_line")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			
			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i) instanceof Line) {
					
					if(model.getShapes().get(i).isSelected()) {
						Line l1 = (Line)model.getShapes().get(i);
						Line l2 = new Line(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3])), new Point(Integer.parseInt(split[7]),
					Integer.parseInt(split[8])), new Color(Integer.parseInt(split[13])));
						l2.setSelected(true);
						updateLineCmd = new UpdateLineCmd(l1,l2);
						commands.add(updateLineCmd);
						//selectedShapes.remove(l1);
						
						updateLineCmd.execute();
						logger.log("Modify_line: " + l2.toString());
						temp.clear();
						
						//l2.setSelected(true);
						//selectedShapes.add(l2);
					}
				}
			}
			
			
		}
		else if(s.contains("Modify_donut")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i) instanceof Donut) {
					
					if(model.getShapes().get(i).isSelected()) {
						Donut d1 = (Donut)model.getShapes().get(i);
						Donut d2 = new Donut(new Point(Integer.parseInt(split[4]), Integer.parseInt(split[5])), Integer.parseInt(split[9]), Integer.parseInt(split[20]),
								new Color(Integer.parseInt(split[13])), new Color(Integer.parseInt(split[17])));
						d2.setSelected(true);
						updateDonutCmd = new UpdateDonutCmd(d1,d2);
						commands.add(updateDonutCmd);
						//selectedShapes.remove(d1);
						
						updateDonutCmd.execute();
						logger.log("Modify_donut: " + d2.toString());
						temp.clear();
						
						//d2.setSelected(true);
						//selectedShapes.add(d2);
					}
				}
			}
			
		}
		else if(s.contains("Modify_circle")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i) instanceof Circle) {
					
					if(model.getShapes().get(i).isSelected()) {
						Circle c1 = (Circle)model.getShapes().get(i);
						Circle c2 = new Circle(new Point(Integer.parseInt(split[4]),Integer.parseInt(split[5])), Integer.parseInt(split[9])
								,new Color(Integer.parseInt(split[13])), new Color(Integer.parseInt(split[17])));
						c2.setSelected(true);
						updateCircleCmd = new UpdateCircleCmd(c1,c2);
						commands.add(updateCircleCmd);
					//	selectedShapes.remove(c1);
						
						updateCircleCmd.execute();
						logger.log("Modify_circle: " + c2.toString());
						temp.clear();
						
						//c2.setSelected(true);
						//selectedShapes.add(c2);
					}
				}
			}
			
			
		}
		else if(s.contains("Modify_hexagon")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");

			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i) instanceof HexagonAdapter) {
					
					if(model.getShapes().get(i).isSelected()) {
						HexagonAdapter ha1 = (HexagonAdapter)model.getShapes().get(i);
						HexagonAdapter ha2 = new HexagonAdapter(new Hexagon(Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[8])));
						ha2.setColorInner(new Color(Integer.parseInt(split[16])));
						ha2.setColor(new Color(Integer.parseInt(split[12])));
						ha2.setSelected(true);
						updateHexagonCmd = new UpdateHexagonCmd(ha1,ha2);
						commands.add(updateHexagonCmd);
						//selectedShapes.remove(ha1);
						
						updateHexagonCmd.execute();
						logger.log("Modify_hexagon: " + ha2.toString());
						temp.clear();
						
						//ha2.setSelected(true);
						//selectedShapes.add(ha2);
					}
				}
			}
			
			
		}
		else if(s.contains("Modify_rectangle")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			
			for(int i=0;i<model.getShapes().size();i++) {
				
				if(model.getShapes().get(i) instanceof Rectangle) {
					
					if(model.getShapes().get(i).isSelected()) {
						Rectangle r1 = (Rectangle)model.getShapes().get(i);
						Rectangle r2 = new Rectangle(new Point(Integer.parseInt(split[5]), Integer.parseInt(split[6])), Integer.parseInt(split[10]), 
								Integer.parseInt(split[13]), new Color(Integer.parseInt(split[17])), new Color(Integer.parseInt(split[21])));
						r2.setSelected(true);
						updateRectCmd = new UpdateRectangleCmd(r1,r2);
						commands.add(updateRectCmd);
						//selectedShapes.remove(r1);
						
						updateRectCmd.execute();
						logger.log("Modify_rectangle: " + r2.toString());
						temp.clear();
						
						//r2.setSelected(true);
						//selectedShapes.add(r2);
					}
				}
			}
		
		}
		
		/*-----------------------------------------------------------------------------FRONT, BACK---------------------------------------------------------------------------------------*/
		else if(s.contains("Bring_to_front")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			BringToFrontCmd bringToFrontCmd = new BringToFrontCmd(model, selectedShapes.get(selectedShapes.size()-1));
			commandsC.add(bringToFrontCmd);
			commands.add(bringToFrontCmd);

			bringToFrontCmd.execute();
			logger.log("Bring_to_front: " + selectedShapes.get(selectedShapes.size()-1).toString());
			//array[lineNumber]="Bring_to_front: " + selectedShapes.get(selectedShapes.size()-1).toString();
		}
		else if(s.contains("Bring_to_back")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			BringToBackCmd bringToBackCmd = new BringToBackCmd(model, selectedShapes.get(selectedShapes.size()-1));
			commandsC.add(bringToBackCmd);
			commands.add(bringToBackCmd);

			bringToBackCmd.execute();
			logger.log("Bring_to_back: " + selectedShapes.get(selectedShapes.size()-1).toString());
			//array[lineNumber]="Bring_to_back: " + selectedShapes.get(selectedShapes.size()-1).toString();
		}
		else if(s.contains("To_front")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			ToFrontCmd toFrontCmd = new ToFrontCmd(model, selectedShapes.get(selectedShapes.size()-1));
			commandsC.add(toFrontCmd);
			commands.add(toFrontCmd);
			
			toFrontCmd.execute();
			logger.log("To_front: " + selectedShapes.get(selectedShapes.size()-1).toString());
			//array[lineNumber]="To_front: " + selectedShapes.get(selectedShapes.size()-1).toString();
		}
		else if(s.contains("To_back")) {
			String []split =s.split(",|\\[|\\]|\\=|\\ ");
			ToBackCmd toBackCmd = new ToBackCmd(model, selectedShapes.get(selectedShapes.size()-1));
			commandsC.add(toBackCmd);
			commands.add(toBackCmd);
			toBackCmd.execute();
			logger.log("To_back: " + selectedShapes.get(selectedShapes.size()-1).toString());
			//array[lineNumber]="To_back: " + selectedShapes.get(selectedShapes.size()-1).toString();
		}
		/*----------------------------------------------------------------------------------------UNDO, REDO--------------------------------------------------------------*/
		else if(s.contains("Undo")) {
			/*if(commandsC.size()!=0) {
				
				UndoCmd undoCmd= new UndoCmd(commandsC.get(commandsC.size()-1));
				logger.log("Undo: " + commandsC.get(commandsC.size()-1));
				tempT.add(commandsC.get(commandsC.size()-1));
				commandsC.remove(commandsC.size()-1);
				undoCmd.execute();
				//logger.log("Undo: " + commands.get(commands.size()-1));
				//commands.get(commands.size()-1).unexecute();
				
				
				
				frame.repaint();
		
			}
			*/
			undo();

		
		/*else {
		JOptionPane.showMessageDialog(null, "No active undo commands");
			//frame.getBtnUndo().setEnabled(false);
		}*/
			
			/*UndoCmd undoCmd = new UndoCmd(commandsC.get(commandsC.size()-1));
			undoCmd.execute();
			logger.log("Undo: "+ commandsC.get(commandsC.size()-1));
			commandsC.remove(commandsC.get(commandsC.size()-1));
			//undo();			*/
			
		}
	
		else if(s.contains("Redo")) {
			
			/*if(tempT.size()!=0) {
				RedoCmd redoCmd = new RedoCmd(tempT.get(tempT.size()-1));
				logger.log("Redo: " + tempT.get(tempT.size()-1));
				redoCmd.execute();
				commandsC.add(tempT.get(tempT.size()-1));
				tempT.remove(tempT.get(tempT.size()-1));
				
				
				//temp.remove(temp.get(temp.size()-1));
				
				//commands.get(commands.size()-1).execute();
				
				
				frame.repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "No active redo commands");
					return;
					//frame.getBtnRedo().setEnabled(false);
				}
		*/
			redo();
		}
		frame.repaint();
		
		
		lineNumber++;
		
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
