package cs501.project;

import java.util.Objects;

// Generic node class
public class Node<T> {
	private T _data = null;
	private Node<T> _next = null;
	private Node<T> _previous = null;
	private int _priority = Integer.MIN_VALUE;
	
	public Node() { }
	public Node(T value) { _data = value; }
	public Node(T value, int priority) { _data = value; _priority = priority; }
	public Node(T value, Node<T> next, Node<T> previous) {
		_data = value;
		_next = next;
		_previous = previous;
	}
	public Node(T value, Node<T> next, Node<T> previous, int priority) {
		_data = value;
		_next = next;
		_previous = previous;
		_priority = priority;
	}
	
	public T GetData() { return _data; }
	public Node<T> SetData(T value) { _data = value; return this; }
	
	public Node<T> GetPrevious() { return _previous; }
	public Node<T> SetPrevious(Node<T> value) { _previous = value; return this; }
	
	public Node<T> GetNext() { return _next; }
	public Node<T> SetNext(Node<T> value) { _next = value; return this; }
	
	public int GetPriority() { return _priority; }
	public Node<T> SetPriority(int priority) { _priority = priority; return this; }
	
	
	
	@Override
	public String toString() {
		if (_priority == Integer.MIN_VALUE)
			return "Node [_data=" + _data + "]";
		return "Node [_data=" + _data + ", _priority=" + _priority + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(_data, _next, _previous, _priority);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Node))
			return false;
		
		Node<?> other = (Node<?>) obj;
		return Objects.equals(_data, other._data) && Objects.equals(_next, other._next)
				&& Objects.equals(_previous, other._previous) && _priority == other._priority;
	}
	
}
