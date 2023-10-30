package frame;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBHAndler2 {
	private static Connection  connectToDb() {
		Connection con =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tornaapp", "root", "");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	public static ArrayList<ExerciseUser> getAllFromDB() {
		
		Connection con = connectToDb();
		ArrayList<ExerciseUser> users = new ArrayList<ExerciseUser>();
	 if(con!=null) {
		 try  {
			 String query = "select phone,first_name,last_name from user_data inner join users on users.id=user_data.user_id";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next())
					users.add(new ExerciseUser(rs.getString("phone"), rs.getString("first_name"), rs.getString("last_name")));
			
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
	 }else {
		 System.err.println("hiba...");
	 }
		
		return users;
	}
	
	public static void getPhone(String email) {

		Connection con = connectToDb();
	 if(con!=null) {
		 try  {
			 String query = "select * from user_data where id=(select id from users where users.email='"+email+"')";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next())
					System.out.println(rs.getString("phone") + "  ");
			
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
	 }else {
		 System.err.println("hiba...");
	 }
		
	}

	public static void getReserved() {
		
	}
}
