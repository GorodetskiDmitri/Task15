package by.epam.multithreading.service;

public class MatrixMultiplierWithTwoThreads {
	
	public int[][] multiply(int[][] m1, int[][] m2) throws InterruptedException {
		if (!validate(m1, m2)) {
			System.out.println("Dimensions of matrixes are not correct");
			return null;
		}
		int[][] result = new int[m1.length][m1.length];
		
		Thread t1 = new ArrayMultiplier(m1, m2, result, true);
		Thread t2 = new ArrayMultiplier(m1, m2, result, false);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		return result;
	}
	
	public boolean validate (int[][] a, int[][] b) {
		if (a != null && b != null) {
			if (a.length == b.length) {
				for (int i = 0; i < a.length; i++) {
					if (a.length != a[i].length) {
						return false;
					}
				}
				for (int i = 0; i < b.length; i++) {
					if (b.length != b[i].length) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	class ArrayMultiplier extends Thread {
		private int[][] matrix1;
		private int[][] matrix2;
		private int[][] result;
		private boolean modulo;
		
		ArrayMultiplier(int[][] matrix1, int[][] matrix2, int[][] result, boolean modulo) {
			this.matrix1 = matrix1;
			this.matrix2 = matrix2;
			this.result = result;
			this.modulo = modulo;
		}
		
		@Override
		public void run() {
			int startRow = 0;
			if (!modulo) {
				startRow = 1;
			}
			
			for (int row = startRow; row < result.length; row = row + 2) {
				for (int i = 0; i < matrix1.length; i++) {
					int value = 0;
					for (int j = 0; j < matrix1.length; j++) {
						value = value + matrix1[row][j] * matrix2[j][i];
					}
					result[row][i] = value;
				}
			}
		}		
	}
}
