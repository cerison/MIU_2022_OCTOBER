package lesson4.labsolns.prob1;

import java.time.LocalDate;

abstract public class Employee {
	private String empId;
	public Employee(String empId) {
		this.empId = empId;
	}
	public void print() {
		LocalDate now = LocalDate.now();
		System.out.println(calcCompensation(now.getMonthValue(), now.getYear()));
	}
	public Paycheck calcCompensation(int month, int year) {
		double grossPay = calcGrossPay(month, year);
		return new Paycheck(grossPay, Tax.FICA, Tax.State, 
				Tax.Local, Tax.Medicare, Tax.SocialSecurity);
	}

	abstract public double calcGrossPay(int month, int year);
}
