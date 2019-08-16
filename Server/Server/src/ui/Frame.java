package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;





import main.server.ServerMain;

import model.Food;
import model.Room;




public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDHT = 900;
	private static final int FRAME_HEIGHT = 600;

	private static final int MIN_WIDHT = 700;
	private static final int MIN_HEIGHT = 400;

	private JList unavailbaleList;

	private static DefaultListModel<Food> unavailbleListModel;

	private JList availbleList;

	private static DefaultListModel<Food> availbleListModel;

	private JButton disableButton;

	

	private JButton enableButton;

	

	

	
	private static Color borderColor;

	

	public static JTable Roomtable;

	private Font temp;

	public static JTable foodTable;

	private JButton foodRefreshButtun;

	public static tableModel RoomTableModel;

	private static String[] columnNames;

	private static Vector<String> columnNamesVec;
	
	private static Vector<String> columnFoodNamesVec;

	private static Vector<Vector<Object>> tableData;
	private static Vector<Vector<Object>> tableFoodData;

	private TableRowSorter<DefaultTableModel> tableRowSorter;

	public static JPanel panelll;
	
	public static Food selectedfoodtable;
	public static int selectedfoodIndex;

	
	public static Room selectedRoomtable;
	public static int selectedRoomIndex;


	private JScrollPane scrollPane1RoomPanel;

	public static tableModel foodTableModel;

	private String[] columnFoodNames;

	private TableRowSorter<DefaultTableModel> tablefoodRowSorter;

	public static JPanel jpanell;

	// ...................................................................

	public Frame() {

		setTitle("سرور");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = dimension.width / 2 - FRAME_WIDHT / 2;
		int y = dimension.height / 2 - FRAME_HEIGHT / 2;
		setBounds(x, y, FRAME_WIDHT, FRAME_HEIGHT);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		setMinimumSize(new Dimension(MIN_WIDHT, MIN_HEIGHT));

		setLayout(createGridbagLayout());

		Color PanelColor = new Color(109, 198, 255);

		getContentPane().setBackground(PanelColor);

		GridBagConstraints gbc = new GridBagConstraints(1, 1, 18, 11, 0.0d,
				0.0d, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 1, 1);

		borderColor = new Color(109, 198, 255);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(BorderFactory.createLineBorder(borderColor, 2));
		
		        panelll = new JPanel();
		panelll.setLayout(createGridbagLayout());

		tabbedPane.addTab("لیست اتاق ها", null, panelll, null);
		tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getContentPane().add(tabbedPane, gbc);
		
		
//		gbc.gridx = 5;
//		gbc.gridwidth = 3;
//		gbc.gridy = 11;
//		gbc.gridheight = 2;
//		gbc.insets = new Insets(15, 0, 15, 0);
//		JButton RefreshButton = new JButton("به روز رسانی");
//		RefreshButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//				Roomtable.removeAll();
//				Roomtable.setModel(tableModel());
//
//			}
//		});
//
//		RefreshButton.setFont(temp);
//		RefreshButton.setBorder(BorderFactory.createLineBorder(borderColor, 2));
//
//		panelll.add(RefreshButton, gbc);
		
		gbc.gridx = 8;
		gbc.gridwidth = 3;
		gbc.gridy = 11;
		gbc.gridheight = 2;
		gbc.insets = new Insets(15, 0, 15, 0);
		JButton NewRoomButton = new JButton("اتاق جدید");
		NewRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new NewRoomFrame().setVisible(true);
			}
		});

		NewRoomButton.setFont(temp);
		NewRoomButton.setBorder(BorderFactory.createLineBorder(borderColor, 2));

		panelll.add(NewRoomButton, gbc);

		gbc.gridx = 2;
		gbc.gridwidth = 16;
		gbc.gridy = 2;
		gbc.gridheight = 9;
		gbc.insets = new Insets(0, 0, 0, 0);

		Roomtable =  new JTable() {

			@Override
			public Component prepareRenderer(TableCellRenderer arg0, int r,
					int c) {

				Component com = super.prepareRenderer(arg0, r, c);
				if (r % 2 == 0) {
					com.setBackground(Color.white);
				} else {
					com.setBackground(new Color(219, 219, 219));
				}

				return com;
			}

		};

		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setViewportView(Roomtable);

		scrollPane.setBorder(BorderFactory.createLineBorder(borderColor, 3));

		Roomtable.setFont(temp);
		
		RoomTableModel = new tableModel(tableData, columnNamesVec);
		
		Roomtable.setModel(RoomTableModel);
		
		 columnNames = new String[]  { "Room ID", "Room Name",
				"Room Type", "price","Capacity","Status" };
		 columnNamesVec = new Vector<String>(Arrays.asList(columnNames));
		 tableData = new Vector<Vector<Object>>();
		 
		 tableRowSorter = new TableRowSorter<DefaultTableModel>(RoomTableModel);
		 Roomtable.setRowSorter(tableRowSorter);
		 
		
			// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			Roomtable.setRowHeight(30);
			Roomtable.getTableHeader().setBackground(borderColor);
			Roomtable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
			scrollPane
					.setBorder(BorderFactory.createLineBorder(borderColor, 3));
			
			
			Roomtable.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						selectedRoomtable=null;
					//   selectedfoodIndex;
						
					    new EditRoomFrame().setVisible(true);
					    
						for (int i = 0; i < ServerMain.RoomList.size(); i++) {
							if (Roomtable.getValueAt(Roomtable.getSelectedRow(), 0).equals(
									ServerMain.RoomList.get(i).getRoomID())) {
								selectedRoomtable = ServerMain.RoomList.get(i);
								
								 selectedRoomIndex=i;
							}
						}
					    
					    if(selectedRoomtable !=null){
					    	
					    	EditRoomFrame.RoomNamefield.setText(selectedRoomtable.getRoomName());
					    	
					    	EditRoomFrame.roomCapacityfield.setText(""+selectedRoomtable.getCapacity());
					    	
					    	EditRoomFrame.comBoModel.setSelectedItem(selectedRoomtable.getRoomType());
					    	EditRoomFrame.roomPricefield.setText(""+selectedRoomtable.getPrice());
					    	
					    }
						
					}
					
					
				}
			});
			
			
			///////////////////////////////////////////////////////////////////////////////////////
			
			
			
			
			

			
			
			
			
			
		 RoomTableloadData();
		
		 
		 
		 
		 
		 
		 
		 
		 ////////////////////////////////////////////////foodpanel.................................
		 
		panelll.add(scrollPane, gbc);

        jpanell = new JPanel();
