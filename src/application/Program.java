package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;



public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract data:");
		System.out.print("Number: ");
		Integer number = sc.nextInt();
		sc.nextLine();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = df.parse(sc.nextLine());
		System.out.print("Contract value: ");
		Double totalValue = sc.nextDouble();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("Number of installments: ");
		int months = sc.nextInt();
		
		PaypalService paypalService = new PaypalService();
		
		ContractService contractService = new ContractService(paypalService);
		
		contractService.processContract(contract, months);
		
		System.out.println("Installments: ");
		
		for (Installment inst : contract.getInstallments()) {
			System.out.printf("%s - %.2f\n",df.format(inst.getDueDate()),inst.getAmount());
		}
		
		sc.close();

	}

}
