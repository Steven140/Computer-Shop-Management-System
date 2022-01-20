import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.Choice;
import java.awt.Button;

public class Login implements ActionListener{
    private Connection user= null;
    private DefaultComboBoxModel model;
	private JFrame frame;
	private JTextField txtusername;
	private JTextField txtpassword;
	JButton Login = new JButton("Login");
	JComboBox txtcategory = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
		    		 UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		    	}
		    	
		            catch(Exception e) {
		            	e.printStackTrace();
		            }
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 1011, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblNewLabel.setBounds(381, 22, 234, 49);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("All In One Computer Shopee");
		lblNewLabel_1.setFont(new Font("Tekton Pro Cond", Font.PLAIN, 50));
		lblNewLabel_1.setBounds(289, 82, 448, 71);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(342, 164, 315, 292);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel txtuser = new JLabel("User:");
		txtuser.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtuser.setBounds(67, 58, 37, 24);
		panel.add(txtuser);
		
		
		JLabel lblNewLabel_3 = new JLabel("Username:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(27, 108, 77, 24);
		panel.add(lblNewLabel_3);
		
		txtusername = new JTextField();
		txtusername.setBounds(114, 112, 121, 31);
		panel.add(txtusername);
		txtusername.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Password:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(27, 163, 81, 24);
		panel.add(lblNewLabel_4);
		
		txtpassword = new JTextField();
		txtpassword.setBounds(114, 167, 121, 31);
		panel.add(txtpassword);
		txtpassword.setColumns(10);
		
		
		Login.setBounds(114, 233, 89, 23);
		Login.addActionListener(this);
		panel.add(Login);
		
		
		
		txtcategory.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Seller", "Manager"}));
		txtcategory.setBounds(114, 61, 121, 22);
		panel.add(txtcategory);
		
	}
    private void getLogin() {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			user=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/computershop","root","13102002");
		} catch (Exception e)
    	
    	{
			
			
			e.printStackTrace();
		}
    	String query="Select * from login;";
    	PreparedStatement State;
		try {
			String Username = txtusername.getText();
    	 	String Password = txtpassword.getText();
    	 	model = (DefaultComboBoxModel) txtcategory.getModel();
    	 	String Role = model.getSelectedItem().toString();
			State = user.prepareStatement(query);
			ResultSet set = State.executeQuery();
			boolean log= false;
	    	while(set.next())
	    	{
	    	 String Name = set.getString("Namess");
	    	 String Pass = set.getString("Pass");
	    	 String Rol = set.getString("Roles");
	    	 if(Name.equals(Username) && Pass.equals(Password) && Rol.equals(Role))
	    	 {
	    		 log=true;
	    		 if(Role.equals("Admin"))
	    		 {
	    			 new Admin();
	    			 frame.dispose();
	    		 }
	    		 if(Role.equals("Seller"))
	    		 {
	    			 new Sales();
	    			 frame.dispose();
	    		 }
	    		 if(Role.equals("Manager"))
	    		 {
	    			 new Reports();
	    			 frame.dispose();
	    		 }
	    		 
	    	 }
	    	 
	    	}
	    	if(!log)
	    	 {
	    		 JOptionPane.showMessageDialog(frame,"Invalid credentials");
	    	 }
	    	

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		
	if(e.getSource()==Login)
	{
		getLogin();
	}
	
	{
	   
	}
	}}
