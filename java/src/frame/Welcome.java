package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToggleButton;

import java.util.ArrayList;

import javax.swing.JScrollPane;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

public class Welcome {
	
	private ArrayList<ExerciseUser> users;
	private JTable table;
	private Object[][] tableData;
	private JTextField textFieldNewUserEmail;
	private JTextField textFieldNewUserPhone;
	private JTextField textFieldNewUserFirstName;
	private JTextField textFieldNewUserLastName;
	private JLabel lblNewUserLastName;
	private JLabel lblNewUserFirstName;
	private JLabel lblNewUserPhone;
	private JLabel lblNewUserEmail;
	private JScrollPane scrollPane2;
	private JPanel panel;
	private JPanel panelUserList;
	private JPanel panelReserves;
	private ArrayList<Reserve> reserves;
	private JScrollPane scrollPaneReserves;
	private Object[][] reservetableData;
	private JPanel panelUserRights;
	private ExerciseUser user;
	private boolean createNewUser;
	private JPanel panelWelcome;

	public JFrame frame;

	


	/**
	 * Create the application.
	 */
	public Welcome(ExerciseUser user) {
		frame=new JFrame();
		
		//ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("./main/resources/t.png"));
		//frame.setIconImage(img.getImage());
		  
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/main/resources/t.png")));
		this.user = user;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		


		frame.setTitle("Villámtánc");
		frame.getContentPane().setLayout(null);

		panelWelcome = new JPanel();
		panelWelcome.setBounds(10, 0, 399, 236);
		frame.getContentPane().add(panelWelcome);
		panelWelcome.setLayout(null);
		 

		JTextPane textPaneWelcomeText = new JTextPane();
		textPaneWelcomeText.setEditable(false);

		textPaneWelcomeText.setText("Üdvözöllek az alkalmazásban!");
		textPaneWelcomeText.setBounds(10, 11, 379, 35);
		panelWelcome.add(textPaneWelcomeText);

		SimpleAttributeSet textStyle = new SimpleAttributeSet();
		StyleConstants.setForeground(textStyle, Color.lightGray);
		StyleConstants.setFontFamily(textStyle, "lucida bright italic");
		StyleConstants.setFontSize(textStyle, 18);
		StyleConstants.setAlignment(textStyle, StyleConstants.ALIGN_CENTER);
		StyledDocument doc = textPaneWelcomeText.getStyledDocument();
		doc.setCharacterAttributes(105, doc.getLength() - 105, textStyle, false);
		doc.setParagraphAttributes(0, 104, textStyle, false);
		
		getAllData();

		createMenu();
	}
	

