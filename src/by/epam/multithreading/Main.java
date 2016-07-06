package by.epam.multithreading;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		int [][] matrixA = {
				{1,2,3,4,},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16}};
		
		int [][] matrixB = {
				{17,18,19,20},
				{21,22,23,24},
				{25,26,27,28},
				{29,30,31,32}};
		
		
		//MatrixMultiplier matrixMultiplier = new MatrixMultiplier(matrixA, matrixB);
		MatrixMultiplier matrixMultiplier = new MatrixMultiplier(matrixA, matrixB, 2);	//Two Threads Limit
		
		Matrix resultMatrix = matrixMultiplier.multiply();
		
		if (resultMatrix != null) {
			int[][] result = resultMatrix.getMatrix();
			for (int[] row : result) {
				for (int elem : row) {
					System.out.print(String.format("%6d", elem));
				}
				System.out.println();
			}
		}
}
}
