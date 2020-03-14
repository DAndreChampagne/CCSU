package cs501.project;

public class Queue<T> {
	private LinkedList<T> _data = null;
	
	public Queue(Class<T> cls) {
		_data = new LinkedList<T>(cls);
	}
	
	public int Size() { return _data.Size(); }
	
	public boolean Empty() { return Size() == 0; }
	public boolean Full() { return false; } // always false because we're using a linkedlist implementation
	
	public T Front() { return _data.Head().GetData(); }
	
	public Queue<T> Enqueue(T value) { return Enqueue(new Node<T>(value, null, null)); }
	public Queue<T> Enqueue(Node<T> n) {
		if (n == null)
			throw new NullPointerException("n cannot be null");
		
		_data.Append(n);
		
		return this;
	}
	
	public T Dequeue() throws NullPointerException {		
		Node<T> p = _data.Head();
		if (p == null)
			throw new NullPointerException();
		
		T value = p.GetData();
		
		_data.Remove(_data.Head());
		
		return value;
	}

	@Override
	public String toString() {
		return "Queue [_data=" + _data.toString() + "]";
	}
	
	public String toRawString() {
		return _data.toRawString();
	}
	
}
