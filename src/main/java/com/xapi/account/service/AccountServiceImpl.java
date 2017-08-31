package com.xapi.account.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Override
	public Collection<?> getAll(Integer userId) {
		// TODO Auto-generated method stub
		return new ArrayList<Object>();
	}

}
