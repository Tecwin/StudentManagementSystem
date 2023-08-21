package com.Project.Project.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Project.Project.DTO.StudentDTO;
import com.Project.Project.POJO.Student;
@Service
public class StudentMapper {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Student toStudent(StudentDTO studentDTO) {
		Student student=new Student();
		if(studentDTO.getName()!=null)
			student.setName(studentDTO.getName());
		if(studentDTO.getAddress()!=null)
			student.setAddress(studentDTO.getAddress());
		if(studentDTO.getName()!=null)
			student.setName(studentDTO.getName());
		if(!studentDTO.getPhoneNo().isEmpty())
			student.setPhoneNo(studentDTO.getPhoneNo());
		if(!studentDTO.getUserName().isEmpty())
			student.setUserName(studentDTO.getUserName());
		if(!studentDTO.getPassword().isEmpty())
			student.setPassword(bCryptPasswordEncoder.encode(studentDTO.getPassword()));
		return student;
		
	}
	
}
