package com.xapi.account.service;

import java.util.List;
import java.util.Set;

import com.xapi.data.model.Account;
import com.xapi.data.model.AccountDetails;
import com.xapi.data.model.Payee;

public interface AccountService {
	public static final String IBAN_VALIDATOR = "https://openiban.com/validate/"; // https://openiban.com/validate/IBAN_NUMBER?getBIC=true&validateBankCode=true
	
	// https://github.com/mangrep/ifsc-rest-api
	public static final String IFSC_VALIDATOR = "https://api.techm.co.in/api/v1/ifsc/"; // https://api.techm.co.in/api/v1/ifsc/SBIN0000138
	public static final String MICR_VALIDATOR = "https://api.techm.co.in/api/v1/micr/"; // https://api.techm.co.in/api/v1/micr/842002002
	
	public static final String ABA_RTN_VALIDATOR = "http://www.routingnumbers.info/api/name.json?rn="; // http://www.routingnumbers.info/api/name.json?rn=324377516
	
	public Set<Account> getAll(Long userId);
	public Account getAccountById(Long id);
	public Account getUserAccountById(Long userId, Long id);
	public Set<Account> getAllPayableAccounts(Long userId);
	public Set<Payee> getUserPayeeAccounts(Long userId);
	public Payee getPayeeByIdAndUserId(Long userId, Long payeeId);
	public Payee createNewPayee(Payee payee, Long userId);
	public Payee updatePayee(Payee payee, Long payeeId, Long userId);
	public Payee deletePayee(Long userId, Long payeeId);
	public Object validateAccount(String accountIdent, String type); // AccountDetails
}
