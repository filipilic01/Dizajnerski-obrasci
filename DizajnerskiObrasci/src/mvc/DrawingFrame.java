package mvc;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DrawingFrame extends JFrame implements PropertyChangeListener{
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
	private JPanel panel;
	private JButton btnUndo;
	private JButton btnRedo;
	

	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnBorder;
	private JButton btnInner;
	
	private JPanel pnlEast;
	private JButton btnSave;
	private JTextArea textArea_1;
	private JScrollPane scrollPane= new JScrollPane();
	
	
	
	public JButton getBtnBorder() {
		return btnBorder;
	}

	public void setBtnBorder(JButton btnBorder) {
		this.btnBorder = btnBorder;
	}

	public JButton getBtnInner() {
		return btnInner;
	}

	public void setBtnInner(JButton btnInner) {
		this.btnInner = btnInner;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public DrawingFrame() {
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("IT 7/2020 Ilic Filip");
		
		setBounds(100, 100, 900, 600);
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
		tglbtnModify.setEnabled(false);
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});
		buttonGroup.add(tglbtnModify);
		pnlSouth.add(tglbtnModify);
		
		tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setEnabled(false);
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		buttonGroup.add(tglbtnDelete);
		pnlSouth.add(tglbtnDelete);
		
		
		
		contentPane.setBackground(Color.white);
		view.setBackground(Color.WHITE);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		//Icon icon = new ImageIcon("/home/filip/Desktop/icons/");
		btnUndo = new JButton("Undo");
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.insets = new Insets(0, 0, 5, 0);
		gbc_btnUndo.gridx = 0;
		gbc_btnUndo.gridy = 0;
		panel.add(btnUndo, gbc_btnUndo);
		
		btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.insets = new Insets(0, 0, 5, 0);
		gbc_btnRedo.gridx = 0;
		gbc_btnRedo.gridy = 1;
		panel.add(btnRedo, gbc_btnRedo);
		
		btnToFront = new JButton("To Front");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toFront();
			}
		});
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnToFront.gridx = 0;
		gbc_btnToFront.gridy = 2;
		panel.add(btnToFront, gbc_btnToFront);
		
		btnToBack = new JButton("To Back");
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toBack();
			}
		});
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnToBack.gridx = 0;
		gbc_btnToBack.gridy = 3;
		panel.add(btnToBack, gbc_btnToBack);
		
		btnBringToFront = new JButton("Bring To Front");
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.bringToFront();
			}
		});
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnBringToFront.gridx = 0;
		gbc_btnBringToFront.gridy = 4;
		panel.add(btnBringToFront, gbc_btnBringToFront);
		
		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.bringToBack();
			}
		});
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBringToBack.gridx = 0;
		gbc_btnBringToBack.gridy = 5;
		panel.add(btnBringToBack, gbc_btnBringToBack);
		
		btnBorder = new JButton("Border color");
		btnBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.colorTheBorder();
			}
		});
		btnBorder.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnBorder = new GridBagConstraints();
		gbc_btnBorder.insets = new Insets(0, 0, 5, 0);
		gbc_btnBorder.gridx = 0;
		gbc_btnBorder.gridy = 6;
		panel.add(btnBorder, gbc_btnBorder);
		
		btnInner = new JButton("Inner color");
		btnInner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.colorTheInner();
			}
		});
		btnInner.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnInner = new GridBagConstraints();
		gbc_btnInner.insets = new Insets(0, 0, 5, 0);
		gbc_btnInner.gridx = 0;
		gbc_btnInner.gridy = 7;
		panel.add(btnInner, gbc_btnInner);
		
		textArea_1 = new JTextArea();
		
		
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 9;
		panel.add(textArea_1, gbc_textArea_1);
		
		/*scrollPane.setViewportView(textArea_1);
		panel.add(scrollPane);*/
		
		pnlEast = new JPanel();
		contentPane.add(pnlEast, BorderLayout.EAST);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.save();
			}
		});
		pnlEast.add(btnSave);
		
		//contentPane.repaint();
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
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
	public JTextArea getTextArea_1() {
		return textArea_1;
	}

	public void setTextArea_1(JTextArea textArea_1) {
		this.textArea_1 = textArea_1;
	}
	
	public void propertyChange(PropertyChangeEvent pce) {
			if(pce.getPropertyName()=="Delete enable") {
				tglbtnDelete.setEnabled(true);
			}
			else if(pce.getPropertyName()=="Delete disabled") {
				tglbtnDelete.setEnabled(false);
			}
			else if(pce.getPropertyName()=="Modify enable") {
				tglbtnModify.setEnabled(true);
			}
			else if(pce.getPropertyName()=="Modify disabled") {
				tglbtnModify.setEnabled(false);
			}
			
			contentPane.repaint();
	}
	
}
