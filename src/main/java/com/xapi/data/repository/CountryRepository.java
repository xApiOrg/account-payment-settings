package com.xapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xapi.data.model.Country;

public interface CountryRepository extends JpaRepository<Country, String>  {
	public Country findByCode(String code);
	public Country findByName(String name);
	public Country findByCurrency(String currency);
}
