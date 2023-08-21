package com.Project.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Project.POJO.Professor;
import com.Project.Project.POJO.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	public Student findByUserName(String name);
}
