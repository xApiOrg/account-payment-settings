package com.xapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xapi.data.model.Payee;

public interface PayeeRepository extends JpaRepository<Payee, Long> {

}
