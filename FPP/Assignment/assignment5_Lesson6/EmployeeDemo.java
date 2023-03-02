package assignment5_Lesson6;

class Clerk extends Employee{
	private int bonus;
	public Clerk() {
		bonus=100;
	}
	
	@Override
	public double addBonus(double salary) {
		return salary+this.bonus+super.getBonus();
	}
	@Override
	public void display() {
		System.out.println("Id : " + super.getId() + " Name : " + super.getName() + " Department : " + super.getDepartment() + " Salary : "+ addBonus(super.getSalary()) + " Designation : " + super.getDesignation() + " Total Bonus : " + (this.bonus + super.getBonus()));
	};
}

// Manager child class =========================
class Manager extends Employee{
	private int bonus;
	public Manager() {
		this.bonus=300;
	}
	
	@Override
	public double addBonus(double salary) {
		return salary+this.bonus+super.getBonus();
	}
	@Override
	public void display() {
		System.out.println("Id : " + super.getId() + " Name : " + super.getName() + " Department : " + super.getDepartment() + " Salary : "+ addBonus(super.getSalary()) + " Designation : " + super.getDesignation() + " Total Bonus : " + (this.bonus + super.getBonus()));
	};
}



// Employee parent class===================
class Employee {

	private int id;
	private String name;
	private String department;
	private double salary;
	private String Designation;
	private double bonus;
	static int count=0; // static to auto generate employee i
	public Employee() {
		count++;
		id=count;
	}
	
	public double getBonus() {
		return bonus;
	}

	public void setBonus() {
		this.bonus = 200;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public double getSalary() {
		return salary;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}
	
	public void display() {
		System.out.println("Id : " + this.id + " Name : " + this.name + " Department : " + this.department + " Salary : "+ addBonus(this.salary) + " Designation : " + this.Designation + " Bonus : " + this.bonus);
	};
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		else if(getClass()!=obj.getClass()) return false;
		else {
			Employee otherEmployee = (Employee)obj;
			return this.Designation.equals(otherEmployee.Designation);
		}
			
		
	}
	
	public double addBonus(double salary) {
		return salary+bonus;
	}
	

}


//Test Demo Class ============================================
public class EmployeeDemo{
public static void main(String[] args) {
	Employee a = new Employee();
	//a.setId(1);
	a.setName("A");
	a.setSalary(2000);
	a.setDepartment("Dept 1");
	a.setDesignation("manager");
	a.setBonus();
	
	a.display();
	
	Employee b = new Employee();
	b.setId(2);
	b.setName("B");
	b.setSalary(2000);
	b.setDepartment("Dept 1");
	b.setDesignation("manager");
	b.setBonus();
	
	b.display();

	
    Employee m = new Manager();
    //m.setId(3);
	m.setName("C");
	m.setSalary(2000);
	m.setDepartment("Dept 2");
	m.setDesignation("manager");
	m.setBonus();
	
    m.display();
    
    Employee c = new Clerk();
    //c.setId(4);
	c.setName("D");
	c.setSalary(2000);
	c.setDepartment("Dept 2");
	c.setDesignation("Clerk");
	c.setBonus();
	
    c.display();
    
    Employee c2 = new Clerk();
    //c.setId(4);
	c2.setName("E");
	c2.setSalary(5000);
	c2.setDepartment("Dept 3");
	c2.setDesignation("Clerk");
	c2.setBonus();
    c2.display();
    
	System.out.println("is a equals b ? " + a.equals(b));
}
}