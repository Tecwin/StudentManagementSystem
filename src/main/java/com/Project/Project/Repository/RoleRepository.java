package com.Project.Project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Project.POJO.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
	Optional<Roles> findByRole(String role);

}
