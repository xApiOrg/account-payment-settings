package com.xapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xapi.data.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
	public Currency findByIso(String isoCode);
	public Currency findByName(String name);
}