	private void createMenu() {

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Foglalás");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Foglalás lista");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showReserves();

			}

		});
		if (user.getUserRight().isReserveList()) {

			mnNewMenu.add(mntmNewMenuItem);
		}

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Új foglalás");
		mnNewMenu.add(mntmNewMenuItem_4);

		JMenu mnNewMenu_1 = new JMenu("Felhasználók");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Felhasználó lista");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				userListInTable();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_2 = new JMenu("Súgó");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Az alkalmazásról");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHelp();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Új felhasználó");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExerciseUser user = new ExerciseUser("", "", "", "", 0, new UserRight(0, false, false));
				createUserInputshow(user);

			}
		});

		if (user.getUserRight().isCreateUser())
			mnNewMenu_1.add(mntmNewMenuItem_2);

	}

	private void showReserves() {

		hideAllComponent();
		if (!this.user.getUserRight().isReserveList()) {
			userListInTable();
			return;
		}
		panelReserves = new JPanel();
		panelReserves.setBounds(10, 20, 650, 300);
		panelReserves.setBackground(Color.cyan);
		frame.getContentPane().add(panelReserves);
		panelReserves.setLayout(null);

		panelReserves.setBorder(new LineBorder(new Color(0, 0, 0)));

		scrollPaneReserves = new JScrollPane();
		scrollPaneReserves.setBounds(5, 20, 600, 200);

		panelReserves.add(scrollPaneReserves);

		String[] columnNames = { "Dátumtól", "Dátumig", "Időtől", "Időig", "Név" };
		reservetableData = new Object[reserves.size()][5];
		for (int i = 0; i < reserves.size(); i++) {
			reservetableData[i][0] = reserves.get(i).getFromDate();

			reservetableData[i][1] = reserves.get(i).getToDate();
			reservetableData[i][2] = reserves.get(i).getFromTime();
			reservetableData[i][3] = reserves.get(i).getToTime();
			reservetableData[i][4] = getUserNameById(reserves.get(i).getUserId());

		}

		DefaultTableModel reservesTableModel = new DefaultTableModel(reservetableData, columnNames);
		JTable reserveTable = new JTable(reservesTableModel);
		scrollPaneReserves.setViewportView(reserveTable);
	}

	private void userListInTable() {
		getAllData();
		hideAllComponent();
		panelUserList = new JPanel();
		panelUserList.setBounds(10, 20, 650, 300);
		panelUserList.setBackground(Color.cyan);
		frame.getContentPane().add(panelUserList);
		panelUserList.setLayout(null);

		panelUserList.setBorder(new LineBorder(new Color(0, 0, 0)));

		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(5, 20, 600, 200);

		panelUserList.add(scrollPane2);

		String[] columnNames = { "Név", "Telefon", "E-mail" };
		tableData = new Object[users.size()][4];
		createRows();
		DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames);
		table = new JTable(tableModel);
		ListSelectionModel select = table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setBackground(Color.lightGray);
		table.setForeground(Color.white);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setAutoCreateRowSorter(true);

		table.setSelectionBackground(new Color(0, 204, 255));
		table.setRowHeight(25);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					int a = table.convertRowIndexToModel(table.getSelectedRow());
					String email = table.getModel().getValueAt(a, 2).toString();

					int i = 0;
					while (!users.get(i).getEmail().equals(email)) {
						i++;
					}
					createUserInputshow(users.get(i));

				}

			}
		});
		scrollPane2.setViewportView(table);

	}

	private void hideAllComponent() {
		if (panelUserList != null)
			frame.getContentPane().remove(panelUserList);
		if (panel != null)
			frame.getContentPane().remove(panel);
		if (panelReserves != null)
			frame.getContentPane().remove(panelReserves);
		if (panelUserRights != null)
			frame.getContentPane().remove(panelUserRights);
		if (panelWelcome != null)
			frame.getContentPane().remove(panelWelcome);

		frame.revalidate();
		frame.repaint();

	}

	private void createRows() {

		for (int i = 0; i < users.size(); i++) {
			tableData[i][0] = users.get(i).getUserName();

			tableData[i][1] = users.get(i).getPhone();
			tableData[i][2] = users.get(i).getEmail();

		}

	}

	private void createUserInputshow(ExerciseUser createdUser) {

		hideAllComponent();
		panel = new JPanel();
		panel.setBounds(33, 11, 320, 300);
		panel.setBackground(new Color(200, 200, 200));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		textFieldNewUserPhone = new JTextField(createdUser.getPhone());
		textFieldNewUserEmail = new JTextField(createdUser.getEmail());
		textFieldNewUserFirstName = new JTextField(createdUser.getFirstName());
		textFieldNewUserLastName = new JTextField(createdUser.getLastName());

		if (!createdUser.getEmail().equals("")) {
			// old user
			JLabel lblUserEmail = new JLabel(user.getEmail());
			lblUserEmail.setBounds(105, 8, 200, 20);
			panel.add(lblUserEmail);
			JButton deleteUser = new JButton("Törlés");
			deleteUser.setBounds(120, 270, 90, 25);
			deleteUser.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (JOptionPane.showConfirmDialog(null, "Valóban törli?", "Törlés",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

						if (DBHAndler2.deleteUser(user))
							userListInTable();
					}

				}
			});
			panel.add(deleteUser);

			JButton btnSetUserRights = new JButton("Jogok szerkesztése");
			btnSetUserRights.setBounds(20, 160, 150, 25);
			panel.add(btnSetUserRights);
			btnSetUserRights.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					userRightsShow(user);

				}
			});

		} else {
			// new user
			createNewUser = true;
			textFieldNewUserEmail.setText("");
			textFieldNewUserFirstName.setText("");
			textFieldNewUserLastName.setText("");
			textFieldNewUserPhone.setText("");

			panel.add(textFieldNewUserEmail);
		}

		lblNewUserEmail = new JLabel("E-mail");
		lblNewUserEmail.setBounds(28, 11, 46, 14);

		lblNewUserPhone = new JLabel("Phone");
		lblNewUserPhone.setBounds(28, 36, 46, 14);

		lblNewUserFirstName = new JLabel("First name");
		lblNewUserFirstName.setBounds(28, 61, 62, 14);

		lblNewUserLastName = new JLabel("Last name");
		lblNewUserLastName.setBounds(28, 86, 62, 14);

		textFieldNewUserEmail.setBounds(105, 8, 120, 20);

		textFieldNewUserEmail.setColumns(10);

		panel.add(lblNewUserEmail);
		panel.add(lblNewUserPhone);
		panel.add(lblNewUserFirstName);
		panel.add(lblNewUserLastName);

		textFieldNewUserPhone.setBounds(105, 33, 120, 20);
		panel.add(textFieldNewUserPhone);
		textFieldNewUserPhone.setColumns(10);

		textFieldNewUserFirstName.setBounds(105, 58, 120, 20);
		panel.add(textFieldNewUserFirstName);
		textFieldNewUserFirstName.setColumns(10);

		textFieldNewUserLastName.setBounds(105, 83, 120, 20);
		panel.add(textFieldNewUserLastName);
		textFieldNewUserLastName.setColumns(10);

		JButton btnCancelSaveUser = new JButton("Mégse");
		btnCancelSaveUser.setBounds(20, 270, 90, 25);
		btnCancelSaveUser.setForeground(new Color(200, 0, 0));
		btnCancelSaveUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldNewUserEmail.setText("");
				textFieldNewUserFirstName.setText("");
				textFieldNewUserLastName.setText("");
				textFieldNewUserPhone.setText("");
				userListInTable();

			}
		});
		panel.add(btnCancelSaveUser);

		JButton btnSaveUser = new JButton("Mentés");
		btnSaveUser.setBounds(221, 270, 90, 25);
		btnSaveUser.setForeground(new Color(0, 200, 0));
		btnSaveUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (createNewUser) {
					ExerciseUser newUser = new ExerciseUser(textFieldNewUserPhone.getText(),
							textFieldNewUserFirstName.getText(), textFieldNewUserLastName.getText(),
							textFieldNewUserEmail.getText(), 1, new UserRight(1, false, false));
					createNewUserInDb(newUser);
				} else {

					createdUser.setFirstName(textFieldNewUserFirstName.getText());
					createdUser.setLastName(textFieldNewUserLastName.getText());
					createdUser.setPhone(textFieldNewUserPhone.getText());
					updateUserData(createdUser);
				}

			}
		});
		panel.add(btnSaveUser);

		/*
		
		*/
	}

	private void updateUserData(ExerciseUser user) {
		if (DBHAndler2.UpdateUserData(user)) {
			userListInTable();
		} else {
			System.err.println("hiba update közben");
		}
	}

	private void createNewUserInDb(ExerciseUser user) {
		if (user.getUserRight().isCreateUser()) {
			if (DBHAndler2.saveNewUserInDb(user)) {
				userListInTable();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nincs jogosultsága", "Hibás jog", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void removeAllRows(JTable table) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		int rowCount = dm.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			dm.removeRow(i);
		}

	}

	public String getUserNameById(int id) {
		String name = "";

		int i = 0;
		while (users.get(i).getUserId() != id) {
			i++;
		}
		name = users.get(i).getUserName();
		return name;
	}

	private void getAllData() {
		this.users = DBHAndler2.getAllFromDB();
		this.reserves = DBHAndler2.getAllReserved();
	}

	private void userRightsShow(ExerciseUser user) {
		hideAllComponent();

		UserRight userRights = user.getUserRight();
		panelUserRights = new JPanel();
		panelUserRights.setBounds(33, 11, 320, 300);
		panelUserRights.setBackground(Color.lightGray);
		frame.getContentPane().add(panelUserRights);

		JCheckBox rightReservesList = new JCheckBox("Foglalás lista");
		rightReservesList.setSelected(userRights.isReserveList());
		rightReservesList.setBounds(10, 10, 100, 20);

		JCheckBox rightCreateNewUser = new JCheckBox("Új felhasználó");
		rightCreateNewUser.setSelected(userRights.isCreateUser());
		rightCreateNewUser.setBounds(10, 45, 100, 20);

		panelUserRights.add(rightReservesList);
		panelUserRights.add(rightCreateNewUser);

		JButton btnSaveUserRights = new JButton("Mentés");
		btnSaveUserRights.setBounds(221, 270, 90, 25);
		btnSaveUserRights.setForeground(new Color(0, 200, 0));
		btnSaveUserRights.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				user.setUserRight(new UserRight(user.getUserId(), rightReservesList.isSelected(),
						rightCreateNewUser.isSelected()));

				if (DBHAndler2.saveUserRightsInDB(user)) {
					userListInTable();
				} else
					JOptionPane.showMessageDialog(null, "Hiba történt");
			}
		});

		JButton btnCancelSaveUserRights = new JButton("Mégse");
		btnCancelSaveUserRights.setBounds(20, 270, 90, 25);
		btnCancelSaveUserRights.setForeground(new Color(200, 0, 0));
		btnCancelSaveUserRights.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userListInTable();

			}
		});

		panelUserRights.add(btnSaveUserRights);
		panelUserRights.add(btnCancelSaveUserRights);

	}

	private void showHelp() {
		JOptionPane.showMessageDialog(null, "kell content", "Súgó", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public ImageIcon loadIcon(String iconName) throws IOException {
		  ClassLoader loader = this.getClass().getClassLoader();
		  BufferedImage icon = 
		  ImageIO.read(loader.getResourceAsStream(iconName));
		  return new ImageIcon(icon);
		}
}
