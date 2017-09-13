package com.xapi.payment.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
//	JpaRepository, CrudRepository
	public List<Payment> findByUserId(Long userId);
}
