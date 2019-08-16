package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Login extends JFrame implements Serializable, ActionListener {
	
	
	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDHT = 400;
	private static final int FRAME_HEIGHT = 400;

	private static final int MIN_WIDHT = 400;
	private static final int MIN_HEIGHT = 400;

	private JTextField userfield;

	private JPasswordField passfield;
	
	//...........................................................................
	
	public Login() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = dimension.width / 2 - FRAME_WIDHT / 2;
		int y = dimension.height / 2 - FRAME_HEIGHT / 2;
		setBounds(x, y, FRAME_WIDHT, FRAME_HEIGHT);
		
		
	
		
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//
		setMinimumSize(new Dimension(MIN_WIDHT, MIN_HEIGHT));
		setLayout(new BorderLayout());
		setTitle("صفحهٔ ورود");
		
		JPanel LoginPanel = new JPanel();

		LoginPanel.setLayout(createGridbagLayout());

		Color PanelColor = new Color(109, 198, 255);
		LoginPanel.setBackground(PanelColor);
		LoginPanel.setPreferredSize(new Dimension(800, 650));

		this.add(LoginPanel, BorderLayout.CENTER);

		GridBagConstraints gbc = new GridBagConstraints(4, 4, 8, 1, 0.0d, 0.0d,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0);

		//
		JLabel userLAbel = new JLabel(" نام کاربری :");
		userLAbel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		Font temp = userLAbel.getFont();
		temp = temp.deriveFont(Font.BOLD);
		userLAbel.setFont(temp);
		

		gbc.gridx = 6;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.insets = new Insets(2, 2, 2, 2);

		LoginPanel.add(userLAbel, gbc);

		gbc.gridy = 5;
		userfield = new JTextField();
		userfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		LoginPanel.add(userfield, gbc);

		gbc.gridy = 6;
		JLabel passLAbel = new JLabel("کلمه ی عبور :");
		passLAbel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		passLAbel.setFont(temp);
		
		LoginPanel.add(passLAbel, gbc);

		gbc.gridy = 7;
		passfield = new JPasswordField();
		passfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		LoginPanel.add(passfield, gbc);

		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridy = 9;
		gbc.gridx = 9;
		gbc.gridwidth = 2;

		JButton LoginBtn = new JButton("ورود");
		LoginBtn.setActionCommand("LOGIN");
		LoginBtn.addActionListener(this);
		LoginPanel.add(LoginBtn, gbc);

		LoginBtn.setFont(temp);
		

		gbc.gridx = 5;
		gbc.gridwidth = 2;

		JButton cancelBtn = new JButton("انصراف");
		cancelBtn.setActionCommand("CANCEL");
		cancelBtn.addActionListener(this);
		LoginPanel.add(cancelBtn, gbc);

		cancelBtn.setFont(temp);
		
	}
	//...........................................................................
	//..............................private_method.................................
	//...........................................................................
	private GridBagLayout createGridbagLayout() {

		GridBagLayout gbl = new GridBagLayout();

		gbl.columnWidths = new int[] { 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,
				50, 50, 50, 50, 50, 50 };
		gbl.rowHeights = new int[] { 41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
				41, 41, 41, 41 };
		gbl.columnWeights = new double[] { 100.0d, 100.0d, 100.0d, 100.0d,
				0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 100.0d, 100.0d,
				100.0d, 100.0d };

		return gbl;

	}
	//...........................................................................
	private void cancel() {
		setVisible(false);

	}
	//...........................................................................
	private void login() {

		new Frame().setVisible(true);

	}
	
	//...........................................................................
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommend = e.getActionCommand();
		
		
		if (actionCommend.equals("LOGIN")) {
			
			if (!userfield.getText().replace(" ", "").equals("")){
				
				if (!passfield.getText().replace(" ", "").equals("")){
					
					
					if (passfield.getText().equals("admin")
							&& userfield.getText().equals("admin")) {
						JOptionPane.showMessageDialog(this,
								"عملیات ورود به سیستم موفقیت آمیز بود!");
						login();
						setVisible(false);
						
					}else {
						JOptionPane a = new JOptionPane();
						a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
						a.showMessageDialog(this,
								"نام کاربری یا رمز عبور اشتباه می‌باشد!!",
								"خطا", JOptionPane.ERROR_MESSAGE);
					}

					
					
				}else {

					JOptionPane a = new JOptionPane();
					a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
					a.showMessageDialog(this,
							"لطفا رمز عبور را وارد نمایید!!", "خطا",
							JOptionPane.ERROR_MESSAGE);
				}
				
				
			} else {

				JOptionPane a = new JOptionPane();
				a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
				a.showMessageDialog(this, "لطفا نام کاربری را وارد نمایید!!",
						"خطا", JOptionPane.ERROR_MESSAGE);

			}
			
			
		}else if (actionCommend.equals("CANCEL")) {

			cancel();

		}
	}
	
	
	

}
