package demo.d4;

	public class Tarkkuus {
		
		public static void main(String[] args) {
			System.out.println(etumerkki(37-36.8, 0.3));
			System.out.println(etumerkki(100-97,5.0));
			System.out.println(etumerkki(100-90,5.0));
		}
	/**
	 * 
	 *  -1  mikäli  x < -eps
	 *	 0  mikäli -eps <= x <= eps
	 *   1  mikäli  x > eps
	 * @example
	 * <pre name="test">
	 * etumerkki(0,0) === 0;
	 * etumerkki(18-16,3) === 0;
	 * </pre>
	 */
	    public static double etumerkki(double x, double eps){
	    	double epsi =0;
	    	
	    	if (-eps <= x && x <= eps) {
	    		return 0;
	    	}
	    	if (x < eps) {
	    		return -1;
	    	}
	    	if (x > eps) {
	    		return 1;
	    	}
	    		
	    	return epsi;
	    }
}
