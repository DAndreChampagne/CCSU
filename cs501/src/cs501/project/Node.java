package cs501.project;

public class Node<T extends Object> {
	private T _data;
	private Node<T> _next;
	private Node<T> _previous;
	
	public Node(T value, Node<T> next, Node<T> previous) {
		_data = value;
		_next = next;
		_previous = previous;
	}
	
	public T GetData() { return _data; }
	public Node<T> SetData(T value) { _data = value; return this; }
	
	public Node<T> GetPrevious() { return _previous; }
	public Node<T> SetPrevious(Node<T> value) { _previous = value; return this; }
	
	public Node<T> GetNext() { return _next; }
	public Node<T> SetNext(Node<T> value) { _next = value; return this; }
}
