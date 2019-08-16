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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import main.server.ServerMain;
import model.Room;





public class EditRoomFrame extends JFrame implements Serializable, ActionListener {
	
	
	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDHT = 600;
	private static final int FRAME_HEIGHT = 550;

	private static final int MIN_WIDHT = 400;
	private static final int MIN_HEIGHT = 400;

	public static  JTextField RoomNamefield;

	

	public static  JTextField roomCapacityfield;
	
	boolean existRoom;

	public static  JTextField roomPricefield;

	public static DefaultComboBoxModel comBoModel;
	
	//...........................................................................
	
	public EditRoomFrame() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = dimension.width / 2 - FRAME_WIDHT / 2;
		int y = dimension.height / 2 - FRAME_HEIGHT / 2;
		setBounds(x, y, FRAME_WIDHT, FRAME_HEIGHT);
		
		
	
		
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//
		setMinimumSize(new Dimension(MIN_WIDHT, MIN_HEIGHT));
		setLayout(new BorderLayout());
		setTitle("EDIT ");
		
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
		
		JLabel RoomNameLAbel = new JLabel("Room Name :");
		
		Font temp = RoomNameLAbel.getFont();
		temp = temp.deriveFont(Font.BOLD);
		RoomNameLAbel.setFont(temp);
		

		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(4, 2, 4, 2);

		LoginPanel.add(RoomNameLAbel, gbc);
		
		
		gbc.gridx = 8;
		gbc.gridwidth = 4;
		gbc.gridy = 5;
		RoomNamefield = new JTextField();
		
		LoginPanel.add(RoomNamefield, gbc);

	
		
		
		
		
		
		JLabel RoomTypeLAbel = new JLabel("Room Type :");
		RoomTypeLAbel.setFont(temp);
		
		
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(2, 2, 2, 2);

		LoginPanel.add(RoomTypeLAbel, gbc);
		
		
		gbc.gridx = 8;
		gbc.gridwidth = 4;
		
		JComboBox	RoomTypeJComboBox = new JComboBox();
		comBoModel = new DefaultComboBoxModel();
		
		
		ArrayList<String> RoomTypeList= new ArrayList<String>(); 
		RoomTypeList.add("SINGLE ROOM");
		RoomTypeList.add("DOUBLE ROOM");
		RoomTypeList.add("TRIPLE ROOM");
		RoomTypeList.add("QUADRUPLE ROOM");
		RoomTypeList.add("SUITE");
		RoomTypeList.add("CONNECTED ROOM");
		
		for (String string : RoomTypeList) {
			
			comBoModel.addElement(string);
			
		}
		RoomTypeJComboBox.setModel(comBoModel);
		
		LoginPanel.add(RoomTypeJComboBox, gbc);
		
		
		//
		JLabel RoomPriceLAbel = new JLabel("Price :");
		RoomPriceLAbel.setFont(temp);
		
		
		gbc.gridx = 3;
		gbc.gridy = 6;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(2, 2, 2, 2);

		LoginPanel.add(RoomPriceLAbel, gbc);
		
		
		gbc.gridx = 8;
		gbc.gridwidth = 4;
		
		roomPricefield = new JTextField();
		roomPricefield.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}

		});
	
		
		LoginPanel.add(roomPricefield, gbc);
		
		
		//
		//
		JLabel RoomCapacityLAbel = new JLabel("Capacity :");
		RoomCapacityLAbel.setFont(temp);
		
		
		gbc.gridx = 3;
		gbc.gridy = 7;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(2, 2, 2, 2);

		LoginPanel.add(RoomCapacityLAbel, gbc);
		
		
		gbc.gridx = 8;
		gbc.gridwidth = 4;
		
		roomCapacityfield = new JTextField();
		roomCapacityfield.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}

		});
	
		
		LoginPanel.add(roomCapacityfield, gbc);
		
		
		//

		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridy = 11;
		gbc.gridx = 9;
		gbc.gridwidth = 2;

		JButton ACCEPTBtn = new JButton("ویرایش");
		ACCEPTBtn.setActionCommand("ACCEPT");
		ACCEPTBtn.addActionListener(this);
		LoginPanel.add(ACCEPTBtn, gbc);

		ACCEPTBtn.setFont(temp);
		

		gbc.gridx = 5;
		gbc.gridwidth = 2;

		JButton DeleteBtn = new JButton("حذف");
		DeleteBtn.setActionCommand("DL");
		DeleteBtn.addActionListener(this);
		LoginPanel.add(DeleteBtn, gbc);

		DeleteBtn.setFont(temp);
		
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
	private void Delete() {
		
		ServerMain.RoomList.remove(Frame.selectedRoomIndex);
		org.File.IOFile.writeObject(ServerMain.RoomList, "Room.rm");

		JOptionPane.showMessageDialog(this,
				"اتاق مورد نظر حذف شد");
		
		 Frame.Roomtable.removeAll();
		 Frame.Roomtable.setModel(Frame.RoomTableModel);
		 Frame.RoomTableloadData();
		
		setVisible(false);

	}
	//...........................................................................
	
	//...........................................................................
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommend = e.getActionCommand();
		
		existRoom=false;
		if (actionCommend.equals("ACCEPT")) {
			
			if (!RoomNamefield.getText().replace(" ", "").equals("") && !roomPricefield.getText().replace(" ", "").equals("")){
				
				if (!roomCapacityfield.getText().replace(" ", "").equals("")){
					
					
					
					
					
					 String roomName=RoomNamefield.getText();
					Frame.selectedRoomtable.setRoomName(roomName);
			
			
					
				
					
					
					 String roomType=(String) comBoModel.getSelectedItem();
					 Frame.selectedRoomtable.setRoomType(roomType);
					 
					 double price=Double.parseDouble(roomPricefield.getText());
					 
					 Frame.selectedRoomtable.setPrice(price);
					 int Capacity=Integer.parseInt(roomCapacityfield.getText());
					 
					 Frame.selectedRoomtable.setCapacity(Capacity);
					 org.File.IOFile.writeObject(ServerMain.RoomList, "Room.rm");
					 
						JOptionPane.showMessageDialog(this,
								"اتاق مورد نظر ویرایش شد");
						
						
						 Frame.Roomtable.removeAll();
						 Frame.Roomtable.setModel(Frame.RoomTableModel);
						 Frame.RoomTableloadData();
						
						 setVisible(false);
			
					 
				

							
							
							
							
					 
					
					
					
					
				
					
					
					
					
					
					
					
					
					
					
					
					
					
					

					
					
				}else {

					JOptionPane a = new JOptionPane();
					a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
					a.showMessageDialog(this,
							"لطفا اطلاعات اتاق را کامل  نمایید!!", "خطا",
							JOptionPane.ERROR_MESSAGE);
				}
				
				
			} else {

				JOptionPane a = new JOptionPane();
				a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
				a.showMessageDialog(this, "لطفا اطلاعات اتاق را کامل  نمایید!!",
						"خطا", JOptionPane.ERROR_MESSAGE);

			}
			
			
		}else if (actionCommend.equals("DL")) {

			Delete();

		}
	}
	
	
	

}
