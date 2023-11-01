package frame;



public class ExerciseUser {
	private String phone;
	private String firstName;
	private String lastName;
	private String email;
	
	public ExerciseUser(String phone, String firstName, String lastName,String email) {
		super();
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email=email;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + ", telefon: " + phone;
	}
	
	public String getUserName() {
		return firstName + " " + lastName ;
	}
	
	public String getUserPhone() {
		return phone;
	}
	
	public String getUserEmail() {
		
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
	
	

}
