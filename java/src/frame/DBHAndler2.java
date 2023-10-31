package frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			 String query = "select users.email,phone,first_name,last_name from user_data inner join users on users.id=user_data.user_id";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next())
					users.add(new ExerciseUser(rs.getString("phone"), rs.getString("first_name"), rs.getString("last_name"),rs.getString("email")));
			
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

	public static ArrayList<Reserve> getReserved() {
ArrayList<Reserve> reserveList = new ArrayList<Reserve>();
		Connection con = connectToDb();
	 if(con!=null) {
		 try  {
			 String query = "select * from reserve inner join user_data ON reserve.user_id=user_data.user_id";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next())
					System.out.println(rs.getString("user_id") + "  " + rs.getString("first_name"));
			
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
	 }else {
		 System.err.println("hiba...");
	 }
	 return reserveList;
	}
	
	public static boolean checkLogin(String email, String password) {
		boolean logged = false;
		Connection con = connectToDb();
		 if(con!=null) {
			 try  {
				 String query = "select email,password from users where email=? and password=?";
					PreparedStatement stmt = con.prepareStatement(query);
					stmt.setString(1, email);
					stmt.setString(2, password);
					ResultSet rs = stmt.executeQuery();
					if (rs.next()) {
						System.out.println("Siker");
						logged = (email.equals(rs.getString("email"))&&password.equals(rs.getString("password")))?true:false;
					}else {
						System.err.println("hiba a loginnal");
					}
				
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
		 }else {
			 System.err.println("hiba...");
		 }
		return logged;
	}
}
