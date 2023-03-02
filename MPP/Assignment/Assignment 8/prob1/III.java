package lab8.prob1;
import java.util.function.Supplier;
public class Prob1_B_III {
	public static void main(String[] args) {
		System.out.println(new Prob1_B_III.MySupplier().get());
	}

	@SuppressWarnings("rawtypes")
	static class MySupplier implements Supplier {
		@Override
		public Double get() {
			return Math.random();
		}
	}
}