//main



public class Employee{

	public static void main(String[] args){

	Company com = new Company();
	com.run();
	com.cal();

	com = null;

	com.run();
	com.cal();
	
	}


}
