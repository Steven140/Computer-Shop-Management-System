import java.awt.EventQueue;

import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Choice;
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
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Admin implements ActionListener,KeyListener{
	private Connection add= null;
	private DefaultTableModel model;
	private DefaultComboBoxModel log;
	private JFrame frame;
	private JTextField txtprice;
	private JTable table;
	JScrollPane txttable = new JScrollPane();
	JComboBox txtproduct = new JComboBox();
	JComboBox txtbrand = new JComboBox();
	JButton Added = new JButton("Add");
	JButton Del = new JButton("Delete");
	JButton Printbill = new JButton("Buy");
	JButton Logout = new JButton("Logout");
	Integer Total = 0;
	 
	 private JTextField txtquantity;
	 private JTextField txttotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
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
	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1003, 592);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("All In One Computer Shopee");
		lblNewLabel.setBounds(253, 22, 492, 61);
		lblNewLabel.setFont(new Font("Tekton Pro Cond", Font.BOLD, 55));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("All types of Computer are available here");
		lblNewLabel_1.setBounds(428, 94, 331, 34);
		lblNewLabel_1.setFont(new Font("Tekton Pro Cond", Font.BOLD, 25));
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(98, 178, 318, 284);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Brand:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(34, 86, 60, 14);
		panel.add(lblNewLabel_2);
		
		
		Added.setBounds(49, 221, 89, 23);
		Added.addActionListener(this);
		panel.add(Added);
		
		
		Del.setBounds(186, 221, 89, 23);
		Del.addActionListener(this);
		panel.add(Del);
		
		txtprice = new JTextField();
		txtprice.setBounds(100, 120, 175, 29);
		panel.add(txtprice);
		txtprice.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Product:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(34, 51, 60, 14);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Price:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(34, 125, 49, 14);
		panel.add(lblNewLabel_3_1);
		
	
		txtproduct.setModel(new DefaultComboBoxModel(new String[] {"Keyboard", "Mouse", "Speaker", "Computer"}));
		txtproduct.setBounds(100, 49, 175, 22);
		panel.add(txtproduct);
		
		
		txtbrand.setModel(new DefaultComboBoxModel(new String[] {"Logitech", "Enter", "HP", "Dell", "Intex", "Iball", "Terabyte", "Samsung","LG"}));
		txtbrand.setBounds(100, 86, 175, 22);
		txtbrand.addKeyListener(this);
		panel.add(txtbrand);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Quantity:");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1_1.setBounds(23, 160, 71, 25);
		panel.add(lblNewLabel_3_1_1);
		
		txtquantity = new JTextField();
		txtquantity.setColumns(10);
		txtquantity.setBounds(100, 160, 175, 29);
		panel.add(txtquantity);
		
		
		Printbill.setBounds(795, 471, 152, 61);
		Printbill.addActionListener(this);
		frame.getContentPane().add(Printbill);
		
		
		Logout.setBounds(607, 471, 152, 61);
		Logout.addActionListener(this);
		frame.getContentPane().add(Logout);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product", "Price", "Brand","Quantity"
			}
		));
		
		txttable.setViewportView(table);
		table.setBounds(539, 216, 410, 150);
		txttable.setBounds(537, 178, 410, 241);
		frame.getContentPane().add(txttable);
		
		JLabel lblNewLabel_3 = new JLabel("Total Price:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(668, 430, 85, 32);
		frame.getContentPane().add(lblNewLabel_3);
		
		txttotal = new JTextField();
		txttotal.setBounds(755, 430, 109, 32);
		frame.getContentPane().add(txttotal);
		txttotal.setColumns(10);
		
		
		
	}
	 private void Purchase()
	 { 
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			add=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/computershop","root","13102002");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		 String query="Insert into purchase(prod,pri,brand,quantity)"+ "Values(?,?,?,?)";
		
		try {
			PreparedStatement State=add.prepareStatement(query);
		
			String Price = txtprice.getText();
			String Quantity = txtquantity.getText();
			 
			
			String Product = txtproduct.getSelectedItem().toString();
			 State.setString(1, Product);
			 State.setInt(2, Integer.parseInt(Price) );
			 String Brand = txtbrand.getSelectedItem().toString();
			 State.setString(3, Brand);
			 State.setInt(4, Integer.parseInt(Quantity));
			 State.executeUpdate();
			 
			 add.close();
			 model=(DefaultTableModel) table.getModel();
			 String arr[]= {Product,Price,Brand,Quantity};
				model.addRow(arr);
			Total += Integer.parseInt(Price)*Integer.parseInt(Quantity);
				txttotal.setText(String.valueOf(Total));
			 
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		
		
		
		 
	 }
	 private void Delete()
	 {
		 
		 int row=table.getSelectedRow();
		 String Product = model.getValueAt(row,0).toString();
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				add=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/computershop","root","13102002");
			} catch (Exception e) 
		    {
				
				e.printStackTrace();
			}
		 String query = "Delete from purchase where prod=?";
		 try {
			PreparedStatement State=add.prepareStatement(query);
			State.setString(1, Product);
			State.execute();
			add.close();
			Integer Row = table.getSelectedRow();
			int Price =  Integer.parseInt(model.getValueAt(Row,1).toString());
			int Quantity =  Integer.parseInt(model.getValueAt(Row,3).toString());
			model.removeRow(row);
			Total -= Price*Quantity;
			txttotal.setText(String.valueOf(Total));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
		 
		 
	 }
	 
	 private void Show()
	 {
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				add=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/computershop","root","13102002");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		   String query="Select * from Purchase;";
	     	PreparedStatement State = null;
	     	try {
	     	State = add.prepareStatement(query);
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Added)
		{
			Purchase();
		}
		else if(e.getSource()==Del)
		{
		   Delete();	
		}
		else if(e.getSource()==Logout)
		{
			new Login();
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
		  Show();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	 
