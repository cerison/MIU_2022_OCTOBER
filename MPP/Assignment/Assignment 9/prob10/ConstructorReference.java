package MPP.Assignment9.prob10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Human
{
	String name;
	int age;
	String gender;
	
	public Human(String name){
		this.name = name;
	}
	public Human(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	public Human(String name,int age, String gender){
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
}

public class ConstructorReference {
public static void main(String args[]){
	Human[] list = { new Human("Joe",35,"Male"), new Human("Jane",45,"Female"), new Human("John",30,"Male")};
	// Converting Human Array into Stream
	System.out.println("Male Candidates");
	Stream<Human> streams = Arrays.stream(list);
	 streams.filter(p -> p.getGender().equals("Male"))
			.map(Human::getName)
			.forEach(System.out::println);
	 
	Function<String, Human> function = Human::new;
	Function<String, Human> function1 = (s)->new Human(s);
	
    Human ob = function.apply("Renuka");
    System.out.println(ob);
    
    Collection<Human> col1 
    = arrayToCollection(ArrayList<Human>::new, list);
    System.out.println(col1);
   }
public static Collection<Human> arrayToCollection(
        Supplier<Collection<Human>> supplier, Human[] 
            humans) {
    Collection<Human> collection = supplier.get();
    for (Human i :  humans) {
        collection.add(i);
    }
    return collection;
}

}
