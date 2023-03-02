final class SingletonClass {
    private static SingletonClass instance = null;
    private SingletonClass() {}

    public static long generateKey() {
        return Math.round(Math.random() * 10000000);
    }

    public static SingletonClass getSingletonObject() {
        if(instance == null) {
            instance = new SingletonClass();
            long key = generateKey();
            System.out.println("Key generated Successfully");
            System.out.println("Your key to activate Avast anti virus is:" + key);
        } else {
            System.out.println("Unsuccessful to produce the key...");
        }
        return instance;
    }
}

public class Main {

    public static void main(String[] args) {
        SingletonClass key1 = SingletonClass.getSingletonObject();
        SingletonClass key2 = SingletonClass.getSingletonObject();
    }
}