package com.techlook.mks.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.techlook.mks.dao.UserDAO;
import com.techlook.mks.model.User;

/**
 * Unit test for simple App.
 */
public class UserDAOImplTestCase {

	
	@Autowired
	static User user;

	@Autowired
	static UserDAO userDAO;

	@Autowired
	static AnnotationConfigApplicationContext context;

	// this method is used for initalizing
	@BeforeClass
	public static void initalize() {
		context = new AnnotationConfigApplicationContext();
		
		context.scan("com.techlook.mks");
		context.refresh();
		

		user = context.getBean(User.class);
		userDAO = (UserDAO) context.getBean("userDAO");

	}

	@Test
	public void insertPositiveTest() 
	{

		// positive testing for insert user
		user.setId(3);
		user.setFirstName("kunal");
		user.setLastName("kamal");
		user.setEmailId("kunal@gmail.com");
		user.setPassword("kunal");
		user.setUserName("kunal");
		userDAO.insert(user);
		assertEquals("Record Inserted!!", "Kunal", user.getFirstName());
		displayInsertRecord("Record Inserted:",user);

	}

	@Test
	public void getAllUserPositiveTest() {
		
		
		int usersCount = userDAO.getAllUsers().size();
		
		List<User> users=userDAO.getAllUsers();
		assertEquals("Fetched data!!", usersCount, userDAO.getAllUsers().size());
		displayAllUsers("List of All Registered Users",users);
		
		
	}

	@Test
	public void getAllUsersNegativeTest() {
		assertFalse("Empty", userDAO.getAllUsers().isEmpty());

	}
	
	
	@Test
	public void getUserByIdTest()
	{
		User user=userDAO.getUserById(4);
		assertEquals("Matched Successfully","Manoj",user.getFirstName());
		displayUserById("Single User",user);
	}
	
	
	@Test
	public void loginTest()
	{
		boolean flag=false;
		User user=userDAO.getUserById(4);
		User validUser=userDAO.login(user);
		String userName=validUser.getUserName();
		String password=validUser.getPassword();
		if(userName.equals("mitali") && password.equals("mitali"))
		{
			flag=true;
		}
		else
			flag=false;
		assertEquals("Retrived successfully",true,flag);
		dislayLogin("Login success!!",user);
	}
	

	public void displayInsertRecord(String testCaseName,User user)
	{
		System.out.println("Record Inserted");
		System.out.println("FirstName: "+user.getFirstName()+ " \n" + "LastName:"+user.getLastName()+ "\n"+"UserName:"+user.getUserName()+"\n"+"EmailId"+user.getEmailId()+"\n"+"Password"+user.getPassword());
		
	}
	
	public void displayAllUsers(String testCaseName,List<User> users)
	{
		System.out.println(testCaseName);
		
		Iterator i= users.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		
		
	}
	private void displayUserById(String testCaseName, User user) {
		System.out.println(testCaseName);
		System.out.println(user.getFirstName());
		
		
	}
	private void dislayLogin(String string, User user2) {
		
		System.out.println(string);
		System.out.println(user.getFirstName()+ "  is a valid user");
	
}
	
	
	
	
	
	

}
