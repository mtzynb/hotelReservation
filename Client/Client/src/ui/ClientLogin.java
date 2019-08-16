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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import model.Client;
import model.Food;
import model.Room;


public class ClientLogin extends JFrame implements Serializable, ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDHT = 300;
	private static final int FRAME_HEIGHT = 300;

	private static final int MIN_WIDHT = 300;
	private static final int MIN_HEIGHT = 300;
	public static JTextField userfield;

	public static ArrayList<Room> RoomList;
	public static ArrayList<Food> foodAvalableList;
	public ClientLogin() {
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = dimension.width / 2 - FRAME_WIDHT / 2;
		int y = dimension.height / 2 - FRAME_HEIGHT / 2;
		setBounds(x, y, FRAME_WIDHT, FRAME_HEIGHT);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//
		setMinimumSize(new Dimension(MIN_WIDHT, MIN_HEIGHT));
		setLayout(new BorderLayout());
		// ....................................................................
		
		
		setTitle("صفحهٔ ورود");

		JPanel LoginPanel = new JPanel();

		LoginPanel.setLayout(createGridbagLayout());

		Color PanelColor = new Color(109, 198, 255);
		LoginPanel.setBackground(PanelColor);
		LoginPanel.setPreferredSize(new Dimension(800, 650));

		this.add(LoginPanel, BorderLayout.CENTER);
		// ....................................................................
		GridBagConstraints gbc = new GridBagConstraints(4, 4, 8, 1, 0.0d, 0.0d,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0);

		JLabel userLAbel = new JLabel(" لطفا نام خود را وارد نمایید .");
		userLAbel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		Font temp = userLAbel.getFont();
		temp = temp.deriveFont(Font.BOLD);
		userLAbel.setFont(temp);

		gbc.gridx = 6;
		gbc.gridy = 5;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.insets = new Insets(2, 2, 2, 2);

		LoginPanel.add(userLAbel, gbc);

		gbc.gridy = 6;
		userfield = new JTextField();
		userfield.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		LoginPanel.add(userfield, gbc);

		gbc.insets = new Insets(2, 15, 2, 15);
		gbc.gridy = 8;
		gbc.gridx = 8;
		gbc.gridwidth = 2;

		JButton LoginBtn = new JButton("ورود");
		LoginBtn.setActionCommand("LOGIN");
		LoginBtn.addActionListener(this);
		LoginPanel.add(LoginBtn, gbc);

		LoginBtn.setFont(temp);

		gbc.gridx = 6;
		gbc.gridwidth = 2;

		JButton cancelBtn = new JButton("انصراف");
		cancelBtn.setActionCommand("CANCEL");
		cancelBtn.addActionListener(this);
		LoginPanel.add(cancelBtn, gbc);

		cancelBtn.setFont(temp);
	}
	
	
	// ....................................................................
	// .........................private_method............................
	// ....................................................................

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
	// ....................................................................
	private void cancel() {

		setVisible(false);

	}


	// ....................................................................
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommend = e.getActionCommand();
		if (actionCommend.equals("LOGIN")) {
			

			if (!userfield.getText().replace(" ", "").equals("")) {

				String name=userfield.getText();


				String TypeRequest = "conectClient";
				Client client = new Client(TypeRequest, name);
				
			
				
				

				
				System.out.println("login:"+client.listttttttt);
				new Thread(client).start();
				Frame clientFrame = new Frame(client);
				System.out.println("login2:"+client.listttttttt);
				clientFrame.setVisible(true);
				
				foodAvalableList=client.getListOfAvailableFiles();
				RoomList=client.getListOfRoom();
				System.out.println("login3:"+client.getListOfAvailableFiles());
				System.out.println("login4:"+client.getListOfRoom());
				setVisible(false);
			

			} else {

				JOptionPane a = new JOptionPane();
				a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
				a.showMessageDialog(this, "لطفا نام خود را وارد نمایید!!",
						"خطا", JOptionPane.ERROR_MESSAGE);

			}

		} else if (actionCommend.equals("CANCEL")) {

			cancel();

		}

	}
}
