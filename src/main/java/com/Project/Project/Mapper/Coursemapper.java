package com.Project.Project.Mapper;

import org.springframework.stereotype.Service;

import com.Project.Project.DTO.CourseDTO;
import com.Project.Project.POJO.Course;

@Service
public class Coursemapper {
	
	public Course toCourse(CourseDTO courseDTO) {
		Course course=new Course();
		if(!courseDTO.getTitle().isEmpty())
			course.setTitle(courseDTO.getTitle());
		return course;
	}

}
