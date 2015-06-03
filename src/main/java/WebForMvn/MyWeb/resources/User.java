package WebForMvn.MyWeb.resources;

public class User {
	@Override
	public String toString() {
		return "User [accountId=" + accountId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", login=" + login + ", email="
				+ email + ", roleId=" + roleId + "]";
	}

	private int accountId;
	private String firstName;
	private String lastName;
	private String login;
	private String email;
	private int roleId;
	
	

	public User(int accountId, String firstName, String lastName, String login,
			String email, int roleId) {
		super();
		this.accountId = accountId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.email = email;
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public int getAccountId() {
		return accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}

}
