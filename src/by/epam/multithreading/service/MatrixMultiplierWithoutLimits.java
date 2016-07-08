package by.epam.multithreading.service;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplierWithoutLimits {
	private List<Thread> threadsHolder;
	
	public int[][] multiply(int[][] m1, int[][] m2) throws InterruptedException {
		if (!validate(m1, m2)) {
			System.out.println("Dimensions of matrixes are not correct");
			return null;
		}
		int[][] result = new int[m1.length][m1.length];
		threadsHolder = new ArrayList<Thread>(result.length * result.length);
		
		ArrayMultiplier thread = null;
		for (int i = 0; i < result.length; i ++) {
			for (int j = 0; j < result[i].length; j++) {
				thread = new ArrayMultiplier(m1, m2, result, i, j);
				threadsHolder.add(thread);
				thread.start();
			}
		}
		
		for (Thread t : threadsHolder) {
			t.join();
		}
		
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
		private int indexOfRow;
		private int indexOfColumn;
		
		ArrayMultiplier(int[][] matrix1, int[][] matrix2, int[][] result, int indexOfRow, int indexOfColumn) {
			this.matrix1 = matrix1;
			this.matrix2 = matrix2;
			this.result = result;
			this.indexOfRow = indexOfRow;
			this.indexOfColumn = indexOfColumn;
		}
		
		@Override
		public void run() {
			int value = 0;
			for (int i = 0; i < matrix1.length; i++) {
				value = value + matrix1[indexOfRow][i] * matrix2[i][indexOfColumn];
			}
			result[indexOfRow][indexOfColumn] = value;
		}
	}
}
