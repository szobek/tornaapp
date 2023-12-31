package frame;

import java.sql.*;
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

		Connection con = null;
		try {
			con = connectToDb();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		ArrayList<ExerciseUser> users = new ArrayList<ExerciseUser>();
		if (con != null) {
			try {
				String query = "select users.id,users.email,phone,first_name,last_name,user_rights.newuser,user_rights.listreserves from user_data inner join users on users.id=user_data.user_id inner join user_rights on user_rights.user_id=users.id where users.deleted=0";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					users.add(new ExerciseUser(rs.getString("phone"), rs.getString("first_name"),
							rs.getString("last_name"), rs.getString("email"),rs.getInt("id"),
							new UserRight(rs.getInt("id"),rs.getBoolean("listreserves"),rs.getBoolean("newuser"))));
				}
					

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

	public static ArrayList<Reserve> getAllReserved() {
		ArrayList<Reserve> reserveList = new ArrayList<Reserve>();
		Connection con = connectToDb();
		if (con != null) {
			try {
				String query = "select * from reserve inner join user_data ON reserve.user_id=user_data.user_id";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next())
					reserveList.add(new Reserve(rs.getDate("from_date"), rs.getDate("to_date"), rs.getTime("from_time"), rs.getTime("to_time"),rs.getInt("user_id")));

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("hiba...");
		}
		return reserveList;
	}

	public static ExerciseUser checkLogin(String email, String password) {
		ExerciseUser user = null;
		Connection con = null;
		try {
			con = connectToDb();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		
		String query ;
		if (con != null) {
			try {
				query = "select users.id,users.email,phone,first_name,last_name,user_rights.newuser,user_rights.listreserves from users inner join user_data on users.id=user_data.user_id inner join user_rights on user_rights.user_id=users.id where email=? and password=?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, email);
				stmt.setString(2, password);
				
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					
					user = new ExerciseUser(rs.getString("phone"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("id"), new UserRight(rs.getInt("id"),rs.getBoolean("listreserves"),rs.getBoolean("newuser")));
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
		return user;
	}
	
	
	public static boolean saveNewUserInDb(ExerciseUser newUser) {
		Connection con = null;
		try {
			con = connectToDb();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
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

				query = "insert into user_rights (id,user_id,newuser,listreserves) values(null," + id + ",0,0)";
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
		Connection con = null;
		try {
			con = connectToDb();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		boolean success=false;
		if (con != null) {
			try {
				
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
	
	public static boolean deleteUser(ExerciseUser user) {

		Connection con = null;
		try {
			con = connectToDb();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		boolean success=false;
		if (con != null) {
			try {
				
				String query = "UPDATE `user_data` inner join users on users.id=user_data.user_id SET `first_name` = ?, `last_name`=?,`phone`=? WHERE users.email = ?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setString(1, "");
			      preparedStmt.setString(2, "");
			      preparedStmt.setString(3, "");
			      preparedStmt.setString(4, user.getEmail());
			       
			     
			      preparedStmt.executeUpdate();
			      
			      
			      query = "UPDATE `users` set deleted=1 WHERE users.email = ?";
					
					preparedStmt = con.prepareStatement(query);
				      preparedStmt.setString(1, user.getEmail());
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
		return success;	}
	public static String getNameById(int id) {
		String name="";
		Connection con = null;
		try {
			con = connectToDb();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		if (con != null) {
			try {
				String query = "select * from user_data where user_id=?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, id);
				
				ResultSet rs = stmt.executeQuery();
					name=(rs.next())?rs.getString("first_name") + "  "+rs.getString("last_name"):"";

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("hiba...");
		}		
		
		return name;
	}
	
	public static UserRight getUserRightsFromDB(int id) {
		UserRight rights=new UserRight();
		Connection con = null;
		try {
			con = connectToDb();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		if (con != null) {
			try {
				String query = "select * from user_rights where user_id=?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, id);
				
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					rights=new UserRight(id, rs.getBoolean("listreserves"), rs.getBoolean("newuser"));
				}
				
					

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("hiba...");
		}		
		
		return rights;
	}
	
	public static boolean saveUserRightsInDB(ExerciseUser user) {
		Connection con = null;
		try {
			con = connectToDb();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		boolean success=false;
		if (con != null) {
			try {
				
				String query = "UPDATE `user_rights` set newuser=?, listreserves=? WHERE user_id= ?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setBoolean(1,user.getUserRight().isCreateUser() );
			      preparedStmt.setBoolean(2, user.getUserRight().isReserveList());
			      preparedStmt.setInt(3, user.getUserId());
			      
			       
			     
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
