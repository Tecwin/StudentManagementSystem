package com.Project.Project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Project.POJO.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
 
	public Professor findByUserName(String userName);
}
