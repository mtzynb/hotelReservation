package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.file.IOFile;
import org.file.IOFile2;

import main.client.ClientMain;
import model.Client;

import model.Food;
import model.Guest;

import model.Receipt;
import model.Room;
import model.Time;









public class Frame extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDHT = 900;
	private static final int FRAME_HEIGHT = 600;

	private static final int MIN_WIDHT = 700;
	private static final int MIN_HEIGHT = 400;
	
	private Room selectedRoom;

	private Client client;


	private static Color borderColor;
	ArrayList<Receipt> ReceiptList;
	private static JTable roomtable;

	public static tableModel roomTableModel;

	private static String[] columnNames;

	private static Vector<String> columnNamesVec;

	private static Vector<Vector<Object>> tableData;

	private TableRowSorter<DefaultTableModel> tableRowSorter;

	private JScrollPane scrollPane;

	private JButton reserve;

	private JTextField cellNumfield;

	private JTextField numberOfGuestfield;

	private JFormattedTextField startReservationDatefield;

	private JFormattedTextField endReservationDatefield;

	private DefaultMutableTreeNode root;

	private DefaultTreeModel treeModel;

	private JTree tree;

	private static JTable foodTable;
	private boolean mousePress;
	private boolean FoodmousePress;

	private static tableModel foodTableModel;

	private String[] columnFoodNames;

	private static Vector<String> columnFoodNamesVec;

	private static Vector<Vector<Object>> tableFoodData;

	private TableRowSorter<DefaultTableModel> tablefoodRowSorter;

	private JLabel numberOfFood;

	private JTextField numberfield;

	private JPanel pricePanel;

	private String[] columnPriceNames;

	private Vector<String> pricecolumnNamesVec;

	private Vector<Vector<Object>> pricetableData;

	private tableModel priceTableModel;

	private JTable pricetable;

	private JLabel roomPrice;

	private JLabel totalPrice;

	private double totalprice2;

	private Font temp;

	private JPanel timePanel;

	private Vector<Vector<Object>> timetableData;

	private TableRowSorter<DefaultTableModel> timetableRowSorter;

	private String[] timeColumnNames;

	private Vector<String> timecolumnNamesVec;

	private tableModel timeTableModel;

	private JTable timeTable;

	private JButton remider;
	
	// ......................................................................
	
	public Frame(Client client) {
		this.client = client;
		
		for (Guest g : ClientMain.ClientList) {
		
			
			if(g.getGuestName().equals(Frame.this.client.getClientName())){
				
				
				 Frame.this.client.setCellNo(g.getCellNo());
					Frame.this.client.setNumberOfGuest(g.getNumberOfGuest());
					Frame.this.client.setStartDate(g.getStartDate());
					Frame.this.client.setEndDate(g.getEndDate());
					Frame.this.client.setRoom(g.getRoom());
					Frame.this.client.setListOfReceipt(g.getListOfReceipt());
					
					
					ArrayList<Receipt> ReceiptList=g.getListOfReceipt();
					
				
			}
			
		}
		
		
		


		setTitle("کلاینت"+" - "+ client.getClientName());


		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = dimension.width / 2 - FRAME_WIDHT / 2;
		int y = dimension.height / 2 - FRAME_HEIGHT / 2;
		setBounds(x, y, FRAME_WIDHT, FRAME_HEIGHT);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		setMinimumSize(new Dimension(MIN_WIDHT, MIN_HEIGHT));
		this.addWindowListener(this);
	

		setLayout(createGridbagLayout());

		Color PanelColor = new Color(109, 198, 255);

		getContentPane().setBackground(PanelColor);
		// ..............................................................
		final GridBagConstraints gbc = new GridBagConstraints(1, 1, 18, 11, 0.0d,
				0.0d, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 1, 1);

		borderColor = new Color(109, 198, 255);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBorder(BorderFactory.createLineBorder(borderColor, 2));
		
		tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getContentPane().add(tabbedPane, gbc);
		
		
		// ..............................................................
		// ........................Rezerv_tab.............................
		// ..............................................................
		JPanel rezervPanel = new JPanel();
		tabbedPane.addTab("رزرو اتاق", null, rezervPanel, null);
		rezervPanel.setLayout(createGridbagLayout());
		
		
		mousePress=false;
		
	JLabel CellNum = new JLabel("Cell Number :");
		
		temp = CellNum.getFont();
		temp = temp.deriveFont(Font.BOLD);
		CellNum.setFont(temp);
		

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		
		rezervPanel.add(CellNum, gbc);
		
		
		gbc.gridx = 6;
		gbc.gridwidth = 3;
		;
		 cellNumfield = new JTextField();
		 cellNumfield.addKeyListener(new KeyAdapter() {

				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
						e.consume();
					}
				}

			});
		
		rezervPanel.add(cellNumfield, gbc);
		
		//
