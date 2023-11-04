package frame;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;

import welcome.WelcomeFunctions;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class Welcome extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public Welcome() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/images/vt_logo.png")));

		setTitle("Villámtánc");
		getContentPane().setLayout(null);
		
		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getAllData();
		createMenu();

	}

	private void createMenu() {

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Foglalás");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Foglalás lista");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showReserves();

			}

		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Új foglalás");
		mnNewMenu.add(mntmNewMenuItem_4);

		JMenu mnNewMenu_1 = new JMenu("Felhasználók");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Felhasználó lista");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getUsersAndShow();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Új felhasználó");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExerciseUser user = new ExerciseUser("", "", "", "", 0);
				createUserInputshow(user);

			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);

	}

	private void showReserves() {
		hideAllComponent();
		panelReserves = new JPanel();
		panelReserves.setBounds(10, 20, 650, 300);
		panelReserves.setBackground(Color.cyan);
		getContentPane().add(panelReserves);
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

	private void getUsersAndShow() {
		getAllData();
		hideAllComponent();
		panelUserList = new JPanel();
		panelUserList.setBounds(10, 20, 650, 300);
		panelUserList.setBackground(Color.cyan);
		getContentPane().add(panelUserList);
		panelUserList.setLayout(null);

		panelUserList.setBorder(new LineBorder(new Color(0, 0, 0)));

		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(5, 20, 600, 200);

		panelUserList.add(scrollPane2);

		String[] columnNames = { "Név", "Telefon", "E-mail" };
		tableData = new Object[users.size()][4];
		createRows();
		DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames);
		table = new JTable(tableModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
				return (column == 3) ? Icon.class : Object.class;
			}

		};

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
					/*
					 * StringBuilder row = new StringBuilder();
					 * row.append(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()
					 * + " "); row.append(table.getModel().getValueAt(table.getSelectedRow(),
					 * 1).toString()); row.append(", e-mail: ");
					 * row.append(table.getModel().getValueAt(table.getSelectedRow(),
					 * 2).toString()); JOptionPane.showMessageDialog(null, row, "adatok",
					 * JOptionPane.PLAIN_MESSAGE, null);
					 * 
					 */
				}

			}
		});
		scrollPane2.setViewportView(table);

	}

	private void hideAllComponent() {
		if (panelUserList != null)
			getContentPane().remove(panelUserList);
		if (panel != null)
			getContentPane().remove(panel);
		if (panelReserves != null)
			getContentPane().remove(panelReserves);
		if (panelUserRights != null)
			getContentPane().remove(panelUserRights);

		revalidate();
		repaint();

	}

	private void createRows() {

		for (int i = 0; i < users.size(); i++) {
			tableData[i][0] = users.get(i).getUserName();

			tableData[i][1] = users.get(i).getPhone();
			tableData[i][2] = users.get(i).getEmail();

		}

	}

	private Icon[] createImages() {
		Icon[] icons = new Icon[2];
		icons[0] = new ImageIcon(this.getClass().getResource("" + "/images/male.png"));
		icons[0] = new ImageIcon(this.getClass().getResource("" + "/images/male.png"));

		return icons;

	}

	private JButton[] createButtons() {
		JButton[] buttons = new JButton[3];

		JButton deleteUser = new JButton("X");
		deleteUser.setBounds(1, 1, 20, 20);
		return buttons;

	}

	private void createUserInputshow(ExerciseUser user) {
		hideAllComponent();
		panel = new JPanel();
		panel.setBounds(33, 11, 320, 300);
		panel.setBackground(new Color(200, 200, 200));
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		lblNewUserEmail = new JLabel("E-mail");
		lblNewUserEmail.setBounds(28, 11, 46, 14);
		panel.add(lblNewUserEmail);

		lblNewUserPhone = new JLabel("Phone");
		lblNewUserPhone.setBounds(28, 36, 46, 14);
		panel.add(lblNewUserPhone);

		lblNewUserFirstName = new JLabel("First name");
		lblNewUserFirstName.setBounds(28, 61, 62, 14);
		panel.add(lblNewUserFirstName);

		lblNewUserLastName = new JLabel("Last name");
		lblNewUserLastName.setBounds(28, 86, 62, 14);
		panel.add(lblNewUserLastName);

		textFieldNewUserEmail = new JTextField(user.getEmail());
		textFieldNewUserEmail.setBounds(105, 8, 86, 20);

		textFieldNewUserEmail.setColumns(10);
		if (!user.getEmail().equals("")) {
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
							getUsersAndShow();
					}

				}
			});
			panel.add(deleteUser);

		} else {
			panel.add(textFieldNewUserEmail);
		}

		textFieldNewUserPhone = new JTextField(user.getPhone());
		textFieldNewUserPhone.setBounds(105, 33, 120, 20);
		panel.add(textFieldNewUserPhone);
		textFieldNewUserPhone.setColumns(10);

		textFieldNewUserFirstName = new JTextField(user.getFirstName());
		textFieldNewUserFirstName.setBounds(105, 58, 120, 20);
		panel.add(textFieldNewUserFirstName);
		textFieldNewUserFirstName.setColumns(10);

		textFieldNewUserLastName = new JTextField(user.getLastName());
		textFieldNewUserLastName.setBounds(105, 83, 120, 20);
		panel.add(textFieldNewUserLastName);
		textFieldNewUserLastName.setColumns(10);

		JButton btnSetUserRights = new JButton("Jogok szerkesztése");
		btnSetUserRights.setBounds(20, 160, 150, 25);
		panel.add(btnSetUserRights);
		btnSetUserRights.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userRightsShow(user);

			}
		});

		JButton btnCancelSaveUser = new JButton("Mégse");
		btnCancelSaveUser.setBounds(20, 270, 90, 25);
		btnCancelSaveUser.setForeground(new Color(200, 0, 0));
		btnCancelSaveUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getUsersAndShow();

			}
		});
		panel.add(btnCancelSaveUser);

		JButton btnSaveUser = new JButton("Mentés");
		btnSaveUser.setBounds(221, 270, 90, 25);
		btnSaveUser.setForeground(new Color(0, 200, 0));
		btnSaveUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (user.getEmail().equals("")) {
					ExerciseUser newUser = new ExerciseUser(textFieldNewUserPhone.getText(),
							textFieldNewUserFirstName.getText(), textFieldNewUserLastName.getText(),
							textFieldNewUserEmail.getText(), 1);
					createNewUserInDb(newUser);
				} else {
					user.setFirstName(textFieldNewUserFirstName.getText());
					user.setLastName(textFieldNewUserLastName.getText());
					user.setPhone(textFieldNewUserPhone.getText());
					updateUserData(user);
				}

			}
		});
		panel.add(btnSaveUser);

	}

	private void updateUserData(ExerciseUser user) {
		if (DBHAndler2.UpdateUserData(user)) {
			getUsersAndShow();
		} else {
			System.err.println("hiba update közben");
		}
	}

	private void createNewUserInDb(ExerciseUser user) {
		if (DBHAndler2.saveNewUserInDb(user)) {
			getUsersAndShow();
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
		
		UserRight userRights = DBHAndler2.getUserRightsFromDB(user.getUserId());
		panelUserRights = new JPanel();
		panelUserRights.setBounds(33, 11, 320, 300);
		panelUserRights.setBackground(Color.lightGray);
		getContentPane().add(panelUserRights);
JCheckBox rightReservesList = new JCheckBox("Foglalás lista");
rightReservesList.setSelected(userRights.isReserveList());
rightReservesList.setBounds(10, 10, 100, 20);

JCheckBox rightCreateNewUser = new JCheckBox("Új felhasználó");
rightCreateNewUser.setSelected(userRights.isCreateUser());
rightCreateNewUser.setBounds(10, 45, 100, 20);


panelUserRights.add(rightReservesList);
panelUserRights.add(rightCreateNewUser);

	}
}
