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
import model.Food;
import model.Room;





public class EditFoodFrame extends JFrame implements Serializable, ActionListener {
	
	
	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDHT = 600;
	private static final int FRAME_HEIGHT = 550;

	private static final int MIN_WIDHT = 400;
	private static final int MIN_HEIGHT = 400;

	
	public static JTextField foodNamefield;

	private JComboBox foodTypeJComboBox;

	public static DefaultComboBoxModel comBoModel;

	public static JTextField foodPricefield;
	boolean existfood;
	//...........................................................................
	
	public EditFoodFrame() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = dimension.width / 2 - FRAME_WIDHT / 2;
		int y = dimension.height / 2 - FRAME_HEIGHT / 2;
		setBounds(x, y, FRAME_WIDHT, FRAME_HEIGHT);
		
		
	
		
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//
		setMinimumSize(new Dimension(MIN_WIDHT, MIN_HEIGHT));
		setLayout(new BorderLayout());
		setTitle("EDIT");
		
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
		
		JLabel FoodNameLAbel = new JLabel("Food Name :");
		
		Font temp = FoodNameLAbel.getFont();
		temp = temp.deriveFont(Font.BOLD);
		FoodNameLAbel.setFont(temp);
		

		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(4, 2, 4, 2);

		LoginPanel.add(FoodNameLAbel, gbc);
		
		
		gbc.gridx = 8;
		gbc.gridwidth = 4;
		gbc.gridy = 5;
		foodNamefield = new JTextField();
		
		LoginPanel.add(foodNamefield, gbc);

	
		
		
		
		
		
		JLabel RoomTypeLAbel = new JLabel("Food Type :");
		RoomTypeLAbel.setFont(temp);
		
		
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		
		
		
		
		
		
		
		
		
		
		
		gbc.gridheight = 1;
		gbc.insets = new Insets(2, 2, 2, 2);

		LoginPanel.add(RoomTypeLAbel, gbc);
		
		
		gbc.gridx = 8;
		gbc.gridwidth = 4;
		
		foodTypeJComboBox = new JComboBox();
comBoModel = new DefaultComboBoxModel();
		
		
		ArrayList<String> FoodTypeList= new ArrayList<String>(); 
		FoodTypeList.add("غذاهای اصلی");
		FoodTypeList.add("پیش غذا");
		FoodTypeList.add("نوشیدنی ها");
		FoodTypeList.add("سالادها");
		FoodTypeList.add("دسر ها");
		
		
		for (String string : FoodTypeList) {
			
			comBoModel.addElement(string);
			
		}
		foodTypeJComboBox.setModel(comBoModel);
		LoginPanel.add(foodTypeJComboBox, gbc);
		
		
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
		
		foodPricefield = new JTextField();
		foodPricefield.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}

		});
	
		
		LoginPanel.add(foodPricefield, gbc);
		////////////////////////////////////////

		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridy = 9;
		gbc.gridx = 9;
		gbc.gridwidth = 2;

		JButton AcceptBtn = new JButton("ویرایش");
		AcceptBtn.setActionCommand("ACCEPT");
		AcceptBtn.addActionListener(this);
		LoginPanel.add(AcceptBtn, gbc);

		AcceptBtn.setFont(temp);
		

		gbc.gridx = 5;
		gbc.gridwidth = 2;

		JButton DeleteBTN = new JButton("حذف");
		DeleteBTN.setActionCommand("DL");
		DeleteBTN.addActionListener(this);
		LoginPanel.add(DeleteBTN, gbc);

		DeleteBTN.setFont(temp);
		
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
	private void delete() {
		ServerMain.foodList.remove(Frame.selectedfoodIndex);
		org.File.IOFile.writeObject(ServerMain.foodList, "Food.fd");

		JOptionPane.showMessageDialog(this,
				"غذای مورد نظر حذف شد");
		
		 Frame.foodTable.removeAll();
		 Frame.foodTable.setModel(Frame.foodTableModel);
		 Frame.FoodTableloadData();
		 Frame.jpanell.updateUI();
		
		setVisible(false);

	}
	//...........................................................................
	
	//...........................................................................
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommend = e.getActionCommand();
		existfood=false;
		
		if (actionCommend.equals("ACCEPT")) {
			
			if (!foodNamefield.getText().replace(" ", "").equals("") && !foodPricefield.getText().replace(" ", "").equals("")){
				
				
				
				
			
					
				
						 Frame.selectedfoodtable.setFoodName(foodNamefield.getText());
						 Frame.selectedfoodtable.setPrice(Double.parseDouble(foodPricefield.getText()));
						 
						 Frame.selectedfoodtable.setFoodType((String) comBoModel.getSelectedItem());
						 
						 
							org.File.IOFile.writeObject(ServerMain.foodList, "Food.fd");

							JOptionPane.showMessageDialog(this,
									"غذای مورد نظر ویرایش شد");
						 
						
							 Frame.foodTable.removeAll();
							 Frame.foodTable.setModel(Frame.foodTableModel);
							 Frame.FoodTableloadData();
							 Frame.jpanell.updateUI();
							 setVisible(false);
				
					
					

					
					
				
				
				
			} else {

				JOptionPane a = new JOptionPane();
				a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
				a.showMessageDialog(this, "لطفا اطلاعات غذای جدید را کامل نمایید!!",
						"خطا", JOptionPane.ERROR_MESSAGE);

			}
			
			
		}else if (actionCommend.equals("DL")) {

			delete();

		}
	}
	
	
	

}
