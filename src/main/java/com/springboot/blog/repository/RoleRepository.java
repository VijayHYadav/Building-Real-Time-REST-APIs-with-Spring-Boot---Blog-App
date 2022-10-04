package com.springboot.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import com.springboot.blog.entity.Role;

public interface RoleRepository extends JpaRepository<User, Long> {
	Optional<Role> findByName(String name);
}
