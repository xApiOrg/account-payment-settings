package com.xapi.data.repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xapi.data.model.Account;
import com.xapi.data.model.AccountType;
import com.xapi.data.model.Payment;

public interface AccountRepository extends JpaRepository<Account, Long> {
	public static final Collection<AccountType> PAYABLE_ACCOUNT_TYPES = 
		Arrays.asList(new AccountType[] { AccountType.CURRENT, AccountType.FX, AccountType.DEBIT, AccountType.CASH });

	public List<Account> findByUserId(Long userId);
	public List<Account> findByAccountId(Long accountId);
	public List<Account> findByUserIdAndAccountId(Long userId, Long accountId);
	public List<Account> findByUserIdAndTypeIn(Long userId, Collection<AccountType> types);
}
