package by.epam.multithreading;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplier {
	
	Matrix m1 = new Matrix();
	Matrix m2 = new Matrix();
	Matrix resultMatrix = new Matrix();
	int countOfThreads = 0;
	private List<ArrayMultiplier> threadsHolder = new ArrayList<ArrayMultiplier>();

	public MatrixMultiplier(int[][] m1, int[][] m2) {
		this.m1.setMatrix(m1);
		this.m2.setMatrix(m2);
	}
	
	public MatrixMultiplier(int[][] m1, int[][] m2, int countOfThreads) {
		this.m1.setMatrix(m1);
		this.m2.setMatrix(m2);
		this.countOfThreads = countOfThreads;
	}

	public Matrix multiply() throws InterruptedException {
		if (countOfThreads <= 0 || countOfThreads > m1.getMatrixLength()) {
			countOfThreads = m1.getMatrixLength();
		}
		
		if (!validate(m1, m2)) {
			System.out.println("Dimensions of matrixes are not correct");
			return null;
		}
		
		resultMatrix.setMatrix(new int[m1.getMatrixLength()][m1.getMatrixLength()]);
		System.out.println("Count of threads: " + countOfThreads);
			
		ArrayMultiplier thread = null;
		for (int i = 0; i < countOfThreads; i++) {
			thread = new ArrayMultiplier(m1, m2, i);
			threadsHolder.add(thread);
			thread.start();
		}
		for (Thread t : threadsHolder) {
			t.join();
		}
		
		return resultMatrix;
	}
	
	public boolean validate (Matrix a, Matrix b) {
		if (a != null && b != null) {
			if (a.getMatrixLength() == b.getMatrixLength()) {
				for (int i = 0; i < a.getMatrixLength(); i++) {
					if (a.getMatrixWidth() != a.getMatrixWidth(i)) {
						return false;
					}
				}
				for (int i = 0; i < b.getMatrixLength(); i++) {
					if (b.getMatrixWidth() != b.getMatrixWidth(i)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	class ArrayMultiplier extends Thread {
		Matrix x1;
		Matrix x2;
		int threadNumber;
		int generalCountOfThreads;

		public ArrayMultiplier(Matrix x1, Matrix x2, int threadNumber) {
			this.x1 = x1;
			this.x2 = x2;
			this.threadNumber = threadNumber;
			generalCountOfThreads = countOfThreads;
		}

		public void run() {
			for (int i = 0; i < x1.getMatrixLength(); i++) {
				for (int j = 0; j < x2.getMatrixWidth(); j++) {
					if (i % generalCountOfThreads == threadNumber) {
						calculate(x1.getMatrixRow(i), x2.getMatrixColumn(j), i, j);
					}
				}
			}
		}

		public void calculate(int[] row, int[] column, int indexOfRow, int indexOfColumn) {
			int value = 0;
			for (int i = 0; i < row.length; i++) {
				value += row[i] * column[i];
			}
			resultMatrix.setElement(value, indexOfRow, indexOfColumn);
		}
	}
}
