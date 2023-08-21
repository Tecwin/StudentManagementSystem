package com.Project.Project.Service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Project.Project.DTO.StudentDTO;
import com.Project.Project.Mapper.StudentMapper;
import com.Project.Project.POJO.Roles;
import com.Project.Project.POJO.Student;
import com.Project.Project.Repository.RoleRepository;
import com.Project.Project.Repository.StudentRepository;
import com.Project.Project.Service.IStudentService;
@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Integer saveStudent(StudentDTO studentDTO) {
		Optional<Roles> roleOptional=roleRepository.findByRole("ROLE_Student");
		Student student=studentMapper.toStudent(studentDTO);	
		student.setRoles(roleOptional.get());
		return studentRepository.save(student).getId();
	}

}
