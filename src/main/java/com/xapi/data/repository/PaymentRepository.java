package com.xapi.data.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xapi.data.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
//	JpaRepository, CrudRepository
	public Set<Payment> findByUserId(Long userId);
	public Set<Payment> findByUserIdAndPlaced(Long userId, Boolean placed);
	public Payment findById(Long Id);
}
