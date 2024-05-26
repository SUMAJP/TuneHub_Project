package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	UserRepository userrepository;
	
	public String addUser(User user) {
		userrepository.save(user);
		return "user addes syccessfully";
		
	}

	
	
	
	//logic to check the duplicate
	public boolean emailExists(String email) {
		if(userrepository.findByEmail(email)!=null) {
			return true;
		}
		else {
			return false;
		}
	}


	
	
	
	//logic to validate user
	public boolean validateUser(String email, String password) {
		//from db got 1 user record
		User user=userrepository.findByEmail(email);
		
		//from user record get password
		String dbpwd=user.getPassword();
		if(password.equals(dbpwd)) {
			return true;
		}
		else {
			return false;
		}
	}



	
	public String getRole(String email) {
		User user = userrepository.findByEmail(email);
		return user.getRole();
	}
	
	
	public User getUser(String email) {
		return userrepository.findByEmail(email);
	}
	
	//to updatte isprimium value 
	public void updateUser(User user) {
		userrepository.save(user);
	}
}
