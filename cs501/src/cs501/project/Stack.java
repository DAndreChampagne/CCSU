package cs501.project;


public class Stack<T> {
	private LinkedList<T> _data = null;
	
	public Stack(Class<T> cls) {
		_data = new LinkedList<>(cls);
	}
	
	public int Size() { return _data.Size(); }
	
	public boolean Empty() { return Size() == 0; }
	public boolean Full() { return false; }
	
	public T Top() { return _data.Tail().GetData(); }
	
	public Stack<T> Push(T value) { return Push(new Node<T>(value, null, null)); }
	public Stack<T> Push(Node<T> n) {
		if (n == null)
			throw new NullPointerException("n cannot be null");
		
		_data.Append(n);
		
		return this;
	}
	
	public T Pop() {		
		Node<T> p = _data.Tail();
		T value = p.GetData();
		
		_data.Remove(p);
		
		return value;
	}
	
	@Override
	public String toString() {
		return "Stack [_data=" + _data.toString() + "]";
	}
	
}
