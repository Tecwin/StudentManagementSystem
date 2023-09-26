package com.Project.Project.Service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Project.Project.POJO.Management;
import com.Project.Project.POJO.Professor;
import com.Project.Project.POJO.Student;
//import com.Project.Project.POJO.User;
import com.Project.Project.POJO.UserRequest;
import com.Project.Project.Repository.ManagementRepository;
import com.Project.Project.Repository.ProfessorRepository;
import com.Project.Project.Repository.StudentRepository;
//import com.Project.Project.Repository.UserRepository;
import com.Project.Project.Service.IUserService;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	
	//@Autowired
	//private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	

	@Autowired
	private ManagementRepository managementRepository;
	/*
	@Override
	public Integer saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Integer userId=userRepository.save(user).getId();
		
	 return userId;
		
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}
*/
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Set<String> roles=new HashSet<String> ();
		Optional<Professor> professorOptional=Optional.ofNullable(professorRepository.findByUserName(username));
		Optional<Student> studentOptional=Optional.ofNullable(studentRepository.findByUserName(username));
		Optional<Management> managementOptional=managementRepository.findByUserName(username);
		if(professorOptional.isEmpty()&&studentOptional.isEmpty()&&managementOptional.isEmpty())
			throw new UsernameNotFoundException("User does not exist");
		else if(!professorOptional.isEmpty()) {
		Professor professor=professorOptional.get();

				roles.add(professor.getRoles().getRole());
		return new org.springframework.security.core.userdetails.User(
				username,
				professor.getPassword(),
				roles.stream()
				.map(role->new SimpleGrantedAuthority(role))
				.collect(Collectors.toList())
				);
		}
		else if(!managementOptional.isEmpty()) {
			Management management=managementOptional.get();

					roles.add(management.getRoles().getRole());
			return new org.springframework.security.core.userdetails.User(
					username,
					management.getPassword(),
					roles.stream()
					.map(role->new SimpleGrantedAuthority(role))
					.collect(Collectors.toList())
					);
			}
		else {
			Student student=studentOptional.get();
			roles.add(student.getRoles().getRole());
			return new org.springframework.security.core.userdetails.User(
					username,
					student.getPassword(),
					roles.stream()
					.map(role->new SimpleGrantedAuthority(role))
					.collect(Collectors.toList())
					);
		}
	}

	@Override
	public Boolean login(UserRequest  userRequest ) {
		Boolean authenticatedBoolean=false;
		Optional<Professor> professorOptional=Optional.ofNullable(professorRepository.findByUserName(userRequest.getUsername()));
		Optional<Student> studentOptional=Optional.ofNullable(studentRepository.findByUserName(userRequest.getUsername()));
		Optional<Management> managementOptional=managementRepository.findByUserName(userRequest.getUsername());
		if(!professorOptional.isEmpty())
		{
			
			if(bCryptPasswordEncoder.matches(userRequest.getPassword(), professorOptional.get().getPassword()))
				authenticatedBoolean=true;
		}
		else if(!studentOptional.isEmpty()) {
			if(bCryptPasswordEncoder.matches(userRequest.getPassword(),studentOptional.get().getPassword()))
				authenticatedBoolean=true;
		}
		else if(!managementOptional.isEmpty()) {
			if(bCryptPasswordEncoder.matches(userRequest.getPassword(),managementOptional.get().getPassword()))
				authenticatedBoolean=true;
		}
		return authenticatedBoolean;
	}


}
