package lab8.prob1;
import java.util.function.Supplier;

public class Prob1_B_II {
	public static void main(String[] args) {
			Supplier<Double> val = () -> Math.random();
				System.out.println(val.get());
	}

}