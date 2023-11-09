package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

	private JFrame frmLogin;
	
	private JTextField textFieldEmail;
	private JPasswordField passwordFieldPassword;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
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
		//System.out.println(ClassLoader.getSystemResource("./images/t.png"));	    
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		
		frmLogin.getContentPane().setBackground(new Color(102, 205, 170));
		
		frmLogin.setBackground(new Color(102, 205, 170));
		frmLogin.setAlwaysOnTop(true);
		frmLogin.setBounds(100, 100, 405, 244);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(94, 138, 46, 14);
		frmLogin.getContentPane().add(lblEmail);

		JLabel lblPassword = new JLabel("Jelszó");
		lblPassword.setBounds(94, 177, 46, 14);
		frmLogin.getContentPane().add(lblPassword);

		textFieldEmail = new JTextField("kunszt.norbert@gmail.com");
		textFieldEmail.setBounds(150, 135, 107, 20);
		frmLogin.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		passwordFieldPassword = new JPasswordField("rrrrrr");
		passwordFieldPassword.setBounds(150, 174, 107, 20);
		frmLogin.getContentPane().add(passwordFieldPassword);

		JSeparator separator = new JSeparator();
		separator.setBounds(44, 113, 295, 14);
		frmLogin.getContentPane().add(separator);

		 
		try {
			
			
			JLabel pic = new JLabel(new ImageIcon(ClassLoader.getSystemResource("./main/resources/login.png")));
			
			
			pic.setSize(277, 91);
			pic.setLocation(59, 11);
			frmLogin.getContentPane().add(pic);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String psw = PasswordHash.hashing(String.valueOf(passwordFieldPassword.getPassword()));
				
				
				String email = textFieldEmail.getText() ;
				
				if(email.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "az e-mail nem lehet üres", "Login hiba", JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				String pswInput=String.valueOf(passwordFieldPassword.getPassword());
				if( pswInput.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "a jelszó nem lehet üres", "Login hiba", JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				System.out.println(psw);
				ExerciseUser user= DBHAndler2.checkLogin(email, psw);
				System.out.println(user);
				if(user!=null) {
					
					Welcome welcomeFrame = new Welcome(user);
					welcomeFrame.frame.setVisible(true);
					welcomeFrame.frame.setBounds(0, 0, 680, 400);
					frmLogin.dispose();

				}else {
					JOptionPane.showMessageDialog(null, "a jelszó vagy az email hibás", "Login hiba", JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				
				
			}
		});
		btnLogin.setBackground(new Color(0, 204, 102));
		btnLogin.setBounds(292, 173, 73, 23);
		frmLogin.getContentPane().add(btnLogin);


	}
}