package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;*/
import java.awt.Component;
import javax.swing.Box;

public class DlgDonut extends JDialog {
	private boolean isOkkk;
	private boolean cancelDonut;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtInner;
	private JTextField txtRadius;
	private JButton btnDonutBorder;
	private JButton btnDonutInner;

	public JButton getBtnDonutBorder() {
		return btnDonutBorder;
	}

	public void setBtnDonutBorder(JButton btnDonutBorder) {
		this.btnDonutBorder = btnDonutBorder;
	}

	public JButton getBtnDonutInner() {
		return btnDonutInner;
	}

	public void setBtnDonutInner(JButton btnDonutInner) {
		this.btnDonutInner = btnDonutInner;
	}

	public boolean isOkkk() {
		return isOkkk;
	}

	public void setOkkk(boolean isOkkk) {
		this.isOkkk = isOkkk;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setBounds(100, 100, 250, 250);
		setModal(true);
		setTitle("Donut");
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(txtRadius.getText().trim().isEmpty() || txtInner.getText().trim().isEmpty())  
								JOptionPane.showMessageDialog(null, "Please enter value in the field!");
							else if((Integer.parseInt(txtRadius.getText().trim())<=0) || (Integer.parseInt(txtInner.getText().trim())<=0) )
								JOptionPane.showMessageDialog(null,"Please, enter value greater than 0!");
							else if((Integer.parseInt(txtRadius.getText().trim()))< (Integer.parseInt(txtInner.getText().trim())))
								JOptionPane.showMessageDialog(null, "Inner radius mustn't be greater than radius!");
							else {
							     setOkkk(true);
							     setVisible(false);
							}
						}catch(Exception exc) {
						JOptionPane.showMessageDialog(null, "Please, enter the numbers in the fields!");
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
						setCancelDonut(true);
						dispose();
						}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblCenter = new JLabel("Center of donut:");
				GridBagConstraints gbc_lblCenter = new GridBagConstraints();
				gbc_lblCenter.insets = new Insets(0, 0, 5, 5);
				gbc_lblCenter.gridx = 2;
				gbc_lblCenter.gridy = 1;
				panel.add(lblCenter, gbc_lblCenter);
			}
			{
				JLabel lblX = new JLabel("Coordinate X:");
				GridBagConstraints gbc_lblX = new GridBagConstraints();
				gbc_lblX.anchor = GridBagConstraints.EAST;
				gbc_lblX.insets = new Insets(0, 0, 5, 5);
				gbc_lblX.gridx = 2;
				gbc_lblX.gridy = 2;
				panel.add(lblX, gbc_lblX);
			}
			{
				txtX = new JTextField();
				GridBagConstraints gbc_txtX = new GridBagConstraints();
				gbc_txtX.insets = new Insets(0, 0, 5, 5);
				gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtX.gridx = 3;
				gbc_txtX.gridy = 2;
				panel.add(txtX, gbc_txtX);
				txtX.setColumns(10);
			}
			{
				JLabel lblY = new JLabel("Coordinate Y:");
				GridBagConstraints gbc_lblY = new GridBagConstraints();
				gbc_lblY.anchor = GridBagConstraints.EAST;
				gbc_lblY.insets = new Insets(0, 0, 5, 5);
				gbc_lblY.gridx = 2;
				gbc_lblY.gridy = 3;
				panel.add(lblY, gbc_lblY);
			}
			{
				txtY = new JTextField();
				GridBagConstraints gbc_txtY = new GridBagConstraints();
				gbc_txtY.insets = new Insets(0, 0, 5, 5);
				gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtY.gridx = 3;
				gbc_txtY.gridy = 3;
				panel.add(txtY, gbc_txtY);
				txtY.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Radius:");
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 2;
				gbc_lblNewLabel_3.gridy = 4;
				panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				 txtRadius = new JTextField();
				GridBagConstraints gbc_txtRadius = new GridBagConstraints();
				gbc_txtRadius.insets = new Insets(0, 0, 5, 5);
				gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtRadius.gridx = 3;
				gbc_txtRadius.gridy = 4;
				panel.add(txtRadius, gbc_txtRadius);
				txtRadius.setColumns(10);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Inner radius:");
				GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
				gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_4.gridx = 2;
				gbc_lblNewLabel_4.gridy = 5;
				panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
			}
			{
				txtInner = new JTextField();
				GridBagConstraints gbc_txtInner = new GridBagConstraints();
				gbc_txtInner.insets = new Insets(0, 0, 5, 5);
				gbc_txtInner.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtInner.gridx = 3;
				gbc_txtInner.gridy = 5;
				panel.add(txtInner, gbc_txtInner);
				txtInner.setColumns(10);
			}
			{
				 btnDonutBorder = new JButton("Border color");
				btnDonutBorder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color dO=JColorChooser.showDialog(null, "Choose a border color!", btnDonutBorder.getBackground());
						if(dO!=null)
							btnDonutBorder.setBackground(dO);
					}
				});
				GridBagConstraints gbc_btnDonutBorder = new GridBagConstraints();
				gbc_btnDonutBorder.insets = new Insets(0, 0, 0, 5);
				gbc_btnDonutBorder.gridx = 2;
				gbc_btnDonutBorder.gridy = 6;
				panel.add(btnDonutBorder, gbc_btnDonutBorder);
			}
			{
				 btnDonutInner = new JButton("Inner color");
				btnDonutInner.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color dI=JColorChooser.showDialog(null, "Choose a inner color!", btnDonutInner.getBackground());
						if(dI!=null)
							btnDonutInner.setBackground(dI);
					}
					
				});
				GridBagConstraints gbc_btnDonutInner = new GridBagConstraints();
				gbc_btnDonutInner.insets = new Insets(0, 0, 0, 5);
				gbc_btnDonutInner.gridx = 3;
				gbc_btnDonutInner.gridy = 6;
				panel.add(btnDonutInner, gbc_btnDonutInner);
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

	public JTextField getTxtInner() {
		return txtInner;
	}

	public void setTxtInner(JTextField txtInner) {
		this.txtInner = txtInner;
	}

	public boolean isCancelDonut() {
		return cancelDonut;
	}

	public void setCancelDonut(boolean cancelDonut) {
		this.cancelDonut = cancelDonut;
	}

}
