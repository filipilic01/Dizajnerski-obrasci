package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;

public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private boolean isGood;
	private boolean cancelCircle;
	private JButton btnCircleBorder;
	private JButton btnCircleColorInner;
	
	Color out=null;
	Color in=null;

	public JButton getBtnCircleBorder() {
		return btnCircleBorder;
	}

	public void setBtnCircleBorder(JButton btnCircleBorder) {
		this.btnCircleBorder = btnCircleBorder;
	}

	public JButton getBtnCircleColorInner() {
		return btnCircleColorInner;
	}

	public void setBtnCircleColorInner(JButton btnCircleColorInner) {
		this.btnCircleColorInner = btnCircleColorInner;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setBounds(100, 100, 250, 250);
		setModal(true);
		setTitle("Circle");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			Box horizontalBox = Box.createHorizontalBox();
			GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
			gbc_horizontalBox.insets = new Insets(0, 0, 5, 5);
			gbc_horizontalBox.gridx = 1;
			gbc_horizontalBox.gridy = 1;
			contentPanel.add(horizontalBox, gbc_horizontalBox);
		}
		{
			JLabel lblCenter = new JLabel("Center of Circle");
			GridBagConstraints gbc_lblCenter = new GridBagConstraints();
			gbc_lblCenter.anchor = GridBagConstraints.WEST;
			gbc_lblCenter.insets = new Insets(0, 0, 5, 0);
			gbc_lblCenter.gridx = 2;
			gbc_lblCenter.gridy = 2;
			contentPanel.add(lblCenter, gbc_lblCenter);
		}
		{
			JLabel lblCX = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblCX = new GridBagConstraints();
			gbc_lblCX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCX.anchor = GridBagConstraints.EAST;
			gbc_lblCX.gridx = 1;
			gbc_lblCX.gridy = 3;
			contentPanel.add(lblCX, gbc_lblCX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.anchor = GridBagConstraints.WEST;
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.gridx = 2;
			gbc_txtX.gridy = 3;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblCY = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblCY = new GridBagConstraints();
			gbc_lblCY.anchor = GridBagConstraints.EAST;
			gbc_lblCY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCY.gridx = 1;
			gbc_lblCY.gridy = 4;
			contentPanel.add(lblCY, gbc_lblCY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.anchor = GridBagConstraints.WEST;
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.gridx = 2;
			gbc_txtY.gridy = 4;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 1;
			gbc_lblRadius.gridy = 5;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.anchor = GridBagConstraints.WEST;
			gbc_txtRadius.gridx = 2;
			gbc_txtRadius.gridy = 5;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
		    btnCircleBorder = new JButton("Border color");
		    
			btnCircleBorder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 out=JColorChooser.showDialog(null, "Choose a border color!", btnCircleBorder.getBackground());
					if(out !=null)
						btnCircleBorder.setBackground(out);
				}
			});
			GridBagConstraints gbc_btnCircleBorder = new GridBagConstraints();
			gbc_btnCircleBorder.insets = new Insets(0, 0, 0, 5);
			gbc_btnCircleBorder.gridx = 1;
			gbc_btnCircleBorder.gridy = 6;
			contentPanel.add(btnCircleBorder, gbc_btnCircleBorder);
		}
		{
			 btnCircleColorInner = new JButton("Inner color");
			 btnCircleColorInner.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		in=JColorChooser.showDialog(null, "Choose a inner color!", btnCircleColorInner.getBackground());
			 		if(in !=null)
			 			btnCircleColorInner.setBackground(in);
			 		
			 	
			 	}
			 });
			GridBagConstraints gbc_btnCircleColorInner = new GridBagConstraints();
			gbc_btnCircleColorInner.gridx = 2;
			gbc_btnCircleColorInner.gridy = 6;
			contentPanel.add(btnCircleColorInner, gbc_btnCircleColorInner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(txtRadius.getText().trim().isEmpty())  
								JOptionPane.showMessageDialog(null, "Please enter value in the field!");
								else if ((Integer.parseInt(txtRadius.getText().trim())<=0))
									JOptionPane.showMessageDialog(null,"Please, enter value greater than 0!");
								else {
									setGood(true);
									setVisible(false);
								}
									
						}catch(Exception e1) {
							JOptionPane.showMessageDialog(null, "Please, enter the number!");
						}
					
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setCancelCircle(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public boolean isGood() {
		return isGood;
	}

	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}

	public boolean isCancelCircle() {
		return cancelCircle;
	}

	public void setCancelCircle(boolean cancelCircle) {
		this.cancelCircle = cancelCircle;
	}

}
