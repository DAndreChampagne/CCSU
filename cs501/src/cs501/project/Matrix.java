package cs501.project;

import java.lang.reflect.Array;

public class Matrix<T> {

	private Node<T> _data[][] = null;
	private Class<T> _class = null;
	private int _rows = 0;
	private int _columns = 0;
	
	
	@SuppressWarnings("unchecked")
	public Matrix(Class<T> cls, int rows, int columns) {
		_class = cls;
		_rows = rows;
		_columns = columns;
		
		_data = (Node<T>[][]) Array.newInstance(cls, rows, columns);
	}
	
	public Matrix<T> setValue(int row, int col, T value) {
		if (row < 0 || row >= _rows)
			throw new IllegalArgumentException("row index must be between 0 and " + (_rows-1));
		
		_data[row][col].SetData(value);
		
		return this;
	}
	
	public T getValue(int row, int col) {
		return _data[row][col].GetData();
	}
	
	public Node<T> getNode(int row, int col) {
		return _data[row][col];
	}
	
	
	
	
	public static Matrix<Number> IdentityMatrix(int n) {
		Matrix<Number> m = new Matrix<Number>(Number.class, n,n);
		
		for (int i=0; i<n; ++i) {
			m.setValue(i, i, 1.0);
		}
		
		return m;
	}
}
