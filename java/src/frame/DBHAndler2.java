package frame;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBHAndler2 {
	private static Connection connectToDb() {
		Connection con = null;
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
		if (con != null) {
			try {
				String query = "select users.email,phone,first_name,last_name from user_data inner join users on users.id=user_data.user_id";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next())
					users.add(new ExerciseUser(rs.getString("phone"), rs.getString("first_name"),
							rs.getString("last_name"), rs.getString("email")));

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("hiba...");
		}

		return users;
	}

	public static void getPhone(String email) {

		Connection con = connectToDb();
		if (con != null) {
			try {
				String query = "select * from user_data where id=(select id from users where users.email='" + email
						+ "')";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next())
					System.out.println(rs.getString("phone") + "  ");

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("hiba...");
		}

	}

	public static ArrayList<Reserve> getReserved() {
		ArrayList<Reserve> reserveList = new ArrayList<Reserve>();
		Connection con = connectToDb();
		if (con != null) {
			try {
				String query = "select * from reserve inner join user_data ON reserve.user_id=user_data.user_id";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next())
					System.out.println(rs.getString("user_id") + "  " + rs.getString("first_name"));

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("hiba...");
		}
		return reserveList;
	}

	public static boolean checkLogin(String email, String password) {
		boolean logged = false;
		Connection con = connectToDb();
		if (con != null) {
			try {
				String query = "select email,password from users where email=? and password=?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, email);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					
					logged = (email.equals(rs.getString("email")) && password.equals(rs.getString("password"))) ? true
							: false;
				} else {
					System.err.println("hiba a loginnal");
				}
				con.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("hiba...");
		}
		return logged;
	}

	public static boolean saveNewUserInDb(ExerciseUser newUser) {
		Connection con = connectToDb();
		ResultSet rs;
		long id = 0;
		boolean success=false;
		if (con != null) {
			try {
				String query = "INSERT INTO `users` (`id`, `email`, `password`, `remember_token`, `created_at`, `updated_at`) VALUES (NULL, '"
						+ newUser.getEmail() + "', 'CTfo3', NULL, NULL, NULL);";
				Statement stmt = con.createStatement();

				stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getLong(1);
				}

				query = "insert into user_data (id,user_id,first_name,last_name,phone) values(null," + id + ",'"
						+ newUser.getFirstName() + "','" + newUser.getLastName() + "','" + newUser.getPhone() + "')";
				stmt = con.createStatement();
				stmt.executeUpdate(query);

				con.close();
				success=true;
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("hiba...");
		}
		return success;

	}
	
	public static boolean UpdateUserData(ExerciseUser user) {
		Connection con = connectToDb();
		boolean success=false;
		if (con != null) {
			try {
				System.out.println(" db handler user:"+user.getUserName());
				String query = "UPDATE `user_data` inner join users on users.id=user_data.user_id SET `first_name` = ?, `last_name`=?,`phone`=? WHERE users.email = ?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setString(1, user.getFirstName());
			      preparedStmt.setString(2, user.getLastName());
			      preparedStmt.setString(3, user.getPhone());
			      preparedStmt.setString(4, user.getEmail());
			      // 
			     
			      preparedStmt.executeUpdate();
			      
			      con.close();
			      success=true;
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("hiba...");
		}
		return success;
	}
}
