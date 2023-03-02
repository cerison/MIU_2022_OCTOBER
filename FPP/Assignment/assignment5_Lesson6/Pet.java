package assignment5_Lesson6;
import java.util.Scanner;

public class Pet {
    private static Scanner scan = new Scanner(System.in);
    private String name;
    private String type;
    private Pet[] pets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Pet[] getPets() {
        return pets;
    }

    public void setPets(Pet[] pets) {
        this.pets = pets;
    }

    public static void main(String[] args) {
        Pet pets = new Pet();
        Pet[] PetArray = new Pet[100];
        int count = 0;

        boolean finished = false;

        while (!finished) {
            System.out.println(
                    "Please enter d to add a dog or c to add a cat ('Stop' to end)");

            char choice = scan.next().charAt(0);

            switch (choice) {

                case 'd':
                    System.out.println("You want to add a Dog");
                    addDog();

                    break;

                case 'c':
                    System.out.println("You want to add a Cat");
                    addCat();

                    break;

                default:
                    finished = true;
            }
        }
    }

    public static void addDog() {
        System.out.println("Please enter name:  ");

        String dName = scan.next();
        System.out.println("Please enter weight of dog:  ");

        double weight = scan.nextDouble();

// ADD A NEW INSTANCE OF Dog TO ARRAY
    }

    public static void addCat() {
        System.out.println("Please enter name:   ");

        String cName = scan.next();
        System.out.println("Please enter the coat color of the cat:  ");

        String cColor = scan.next();
    }

    class Cat extends Pet {
        private String coatColor;

        public Cat() {
            super();
            setType("c");
        }

        public Cat(String cColor) { //duplicate method HOW DO I CHANGE THIS?
            super();
            setType("c)");
            setCoatColor(cColor);
        }

        public String getCoatColor() {
            return coatColor;
        }

        public void setCoatColor(String coatColor) {
            this.coatColor = coatColor;
        }

    }

    class Dog extends Pet {
        private double weight;

        public Dog(double weight) {
            super();
            setType("d");
            setWeight(weight);
        }

        public Dog() {
            super();
            setType("d");
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }
}