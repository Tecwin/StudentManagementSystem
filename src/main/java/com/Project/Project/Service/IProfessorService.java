package com.Project.Project.Service;

import com.Project.Project.DTO.CourseDTO;
import com.Project.Project.DTO.CustomResponseDTO;
import com.Project.Project.DTO.ProfessorDTO;

public interface IProfessorService {
	Integer saveProfessor(ProfessorDTO professorDTO);
	CustomResponseDTO saveCourse(CourseDTO courseDTO,String userName);
	CustomResponseDTO deleteProfessor(Integer id);
}
