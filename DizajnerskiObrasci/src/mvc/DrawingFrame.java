package mvc;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;


import geometry.Point;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingFrame extends JFrame{
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Point sP;
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private JToggleButton tglbtnPoint;
	
	

	private JToggleButton tglbtnLine; 
	private JToggleButton tglbtnRectangle; 
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut; 
	private JToggleButton tglbtnSelect;
	private JToggleButton tglbtnModify;
	private JToggleButton tglbtnDelete; 
	private JToggleButton tglbtnHexagon;
	
	
	
	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public DrawingFrame() {
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("IT 7/2020 Ilic Filip");
		
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		tglbtnPoint = new JToggleButton("Point");

		pnlNorth.setLayout(new GridLayout(0, 5, 0, 0));
		buttonGroup.add(tglbtnPoint);
		pnlNorth.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
	
		buttonGroup.add(tglbtnLine);
		pnlNorth.add(tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
	
		buttonGroup.add(tglbtnRectangle);
		pnlNorth.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		
		buttonGroup.add(tglbtnCircle);
		pnlNorth.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
	
		buttonGroup.add(tglbtnDonut);
		pnlNorth.add(tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		
		buttonGroup.add(tglbtnHexagon);
		pnlNorth.add(tglbtnHexagon);
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		tglbtnSelect = new JToggleButton("Select");
		buttonGroup.add(tglbtnSelect);
		pnlSouth.add(tglbtnSelect);
		
		tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});
		buttonGroup.add(tglbtnModify);
		pnlSouth.add(tglbtnModify);
		
		tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		buttonGroup.add(tglbtnDelete);
		pnlSouth.add(tglbtnDelete);
		
		
		contentPane.setBackground(Color.white);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public DrawingView getView() {
		return view;
	}

	public void setDrawingController(DrawingController drawingController) {
		this.controller = drawingController;
	}
	
	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public JToggleButton getTglbtnModify() {
		return tglbtnModify;
	}

	public JToggleButton getTglbtnDelete() {
		return tglbtnDelete;
	}
	
}
