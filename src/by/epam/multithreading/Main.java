package by.epam.multithreading;

public class Main {

	 private static int[][] result = null; // результирующая матрица 3х5
	 
	 private static int [][] a = { // матрица 3х4
	            {1,2,3,4},
	            {5,6,7,8},
	            {9,10,11,12}
	      };
	 
	 private static int [][] b = { // матрица 4х5
			 {1,2,3,4,5},   
			 {6,7,8,9,10},
	         {11,12,13,14,15},
	         {16,17,18,19,20}
	      };
	 
	 public static void main(String[] args) {
	        if (validate(a,b)) {
	        	result = new int[a.length][b[0].length];
	        	for (int i = 0; i < result.length; i++) {
	        		for (int j = 0; j < result[i].length; j++) {
	        			int sum = 0;
	        			for (int m = 0; m < a[i].length; m++) {
	        				sum = sum + (a[i][m] * b[m][j]);
	        			}
	        			result [i][j] = sum;
	        		}
	        	}
	        }
	        
	        // вывод результата перемножения матриц на консоль
	        for (int i = 0; i < result.length; i++) {
	            for (int j = 0; j < result[0].length; j++)  {
	                System.out.print(String.format("%6d", result[i][j]));
	            }
	            System.out.println();
	        }
	        
	 }
	 
	 // проверка на совпадение размеров перемножаемых сторон матрицы
	 public static boolean validate(int [][] a, int [][] b) {
		  if (validateRightConstruction(a) && validateRightConstruction(b)) {
			  if (a[0].length == b.length || a.length == b[0].length) {
				  return true;
			  }
		  }
		  return false;
	 }
	 
	 // проверяем, чтобы матрица имела одинаковое кол-во столбцов в каждой строке
	 public static boolean validateRightConstruction(int [][] matrix) {
		 for (int i = 0; i < matrix.length-1; i++) {	
			 if (matrix[i].length != matrix[i+1].length)
				 return false;
		 }
	     return true;  
	 }
}
