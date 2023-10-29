package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.JSeparator;
import javax.swing.JPanel;

public class Login {

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;

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

		textField = new JTextField();
		textField.setBounds(150, 135, 107, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(150, 174, 107, 20);
		frmLogin.getContentPane().add(passwordField);

		JSeparator separator = new JSeparator();
		separator.setBounds(44, 113, 295, 14);
		frmLogin.getContentPane().add(separator);

		 
		try {
			
			BufferedImage img = ImageIO.read(new File("src/images/l.png"));
			JLabel pic = new JLabel(new ImageIcon("D:\\projects\\all\\tornaapp\\java\\src\\images\\l.png"));
			pic.setSize(263, 91);
			pic.setLocation(59, 11);
			frmLogin.getContentPane().add(pic);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
