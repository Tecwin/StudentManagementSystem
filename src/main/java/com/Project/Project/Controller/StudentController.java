package com.Project.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.Project.DTO.StudentDTO;
import com.Project.Project.Service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentServiceImpl studentServiceImpl;
	
	@PostMapping("/save")
	public String saveStudent(@RequestBody StudentDTO studentDTO) {
	Integer id=studentServiceImpl.saveStudent(studentDTO);
	return "Student "+id+" Saved";
	}

}
