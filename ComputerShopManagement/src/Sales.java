import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Choice;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JComboBox;


public class Sales implements ActionListener,KeyListener{
   
	
	private Connection fix=null;
	private JFrame frame;
	private DefaultTableModel model;
	private DefaultComboBoxModel log;
	JButton Sell = new JButton("Sell");
	JButton Logout = new JButton("LogOut");
	JButton Inventory = new JButton("Inventory");
	JScrollPane txttable = new JScrollPane();
	private JTextField txtcustomer;
	private JTextField txtmobile;
	private JTextField txtmodel;
	private JTextField txtprice;
	private JTable table;
	JComboBox txtproduct = new JComboBox();
	JComboBox txtbrand = new JComboBox();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales window = new Sales();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sales() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1005, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("All In One Computer Shopee");
		lblNewLabel.setFont(new Font("Tekton Pro Cond", Font.BOLD, 55));
		lblNewLabel.setBounds(266, 11, 492, 61);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("All types of Computer are available here");
		lblNewLabel_1.setFont(new Font("Tekton Pro Cond", Font.BOLD, 25));
		lblNewLabel_1.setBounds(441, 83, 331, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(36, 128, 492, 390);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		Sell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		txtcustomer = new JTextField();
		txtcustomer.setColumns(10);
		txtcustomer.setBounds(158, 66, 298, 29);
		panel.add(txtcustomer);
		
		txtmobile = new JTextField();
		txtmobile.setColumns(10);
		txtmobile.setBounds(158, 104, 298, 29);
		panel.add(txtmobile);
		
		txtmodel = new JTextField();
		txtmodel.setColumns(10);
		txtmodel.setBounds(158, 219, 298, 29);
		panel.add(txtmodel);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(158, 259, 298, 29);
		panel.add(txtprice);
		
		Sell.setBounds(158, 299, 89, 38);
		Sell.addActionListener(this);
		panel.add(Sell);
		
		JLabel lblNewLabel_2_1 = new JLabel("Customer Name:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(31, 67, 117, 14);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Mobile No:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(35, 107, 79, 14);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Product:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_3.setBounds(35, 150, 79, 14);
		panel.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Model:");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_4.setBounds(35, 224, 79, 14);
		panel.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Price:");
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_5.setBounds(35, 264, 79, 14);
		panel.add(lblNewLabel_2_5);
		
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product", "Price", "Customer name", "Model"
			}
		));
		
		txttable.setViewportView(table);
		table.setBounds(539, 216, 410, 150);
		txttable.setBounds(539, 141, 410, 297);
		frame.getContentPane().add(txttable);
		
		
		Logout.setBounds(817, 484, 108, 27);
		Logout.addActionListener(this);
		frame.getContentPane().add(Logout);
		
		txtproduct.setModel(new DefaultComboBoxModel(new String[] {"Keyboard", "Mouse", "Speaker", "Computer"}));
		txtproduct.setBounds(158, 148, 298, 22);
		panel.add(txtproduct);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Brand:");
		lblNewLabel_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_3_1.setBounds(35, 183, 79, 14);
		panel.add(lblNewLabel_2_3_1);
		
		
		txtbrand.setModel(new DefaultComboBoxModel(new String[] {"Logitech", "Enter", "HP", "Dell", "Intex", "Iball", "Terabyte", "Samsung", "LG"}));
		txtbrand.setBounds(158, 181, 298, 22);
		txtbrand.addKeyListener(this);
		panel.add(txtbrand);
		
		
		Inventory.setBounds(655, 486, 103, 23);
		Inventory.addActionListener(this);
		frame.getContentPane().add(Inventory);
	}
    private void Sales()
    {
    	 String format = "dd/MM/yyyy";
		 SimpleDateFormat sd = new SimpleDateFormat(format);
		 String date = sd.format(new Date());
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				fix=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/computershop","root","13102002");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		 String query ="Insert into Sales(prod,pri,dates,Customername,model)" + "Values(?,?,?,?,?)";
		 try {
			PreparedStatement State = fix.prepareStatement(query);
			String Customername = txtcustomer.getText();
			String Mobile = txtmobile.getText();
			String Model = txtmodel.getText();
			String Price = txtprice.getText();
			
			
			
			 String Product = txtproduct.getSelectedItem().toString();
			 State.setString(1, Product);
			 State.setInt(2, Integer.parseInt(Price) );
			 State.setString(3,  date);
			 State.setString(4, Customername);
			 String Brand = txtbrand.getSelectedItem().toString();
			 State.setString(5, Brand );
			 State.executeUpdate();
			 
			fix.close();
			model=(DefaultTableModel) table.getModel();
			String arr[]= {Product,Price,Customername,Model};
			model.addRow(arr);
			
			
			
			 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				 
    }
    private void Display()
	 {
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				fix=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/computershop","root","13102002");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		   String query="Select * from Purchase;";
	     	PreparedStatement State = null;
	     	try {
	     	State = fix.prepareStatement(query);
	     	ResultSet set = State.executeQuery();
	     	txtprice.setText("");
	     	while(set.next())
	     	{
	     		
	     		Integer Price= set.getInt(2);
	     		String Brand = set.getString(3);
	     		String Product = set.getString(1);
	     		String prod = txtproduct.getSelectedItem().toString();
	     		if(txtbrand.getSelectedItem().toString().equals(Brand) && prod.equals(Product))
	     		{
	     			txtprice.setText(String.valueOf(Price));
	     			
	     		}
	     	
	     		
	     		
	     	}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} 
	 }
    
    
    
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==Sell)
		{
			Sales();
		}
		else if(e.getSource()==Logout)
		{
			new Login();
			frame.dispose();
		}
		else if(e.getSource()==Inventory)
		{
			new Stock();
			frame.dispose();
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource()== txtbrand)
		{
		  Display();
		  
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
