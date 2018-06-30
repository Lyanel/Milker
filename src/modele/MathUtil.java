package modele;

public class MathUtil{
		
	/**
	 * return minValue+((maxValue-minValue)*Math.random()*mod)
	 * */
	public static double getRandomValue(double mod, double minValue, double maxValue) {
		return minValue+((maxValue-minValue)*Math.random()*mod);
	}
	
	/**
	 * return x>min && x<max;    
	 * */
	public static boolean betweenExclusive(int x, int min, int max){
		return x>min && x<max;    
	}

	/**
	 * return x>=min && x<=max;    
	 * */
	public static boolean betweenInclusive(int x, int min, int max){
		return x>=min && x<=max;    
	}
}