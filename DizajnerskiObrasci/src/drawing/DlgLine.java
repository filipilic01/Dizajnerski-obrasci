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
import java.awt.Component;
import javax.swing.Box;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSPX;
	private JTextField txtEPX;
	private JTextField txtSPY;
	private JTextField txtEPY;
	private boolean Okayy;
	Color clr;
	private boolean cancelLine;
	private JButton btnColorLine;
	
	public JButton getBtnColorLine() {
		return btnColorLine;
	}

	public void setBtnColorLine(JButton btnColorLine) {
		this.btnColorLine = btnColorLine;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBounds(100, 100, 250, 250);
		setModal(true);
		setTitle("Line");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblSP = new JLabel("Start Point");
			GridBagConstraints gbc_lblSP = new GridBagConstraints();
			gbc_lblSP.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblSP.insets = new Insets(0, 0, 5, 5);
			gbc_lblSP.gridx = 2;
			gbc_lblSP.gridy = 1;
			contentPanel.add(lblSP, gbc_lblSP);
		}
		{
			JLabel lblSPX = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblSPX = new GridBagConstraints();
			gbc_lblSPX.insets = new Insets(0, 0, 5, 5);
			gbc_lblSPX.anchor = GridBagConstraints.EAST;
			gbc_lblSPX.gridx = 1;
			gbc_lblSPX.gridy = 2;
			contentPanel.add(lblSPX, gbc_lblSPX);
		}
		{
			txtSPX = new JTextField();
			GridBagConstraints gbc_txtSPX = new GridBagConstraints();
			gbc_txtSPX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSPX.insets = new Insets(0, 0, 5, 5);
			gbc_txtSPX.gridx = 2;
			gbc_txtSPX.gridy = 2;
			contentPanel.add(txtSPX, gbc_txtSPX);
			txtSPX.setColumns(10);
		}
		{
			JLabel lblSPY = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblSPY = new GridBagConstraints();
			gbc_lblSPY.anchor = GridBagConstraints.EAST;
			gbc_lblSPY.insets = new Insets(0, 0, 5, 5);
			gbc_lblSPY.gridx = 1;
			gbc_lblSPY.gridy = 3;
			contentPanel.add(lblSPY, gbc_lblSPY);
		}
		{
			txtSPY = new JTextField();
			GridBagConstraints gbc_txtSPY = new GridBagConstraints();
			gbc_txtSPY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSPY.insets = new Insets(0, 0, 5, 5);
			gbc_txtSPY.gridx = 2;
			gbc_txtSPY.gridy = 3;
			contentPanel.add(txtSPY, gbc_txtSPY);
			txtSPY.setColumns(10);
		}
		{
			JLabel lblEP = new JLabel("End Point");
			GridBagConstraints gbc_lblEP = new GridBagConstraints();
			gbc_lblEP.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblEP.insets = new Insets(0, 0, 5, 5);
			gbc_lblEP.gridx = 2;
			gbc_lblEP.gridy = 4;
			contentPanel.add(lblEP, gbc_lblEP);
		}
		{
			JLabel lblEPX = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblEPX = new GridBagConstraints();
			gbc_lblEPX.anchor = GridBagConstraints.EAST;
			gbc_lblEPX.insets = new Insets(0, 0, 5, 5);
			gbc_lblEPX.gridx = 1;
			gbc_lblEPX.gridy = 5;
			contentPanel.add(lblEPX, gbc_lblEPX);
		}
		{
			txtEPX = new JTextField();
			GridBagConstraints gbc_txtEPX = new GridBagConstraints();
			gbc_txtEPX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEPX.insets = new Insets(0, 0, 5, 5);
			gbc_txtEPX.gridx = 2;
			gbc_txtEPX.gridy = 5;
			contentPanel.add(txtEPX, gbc_txtEPX);
			txtEPX.setColumns(10);
		}
		{
			JLabel lblEPY = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblEPY = new GridBagConstraints();
			gbc_lblEPY.anchor = GridBagConstraints.EAST;
			gbc_lblEPY.insets = new Insets(0, 0, 5, 5);
			gbc_lblEPY.gridx = 1;
			gbc_lblEPY.gridy = 6;
			contentPanel.add(lblEPY, gbc_lblEPY);
		}
		{
			txtEPY = new JTextField();
			GridBagConstraints gbc_txtEPY = new GridBagConstraints();
			gbc_txtEPY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEPY.insets = new Insets(0, 0, 5, 5);
			gbc_txtEPY.gridx = 2;
			gbc_txtEPY.gridy = 6;
			contentPanel.add(txtEPY, gbc_txtEPY);
			txtEPY.setColumns(10);
		}
		{
		    btnColorLine = new JButton("Border color");
			btnColorLine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clr=JColorChooser.showDialog(null, "Choose a color!", clr);
					btnColorLine.getBackground();
					if(clr !=null) {
						btnColorLine.setBackground(clr);
					}
				}
			});
			GridBagConstraints gbc_btnColorLine = new GridBagConstraints();
			gbc_btnColorLine.insets = new Insets(0, 0, 5, 5);
			gbc_btnColorLine.gridx = 2;
			gbc_btnColorLine.gridy = 7;
			contentPanel.add(btnColorLine, gbc_btnColorLine);
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
			gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
			gbc_horizontalStrut.gridx = 3;
			gbc_horizontalStrut.gridy = 7;
			contentPanel.add(horizontalStrut, gbc_horizontalStrut);
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
			gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
			gbc_horizontalStrut.gridx = 4;
			gbc_horizontalStrut.gridy = 7;
			contentPanel.add(horizontalStrut, gbc_horizontalStrut);
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
			gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
			gbc_horizontalStrut.gridx = 0;
			gbc_horizontalStrut.gridy = 8;
			contentPanel.add(horizontalStrut, gbc_horizontalStrut);
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
							 if(txtSPX.getText().trim().isEmpty() || txtSPY.getText().trim().isEmpty() || txtEPX.getText().trim().isEmpty() || txtEPY.getText().trim().isEmpty()) {
								 JOptionPane.showMessageDialog(null, "Please, enter values in the fields!");
							 }
							 else {
								 setOkayy(true);
								 setVisible(false);
							 }
							 
						 }catch(Exception exception) {
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
						setOkayy(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtSPX() {
		return txtSPX;
	}

	public void setTxtSPX(JTextField txtSPX) {
		this.txtSPX = txtSPX;
	}

	public JTextField getTxtEPX() {
		return txtEPX;
	}

	public void setTxtEPX(JTextField txtEPX) {
		this.txtEPX = txtEPX;
	}

	public JTextField getTxtSPY() {
		return txtSPY;
	}

	public void setTxtSPY(JTextField txtSPY) {
		this.txtSPY = txtSPY;
	}

	public JTextField getTxtEPY() {
		return txtEPY;
	}

	public void setTxtEPY(JTextField txtEPY) {
		this.txtEPY = txtEPY;
	}

	public boolean isOkayy() {
		return Okayy;
	}

	public void setOkayy(boolean okayy) {
		Okayy = okayy;
	}

	public boolean isCancelLine() {
		return cancelLine;
	}

	public void setCancelLine(boolean cancelLine) {
		this.cancelLine = cancelLine;
	}

}
