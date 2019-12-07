import java.util.Scanner;

public class det{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Size of Matrix: ");
		int n = in.nextInt();
		int[][] matrix = new int[n][n];
		System.out.println("Fill the space below by numbers:");
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				matrix[i][j] = in.nextInt();
			}
		}


		System.out.println("The determinant is: " + getDet(matrix, matrix[0][0]) / matrix[0][0]); // I'm dividing by matrix[0][0] because I had a problem when I've got the last value, such that the matrix[0][0] multiplied to the answer.
		

	}

	//If you wanna see more complitely just delete all comment symbols ('//' or '/*' '*/')

	public static int getDet(int[][] list, int pivot){	//This method gets the list and pivot.
		int sum = 0;
		if(list.length == 2){
			//System.out.println( "det: " + pivot * ( (list[0][0]*list[1][1]) - (list[0][1]*list[1][0])) + " pivot: " + pivot);
			return pivot*( (list[0][0]*list[1][1]) - (list[0][1]*list[1][0]) ); //Base formula to the 2x2 matrix 
		}
		for(int i = 0; i < list.length; i++){
			if(i % 2 == 0){
				sum += pivot * getDet(giveMeNewMatrix(list, i), list[0][i]); // if the 
			}
			else{
				sum -= pivot * getDet(giveMeNewMatrix(list, i), list[0][i]);	
			}
			//System.out.println("sum in for: " + sum);
		}
		//System.out.println("sum after the for: " + sum + " pivot: " + pivot);
		return sum;

	}

	public static int[][] giveMeNewMatrix(int[][] matrix, int cont){
		int n = matrix.length;
		int[][] temp = new int[n - 1][n - 1];
		int x = 0;
		int y = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n ; j++){
				if(i != 0 && j != cont){
					//System.out.println("x: " + x + " y: " + y + " i: " + i + " j: " + j );
					temp[x][y] = matrix[i][j];
					//System.out.println("temp: " + temp[x][y]);
					y++;
				}
			}
			y = 0;
			if(i != 0){
				x++;
			}
		}

		/*
		for(int i = 0; i < temp.length; i++){
			for(int j = 0; j < temp[i].length; j++){
				System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		*/
		return temp;
	}
}
