package com.xapi.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xapi.data.model.Payee;
import com.xapi.data.model.User;

public interface PayeeRepository extends JpaRepository<User, Long> { //JpaRepository<Payee, Long>, 
	
	//@Query("select t from Test t join User u where u.username = :username")
	// @Query("SELECT u.tags FROM User u WHERE u.id = :id")
	// @Query("select sum(t.value) from Transactions t inner join t.customer c where c.id= ?1 and c.ageClass= ?2 ")

//	@Query("select p from Payee p join User u where u in p.users and u.id = :id")
	@Query("select p from Payee p join p.users u where u.id = :id")
	public List<Payee> findByUserId(@Param("id") Long id); // ( @Param("id") Long userId)

}
