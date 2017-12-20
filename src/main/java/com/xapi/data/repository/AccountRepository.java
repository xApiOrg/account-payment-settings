package com.xapi.data.repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xapi.data.model.Account;
import com.xapi.data.model.AccountType;

public interface AccountRepository extends JpaRepository<Account, Long> {
	public static final Collection<AccountType> PAYABLE_ACCOUNT_TYPES = 
		Arrays.asList(new AccountType[] { AccountType.CURRENT, AccountType.FX, AccountType.DEBIT, AccountType.CASH });

	public Set<Account> findByUserId(Long userId);
	public Account findById(Long id);
	public Account findByUserIdAndId(Long userId, Long id);
	public Set<Account> findByUserIdAndTypeIn(Long userId, Collection<AccountType> types);
}
