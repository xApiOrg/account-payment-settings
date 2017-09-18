package com.xapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xapi.data.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findById(Long Id);
}
