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
	public Welcome() {
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 354, 208);
		getContentPane().add(scrollPane);
		ArrayList<ExerciseUser> users = DBHAndler2.getAllFromDB();
		
		
		
		JList list = new JList(users.toArray());
		JList<Reserve> reserveList ;
		
		
		scrollPane.setViewportView(list);
		
		JMenuBar menuBar = new JMenuBar();
		scrollPane.setColumnHeaderView(menuBar);
		
		JMenu mnNewMenu = new JMenu("Foglalás");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Foglalás lista");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBHAndler2.getReserved();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
