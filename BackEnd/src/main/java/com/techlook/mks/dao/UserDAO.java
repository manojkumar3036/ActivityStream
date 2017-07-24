package com.techlook.mks.dao;

import java.util.List;

import com.techlook.mks.model.User;

public interface UserDAO {

	// for inserting user
	public User insert(User user);

	// for fetching a single user by its id
	public User getUserById(int id);

	// for fetching list of all users registered successfully
	public List<User> getAllUsers();

	// for user login
	public User login(User user);

}
