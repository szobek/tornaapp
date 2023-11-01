package frame;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;

import welcome.WelcomeFunctions;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

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

	public Welcome() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/images/vt_logo.png")));

		setTitle("Villámtánc");
		getContentPane().setLayout(null);
		;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
				DBHAndler2.getReserved();
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
				createNewUserInputshow();

			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Jogok állítása");
		mnNewMenu_1.add(mntmNewMenuItem_3);
	}

	private void getUsersAndShow() {
		hideAllComponent();
		this.users = DBHAndler2.getAllFromDB();
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 20, 400, 150);
		getContentPane().add(scrollPane2);

		String[] columnNames = { "Név", "Telefon", "E-mail" };
		tableData = new Object[users.size()][3];
		createRows();
		DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames);
		table = new JTable(tableModel);

		table.setBackground(new Color(153, 204, 255));
		table.setForeground(new Color(0, 102, 153));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		scrollPane2.setViewportView(table);
		// removeAllRows(table);

		JButton btnDeleteAllRows = new JButton("minden sor törlés");
		// getContentPane().add(btnDeleteAllRows);
		btnDeleteAllRows.setMnemonic('m');
		btnDeleteAllRows.setBounds(200, 200, 150, 20);
		btnDeleteAllRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAllRows(table);
				btnDeleteAllRows.setVisible(tableModel.getRowCount() > 0);
			}
		});

	}

	private void hideAllComponent() {
		if (scrollPane2 != null)
			scrollPane2.setVisible(false);
		if (panel != null)
			panel.setVisible(false);
	}

	private void createRows() {

		for (int i = 0; i < users.size(); i++) {
			tableData[i][0] = users.get(i).getUserName();

			tableData[i][1] = users.get(i).getUserPhone();
			tableData[i][2] = users.get(i).getUserEmail();

		}

	}

	private void createNewUserInputshow() {
		hideAllComponent();
		panel = new JPanel();
		panel.setBounds(33, 11, 320, 300);
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

		textFieldNewUserEmail = new JTextField();
		textFieldNewUserEmail.setBounds(105, 8, 86, 20);
		panel.add(textFieldNewUserEmail);
		textFieldNewUserEmail.setColumns(10);

		textFieldNewUserPhone = new JTextField();
		textFieldNewUserPhone.setBounds(105, 33, 86, 20);
		panel.add(textFieldNewUserPhone);
		textFieldNewUserPhone.setColumns(10);

		textFieldNewUserFirstName = new JTextField();
		textFieldNewUserFirstName.setBounds(105, 58, 86, 20);
		panel.add(textFieldNewUserFirstName);
		textFieldNewUserFirstName.setColumns(10);

		textFieldNewUserLastName = new JTextField();
		textFieldNewUserLastName.setBounds(105, 83, 86, 20);
		panel.add(textFieldNewUserLastName);
		textFieldNewUserLastName.setColumns(10);

		JButton btnCancelSaveUser = new JButton("Mégse");
		btnCancelSaveUser.setBounds(20, 270, 90, 25);
		btnCancelSaveUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getUsersAndShow();

			}
		});
		panel.add(btnCancelSaveUser);

		JButton btnSaveUser = new JButton("Mentés");
		btnSaveUser.setBounds(221, 272, 90, 25);
		btnSaveUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ExerciseUser newUser = new ExerciseUser(textFieldNewUserPhone.getText(),
						textFieldNewUserFirstName.getText(), textFieldNewUserLastName.getText(),
						textFieldNewUserEmail.getText());
				createNewUserInDb(newUser);
			}
		});
		panel.add(btnSaveUser);

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
}
