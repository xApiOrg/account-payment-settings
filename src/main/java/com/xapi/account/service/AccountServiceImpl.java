package com.xapi.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xapi.data.model.Account;
import com.xapi.data.model.Payee;
import com.xapi.data.repository.AccountRepository;
import com.xapi.data.repository.PayeeRepository;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired private AccountRepository accountRepository;
	@Autowired private PayeeRepository payeeRepository;
	
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

	@Override
	public List<Payee> getUserPayeeAccounts(Long userId) {
		return payeeRepository.findByUserId(userId);
	}

	@Override
	public Payee getPayeeByIdAndUserId(Long userId, Long payeeId) {
		return payeeRepository.findPayeeByIdandUserId( payeeId, userId);
	}

	@Override
	public Payee createNewPayee(Object payee, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
