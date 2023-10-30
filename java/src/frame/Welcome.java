package frame;

import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class Welcome extends JFrame {
	public Welcome() {
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 354, 208);
		getContentPane().add(scrollPane);
		ArrayList<ExerciseUser> users = DBHAndler2.getAllFromDB();
		
		
		
		JList list = new JList(users.toArray());
		
		
		scrollPane.setViewportView(list);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
