package assignment4_Lab3;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Employee {

	private String name;
	private Date hireDate;
	private List<Account> accounts;
	
	public Employee(String name, int yearOfHire, int monthOfHire, int dayOfHire){
		this.name = name;
		GregorianCalendar cal = new GregorianCalendar(yearOfHire,monthOfHire-1,dayOfHire);
		hireDate = cal.getTime();
		accounts = new ArrayList<>();
	}
    public String getName() {
        return name;
    }
    public String getHireDay() {
        return Util.dateAsString(hireDate);
    }
	public void createNewChecking(double startAmount) {
		accounts.add(new CheckingAccount(this, startAmount));
	}
	public void createNewSavings(double startAmount) {
		accounts.add(new SavingsAccount(this, startAmount));
	}
	public void createNewRetirement(double startAmount) {
		accounts.add(new RetirementAccount(this, startAmount));
	}
	public void deposit(AccountType acctType, double amt){
		for(Account ac:accounts) {
			if(ac.getAcctType().equals(acctType)) {
				ac.makeDeposit(amt);
				return;
			}
		}
		System.out.println("Error in depositing");
	}
	public boolean withdraw(AccountType acctType, double amt){
		for(Account ac:accounts) {
			if(ac.getAcctType().equals(acctType)) {
				return ac.makeWithdrawal(amt);
			}
		}
		return false;
	}
	public String getFormattedAcctInfo() {
		String newline = System.getProperty("line.separator");
		StringBuilder str = new StringBuilder();
		str.append("ACCOUNT INFO FOR "+getName()+": ");
		str.append(newline+"Hire Date : "+getHireDay()+newline+newline);
		for(Account ac:accounts) {
			str.append(ac.toString()+newline);
		}
		return str.toString();
	}
}