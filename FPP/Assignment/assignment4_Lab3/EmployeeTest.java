package assignment4_Lab3;

import java.util.Scanner;

public class EmployeeTest {
	Employee[] emps = null;
	public static void main(String[] args) {
		new EmployeeTest();
	}
	EmployeeTest(){
		emps = new Employee[3];
		emps[0] = new Employee("Jim Daley", 2000, 9, 4);
		emps[1] = new Employee("Bob Reuben", 1998, 1, 5);
		emps[2] = new Employee("Susan Randolph", 1997, 2,13);

		emps[0].createNewChecking(10500);
		emps[0].createNewSavings(1000);
		emps[0].createNewRetirement(9300);

		emps[1].createNewChecking(34000);
		emps[1].createNewSavings(27000);

		emps[2].createNewChecking(10038);
		emps[2].createNewSavings(12600);
		emps[2].createNewRetirement(9000);


		Scanner sc = new Scanner(System.in);
		System.out.print("Do you want to see a report of all account balances? (y/n) ");
		String answer = sc.next();
		if(answer.equalsIgnoreCase("y")){
			System.out.println(getFormattedAccountInfo());
			
			System.out.println("--------------------------------------");
			System.out.println("Account balance after some operation");
			
			emps[0].withdraw(AccountType.CHECKING,1000);
			emps[0].deposit(AccountType.SAVINGS,500);
			emps[0].deposit(AccountType.RETIREMENT,500);
			
			System.out.println(getFormattedAccountInfo());
		}
	}
	String getFormattedAccountInfo() {
		String res = "";
		for(Employee emp:emps) {
			res += emp.getFormattedAcctInfo() + "\n";
		}
		return res;
	}
}