jpanell.setLayout(createGridbagLayout());

tabbedPane.addTab("لیست غذاها ", null, jpanell, null);
tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
getContentPane().add(tabbedPane, gbc);


//gbc.gridx = 5;
//gbc.gridwidth = 3;
//gbc.gridy = 11;
//gbc.gridheight = 2;
//gbc.insets = new Insets(15, 0, 15, 0);
//foodRefreshButtun = new JButton("به روز رسانی");
//foodRefreshButtun.addActionListener(new ActionListener() {
//	public void actionPerformed(ActionEvent arg0) {
//		
//		foodTable.removeAll();
//		foodTable.setModel(tableModel());
//
//	}
//});
//
//foodRefreshButtun.setFont(temp);
//foodRefreshButtun.setBorder(BorderFactory.createLineBorder(borderColor, 2));
//
//jpanell.add(foodRefreshButtun, gbc);

gbc.gridx = 8;
gbc.gridwidth = 3;
gbc.gridy = 11;
gbc.gridheight = 2;
gbc.insets = new Insets(15, 0, 15, 0);
JButton NewFoodButton1 = new JButton("غذای جدید");
NewFoodButton1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		
		new NewFoodFrame().setVisible(true);

	}
});

NewFoodButton1.setFont(temp);
NewFoodButton1.setBorder(BorderFactory.createLineBorder(borderColor, 2));

jpanell.add(NewFoodButton1, gbc);

gbc.gridx = 2;
gbc.gridwidth = 16;
gbc.gridy = 2;
gbc.gridheight = 9;
gbc.insets = new Insets(0, 0, 0, 0);

