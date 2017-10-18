package com.xapi.payment.service;

import java.util.Collection;
import java.util.List;

import com.xapi.data.model.Payment;

public interface PaymentService {
	public Collection<Payment> getAllPlaced(Long userId);

	public Payment cancelPayment(Payment payment);

	public Payment placePayment(Payment payment);

	public Payment calculate(Payment payment, Boolean calculatePayee);

	public Payment createPayment(Long userId, Long accountId, Long payeeId, Payment paymentPayeeAmounts);
}