JLabel numberOfGuest = new JLabel("Number Of Guest: ");
		
		
		numberOfGuest.setFont(temp);
		

		gbc.gridx = 11;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		
		rezervPanel.add(numberOfGuest, gbc);
		
		
		gbc.gridx = 15;
		gbc.gridwidth = 3;
		
		
		numberOfGuestfield = new JTextField();
		numberOfGuestfield.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}

		});
		
		rezervPanel.add(numberOfGuestfield, gbc);
		
		//
		
	JLabel StartReservationDate = new JLabel("Start Reservation Date:");
		
		
		StartReservationDate.setFont(temp);
		
		 DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		
		rezervPanel.add(StartReservationDate, gbc);
		
		
		gbc.gridx = 6;
		gbc.gridwidth = 3;
		;
		startReservationDatefield = new JFormattedTextField(format);
		startReservationDatefield.setText("MM/dd/yyyy");
		startReservationDatefield.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!((c >= '0') && (c <= '9') ||
		           (c == KeyEvent.VK_BACK_SPACE) ||
		           (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
		        {
		        	JOptionPane a = new JOptionPane();
					a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
					a.showMessageDialog(Frame.this, "Please Enter Valid Date",
							"eror", JOptionPane.ERROR_MESSAGE);
		          e.consume();
		        }
		      }
		    });
		
		rezervPanel.add(startReservationDatefield, gbc);
		
		//
