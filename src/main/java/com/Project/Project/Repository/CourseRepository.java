package com.Project.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.Project.POJO.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
