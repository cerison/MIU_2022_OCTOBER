package assignment4_Lab3;

public abstract class Account {
	private final static double DEFAULT_BALANCE = 0.0;
	private double balance;
	private Employee employee;
	
	abstract public AccountType getAcctType();
	
	Account(Employee emp, double balance){
		employee = emp;
		this.balance = balance;
	}
	
	Account(Employee emp){
		this(emp,DEFAULT_BALANCE);
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String toString() {
		String newline = System.getProperty("line.separator");
		return "Account type: "+ getAcctType()+newline +
				"Current bal: "+ balance;
	}

	public void makeDeposit(double deposit) {
		balance += deposit;
	}
	
	public boolean makeWithdrawal(double amount) {
		if(amount <= balance) {
			balance -= amount;
			return true;
		}
		return false;
	}
}

class CheckingAccount extends Account{

	CheckingAccount(Employee emp) {
		super(emp);
	}

	public CheckingAccount(Employee emp, double balance) {
		super(emp, balance);
	}

	@Override
	public AccountType getAcctType() {
		// TODO Auto-generated method stub
		return AccountType.CHECKING;
	}
}

class RetirementAccount extends Account{

	RetirementAccount(Employee emp) {
		super(emp);
	}

	public RetirementAccount(Employee emp, double balance) {
		super(emp, balance);
	}

	@Override
	public AccountType getAcctType() {
		// TODO Auto-generated method stub
		return AccountType.RETIREMENT;
	}
}

class SavingsAccount extends Account{

	SavingsAccount(Employee emp) {
		super(emp);
	}

	public SavingsAccount(Employee emp, double balance) {
		super(emp, balance);
	}

	@Override
	public AccountType getAcctType() {
		// TODO Auto-generated method stub
		return AccountType.SAVINGS;
	}
}
