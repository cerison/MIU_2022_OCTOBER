package assignment2_Lesson2;
//Answer for 7th question
public class Question7 {
	String name;
	public static void main(String[] args) {
		
		final int size  = 365;
		double [] temp = new double[size];
		double min = -15;//minimum temperature
	    double max = 40;//maximum temperature
		for(int i=0; i<size; i++)
		{
			temp[i] = Math.floor(Math.random()*(max-min+1)+min);//generate random temperature based on min and max temperature
		}
		System.out.println("The following are daily temperature value for 365 days");
		for(double i : temp) {
			System.out.print(i+" ");
		}
		min = temp[0];
		max = temp[0];
		int minDay = 1, maxDay = 1;
		for(int i = 0; i < size; i++) {
			if(temp[i] < min) {
				min = temp[i];
				minDay = i+1;
			}
			if(temp[i] > max) {
				max = temp[i];
				maxDay = i+1;
			}
		}
		System.out.println("");
		System.out.println("");
		System.out.println("Hottest day is : day "+maxDay+" ( temperature in degree celicius: "+max+")");
		System.out.println("Coldest day is : day "+minDay+" ( temperature in degree celicius: "+min+")");
	}
}
