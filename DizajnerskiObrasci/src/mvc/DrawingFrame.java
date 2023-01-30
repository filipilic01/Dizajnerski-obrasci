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
import javax.swing.JScrollBar;

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
	private JButton btnLoad;
	private JButton btnExecute;
	private JButton btnUnexecute;
	
	
	
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
		
		setBounds(100, 100, 1136, 720);
		contentPane = new JPanel();
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlNorth = new JPanel();
		
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		GridBagLayout gbl_pnlNorth = new GridBagLayout();
		gbl_pnlNorth.columnWidths = new int[]{178, 178, 178, 178, 178, 149, 0};
		gbl_pnlNorth.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 25, 0, 25, 0};
		gbl_pnlNorth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlNorth.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlNorth.setLayout(gbl_pnlNorth);
		
		tglbtnPoint = new JToggleButton("Point");
		buttonGroup.add(tglbtnPoint);
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.fill = GridBagConstraints.BOTH;
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPoint.gridx = 0;
		gbc_tglbtnPoint.gridy = 6;
		pnlNorth.add(tglbtnPoint, gbc_tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		
			buttonGroup.add(tglbtnLine);
			GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
			gbc_tglbtnLine.fill = GridBagConstraints.BOTH;
			gbc_tglbtnLine.insets = new Insets(0, 0, 5, 5);
			gbc_tglbtnLine.gridx = 1;
			gbc_tglbtnLine.gridy = 6;
			pnlNorth.add(tglbtnLine, gbc_tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		
			buttonGroup.add(tglbtnRectangle);
			GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
			gbc_tglbtnRectangle.fill = GridBagConstraints.BOTH;
			gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 5);
			gbc_tglbtnRectangle.gridx = 2;
			gbc_tglbtnRectangle.gridy = 6;
			pnlNorth.add(tglbtnRectangle, gbc_tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		
		buttonGroup.add(tglbtnCircle);
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.fill = GridBagConstraints.BOTH;
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCircle.gridx = 3;
		gbc_tglbtnCircle.gridy = 6;
		pnlNorth.add(tglbtnCircle, gbc_tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		
			buttonGroup.add(tglbtnDonut);
			GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
			gbc_tglbtnDonut.fill = GridBagConstraints.BOTH;
			gbc_tglbtnDonut.insets = new Insets(0, 0, 5, 5);
			gbc_tglbtnDonut.gridx = 4;
			gbc_tglbtnDonut.gridy = 6;
			pnlNorth.add(tglbtnDonut, gbc_tglbtnDonut);
			
			tglbtnHexagon = new JToggleButton("Hexagon");
			
			buttonGroup.add(tglbtnHexagon);
			GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
			gbc_tglbtnHexagon.insets = new Insets(0, 0, 5, 0);
			gbc_tglbtnHexagon.fill = GridBagConstraints.VERTICAL;
			gbc_tglbtnHexagon.gridx = 5;
			gbc_tglbtnHexagon.gridy = 6;
			pnlNorth.add(tglbtnHexagon, gbc_tglbtnHexagon);
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		GridBagLayout gbl_pnlSouth = new GridBagLayout();
		gbl_pnlSouth.columnWidths = new int[]{320, 78, 81, 81, 0};
		gbl_pnlSouth.rowHeights = new int[]{0, 25, 0};
		gbl_pnlSouth.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlSouth.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		pnlSouth.setLayout(gbl_pnlSouth);
		
		JScrollPane pane = new JScrollPane();
		
		
		
		textArea_1 = new JTextArea(10,70);
		pnlSouth.add(textArea_1);
	
		
		pane.getViewport().add(textArea_1);
		pnlSouth.add(pane);
		//pane.setBounds(10,60,780,500);
		
		
		
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
		
		/*scrollPane.setViewportView(textArea_1);
		panel.add(scrollPane);*/
		
		pnlEast = new JPanel();
		contentPane.add(pnlEast, BorderLayout.EAST);
		GridBagLayout gbl_pnlEast = new GridBagLayout();
		gbl_pnlEast.columnWidths = new int[]{0, 94, 0};
		gbl_pnlEast.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlEast.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlEast.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlEast.setLayout(gbl_pnlEast);
		
		btnSave = new JButton("Save log");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.save();
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 0;
		pnlEast.add(btnSave, gbc_btnSave);
		
		btnExecute = new JButton("Execute");
		btnExecute.setEnabled(false);
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.executeCommand();
			}
		});
		
		btnLoad = new JButton("Load file");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.load();
			}
		});
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoad.gridx = 1;
		gbc_btnLoad.gridy = 1;
		pnlEast.add(btnLoad, gbc_btnLoad);
		GridBagConstraints gbc_btnExecute = new GridBagConstraints();
		gbc_btnExecute.insets = new Insets(0, 0, 5, 0);
		gbc_btnExecute.gridx = 1;
		gbc_btnExecute.gridy = 2;
		pnlEast.add(btnExecute, gbc_btnExecute);
		
		/*btnUnexecute = new JButton("Unexecute");
		btnUnexecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.unexecuteCommand();
			}
		});
		btnUnexecute.setEnabled(false);
		GridBagConstraints gbc_btnUnexecute = new GridBagConstraints();
		gbc_btnUnexecute.insets = new Insets(0, 0, 5, 0);
		gbc_btnUnexecute.gridx = 1;
		gbc_btnUnexecute.gridy = 3;
		pnlEast.add(btnUnexecute, gbc_btnUnexecute);
		*/
		tglbtnSelect = new JToggleButton("Select");
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSelect.gridx = 1;
		gbc_tglbtnSelect.gridy = 6;
		pnlEast.add(tglbtnSelect, gbc_tglbtnSelect);
		buttonGroup.add(tglbtnSelect);
		
		tglbtnModify = new JToggleButton("Modify");
		GridBagConstraints gbc_tglbtnModify = new GridBagConstraints();
		gbc_tglbtnModify.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnModify.gridx = 1;
		gbc_tglbtnModify.gridy = 7;
		pnlEast.add(tglbtnModify, gbc_tglbtnModify);
		tglbtnModify.setEnabled(false);
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});
		buttonGroup.add(tglbtnModify);
		
		tglbtnDelete = new JToggleButton("Delete");
		GridBagConstraints gbc_tglbtnDelete = new GridBagConstraints();
		gbc_tglbtnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnDelete.gridx = 1;
		gbc_tglbtnDelete.gridy = 8;
		pnlEast.add(tglbtnDelete, gbc_tglbtnDelete);
		tglbtnDelete.setEnabled(false);
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		buttonGroup.add(tglbtnDelete);
		
		//contentPane.repaint();
	}

	public JButton getBtnUnexecute() {
		return btnUnexecute;
	}

	public void setBtnUnexecute(JButton btnUnexecute) {
		this.btnUnexecute = btnUnexecute;
	}

	public JButton getBtnExecute() {
		return btnExecute;
	}

	public void setBtnExecute(JButton btnExecute) {
		this.btnExecute = btnExecute;
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
			
			//contentPane.repaint();
	}
	
}
