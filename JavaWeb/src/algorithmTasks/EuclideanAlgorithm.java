package algorithmTasks;

public class EuclideanAlgorithm {

	public static void main(String[] args) {

// finding GCD
		System.out.println(gcd(1000, 100));
		System.out.println(recursionGCD(6, 15));
		
// finding LCM using Euclidean algorithm for finding GCD
		System.out.println(LCM(15, 6));
		System.out.println(recursionLCM(6, 15));
	

	}
// finding GCD using Euclidean algorithm
	static int gcd(int m, int n) {
		int r = 0;

		if (m >= n) {

			if (n == 0) {
				return m;
			}

			while (n != 0) {

				r = m % n;
				m = n;
				n = r;

			}
			return m;
			
		} else {
			if (m == 0) {
				return n;
			}
			while (m != 0) {
				r = n % m;
				n = m;
				m = r;
			}
			return n;

		}
		
	}
	
	// GCD with recursion
	static int recursionGCD(int m, int n){
		
		if (m >= n) {
			if (n == 0){
				return m;
			} else {
				return recursionGCD(n, m%n);
			}
		} else {
			if (m == 0){
				return n;
			} else {
				return recursionGCD(n%m, m);
			}
		}
		
	}
	
	// LCM
	static int LCM(int m, int n){
		return m*n/gcd(m, n);
	}
	
	// LCM recursion
	
	static int recursionLCM(int m, int n){
		return m*n/recursionGCD(m, n);
	}
	
	
	
}