foodTable =new JTable() {

	@Override
	public Component prepareRenderer(TableCellRenderer arg0, int r,
			int c) {

		Component com = super.prepareRenderer(arg0, r, c);
		if (r % 2 == 0) {
			com.setBackground(Color.white);
		} else {
			com.setBackground(new Color(219, 219, 219));
		}

		return com;
	}

};

scrollPane1RoomPanel = new JScrollPane();

scrollPane1RoomPanel.setViewportView(foodTable);

scrollPane1RoomPanel.setBorder(BorderFactory.createLineBorder(borderColor, 3));

foodTable.setFont(temp);


foodTableModel = new tableModel(tableFoodData, columnNamesVec);

foodTable.setModel(foodTableModel);

columnFoodNames = new String[]  { "Food ID", "Food Name",
			"Food Type", "price","Status" };
columnFoodNamesVec = new Vector<String>(Arrays.asList(columnFoodNames));
tableFoodData = new Vector<Vector<Object>>();
 
tablefoodRowSorter = new TableRowSorter<DefaultTableModel>(foodTableModel);
 foodTable.setRowSorter(tablefoodRowSorter);
 

	// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 foodTable.setRowHeight(30);
 foodTable.getTableHeader().setBackground(borderColor);
 foodTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
	scrollPane
			.setBorder(BorderFactory.createLineBorder(borderColor, 3));
	
	jpanell.add(scrollPane1RoomPanel, gbc);
 FoodTableloadData();
