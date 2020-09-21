package com.questions.backend;



public class Main {

	public static void main(String[] args) {
		
		double serviceCharge = 5.00;
		double creditCardFee = 10.00;
		//example amount = 100.00
		double inputAmount = 100.00;
		System.out.println("CashPaymentStrategy, pay: " + pay(amount -> amount + serviceCharge, inputAmount));
		System.out.println("CreditCardStrategy, pay: " + pay(amount -> amount + serviceCharge + creditCardFee, inputAmount));
	}	
	
	private static double pay(PaymentStrategy payment, double inputAmount) {
		return payment.pay(inputAmount);
	}

	
}
