package assignment12_Lesson13;

public class Exception_CustomerAccount {
	private String cus_name;
	private int acc_No;
	private double balance;
	public boolean deposit (double amount) {
		if(amount > 0) {
			balance += amount;
			return true;
		}
		else return false;
	}
	public boolean withdraw(double amount) {
		try {
		if(amount < 0) return false;
		if(balance < 100 ) throw new LowBalanceException();
		else {
			if(balance < amount) throw new ExceedBalanceException();
			else {
				balance -= amount;
				return false;
			}
		}
		}catch(LowBalanceException e) {
			System.out.println(e);
			return false;
		}catch(ExceedBalanceException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public static void main(String[] args) {
		Exception_CustomerAccount ec = new Exception_CustomerAccount();
		System.out.println("Deposit : " + ec.deposit(-10));//trying to deposit negative number
		System.out.println("Current balance: $" + ec.getBalance());
		System.out.println("Deposit : " + ec.deposit(10));
		System.out.println("Current balance: $" + ec.getBalance());
		System.out.println("Deposit : " + ec.deposit(10));
		System.out.println("Current balance: $" + ec.getBalance());
		System.out.println("Withdraw : " + ec.withdraw(-10));//trying to withdraw negative number
		System.out.println("Current balance: $" + ec.getBalance());
		System.out.println("Withdraw : " + ec.withdraw(10));
		System.out.println("Current balance: $" + ec.getBalance());
		System.out.println("Deposit : " + ec.deposit(150));//trying to withdraw morethan balance
		System.out.println("Current balance: $" + ec.getBalance());
		System.out.println("Withdraw : " + ec.withdraw(200));
		System.out.println("Current balance: $" + ec.getBalance());
	}
}

class LowBalanceException extends Exception
{
    public LowBalanceException()  
    {
        super("Your balance is low, less than $100");  
    }
}

class ExceedBalanceException extends Exception
{
    public ExceedBalanceException()
    {
        super("Trying to withdraw morethan current balance");  
    }
} 