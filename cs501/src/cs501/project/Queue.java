package cs501.project;


public class Queue<T> {
	private int _size = 0;
	private LinkedList<T> _data = null;
	
	
	public Queue<T> Enqueue(T value) { return Enqueue(new Node<T>(value, null, null)); }
	public Queue<T> Enqueue(Node<T> n) {
		if (n == null)
			throw new NullPointerException("n cannot be null");
		
		_data.Append(n);
		
		return this;
	}
	
	public T Dequeue(T value) { return Dequeue(new Node<T>(value, null, null)); }
	public T Dequeue(Node<T> n) {
		if (n == null)
			throw new NullPointerException("n cannot be null");
		
		Node<T> p = _data.Head();
		T value = p.GetData();
		
		_data.Remove(_data.Head());
		
		
		return p.GetData();
	}
	
}
