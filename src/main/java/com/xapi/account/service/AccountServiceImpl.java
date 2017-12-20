package com.xapi.account.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xapi.data.model.Account;
import com.xapi.data.model.Payee;
import com.xapi.data.model.User;
import com.xapi.data.repository.AccountRepository;
import com.xapi.data.repository.PayeeRepository;
import com.xapi.data.repository.UserRepository;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired private AccountRepository accountRepository;
	@Autowired private PayeeRepository payeeRepository;
	@Autowired private UserRepository userRepository;
	
	private static final RestTemplate restTemplate = new RestTemplateBuilder().build();
	
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public static final Map<String,String> URLS = new HashMap(){{ 
		put("iban", AccountService.IBAN_VALIDATOR); put("ifsc", AccountService.IFSC_VALIDATOR);
		put("micr", AccountService.MICR_VALIDATOR); put("rtn", AccountService.ABA_RTN_VALIDATOR);}};
	
	@Override
	public Set<Account> getAll(Long userId) {
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
	public Set<Account> getAllPayableAccounts(Long userId) {
		return accountRepository.findByUserIdAndTypeIn(userId, AccountRepository.PAYABLE_ACCOUNT_TYPES);
	}

	@Override
	public Set<Payee> getUserPayeeAccounts(Long userId) {
		return payeeRepository.findByUserId(userId);
	}

	@Override
	public Payee getPayeeByIdAndUserId(Long userId, Long payeeId) {
		return payeeRepository.findPayeeByIdandUserId( payeeId, userId);
	}

	@Override
	public Payee createNewPayee(Payee payee, Long userId) {
		User user = userRepository.findById(userId);
		if( user == null )
			return null;
		
		Payee newPayee = payeeRepository.findByName(payee.getName());
		newPayee = newPayee == null? (Payee) payee: newPayee;
		if(newPayee == null || newPayee.getName() == null || newPayee.getName().isEmpty())
			return null;
		
		newPayee.getUsers().add(user);
		payeeRepository.save(newPayee);
		
		if(user.getPayees() == null)
			user.setPayees( new HashSet<>() );
		user.getPayees().add(newPayee);
		userRepository.save(user);
		return newPayee;
	}

	@Override
	public Payee updatePayee(Payee payeeDto, Long payeeId, Long userId) {
		Payee payee = payeeRepository.findPayeeByIdandUserId(payeeId, userId);
		
		if(payeeDto == null || payee == null)
			return null;		
		payeeDto.setId(payeeId);
		payeeRepository.save(payeeDto);
//		Map<String, Object> valuesMap = (LinkedHashMap<String, Object>) payeeObject;
//		for(Field field: payee.getClass().getDeclaredFields()){
//			field.
//			field.setAccessible(true); 
//			try {
//				field.set(payee, valuesMap.get(field.getName()));
//			} catch (IllegalArgumentException | IllegalAccessException e) {
//				e.printStackTrace();
//			}
//		}			
		
		return payeeRepository.findPayeeByIdandUserId(payeeId, userId);
	}

	@Override
	public Payee deletePayee(Long userId, Long payeeId) {
		Payee payee = payeeRepository.findPayeeByIdandUserId(payeeId, userId);		
		if( payee == null)
			return null;
		
		payee.setActive(false);
		payeeRepository.save(payee);
		
		return payee;
	}

	@Override
	public Object validateAccount(String accountIdent, String type) {
//		ResponseEntity<Rates> response = restTemplate.getForEntity( FX_RATE_URL, Rates.class, currency );
//		ResponseEntity<Object> response = restTemplate.getForEntity( 
//				"https://openiban.com/validate/" + accountIdent, Object.class );
		
		String url = URLS.get(type == null || type.isEmpty()? "iban": type.toLowerCase().trim());
		url = ( url == null || url.isEmpty() )? AccountService.IBAN_VALIDATOR: url;

		Object response = restTemplate.getForObject( url + accountIdent, Object.class );		
		return response;
	}
}
