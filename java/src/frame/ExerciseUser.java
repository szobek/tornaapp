package frame;



public class ExerciseUser {
	private String phone;
	private String firstName;
	private String lastName;
	
	public ExerciseUser(String phone, String firstName, String lastName) {
		super();
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "ExerciseUser [phone=" + phone + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
