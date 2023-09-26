package com.Project.Project.Service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.Project.DTO.CourseDTO;
import com.Project.Project.DTO.CustomResponseDTO;
import com.Project.Project.DTO.ProfessorDTO;
import com.Project.Project.Mapper.Coursemapper;
import com.Project.Project.Mapper.ProfessorMapper;
import com.Project.Project.POJO.Course;
import com.Project.Project.POJO.Professor;
import com.Project.Project.POJO.Roles;
import com.Project.Project.Repository.CourseRepository;
import com.Project.Project.Repository.ProfessorRepository;
import com.Project.Project.Repository.RoleRepository;
import com.Project.Project.Service.IProfessorService;
@Service
public class ProfessorServiceImpl implements IProfessorService {

	@Autowired
	ProfessorRepository professorRepository;
	
	@Autowired
	ProfessorMapper professorMapper;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	Coursemapper coursemapper;
	
	
	@Override
	public Integer saveProfessor(ProfessorDTO professorDTO) {
		Optional<Roles> roleOptional=roleRepository.findByRole("ROLE_Professor");
		Professor professor=professorMapper.toProfessor(professorDTO);	
		professor.setRoles(roleOptional.get());
		return professorRepository.save(professor).getId();
	}
	

	@Override
	public CustomResponseDTO saveCourse(CourseDTO courseDTO, String userName) {
		Course course=coursemapper.toCourse(courseDTO);
		course.setProfessor(professorRepository.findByUserName(userName));
		courseRepository.save(course);
		CustomResponseDTO customResponseDTO=new CustomResponseDTO();
		customResponseDTO.setMessage("Success");
		customResponseDTO.setStatus_Code(200);
		return customResponseDTO;
	}


	@Override
	public CustomResponseDTO deleteProfessor(Integer id) {
		Optional<Professor> professor=professorRepository.findById(id);
		professorRepository.delete(professor.get());
		CustomResponseDTO customResponseDTO=new CustomResponseDTO();
		customResponseDTO.setMessage("Success");
		customResponseDTO.setStatus_Code(200);
		return customResponseDTO;
	}
	
	
}
