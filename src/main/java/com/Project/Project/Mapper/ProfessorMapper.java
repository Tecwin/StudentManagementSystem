package com.Project.Project.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Project.Project.DTO.ProfessorDTO;
import com.Project.Project.DTO.StudentDTO;
import com.Project.Project.POJO.Professor;


@Service
public class ProfessorMapper {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Professor toProfessor(ProfessorDTO professorDTO) {
		Professor professor=new Professor();
		if(professorDTO.getName()!=null)
			professor.setName(professorDTO.getName());
		if(professorDTO.getAddress()!=null)
			professor.setAddress(professorDTO.getAddress());
		if(professorDTO.getName()!=null)
			professor.setName(professorDTO.getName());
		if(!professorDTO.getPhoneNo().isEmpty())
			professor.setPhoneNo(professorDTO.getPhoneNo());
		if(!professorDTO.getUserName().isEmpty())
			professor.setUserName(professorDTO.getUserName());
		if(!professorDTO.getPassword().isEmpty())
			professor.setPassword(bCryptPasswordEncoder.encode(professorDTO.getPassword()));
		return professor;
		
	}
	
}
