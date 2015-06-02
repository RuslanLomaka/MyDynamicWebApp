package user;

public class Student extends AbstractUser {
	public Student(String FirstName, String LastName, String login, String email, String CreationDateTime){
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.login = login;
		this.email = email;
		this.CreationDateTime = CreationDateTime;
		
	}
}
