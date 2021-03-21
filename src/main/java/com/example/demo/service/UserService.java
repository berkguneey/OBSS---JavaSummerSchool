package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public interface UserService {
	
	public List<User> getUsers();
	
	public User getUserById(int id);
	
	public Boolean insertUser(User u);
	
	public Boolean updateUser(int id, User u);
	
	public Boolean deleteUser(int id);
	
	public int getUserIdByEmail(String email);
	
	public List<User> getUserByName(String username);
	

}
