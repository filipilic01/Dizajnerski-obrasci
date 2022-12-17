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
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
	private JPanel panel;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	
	
	
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
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
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
				controller.ToFront();
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
				controller.ToBack();
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
				controller.BringToFront();
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
				controller.BringToBack();
			}
		});
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.gridx = 0;
		gbc_btnBringToBack.gridy = 5;
		panel.add(btnBringToBack, gbc_btnBringToBack);
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
