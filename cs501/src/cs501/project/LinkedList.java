package cs501.project;

import java.util.Arrays;

import cs501.project.Node;

public class LinkedList<T extends Object> {
	private int _size = 0;
	private Node<T> _head = null;
	private Node<T> _tail = null;
	
	public int Size() { return _size; }
	
	public boolean Empty() { return Size() == 0; }
	
	public Node<T> Head() { return _head; }
	
	public Node<T> Tail() { return _tail; }
	
	public Node<T> Get(int index) {
		if (index < 0 || index >= Size())
			throw new IllegalArgumentException("invalid index: 0 <= " + index + " < " + Size());
		
		int i=0;
		Node<T> p = _head;
		for (; i != index && p.GetNext() != null; p = p.GetNext());
		
		return p;
	}
	
	@SuppressWarnings("unchecked")
	public T[] ToArray() {
		T[] array = (T[])new Object[_size];
		
		int i=0;
		for (Node<T> p = _head; p != null; p = p.GetNext()) {
			array[i++] = p.GetData();
		}
		
		Arrays.stream(array).map(x -> (T)x).toArray();
		
		return array;
	}
	
	public LinkedList<T> Append(T value) throws NullPointerException { return Append(new Node<T>(value, null, null)); }
	public LinkedList<T> Append(Node<T> n) throws NullPointerException {
		if (n == null)
			throw new NullPointerException("n cannot be null");
		
		if (_tail == null) {
			_head = _tail = n;
		} else {
			_tail.SetNext(n);
			n.SetPrevious(_tail);
			_tail = n;
		}
		
		_size++;		
		return this;
	}
	
	public LinkedList<T> Prepend(T value) throws NullPointerException { return Prepend(new Node<T>(value, null, null)); }
	public LinkedList<T> Prepend(Node<T> n) throws NullPointerException {
		if (n == null)
			throw new NullPointerException("n cannot be null");
		
		if (_head == null) {
			_head = _tail = n;
		} else {
			_head.SetPrevious(n);
			n.SetNext(_head);
			_head = n;
		}
		
		_size++;		
		return this;
	}
	
	public LinkedList<T> Remove(Node<T> n) throws NullPointerException {
		if (n == null)
			throw new NullPointerException("n cannot be null");
		
		Node<T> prev = n.GetPrevious();
		Node<T> next = n.GetNext();
		
		if (prev != null)
			prev.SetNext(next);
		
		if (next != null)
			next.SetPrevious(prev);
		
		n.SetNext(null);
		n.SetPrevious(null);
		
		return this;
	}
	
	
}
