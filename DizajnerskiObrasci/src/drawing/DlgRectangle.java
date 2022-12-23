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

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private boolean isOkk;
	private boolean cancelRect;
	private JButton btnBorderColor;
	private JButton btnInnerColor;
	//JColorChooser colorChooser=new JColorChooser();

	public boolean isOkk() {
		return isOkk;
	}

	public void setOkk(boolean isOkk) {
		this.isOkk = isOkk;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setBounds(100, 100, 250, 250);
		setModal(true);
		setTitle("Rectangle");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblULP = new JLabel("Upper Left Point:");
			GridBagConstraints gbc_lblULP = new GridBagConstraints();
			gbc_lblULP.anchor = GridBagConstraints.EAST;
			gbc_lblULP.insets = new Insets(0, 0, 5, 5);
			gbc_lblULP.gridx = 1;
			gbc_lblULP.gridy = 1;
			contentPanel.add(lblULP, gbc_lblULP);
		}
		{
			JLabel lblULPX = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblULPX = new GridBagConstraints();
			gbc_lblULPX.anchor = GridBagConstraints.EAST;
			gbc_lblULPX.insets = new Insets(0, 0, 5, 5);
			gbc_lblULPX.gridx = 1;
			gbc_lblULPX.gridy = 2;
			contentPanel.add(lblULPX, gbc_lblULPX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 2;
			gbc_txtX.gridy = 2;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblULPY = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblULPY = new GridBagConstraints();
			gbc_lblULPY.anchor = GridBagConstraints.EAST;
			gbc_lblULPY.insets = new Insets(0, 0, 5, 5);
			gbc_lblULPY.gridx = 1;
			gbc_lblULPY.gridy = 3;
			contentPanel.add(lblULPY, gbc_lblULPY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 2;
			gbc_txtY.gridy = 3;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.anchor = GridBagConstraints.EAST;
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 1;
			gbc_lblWidth.gridy = 4;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 2;
			gbc_txtWidth.gridy = 4;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height:");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.anchor = GridBagConstraints.EAST;
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 1;
			gbc_lblHeight.gridy = 5;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 2;
			gbc_txtHeight.gridy = 5;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		/*{
		    btnBorderColor = new JButton("Border color");
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//JColorChooser colorChooser=new JColorChooser();
					Color outline=JColorChooser.showDialog(null, "Pick color for border", btnBorderColor.getBackground());
					if(outline !=null)
					btnBorderColor.setBackground(outline);
				
					
				}
			});
			GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
			gbc_btnBorderColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnBorderColor.gridx = 1;
			gbc_btnBorderColor.gridy = 7;
			contentPanel.add(btnBorderColor, gbc_btnBorderColor);
		}
		{
			 btnInnerColor = new JButton("Inner color");
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color inner =JColorChooser.showDialog(null, "Pick inner color", btnInnerColor.getBackground());
					if(inner !=null)
					btnInnerColor.setBackground(inner);
					
				}
			});
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.insets = new Insets(0, 0, 5, 0);
			gbc_btnInnerColor.gridx = 2;
			gbc_btnInnerColor.gridy = 7;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		}*/
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(txtHeight.getText().trim().isEmpty() || txtWidth.getText().trim().isEmpty() || txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty())
								JOptionPane.showMessageDialog(null, "Please, enter values!");
							else if(Integer.parseInt(txtHeight.getText().trim())<=0 || (Integer.parseInt(txtWidth.getText().trim())<=0)) {
								JOptionPane.showMessageDialog(null, "Please, enter values greater than 0!");	
							}
							
							else {
							isOkk=true;
							setVisible(false);
						}
							}catch(NumberFormatException me) {
								
								JOptionPane.showMessageDialog(null, "Please enter numbers in the fields!");
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
						setCancelRect(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
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

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public boolean isCancelRect() {
		return cancelRect;
	}

	public void setCancelRect(boolean cancelRect) {
		this.cancelRect = cancelRect;
	}

}
