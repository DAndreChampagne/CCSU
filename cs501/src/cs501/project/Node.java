package cs501.project;

public class Node<T> {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_data == null) ? 0 : _data.hashCode());
		result = prime * result + ((_next == null) ? 0 : _next.hashCode());
		result = prime * result + ((_previous == null) ? 0 : _previous.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Node<T> other = (Node<T>)obj;
		
		if (_data == null) {
			if (other._data != null)
				return false;
		} else if (!_data.equals(other._data))
			return false;
		if (_next == null) {
			if (other._next != null)
				return false;
		} else if (!_next.equals(other._next))
			return false;
		if (_previous == null) {
			if (other._previous != null)
				return false;
		} else if (!_previous.equals(other._previous))
			return false;
		return true;
	}
	
	
}
