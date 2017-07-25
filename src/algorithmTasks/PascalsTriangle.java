package algorithmTasks;

public class PascalsTriangle {

	static void getNRowsOfPascalsTriangle(int n){
		int [][] arr = new int [n] [];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new int [i+1];
			System.out.println();
			for (int j = 0; j < arr[i].length; j++) {
				if(j==0 || j==i){
					arr[i][j] = 1;
				} else {
					arr[i][j] = arr[i-1][j] + arr[i-1][j-1];
				}
				System.out.print(arr[i][j] + " ");
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		getNRowsOfPascalsTriangle(5);
	}
	
	
}
