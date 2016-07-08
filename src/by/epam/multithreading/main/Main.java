package by.epam.multithreading.main;

import by.epam.multithreading.service.MatrixMultiplierWithTwoThreads;
import by.epam.multithreading.service.MatrixMultiplierWithoutLimits;

public class Main {
	public final static int SIZE = 5;

	public static void main(String[] args) {
		
		int[][] matrixA = generateMatrix(SIZE);
		int[][] matrixB = generateMatrix(SIZE);
		
		int[][] result1;
		int[][] result2;
		
		System.out.println("\nMatrix A:");
		showMatrix(matrixA);
		System.out.println("\nMatrix B:");
		showMatrix(matrixB);
		
		MatrixMultiplierWithoutLimits matrixMultiplier1 = new MatrixMultiplierWithoutLimits();
		MatrixMultiplierWithTwoThreads matrixMultiplier2 = new MatrixMultiplierWithTwoThreads();
		try {
			result1 = matrixMultiplier1.multiply(matrixA, matrixB);
			result2 = matrixMultiplier2.multiply(matrixA, matrixB);
		} catch (InterruptedException e) {
			result1 = null;
			result2 = null;
		}
		
		System.out.println("\nMatrix Multiply Without Limit Result:");
		showMatrix(result1);
		System.out.println("\nMatrix Multiply With Two Threads Result:");
		showMatrix(result2);
		
	}
	
	public static int[][] generateMatrix(int size) {
		int[][] matrix = new int[size][size];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = (int)(Math.random() * 50);
			}
		}
		return matrix;
	}
	
	public static void showMatrix(int[][] matrix){
		for (int[] row : matrix) {
			for (int elem : row) {
				System.out.print(String.format("%6d", elem));
			}
			System.out.println();
		}
	}

}
