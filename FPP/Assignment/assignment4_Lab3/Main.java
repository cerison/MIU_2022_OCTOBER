package assignment4_Lab3;


public class Main {


    public static void main(String[] args) {

        Employee employee = new Employee("Peter","pet",5678,2022,05,26);
        
        Account[] account = new Account[3];
        account[0] = new CheckingAccount(employee,300);
        account[1] = new SavingsAccount(employee,300);
        account[2] = new RetirementAccount(employee,300);
        
        System.out.println(employee.toString());
        
        for(Account ac:account)
        {
            System.out.println(ac.toString());
        }
    }
}
