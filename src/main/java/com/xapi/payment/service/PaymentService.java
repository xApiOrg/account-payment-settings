package com.xapi.payment.service;

import java.util.Collection;

public interface PaymentService {
	public Collection<?> getAll(Long userId);

	public Object placePayment(Object payment);

	public Object calculate(Object paymentPayeeAmounts);

	public Object createPayment(Long userId, Long accountId, Long payeeId, Object paymentPayeeAmounts);
}
