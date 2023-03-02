package lab8.prob3;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class prob3 {
	public static void main(String[] args) {
		/* 
		 * The answer is yes, the lambda expression can be expressed as BiFunction interface 
		*/
		BiFunction<Double, Double, List<Double>> lambdaFun = (x,y) -> {
				List<Double> list = new ArrayList<>();
				list.add(Math.pow(x,y));
				list.add(x*y);
				return list;
			};
		System.out.println(lambdaFun.apply(2.0, 3.0)); 
	}
}
