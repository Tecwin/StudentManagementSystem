package com.Project.Project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Project.POJO.Management;

@Repository
public interface ManagementRepository extends JpaRepository<Management, Integer> {

	
	public Optional<Management> findByUserName(String username);
}
