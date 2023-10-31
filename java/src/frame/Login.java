package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmLogin;
	private JFrame frmWelcome;
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.getContentPane().setBackground(new Color(102, 205, 170));
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\login-935679.png"));
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

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(150, 135, 107, 20);
		frmLogin.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(150, 174, 107, 20);
		frmLogin.getContentPane().add(passwordFieldPassword);

		JSeparator separator = new JSeparator();
		separator.setBounds(44, 113, 295, 14);
		frmLogin.getContentPane().add(separator);

		 
		try {
			
			BufferedImage img = ImageIO.read(new File("src/images/l.png"));
			JLabel pic = new JLabel(new ImageIcon("D:\\projects\\all\\tornaapp\\java\\src\\images\\l.png"));
			pic.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
			pic.setForeground(new Color(153, 255, 102));
			pic.setSize(263, 91);
			pic.setLocation(59, 11);
			frmLogin.getContentPane().add(pic);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String psw = PasswordHash.hashing(String.valueOf(passwordFieldPassword.getPassword()));
				System.out.println(psw);
				
				String email = textFieldEmail.getText() ;
				System.err.println(psw);
				if(email.equals("")) {
					
					JOptionPane.showMessageDialog(null, "az e-mail nem lehet üres", "Login hiba", JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				if( String.valueOf(passwordFieldPassword.getPassword()).equals("")) {
					
					JOptionPane.showMessageDialog(null, "a jelszó nem lehet üres", "Login hiba", JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				Boolean loggedIn = DBHAndler2.checkLogin(email, psw);
				if(loggedIn) {
					Welcome welcomeWindow = new Welcome();
					frmLogin.setVisible(false);
					welcomeWindow.setVisible(true);
					welcomeWindow.setBounds(0, 0, 500, 400);
					
				}else {
					return;
				}
				
				
			}
		});
		btnLogin.setBackground(new Color(0, 204, 102));
		btnLogin.setBounds(292, 173, 73, 23);
		frmLogin.getContentPane().add(btnLogin);


	}
}
