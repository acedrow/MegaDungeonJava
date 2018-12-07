package game;

public class Timer {
	//returns time in seconds 
	public static double getTime(){
		return (double) System.nanoTime() / AppConstants.ONE_BILLION;
	}
	
}
