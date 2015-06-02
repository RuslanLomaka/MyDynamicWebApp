package user;

public abstract class AbstractUser {
	
	String FirstName;
	String LastName;
	String login;
	String email;
	String CreationDateTime;
	
	public String getAccountData(){
		
		return (FirstName+" "+LastName+" "+login+" "+email+" "+CreationDateTime);
	};
	
	public void changePassword(){
		
	};
	
	public void changeFirstName(){
	
	};
	
	public void changeLastName(){
		
	};
	
	public void changeEmail(){
		
	};
}
