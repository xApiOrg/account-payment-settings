package com.xapi.account.service;

import java.util.List;

import com.xapi.data.model.Account;
import com.xapi.data.model.Payee;

public interface AccountService {
	public List<Account> getAll(Long userId);
	public Account getAccountById(Long id);
	public Account getUserAccountById(Long userId, Long id);
	public List<Account> getAllPayableAccounts(Long userId);
	public List<Payee	> getUserPayeeAccounts(Long userId);
}
