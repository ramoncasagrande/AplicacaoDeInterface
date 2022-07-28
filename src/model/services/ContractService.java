package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	
	
	public void processContract(Contract contract, Integer months) {
		
		double quotaValue = (contract.getTotalValue() / months);
		
		for( int i = 1; i <= months; i++) {
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(contract.getDate());
			calendar.add(Calendar.MONTH, i);
			Date date = calendar.getTime();
			
			double tempQuota = quotaValue + onlinePaymentService.interest(quotaValue, i);
			double totalQuota = tempQuota + onlinePaymentService.paymentFee(tempQuota);
			
			Installment installment = new Installment(date, totalQuota);
			contract.addInstallment(installment);
			}
		
		
		
		
		
		
		
	}

}