JLabel EndReservationDateLabel= new JLabel("End Reservation Date:");
		
		
EndReservationDateLabel.setFont(temp);
		

		gbc.gridx = 11;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		
		rezervPanel.add(EndReservationDateLabel, gbc);
		
		
		gbc.gridx = 15;
		gbc.gridwidth = 3;
		
		endReservationDatefield = new JFormattedTextField(format);
		endReservationDatefield.setText("MM/dd/yyyy");
		endReservationDatefield.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!((c >= '0') && (c <= '9') ||
		           (c == KeyEvent.VK_BACK_SPACE) ||
		           (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
		        {
		        	JOptionPane a = new JOptionPane();
					a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
					a.showMessageDialog(Frame.this, "Please Enter Valid Date",
							"خطا", JOptionPane.ERROR_MESSAGE);
					 e.consume();

		        }
		      }
		    });
		
		rezervPanel.add(endReservationDatefield, gbc);
		
		
		gbc.gridx = 2;
		gbc.gridwidth = 16;
		gbc.gridy = 5;
		gbc.gridheight = 6;
		gbc.insets = new Insets(0, 0, 0, 0);

		roomtable = new JTable() {

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
roomtable.addMouseListener(new MouseListener() {

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mousePress = true;

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
});
		scrollPane = new JScrollPane();
		
		scrollPane.setViewportView(roomtable);

		scrollPane.setBorder(BorderFactory.createLineBorder(borderColor, 3));

		roomtable.setFont(temp);
		
		
		
		
		
		columnNames = new String[]  { "Room ID", "Room Name",
				"Room Type", "price","Capacity","Status" };
		columnNamesVec = new Vector<String>(Arrays.asList(columnNames));
		tableData = new Vector<Vector<Object>>();
		
		roomTableModel = new tableModel(tableData, columnNamesVec);
		roomtable.setModel(roomTableModel);
		 
		 tableRowSorter = new TableRowSorter<DefaultTableModel>(roomTableModel);
		 roomtable.setRowSorter(tableRowSorter);
		 
		
			// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			roomtable.setRowHeight(30);
			roomtable.getTableHeader().setBackground(borderColor);
			roomtable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
			scrollPane
					.setBorder(BorderFactory.createLineBorder(borderColor, 3));
			
			 RoomTableloadData();
			
			
			
			rezervPanel.add(scrollPane, gbc);
			
			
			
			
			gbc.gridx = 9;
			gbc.gridwidth = 2;
			gbc.gridy = 11;
			gbc.gridheight = 2;

			reserve = new JButton("رزرو");
			reserve.setFont(temp);
			reserve.setBorder(BorderFactory.createLineBorder(borderColor, 2));
			reserve.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				public void actionPerformed(ActionEvent arg0) {
					


	if(mousePress){
		
		selectedRoom=null;
		
		for (int i = 0; i < Frame.this.client.getListOfRoom().size(); i++) {

			if (roomtable.getValueAt(roomtable.getSelectedRow(), 0).equals(
					Frame.this.client.getListOfRoom().get(i).getRoomID())) {
				selectedRoom = Frame.this.client.getListOfRoom().get(i);
				System.out.println("selectedRoom"+selectedRoom.toString());

			}

		}
		if(!cellNumfield.getText().replace(" ", "").equals("") && !numberOfGuestfield.getText().replace(" ", "").equals("")&&!startReservationDatefield.getText().replace(" ", "").equals("")&&!endReservationDatefield.getText().replace(" ", "").equals("")   ){
			
			
			
			
			
			
			
			
			
		if(selectedRoom != null){
			
			
			
			 
			if(Frame.this.client.getRoom()==null){
				
				 
				 File file = new File("Client.Client");
				 if (!file.exists()){
					 
					 Receipt r = new Receipt();
						r.setReceiptName("هزینه ی اقامت");
						r.setReceiptPrice(selectedRoom.getPrice());
						
						
						
						
					 Frame.this.client.setCellNo(cellNumfield.getText());
						Frame.this.client.setNumberOfGuest(Integer.parseInt(numberOfGuestfield.getText()));
						Frame.this.client.setStartDate(startReservationDatefield.getText());
						Frame.this.client.setEndDate(endReservationDatefield.getText());
						Frame.this.client.setRoom(selectedRoom);
						
						ReceiptList=new ArrayList<Receipt>();
						ReceiptList.add(r);
						
						Frame.this.client.setListOfReceipt(ReceiptList);
						
						
						Guest Guest =new  Guest();
						Guest.setGuestName(Frame.this.client.getClientName());
						Guest.setCellNo(cellNumfield.getText());
						Guest.setNumberOfGuest(Integer.parseInt(numberOfGuestfield.getText()));
						Guest.setStartDate(startReservationDatefield.getText());
						Guest.setEndDate(endReservationDatefield.getText());
						Guest.setRoomID(selectedRoom);
						Guest.setListOfReceipt(ReceiptList);
						
						 ClientMain.ClientList.add(Guest);
						 
						 IOFile.writeObject(ClientMain.ClientList,"Client.Client");
						 JOptionPane
							.showMessageDialog(Frame.this,
									"عملیات رزرو اناق  با موفقیت انجام شد");
						 
						 roomPrice.setText(" هزینه ی اقامت: "+selectedRoom.getPrice()+" تومان " );
						 pricePanel.updateUI();
						 
							if(Frame.this.client.getListOfReceipt()!=null){
								totalprice2 = 0;
								
								for (Receipt receipt : Frame.this.client.getListOfReceipt()) {
									
									
									totalprice2=totalprice2+receipt.getReceiptPrice();

									totalPrice.setText(" هزینه ی کل: "+totalprice2+" تومان ");
									
									
									
									pricePanel.updateUI();
									
									
									
								}
								
							}
					 
					 
				 }else {
					 ClientMain.ClientList= (ArrayList<Guest>) IOFile2.Load("Client.Client");
					 
					 Receipt r = new Receipt();
						r.setReceiptName("هزینه ی اقامت");
						r.setReceiptPrice(selectedRoom.getPrice()); 
						
					 Frame.this.client.setCellNo(cellNumfield.getText());
						Frame.this.client.setNumberOfGuest(Integer.parseInt(numberOfGuestfield.getText()));
						Frame.this.client.setStartDate(startReservationDatefield.getText());
						Frame.this.client.setEndDate(endReservationDatefield.getText());
						Frame.this.client.setRoom(selectedRoom);
						
						ReceiptList=new ArrayList<Receipt>();
						ReceiptList.add(r);
						
						Frame.this.client.setListOfReceipt(ReceiptList);
						
						
	Guest Guest =new  Guest();
						
						Guest.setGuestName(Frame.this.client.getClientName());
						Guest.setCellNo(cellNumfield.getText());
						Guest.setNumberOfGuest(Integer.parseInt(numberOfGuestfield.getText()));
						Guest.setStartDate(startReservationDatefield.getText());
						Guest.setEndDate(endReservationDatefield.getText());
						Guest.setRoomID(selectedRoom);
						Guest.setListOfReceipt(ReceiptList);
						
						 ClientMain.ClientList.add(Guest);
						 
						 
						 IOFile.writeObject( ClientMain.ClientList,"Client.Client");
						 JOptionPane
							.showMessageDialog(Frame.this,
									"عملیات رزرو اناق  با موفقیت انجام شد");
						 
						 roomPrice.setText(" هزینه ی اقامت: "+selectedRoom.getPrice()+" تومان " );
						 pricePanel.updateUI();
						 
							if(Frame.this.client.getListOfReceipt()!=null){
								totalprice2 = 0;
								
								for (Receipt receipt : Frame.this.client.getListOfReceipt()) {
									
									
									totalprice2=totalprice2+receipt.getReceiptPrice();

									totalPrice.setText(" هزینه ی کل: "+totalprice2+" تومان ");
									
									
									
									pricePanel.updateUI();
									
									
									
								}
								
							}
						 
						 
						 
					 
				 }
			}else {
				
				JOptionPane a = new JOptionPane();
				a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
				a.showMessageDialog(Frame.this, "شخص مورد نظر قبلا اتاق رزرو کرده است!!",
						"خطا", JOptionPane.ERROR_MESSAGE);
				
			}
			
			
			
			
		}else {
			
			JOptionPane a = new JOptionPane();
			a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
			a.showMessageDialog(Frame.this, "لطفا اطلاعات مورد نیاز را کامل نمایید!!",
					"خطا", JOptionPane.ERROR_MESSAGE);
			
		}
			
			
			
			
			
			
			
		}else {
			
			JOptionPane a = new JOptionPane();
			a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
			a.showMessageDialog(Frame.this, "لطفا اطلاعات مورد نیاز را کامل نمایید!!",
					"خطا", JOptionPane.ERROR_MESSAGE);
		}
	}else {
		
		JOptionPane a = new JOptionPane();
		a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
		a.showMessageDialog(Frame.this, "لطفا اتاق مورد نظر را انتخاب کنید!",
				"خطا", JOptionPane.ERROR_MESSAGE);
		
		
	}

				}
			});
			gbc.insets = new Insets(25, 5, 25, 5);
			
			rezervPanel.add(reserve, gbc);
			
			
			
			// ..............................................................
			// ........................food_tab.............................
			// ..............................................................
			FoodmousePress=false;
			
			
			JPanel FoodPanel = new JPanel();
			tabbedPane.addTab("سفارش غذا", null, FoodPanel, null);
			FoodPanel.setLayout(createGridbagLayout());
			
			root = new DefaultMutableTreeNode("منوی غذا");

			treeModel = new DefaultTreeModel(root);
			tree = new JTree(treeModel);

			
			tree.setFont(temp);

			tree.setBackground(new Color(238, 238, 238));
			tree.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
			tree.getSelectionModel().setSelectionMode(
					TreeSelectionModel.SINGLE_TREE_SELECTION);
			
			String[] c = new String[] { "غذاهای اصلی","پیش غذا","نوشیدنی ها","سالادها","دسر ها" };
			
			
			for (int i = 0; i < c.length; i++) {
				DefaultMutableTreeNode cat = new DefaultMutableTreeNode(c[i]);

				root.add(cat);
			}
			
		

			tree.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
				
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {

				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getClickCount() == 2) {

						FoodTableloadData();

					}

				}
			});

			tree.setCellRenderer(new DefaultTreeCellRenderer() {
				@Override
				public Component getTreeCellRendererComponent(JTree pTree,
						Object pValue, boolean pIsSelected, boolean pIsExpanded,
						boolean pIsLeaf, int pRow, boolean pHasFocus) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) pValue;
					super.getTreeCellRendererComponent(pTree, pValue, pIsSelected,
							pIsExpanded, pIsLeaf, pRow, pHasFocus);

					setBackgroundNonSelectionColor(borderColor);
					setBorder(BorderFactory.createEtchedBorder());

					setFont(new Font("tahoma", Font.BOLD, 8));
					if (node.isRoot()) {

						setBackgroundSelectionColor(borderColor);
					} else if (node.getChildCount() > 0)
						setBackgroundSelectionColor(borderColor);
					else if (pIsLeaf)
						setBackgroundSelectionColor(borderColor);
					return (this);
				}
			});
			
			
			gbc.insets = new Insets(0, 0, 0, 0);

			gbc.gridx = 13;
			gbc.gridy = 1;
			gbc.gridwidth = 5;
			gbc.gridheight =5;
			FoodPanel.add(tree, gbc);
			
			
			
			gbc.gridx = 2;
			gbc.gridwidth = 16;
			gbc.gridy = 6;
			gbc.gridheight = 5;
			gbc.insets = new Insets(0, 0, 0, 0);

			
			foodTable = new JTable() {

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
foodTable.addMouseListener(new MouseListener() {
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		FoodmousePress=true;
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
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
});
			JScrollPane 	scrollPane1RoomPanel = new JScrollPane();

			scrollPane1RoomPanel.setViewportView(foodTable);

			scrollPane1RoomPanel.setBorder(BorderFactory.createLineBorder(borderColor, 3));

			foodTable.setFont(temp);


			

			columnFoodNames = new String[]  { "Food ID", "Food Name",
						"Food Type", "price","Status" };
			columnFoodNamesVec = new Vector<String>(Arrays.asList(columnFoodNames));
			tableFoodData = new Vector<Vector<Object>>();
			foodTableModel = new tableModel(tableFoodData, columnFoodNamesVec);

			foodTable.setModel(foodTableModel);
			 
			tablefoodRowSorter = new TableRowSorter<DefaultTableModel>(foodTableModel);
			 foodTable.setRowSorter(tablefoodRowSorter);
			 

				// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 foodTable.setRowHeight(30);
			 foodTable.getTableHeader().setBackground(borderColor);
			 foodTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
				scrollPane
						.setBorder(BorderFactory.createLineBorder(borderColor, 3));
				
				FoodPanel.add(scrollPane1RoomPanel, gbc);

				gbc.gridx = 9;
				gbc.gridwidth = 2;
				gbc.gridy = 11;
				gbc.gridheight = 2;
				gbc.insets = new Insets(25, 5, 25, 5);

				JButton foodOrder = new JButton("سفارش غذا");
				foodOrder.setFont(temp);
				foodOrder.setBorder(BorderFactory.createLineBorder(borderColor, 2));
				foodOrder.addActionListener(new ActionListener() {
					
					private Food selectedFood;

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(!(Frame.this.client.getRoom() ==null)){
							
							if(FoodmousePress){
								
								
								
								
							
									selectedFood = null;
									
									for (int i = 0; i < Frame.this.client.getListOfAvailableFiles().size(); i++) {

										if (foodTable.getValueAt(foodTable.getSelectedRow(), 0).equals(
												Frame.this.client.getListOfAvailableFiles().get(i).getFoodID())) {
											selectedFood = Frame.this.client.getListOfAvailableFiles().get(i);
										//	System.out.println("selectedRoom"+selectedRoom.toString());

										}

									}
									
									if(!numberfield.getText().replace(" ", "").equals("" )){
							if(selectedFood!=null){
								
								
								
								Receipt r = new Receipt();
								r.setFood(selectedFood);
								r.setReceiptName(selectedFood.getFoodType());
								r.setReceiptPrice(Integer.parseInt(numberfield.getText())*selectedFood.getPrice());
								r.setNumberOfFood(Integer.parseInt(numberfield.getText()));
								
								ArrayList<Receipt>listtttt=	Frame.this.client.getListOfReceipt();
								listtttt.add(r);
								
								
								//ReceiptList.add(r);
								Frame.this.client.setListOfReceipt(listtttt);
								
								
								org.file.IOFile.writeObject(ClientMain.ClientList,"Client.Client");
								 JOptionPane
									.showMessageDialog(Frame.this,
											"سفارش مورد نظر ثبت شد");
								 numberfield.setText("");
								 
								 pricetable.removeAll();
								 pricetable.setModel(priceTableModel);
								PriceTableloadData();
								 
								 
								 for (Guest g : ClientMain.ClientList) {
										System.out.println("bbbbbbbbbbb"+g.toString());
										
										if(g.getGuestName().equals(Frame.this.client.getClientName())){
											
											
											g.setListOfReceipt(listtttt);
											 org.file.IOFile.writeObject( ClientMain.ClientList,"Client.Client");
											
												
											
										}
										
									}
									if(Frame.this.client.getListOfReceipt()!=null){
										totalprice2 = 0;
										
										for (Receipt receipt : Frame.this.client.getListOfReceipt()) {
											
											
											totalprice2=totalprice2+receipt.getReceiptPrice();

											totalPrice.setText(" هزینه ی کل: "+totalprice2+" تومان ");
											
											
											
											pricePanel.updateUI();
											
											
											
										}
										
									}
								 
								 
								 
								 
								 
								 
							}
										 
										 
										 
										
									}else{
										
										JOptionPane a = new JOptionPane();
										a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
										a.showMessageDialog(Frame.this, "تعداد غذای مورد نظر را انتخاب کنید",
												"خطا", JOptionPane.ERROR_MESSAGE);
										
									}
									
									
									
									
								
								
								
								
							}else{
								JOptionPane a = new JOptionPane();
								a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
								a.showMessageDialog(Frame.this, "غذای مورد نظر را انتخاب کنید",
										"خطا", JOptionPane.ERROR_MESSAGE);
							}
							
							
						}else{
							
							JOptionPane a = new JOptionPane();
							a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
							a.showMessageDialog(Frame.this, "برای سفارش غذا حتما باید از فپل اتاق رزرو کرده باشید",
									"خطا", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				
				FoodPanel.add(foodOrder, gbc);
				
				
				numberOfFood = new JLabel(" تعداد غذا : ");
				
				
				numberOfFood.setFont(temp);
				

				gbc.gridx = 10;
				gbc.gridy = 4;
				gbc.gridwidth = 4;
				gbc.gridheight = 1;
				gbc.insets = new Insets(4, 2, 4, 2);

				FoodPanel.add(numberOfFood, gbc);
				
				
				gbc.gridx =8;
				gbc.gridwidth = 2;
				gbc.gridy = 4;
				numberfield = new JTextField();
				numberfield.addKeyListener(new KeyAdapter() {

					public void keyTyped(KeyEvent e) {
						char c = e.getKeyChar();
						if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
							e.consume();
						}
					}

				});
				
				FoodPanel.add(numberfield, gbc);

			
			
		
				
				// ..............................................................
				// ........................price_tab.............................
				// ..............................................................
				
				pricePanel = new JPanel();
				tabbedPane.addTab("هزینه ها", null, pricePanel, null);
				pricePanel.setLayout(createGridbagLayout());
				
				
				
				gbc.gridx = 2;
				gbc.gridwidth = 16;
				gbc.gridy = 3;
				gbc.gridheight = 6;
				gbc.insets = new Insets(0, 0, 0, 0);

				pricetable = new JTable() {

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

				JScrollPane PricescrollPane = new JScrollPane();
				
				PricescrollPane.setViewportView(pricetable);

				PricescrollPane.setBorder(BorderFactory.createLineBorder(borderColor, 3));

				pricetable.setFont(temp);
				columnPriceNames = new String[]  {  "Order Name",
						"Food Name", "number of food","cost" };
				
				pricecolumnNamesVec = new Vector<String>(Arrays.asList(columnPriceNames));
				 pricetableData = new Vector<Vector<Object>>();
				
				
				
				priceTableModel = new tableModel(pricetableData, pricecolumnNamesVec);
				pricetable.setModel(priceTableModel);
				
				
				 
				TableRowSorter<DefaultTableModel> PricetableRowSorter = new TableRowSorter<DefaultTableModel>(priceTableModel);
				 pricetable.setRowSorter(PricetableRowSorter);
				 
				
					// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					pricetable.setRowHeight(30);
					pricetable.getTableHeader().setBackground(borderColor);
					pricetable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
					PricescrollPane
							.setBorder(BorderFactory.createLineBorder(borderColor, 3));
					PriceTableloadData();
				
					pricePanel.add(PricescrollPane,gbc);
					
					roomPrice=new JLabel();
					
					if(Frame.this.client.getListOfReceipt()!=null){
						
						for (Receipt receipt : Frame.this.client.getListOfReceipt()) {
							
							
							if(receipt.getReceiptName().equals("هزینه ی اقامت")){
								
								roomPrice.setText(" هزینه ی اقامت: "+ receipt.getReceiptPrice()+" تومان " );
								
							}

							
						}
						
					}else {
						
						roomPrice.setText("هزینه ی اقامت : صفر تومان");
						
					}
					
				

					
					
					
					
					roomPrice.setFont(temp);
					

					gbc.gridx = 12;
					gbc.gridy = 11;
					gbc.gridwidth = 6;
					gbc.gridheight = 1;
					gbc.insets = new Insets(4, 2, 4, 2);

					pricePanel.add(roomPrice, gbc);
					
					
					////
					
					
					gbc.gridx = 4;
					gbc.gridy = 11;
					gbc.gridwidth = 6;
					gbc.gridheight = 1;
					gbc.insets = new Insets(4, 2, 4, 2);
					
					totalPrice = new JLabel();
					if(Frame.this.client.getListOfReceipt()!=null){
						totalprice2 = 0;
						
						for (Receipt receipt : Frame.this.client.getListOfReceipt()) {
							
							
							totalprice2=totalprice2+receipt.getReceiptPrice();
							totalPrice.setText(" هزینه ی کل: "+totalprice2+" تومان ");
							
						}
						
					}
					
					else{
						totalPrice.setText("هزینه ی کل : صفر تومان");
					}
					
					
					
										totalPrice.setFont(temp);
					
					pricePanel.add(totalPrice, gbc);
					
					
					// ..............................................................
					// ........................TIME_tab.............................
					// ..............................................................
				
					
					
					timePanel = new JPanel();
					tabbedPane.addTab("ساعت کاری بخش های مختلق هتل و سایر خدمات", null, timePanel, null);
					timePanel.setLayout(createGridbagLayout());	
					
					
					
					
					
					gbc.gridx = 2;
					gbc.gridwidth = 16;
					gbc.gridy = 2;
					gbc.gridheight = 6;
					gbc.insets = new Insets(7,7,7,7);

					timeTable = new JTable() {

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
					
					scrollPane.setViewportView(timeTable);

					scrollPane.setBorder(BorderFactory.createLineBorder(borderColor, 3));

					timeTable.setFont(temp);
					
					
					timeColumnNames = new String[]  { "Section Name", "Start",
							"End" };
					timecolumnNamesVec = new Vector<String>(Arrays.asList(timeColumnNames));
					 timetableData = new Vector<Vector<Object>>();

						timeTableModel = new tableModel(timetableData, timecolumnNamesVec);
						
						timeTable.setModel(timeTableModel);
					 
					 timetableRowSorter = new TableRowSorter<DefaultTableModel>(timeTableModel);
					 timeTable.setRowSorter(timetableRowSorter);
					 
					
						// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						timeTable.setRowHeight(30);
						timeTable.getTableHeader().setBackground(borderColor);
						timeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
						scrollPane
								.setBorder(BorderFactory.createLineBorder(borderColor, 3));
						
						TimeTableloadData();
							
							timePanel.add(scrollPane, gbc);
							
							
							gbc.gridx = 12;
							gbc.gridwidth = 3;
							gbc.gridy = 10;
							gbc.gridheight = 2;
							gbc.insets = new Insets(20, 0, 20, 0);
							JButton nezafat = new JButton("درخواست نظافت اتاق");
							nezafat.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									
									
									if(Frame.this.client.getRoom()!=null){
										
										 JOptionPane
											.showMessageDialog(Frame.this,
													" درخواست نظافت برای اتاق "+Frame.this.client.getRoom().getRoomName()+" ارسال شد " );
										
									}else{
										JOptionPane a = new JOptionPane();
										a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
										a.showMessageDialog(Frame.this,
												"برای درخواست نظافت ابتدا باید اتاق رزرو کرده باشید!!",
												"خطا", JOptionPane.ERROR_MESSAGE);

									}
									
									

								}
							});

							nezafat.setFont(temp);
							nezafat.setBorder(BorderFactory.createLineBorder(borderColor, 2));

							timePanel.add(nezafat, gbc);
							
							
							gbc.gridx = 5;
							gbc.gridwidth = 3;
							gbc.gridy = 10;
							gbc.gridheight = 2;
							gbc.insets = new Insets(20, 0, 20, 0);
							remider = new JButton("ریمایندر تخلیه اتاق");
							remider.setFont(temp);
							remider.setBorder(BorderFactory.createLineBorder(borderColor, 2));
							remider.addActionListener(new ActionListener() {
	
	                    private String datee;

						@Override
	                    public void actionPerformed(ActionEvent arg0) {
	                    	
	                    	if(Frame.this.client.getRoom()!=null){
								
	                    		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	                    		
	                    		String StartDate=Frame.this.client.getStartDate();
	                    		String EndDate=Frame.this.client.getEndDate();
	                    		
	                    		 Date ddd = new Date();
	                    		Date Start=new Date();
	                    		Date End=new Date();
	                    		
	                    		
	                    		 
	                    		try {
									Start=formatter.parse(StartDate);
									End=formatter.parse(EndDate);
									
									
								
		                    		
		                    		
		                    		 long diff = End.getTime() - ddd.getTime();
		                    		 
		                    		 long diffSeconds = diff / 1000 % 60;
		                    	        long diffMinutes = diff / (60 * 1000) % 60;
		                    	        long diffHours = diff / (60 * 60 * 1000) % 24;
		                    	        long diffDays = diff / (24 * 60 * 60 * 1000);
		                    	        
		                    	        if(diffDays >0 && diffHours>0 && diffMinutes>0){

		                    	            datee = diffDays+" روز "+diffHours+" ساعت "+diffMinutes+" دقیقه ";
		                    	        }else if(diffDays <=0 && diffHours>0 && diffMinutes>0){

		                    	            datee =diffHours+" ساعت "+diffMinutes+" دقیقه ";
		                    	        }
		                    	        else if(diffDays <=0 && diffHours<=0 && diffMinutes>0){

		                    	            datee =diffMinutes+" دقیقه ";
		                    	        }
		                    	        else if(diffDays <=0 && diffHours>0 && diffMinutes<=0){

		                    	            datee =diffHours+"ساعت ";
		                    	        }
		                    	        else if(diffDays >0 && diffHours<=0 && diffMinutes<=0){

		                    	            datee =diffDays+" روز ";
		                    	        }
		                    	        
		                    	        remider.setText(datee);
		                    	        
		                    	        
		                    	        
		                    	        
		                    	        
		                    	        
									
									
									
									
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                    		
	                    		
	                    		
	                    		
							}else{
								JOptionPane a = new JOptionPane();
								a.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
								a.showMessageDialog(Frame.this,
										" ابتدا باید اتاق رزرو کرده باشید!!",
										"خطا", JOptionPane.ERROR_MESSAGE);

							}
	                           }
                         });
							timePanel.add(remider, gbc);
		
	}

	// .............................................................................
	// privateMethods................................
	// .............................................................................
	
	
	
		private GridBagLayout createGridbagLayout() {

			GridBagLayout gbl = new GridBagLayout();

			gbl.columnWidths = new int[] { 45, 45, 45, 45, 45, 45, 45, 45, 45, 45,
					45, 45, 45, 45, 45, 45, 45, 45, 45, 45 };
			gbl.rowHeights = new int[] { 46, 46, 46, 46, 46, 46, 46, 46, 46, 46,
					46, 46, 46 };
			gbl.columnWeights = new double[] { 50.0d, 50.0d, 50.0d, 50.0d, 50.0d,
					50.0d, 50.0d, 50.0d, 50.0d, 50.0d, 50.0d, 50.0d, 50.0d, 50.0d,
					50.0d, 50.0d, 50.0d, 50.0d, 50.0d, 50.0d };

			gbl.rowWeights = new double[] { 50.0d, 50.0d, 50.0d, 50.0d, 50.0d,
					50.0d, 50.0d, 50.0d, 50.0d, 50.0d };

			return gbl;

		}
		// ......................................................................
	


	// ......................................................................

		public  void TimeTableloadData() {
			
			
			
			timetableData.removeAllElements();
			timeTableModel = new tableModel(timetableData, timecolumnNamesVec);
			ArrayList<Time>TimeList=new ArrayList<Time>();
			Time t=new Time();
			t.setName("کافی نت");
			t.setStart(" 8 صبج");
			t.setEnd("12 شب");
			TimeList.add(t);
			
			//
			Time t2=new Time();
			t2.setName("گیم  نت");
			t2.setStart(" 9 صبج");
			t2.setEnd("10 شب");
			TimeList.add(t2);
			
			//
			Time t3=new Time();
			t3.setName("فروشگاه");
			t3.setStart(" 6 صبج");
			t3.setEnd("12 شب");
			TimeList.add(t3);
			//
			Time t4=new Time();
			t4.setName("رستوران");
			t4.setStart(" 6 صبج");
			t4.setEnd("12 شب");
			TimeList.add(t4);
			//
			Time t5=new Time();
			t5.setName("شهربازی");
			t5.setStart( "10 صبج");
			t5.setEnd("10 شب");
			TimeList.add(t5);
			
		
				for (Time t1 : TimeList) {
					java.util.Vector<Object> Row = new java.util.Vector<Object>();
					
					Row.add(t1.getName());
					Row.add(t1.getStart());
					Row.add(t1.getEnd());
					
					
					timetableData.add(Row);
					timeTableModel.setDataVector(pricetableData, pricecolumnNamesVec);
					

					
				}
			

		

			

				timeTable.setRowHeight(30);

		}
	

	public  void PriceTableloadData() {
		
		columnPriceNames = new String[]  {  "Order Name",
				"Food Name", "number of food","cost" };
		
		pricetableData.removeAllElements();
		priceTableModel.setDataVector(pricetableData, pricecolumnNamesVec);
		
		if( Frame.this.client.getListOfReceipt()!=null){
			for (Receipt receipt : Frame.this.client.getListOfReceipt()) {
				java.util.Vector<Object> Row = new java.util.Vector<Object>();
				
				if(!receipt.getReceiptName().equals("هزینه ی اقامت")){
					
					Row.add(receipt.getReceiptName());
					Row.add(receipt.getFood().getFoodName());
					Row.add(receipt.getNumberOfFood());
					
					double totalcost= (receipt.getNumberOfFood()*receipt.getFood().getPrice());
					Row.add(totalcost);
					
					pricetableData.add(Row);
					priceTableModel.setDataVector(pricetableData, pricecolumnNamesVec);
					
				}

				
			}
		}

	

		

		pricetable.setRowHeight(30);

	}

public  void RoomTableloadData() {
		
		columnNames = new String[]  { "Room ID", "Room Name",
				"Room Type", "price","Capacity","Status" };

		tableData.removeAllElements();
		roomTableModel = new tableModel(tableData, columnNamesVec);
		Frame.this.client.requestToServer("listRoom");
		
		

		for (Room room : Frame.this.client.getListOfRoom()) {
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
			roomTableModel = new tableModel(tableData, columnNamesVec);
		
		}

	

		roomtable.setRowHeight(30);
	
	}

public  void FoodTableloadData() {

	tableFoodData.removeAllElements();
	foodTableModel.setDataVector(tableFoodData, columnFoodNamesVec);
	
	Frame.this.client.requestToServer("listFile");

	for (Food food : Frame.this.client.getListOfAvailableFiles()) {
		java.util.Vector<Object> Row = new java.util.Vector<Object>();
		
		if(food.getFoodType().equals(tree.getLastSelectedPathComponent().toString())){
			

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
		
		
		
		

		
		
		
		
		
		
		
	}

	
	
	foodTable.setRowHeight(30);

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

@Override
public void windowActivated(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosed(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosing(WindowEvent arg0) {
	client.requestToServer("exit");

	
}

@Override
public void windowDeactivated(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeiconified(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowIconified(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowOpened(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}
}
