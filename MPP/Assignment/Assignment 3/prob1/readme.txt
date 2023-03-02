The reason there are different results when
testing for equality is this:

In the code:
        Person p1 = new PersonWithJob("Joe", 30000);
		Person p2 = new Person("Joe");
		//As PersonsWithJobs, p1 should be equal to p2
		System.out.println("p1.equals(p2)? " + p1.equals(p2));
		System.out.println("p2.equals(p1)? " + p2.equals(p1));

The computation p1.equals(p2) compares a Person with a PersonWithJob
and first tests whether Person satisfies instanceof PersonWithJob -- but
this is false since Person is not a subclass of PersonWithJob. So the result
is false here. The computation p2.equals(p1) tests whether 
PersonWithJob satisfies instanceof Person -- and it does since it is
a subclass of Person. Then names of the two objects are compared -- and
these are equal. Therefore the output is true in this case.

At a high level, the reason for the different outputs is that different
equals methods for Person and PersonWithJob have been implemented using
the instanceof strategy.