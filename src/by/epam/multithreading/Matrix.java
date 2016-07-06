package by.epam.multithreading;

public class Matrix {
	private int[][] matrix;

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public int[] getMatrixColumn(int indexOfColumn) {
		int length = matrix.length;
		int[] column = new int[length];
		for (int i = 0; i < length; i++) {
			if (indexOfColumn < matrix[i].length) {
				column[i] = matrix[i][indexOfColumn];
			} else {
				return null;
			}
		}
		return column;
	}

	public int[] getMatrixRow(int indexOfRow) {
		if (indexOfRow < matrix.length) {
			return matrix[indexOfRow];
		} else {
			return null;
		}
	}

	public int getMatrixLength() {
		return matrix.length;
	}

	public int getMatrixWidth() {
		return getMatrixWidth(0);
	}
	
	public int getMatrixWidth(int indexOfRow) {
		return matrix[indexOfRow].length;
	}

	public void setElement(int value, int indexOfRow, int indexOfColumn) {
		matrix[indexOfRow][indexOfColumn] = value;
	}
	
}
