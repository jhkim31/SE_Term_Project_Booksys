package booksys.application.domain;

public class User {
	private String userId;
	private String pw;
	private String userName;
	
	public User(String userId, String pw, String userName) 
	{
		this.userId = userId;
		this.pw = pw;
		this.userName = userName;
	}
	
	public String getUserId()
	{
		return this.userId;
	}
	
	public String getPw() 
	{
		return this.pw;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
}
