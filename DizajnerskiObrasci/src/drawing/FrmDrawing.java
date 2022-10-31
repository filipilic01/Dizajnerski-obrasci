package drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Point sP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("IT 7/2020 Ilic Filip");
		PnlDrawing pnlDrawing=new PnlDrawing();
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JToggleButton tglbtnPoint = new JToggleButton("Point");

		pnlNorth.setLayout(new GridLayout(0, 5, 0, 0));
		buttonGroup.add(tglbtnPoint);
		pnlNorth.add(tglbtnPoint);
		
		JToggleButton tglbtnLine = new JToggleButton("Line");
	
		buttonGroup.add(tglbtnLine);
		pnlNorth.add(tglbtnLine);
		
		JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	
		buttonGroup.add(tglbtnRectangle);
		pnlNorth.add(tglbtnRectangle);
		
		JToggleButton tglbtnCircle = new JToggleButton("Circle");
		
		buttonGroup.add(tglbtnCircle);
		pnlNorth.add(tglbtnCircle);
		
		JToggleButton tglbtnDonut = new JToggleButton("Donut");
	
		buttonGroup.add(tglbtnDonut);
		pnlNorth.add(tglbtnDonut);
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JToggleButton tglbtnSelect = new JToggleButton("Select");
		buttonGroup.add(tglbtnSelect);
		pnlSouth.add(tglbtnSelect);
		
		JToggleButton tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.modify();
			}
		});
		buttonGroup.add(tglbtnModify);
		pnlSouth.add(tglbtnModify);
		
		JToggleButton tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.delete();
			}
		});
		buttonGroup.add(tglbtnDelete);
		pnlSouth.add(tglbtnDelete);
		
		
		
		pnlDrawing.setBackground(Color.white);
		pnlDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Shape sh;
				Point clickPoint=new Point(e.getX(),e.getY());
		   
					
				
				if(tglbtnPoint.isSelected()) {
					sh=new Point(e.getX(),e.getY(), Color.black);
					pnlDrawing.shapesAdd(sh);
					
				}
				
				if(tglbtnLine.isSelected()) {
				
					if(sP==null) {
						sP=clickPoint;
						
					}
					else {
						sh=new Line(sP, new Point(e.getX(), e.getY()), Color.black);
						pnlDrawing.shapesAdd(sh);
						sP=null;
						
					}
					
					
				}
				 if(tglbtnCircle.isSelected()) {
					DlgCircle dlgCircle=new DlgCircle();
					dlgCircle.getTxtX().setText(Integer.toString(e.getX()));
					dlgCircle.getTxtY().setText(Integer.toString(e.getY()));
					dlgCircle.getTxtX().setEditable(false);
					dlgCircle.getTxtY().setEditable(false);
					dlgCircle.setVisible(true);
					
					if(dlgCircle.isGood()) {
						Circle c=new Circle(new Point(e.getX(),e.getY()),(Integer.parseInt(dlgCircle.getTxtRadius().getText())), dlgCircle.getBtnCircleBorder().getBackground(), dlgCircle.getBtnCircleColorInner().getBackground());
						pnlDrawing.shapesAdd(c);
					}
					
					
					
				}
				 if(tglbtnRectangle.isSelected()) {
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
							pnlDrawing.shapesAdd(r);
						}
					
					
				}
				 if(tglbtnDonut.isSelected()) {
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
				 		pnlDrawing.shapesAdd(d);
				 	}
				
				}
				
				 if(tglbtnSelect.isSelected()) {
			
					 Point point=new Point(e.getX(),e.getY());
					 pnlDrawing.shapesSelect(point.getX(),point.getY());
				//	 tglbtnSelect.setSelected(false);
						
		
				 }
	
				 
			}
			
		});

		contentPane.add(pnlDrawing, BorderLayout.CENTER);
		
	}
}