/////////
foodTable.addMouseListener(new MouseListener() {
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			selectedfoodtable=null;
		 
			
		    new EditFoodFrame().setVisible(true);
		    
			for (int i = 0; i < ServerMain.foodList.size(); i++) {
				if (foodTable.getValueAt(foodTable.getSelectedRow(), 0).equals(
						ServerMain.foodList.get(i).getFoodID())) {
					selectedfoodtable = ServerMain.foodList.get(i);
					selectedfoodIndex = i;

				}
			}
		    
		    if(selectedfoodtable !=null){
		    	
		    	EditFoodFrame.foodNamefield.setText(selectedfoodtable.getFoodName());
		    	
		    	EditFoodFrame.foodPricefield.setText(""+selectedfoodtable.getPrice());
		    	
		    	EditFoodFrame.comBoModel.setSelectedItem(selectedfoodtable.getFoodType());
		    	
		    }
			
		}
		
	}
});


		// ..............................................................
		// ........................File_panel.............................
		// ..............................................................
		JPanel panel = new JPanel();
		panel.setLayout(createGridbagLayout());

		tabbedPane.addTab("مدیریت منوی غذا", null, panel, null);
		tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getContentPane().add(tabbedPane, gbc);

		gbc.gridx = 2;
		gbc.gridwidth = 6;
		gbc.gridy = 0;
		gbc.gridheight = 1;

		JLabel unavailbaleListLabel = new JLabel(
				" لیست غذا های ناموجود :");
		unavailbaleListLabel
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		temp = unavailbaleListLabel.getFont();
		temp = temp.deriveFont(Font.BOLD);
		unavailbaleListLabel.setFont(temp);

		panel.add(unavailbaleListLabel, gbc);

		gbc.gridx = 1;
		gbc.gridwidth = 7;
		gbc.gridy = 1;
		gbc.gridheight = 11;
		unavailbaleList = new JList();
		unavailbaleList.setBorder(BorderFactory
				.createLineBorder(borderColor, 2));
		panel.add(unavailbaleList, gbc);
		unavailbleListModel = new DefaultListModel<>();

		unavailbaleList.setModel(unavailbleListModel);

		gbc.gridx = 13;
		gbc.gridwidth = 6;
		gbc.gridy = 0;
		gbc.gridheight = 1;

		JLabel enableListLabel = new JLabel("لیست غذا های موجود  :");
		enableListLabel
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		enableListLabel.setFont(temp);

		panel.add(enableListLabel, gbc);

		gbc.gridx = 12;
		gbc.gridwidth = 7;
		gbc.gridy = 1;
		gbc.gridheight = 11;
		availbleList = new JList();
		availbleList.setBorder(BorderFactory.createLineBorder(borderColor, 2));
		panel.add(availbleList, gbc);
		availbleListModel = new DefaultListModel<>();

		availbleList.setModel(availbleListModel);

		gbc.gridx = 9;
		gbc.gridwidth = 2;
		gbc.gridy = 4;
		gbc.gridheight = 1;

		disableButton = new JButton("<=");
		disableButton.setFont(temp);
		disableButton.setBorder(BorderFactory.createLineBorder(borderColor, 2));

		disableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!Frame.this.availbleList.isSelectionEmpty()) {

					Food f = (Food) Frame.this.availbleList
							.getSelectedValue();
					
					Food SelectefFood = null;
					
					for (int i = 0; i < ServerMain.foodList.size(); i++) {
						if (f.getFoodID().equals(
								 ServerMain.foodList.get(i).getFoodID())) {
							SelectefFood =  ServerMain.foodList.get(i);
							

						}
					}

				
					availbleListModel.removeElement(f);
					SelectefFood.setExist(false);
					org.File.IOFile.writeObject(ServerMain.foodList, "Food.fd");
					
					unavailbleListModel.addElement(f);
					
					foodTable.removeAll();
					 foodTable.setModel(foodTableModel);
					 FoodTableloadData();

				} else {

					JOptionPane a = new JOptionPane();
					a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
					a.showMessageDialog(Frame.this,
							"لطفا غذای مورد نظر را از لیست انتخاب کنید!!",
							"خطا", JOptionPane.ERROR_MESSAGE);

				}

			}
		});

		panel.add(disableButton, gbc);

		

		gbc.gridx = 9;
		gbc.gridwidth = 2;
		gbc.gridy = 8;
		gbc.gridheight = 1;

		enableButton = new JButton("=>");
		enableButton.setBorder(BorderFactory.createLineBorder(borderColor, 2));

		enableButton.setFont(temp);
		enableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!Frame.this.unavailbaleList.isSelectionEmpty()) {


					Food f = (Food) Frame.this.unavailbaleList
							.getSelectedValue();
					
					Food SelectefFood = null;
					
					for (int i = 0; i < ServerMain.foodList.size(); i++) {
						if (f.getFoodID().equals(
								 ServerMain.foodList.get(i).getFoodID())) {
							SelectefFood =  ServerMain.foodList.get(i);
							

						}
					}

				unavailbleListModel.removeElement(f);
					SelectefFood.setExist(true);
					org.File.IOFile.writeObject(ServerMain.foodList, "Food.fd");
					
					availbleListModel.addElement(f);
					
					foodTable.removeAll();
					 foodTable.setModel(foodTableModel);
					 FoodTableloadData();
					
				} else {

					JOptionPane a = new JOptionPane();
					a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
					a.showMessageDialog(Frame.this,
							"لطفا غذای مورد نظر را از لیست انتخاب کنید!!",
							"خطا", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		panel.add(enableButton, gbc);

		
		

		new Thread(new ServerMain()).start();
		
		//................................................................
		
	

		if (ServerMain.foodList != null) {

			availbleListModel.removeAllElements();
			ArrayList<Food> foodList=ServerMain.foodList;
			for (Food food : foodList) {
				
				if(food.isExist()==true){
					
					availbleListModel.addElement(food);
				}
				
			}
			

			
		}
		
		///////////
		if (ServerMain.foodList != null) {

			unavailbleListModel.removeAllElements();
			ArrayList<Food> foodList=ServerMain.foodList;
			for (Food food : foodList) {
				
				if(food.isExist()==false){
					
					unavailbleListModel.addElement(food);
				}
				
			}
			

			
		}
		
		
		//................................................................
		




	}

	// .............................................................................
	// privateMethods...............................................................
	// .............................................................................

	
		private static GridBagLayout createGridbagLayout() {

			GridBagLayout gbl = new GridBagLayout();

			gbl.columnWidths = new int[] { 45, 45, 45, 45, 45, 45, 45, 45, 45, 45,
					45, 45, 45, 45, 45, 45, 45, 45, 45, 45 };
			gbl.rowHeights = new int[] { 46, 46, 46, 46, 46, 46, 46, 46, 46, 46,
					46, 46, 46 };
			gbl.columnWeights = new double[] { 45.0d, 45.0d, 45.0d, 45.0d, 45.0d,
					45.0d, 45.0d, 45.0d, 45.0d, 45.0d, 45.0d, 45.0d, 45.0d, 45.0d,
					45.0d, 45.0d, 45.0d, 45.0d, 45.0d, 45.0d };

			gbl.rowWeights = new double[] { 46.0d, 46.0d, 46.0d, 46.0d, 46.0d,
					46.0d, 46.0d, 46.0d, 46.0d, 46.0d, 46.0d, 46.0d, 46.0d };

			return gbl;

		}
		
		// ...................................................................
		
		


	

	// ...................................................................
	
	

	private DefaultTableModel tableModel() {

		String[] s = { "Room ID", "Room Name",
				"Room Type", "price","Capacity","Status" };
		
		
		Vector<String> columnNamesVec = new Vector<String>();
		
		
		columnNamesVec.addAll(Arrays.asList(s));
		Vector<Vector<String>> tableData = new Vector<Vector<String>>();


		return new DefaultTableModel(tableData, columnNamesVec);
	}

	private DefaultTableModel tableModel2() {

		String[] s = { "Food ID", "Food Name",
				"Food Type", "price","Status" };
		
		
		Vector<String> columnNamesVec = new Vector<String>();
		
		
		columnNamesVec.addAll(Arrays.asList(s));
		Vector<Vector<String>> tableData = new Vector<Vector<String>>();


		return new DefaultTableModel(tableData, columnNamesVec);
	}
	public static void FoodTableloadData() {

		tableFoodData.removeAllElements();
		foodTableModel.setDataVector(tableFoodData, columnFoodNamesVec);

		for (Food food : ServerMain.foodList) {
			java.util.Vector<Object> Row = new java.util.Vector<Object>();

			Row.add(food.getFoodID());
			Row.add(food.getFoodName());
			Row.add(food.getFoodType());
			Row.add(food.getPrice());
		
			
			if(food.isExist()){
				
				Row.add("موجود");
				
			}else{
				Row.add("غیر موجود");
			}
			tableFoodData.add(Row);
			foodTableModel.setDataVector(tableFoodData, columnFoodNamesVec);
		}


		foodTable.setRowHeight(30);

	}
	public static void RoomTableloadData() {
		
		columnNames = new String[]  { "Room ID", "Room Name",
				"Room Type", "price","Capacity","Status" };

		tableData.removeAllElements();
		RoomTableModel.setDataVector(tableData, columnNamesVec);

		for (Room room : ServerMain.RoomList) {
			java.util.Vector<Object> Row = new java.util.Vector<Object>();

			Row.add(room.getRoomID());
			Row.add(room.getRoomName());
			Row.add(room.getRoomType());
			Row.add(room.getPrice());
			Row.add(room.getCapacity());
			
			if(room.isExist()){
				
				Row.add("خالی");
				
			}else{
				Row.add("رزرو شده");
			}
			tableData.add(Row);
			RoomTableModel.setDataVector(tableData, columnNamesVec);
		}

	
		Roomtable.setRowHeight(30);

	}
	
	public  static void Availablelist(){
		
		if (ServerMain.foodList != null) {

			availbleListModel.removeAllElements();
			ArrayList<Food> foodList=ServerMain.foodList;
			for (Food food : foodList) {
				
				if(food.isExist()==true){
					
					availbleListModel.addElement(food);
				}
				
			}
			

			
		}
	}

	
	public static void  unavailable() {
		
		if (ServerMain.foodList != null) {

			unavailbleListModel.removeAllElements();
			ArrayList<Food> foodList=ServerMain.foodList;
			for (Food food : foodList) {
				
				if(food.isExist()==false){
					
					unavailbleListModel.addElement(food);
				}
				
			}
			

			
		}
		
		
	}
	
	class tableModel extends DefaultTableModel {

		public tableModel(Vector<Vector<Object>> data,
				Vector<String> columnnames) {
			super(data, columnnames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {

			return false;

		}
	}

}
