package com.xapi.account.service;

import java.util.Collection;
import java.util.List;

import com.xapi.data.model.Account;

public interface AccountService {
	public List<Account> getAll(Long userId);
	public Account getAccountById(Long id);
	public Account getUserAccountById(Long userId, Long id);
	public List<Account> getAllPayableAccounts(Long userId);
}
