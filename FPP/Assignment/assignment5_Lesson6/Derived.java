package assignment5_Lesson6;

class Parent {
    void myMethod() {
        System.out.println("super class");
    }
}
public class Derived extends Parent {
    // @Override
	void myMethod() {
        System.out.println("derived class");
    }
    public static void main(String[] args) {
        Derived object =  (Derived) new  Parent ();
        object.myMethod();
    }
}

/// c) Compilation error
