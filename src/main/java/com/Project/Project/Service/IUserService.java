package com.Project.Project.Service;

import java.util.Optional;

//import com.Project.Project.POJO.User;
import com.Project.Project.POJO.UserRequest;

public interface IUserService {

/*	Integer saveUser(User user);
	Optional<User> findByUsername(String username);*/
	Boolean login(UserRequest userRequest);
	
}
