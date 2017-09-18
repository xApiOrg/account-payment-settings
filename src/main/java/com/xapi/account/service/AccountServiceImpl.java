package com.xapi.account.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xapi.data.model.Account;
import com.xapi.data.repository.AccountRepository;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired private AccountRepository accountRepository;

	@Override
	public List<Account> getAll(Long userId) {
		return accountRepository.findByUserId(userId);
	}

	@Override
	public Account getAccountById(Long id) {
		return accountRepository.findById(id);
	}

	@Override
	public Account getUserAccountById(Long userId, Long id) {
		return accountRepository.findByUserIdAndId(userId, id);
		
	}
	
	@Override
	public List<Account> getAllPayableAccounts(Long userId) {
		return accountRepository.findByUserIdAndTypeIn(userId, AccountRepository.PAYABLE_ACCOUNT_TYPES);
	}
}
