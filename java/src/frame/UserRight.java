package frame;

public class UserRight {
	private int userId;
	private boolean reserveList;
	private boolean createUser;
	
	
	
	public UserRight() {
		super();
		
	}
	public UserRight(int userId, boolean reserveList, boolean createUser) {
		super();
		this.userId = userId;
		this.reserveList = reserveList;
		this.createUser = createUser;
	}
	public int getUserId() {
		return userId;
	}
	public boolean isReserveList() {
		return reserveList;
	}
	public boolean isCreateUser() {
		return createUser;
	}
	@Override
	public String toString() {
		return "UserRight [userId=" + userId + ", reserveList=" + reserveList + ", createUser=" + createUser + "]";
	}
	
	

}
