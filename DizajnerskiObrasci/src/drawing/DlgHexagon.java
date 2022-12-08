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
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JButton btnInner;
	private JButton btnBorder;
	

	private boolean isGood;
	private boolean cancelCircle;
	Color out=null;
	Color in=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagon() {
		setBounds(100, 100, 250, 250);
		setModal(true);
		setTitle("Hexagon");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCenter = new JLabel("Center of Hexagon");
			GridBagConstraints gbc_lblCenter = new GridBagConstraints();
			gbc_lblCenter.insets = new Insets(0, 0, 5, 0);
			gbc_lblCenter.gridx = 10;
			gbc_lblCenter.gridy = 1;
			contentPanel.add(lblCenter, gbc_lblCenter);
		}
		{
			JLabel lblCX = new JLabel("Coordinate of X:");
			GridBagConstraints gbc_lblCX = new GridBagConstraints();
			gbc_lblCX.anchor = GridBagConstraints.EAST;
			gbc_lblCX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCX.gridx = 9;
			gbc_lblCX.gridy = 3;
			contentPanel.add(lblCX, gbc_lblCX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 10;
			gbc_txtX.gridy = 3;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblCY = new JLabel("Coordinate of Y:");
			GridBagConstraints gbc_lblCY = new GridBagConstraints();
			gbc_lblCY.anchor = GridBagConstraints.EAST;
			gbc_lblCY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCY.gridx = 9;
			gbc_lblCY.gridy = 4;
			contentPanel.add(lblCY, gbc_lblCY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 10;
			gbc_txtY.gridy = 4;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 9;
			gbc_lblRadius.gridy = 5;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 10;
			gbc_txtRadius.gridy = 5;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			btnBorder = new JButton("Border color");
			btnBorder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					out=JColorChooser.showDialog(null, "Choose a border color!", btnBorder.getBackground());
					if(out !=null)
						btnBorder.setBackground(out);
				}
			});
			GridBagConstraints gbc_btnBorder = new GridBagConstraints();
			gbc_btnBorder.insets = new Insets(0, 0, 5, 5);
			gbc_btnBorder.gridx = 9;
			gbc_btnBorder.gridy = 7;
			contentPanel.add(btnBorder, gbc_btnBorder);
		}
		{
			btnInner = new JButton("Inner color");
			btnInner.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					in=JColorChooser.showDialog(null, "Choose a border color!", btnInner.getBackground());
					if(in !=null)
						btnInner.setBackground(in);
				}
			});
			GridBagConstraints gbc_btnInner = new GridBagConstraints();
			gbc_btnInner.insets = new Insets(0, 0, 5, 0);
			gbc_btnInner.gridx = 10;
			gbc_btnInner.gridy = 7;
			contentPanel.add(btnInner, gbc_btnInner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
					public void actionPerformed(ActionEvent arg0) {
						setCancelCircle(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
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
	
	public JButton getBtnInner() {
		return btnInner;
	}

	public void setBtnInner(JButton btnInner) {
		this.btnInner = btnInner;
	}

	public JButton getBtnBorder() {
		return btnBorder;
	}

	public void setBtnBorder(JButton btnBorder) {
		this.btnBorder = btnBorder;
	}

}
