package com.Project.Project.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.Project.DTO.CourseDTO;
import com.Project.Project.DTO.CustomResponseDTO;
import com.Project.Project.DTO.ProfessorDTO;
import com.Project.Project.Service.impl.ProfessorServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	ProfessorServiceImpl professorServiceImpl;
	
	@PostMapping("/save")
	public String saveProfessor(@RequestBody ProfessorDTO professorDTO) {
		Integer id=professorServiceImpl.saveProfessor(professorDTO);
		return "Professor "+id+" Saved";
	}
	
	@PostMapping("/saveCourse")
	public CustomResponseDTO saveCoursse(@RequestBody CourseDTO courseDTO,Principal principal,HttpServletRequest httpServletRequest) {
		System.out.println("***********");
		boolean flag=httpServletRequest.isUserInRole("ROLE_Professor");
		CustomResponseDTO customResponseDTO=null;
		String userName=principal.getName();
		customResponseDTO=professorServiceImpl.saveCourse(courseDTO,userName);
		
		return customResponseDTO;
	}

	@DeleteMapping("/delete/{id}")
	public CustomResponseDTO deleteProfessor(@PathVariable Integer id) {
		CustomResponseDTO customResponseDTO=professorServiceImpl.deleteProfessor(id);
		return customResponseDTO;
	}
}
