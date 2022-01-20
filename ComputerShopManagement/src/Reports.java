import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Reports implements ActionListener {
	Connection add = null;
	private DefaultTableModel model;
	private JFrame frame;
	private JTable table;
	JScrollPane txttable = new JScrollPane();
	JButton Logout = new JButton("LogOut");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reports window = new Reports();
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
	public Reports() {
		
		initialize();
		Update();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 901, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product","Price", "Date", "Customer Name","Brand"
			}
		));
		
		txttable.setViewportView(table);
		table.setBounds(400, 100, 410, 150);
		txttable.setBounds(48, 30, 789, 376);
		frame.getContentPane().add(txttable);
		
		
		Logout.setBounds(765, 429, 89, 23);
		Logout.addActionListener(this);
		frame.getContentPane().add(Logout);
	}

   
   private void Update()
   {   
	   try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			add=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/computershop","root","13102002");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	   String query="Select * from Sales;";
     	PreparedStatement State = null;
     	try {
     	State = add.prepareStatement(query);
     	ResultSet set = State.executeQuery();
     	while(set.next())
     	{
     		String Product = set.getString(1);
     		Integer Price= set.getInt(2);
     		String Dates = set.getString(3);
     		String Customername = set.getString(4);
     		String Model = set.getString(5);
     		
     		String arr[]= {Product,Integer.toString(Price),Dates,Customername,Model};
     		model=(DefaultTableModel) table.getModel();
     		model.addRow(arr);
     		
     		
     	}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
     	
		
   }

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==Logout)
	{
		new Login();
		frame.dispose();
	}
		
	
}
}

   