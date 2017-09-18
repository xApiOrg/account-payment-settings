package com.xapi.account.service;

import java.util.Collection;
import java.util.List;

import com.xapi.data.model.Account;

public interface AccountService {
	public List<Account> getAll(Long userId);
	public Account getAccountById(Long accountId);
	public Account getUserAccountById(Long userId, Long accountId);
	public List<Account> getAllPayableAccounts(Long userId);
}
