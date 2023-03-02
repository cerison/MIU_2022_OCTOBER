package assignment3_Lesson3;
public class Question1 {

	public static void main(String[] args) {
		Exponential ex = new Exponential();
		System.out.println(ex.power(2,-1));
	}
}

 class Exponential{
	double power (double x, int n) {
		if(n == 0)
			return 1;
		else if(n > 0)
			return x * power(x, n-1);
		else
			return 1/x * power(x, n+1);
	}
}