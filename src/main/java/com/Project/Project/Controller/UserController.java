package com.Project.Project.Controller;

import java.io.Console;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.Project.POJO.Professor;
import com.Project.Project.POJO.User;
import com.Project.Project.POJO.UserRequest;
import com.Project.Project.POJO.UserResponse;
import com.Project.Project.Repository.ProfessorRepository;
import com.Project.Project.Repository.StudentRepository;
import com.Project.Project.Service.impl.UserServiceImpl;
import com.Project.Project.Utility.JWTUtil;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService ;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	ProfessorRepository professorRepository;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveuser(@RequestBody User user) {
		Integer id=userService.saveUser(user);
		String body="User '"+id+"' saved";

		return new ResponseEntity<String>(body,HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest){
		Boolean authenticated=false;
		String token,msg;	
		authenticated=userService.login(userRequest);
		if(authenticated)
		{token=jwtUtil.generateToken(userRequest.getUsername());
		msg="Success";
		}
		else {
		token=null;
		msg="Failure";
		
		}
		return ResponseEntity.ok(new UserResponse(token,msg));

	}
	
	@GetMapping("/getRole")
	public String getRolel(HttpServletRequest httpServletRequest){
		if(httpServletRequest.isUserInRole("ROLE_ADMIN"))
			return "ROLE_ADMIN";
		else if(httpServletRequest.isUserInRole("ROLE_Student"))
			return "ROLE_Student";
		else if(httpServletRequest.isUserInRole("ROLE_Professor"))
			return "ROLE_Professor";
		return "role not found";
	}

}
