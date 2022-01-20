import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class Stock implements ActionListener {
   
	private JFrame frame;
	private DefaultTableModel model;
	JButton btnback = new JButton("Back");
	private JTable table;
	JScrollPane txttable = new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock window = new Stock();
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
	public Stock() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 990, 592);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		
		
		
		btnback.setBounds(10, 521, 89, 23);
		btnback.addActionListener(this);
		frame.getContentPane().add(btnback);
		
		JLabel lblNewLabel = new JLabel("All In One Computer Shopee");
		lblNewLabel.setFont(new Font("Tekton Pro Cond", Font.BOLD, 55));
		lblNewLabel.setBounds(211, 28, 492, 61);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("All types of Computer are available here");
		lblNewLabel_1.setFont(new Font("Tekton Pro Cond", Font.BOLD, 25));
		lblNewLabel_1.setBounds(386, 100, 331, 34);
		frame.getContentPane().add(lblNewLabel_1);
	
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product", "Price", "Quantity","Brand"
			}
		));
		
		txttable.setViewportView(table);
		table.setBounds(539, 216, 410, 150);
		txttable.setBounds(61, 145, 886, 345);
		frame.getContentPane().add(txttable);
		Update();
		
	}

	private void Update()
	   {   
		 Connection log = null;
		   try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				log=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/computershop","root","13102002");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		   String query="Select * from purchase;";
	     	PreparedStatement State;
	     	try {
	     	State = log.prepareStatement(query);
	     	ResultSet set = State.executeQuery();
	     	while(set.next())
	     	{
	     		String Product = set.getString(1);
	     		String Brand = set.getString(3);
	     		Integer Price= set.getInt(2);
	     		Integer Quantity = set.getInt(4);	     		
	     		String arr[]= {Product,Integer.toString(Price),Integer.toString(Quantity),Brand};
	     		model=(DefaultTableModel) table.getModel();
	     		model.addRow(arr);
	     		
	     		
	     	}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	   }
			

	@Override
	public void actionPerformed(ActionEvent e)
	{
	if(e.getSource()==btnback)
	{
		new Sales();
		frame.dispose();
	}
	

	}
}
