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

public class Welcome extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private ArrayList<ExerciseUser> users;
	public Welcome() {
		getContentPane().setLayout(null);
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
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Jogok állítása");
		mnNewMenu_1.add(mntmNewMenuItem_3);
	}
	private void getUsersAndShow() {
		this.users = DBHAndler2.getAllFromDB();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 354, 208);
		getContentPane().add(scrollPane);
		JList<ExerciseUser> list;
		list=new JList(users.toArray());
		scrollPane.setViewportView(list);
	}
}
