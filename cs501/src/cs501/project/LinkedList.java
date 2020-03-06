package cs501.project;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;

import cs501.project.Node;

public class LinkedList<T> {
	
	public class LinkedListIterator implements Iterable<T> {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public java.util.Iterator iterator() {
			java.util.Iterator iterator = new java.util.Iterator() {
				private Node<T> current = Head();
				
				@Override
				public boolean hasNext() { 
					return current != null; 
				}

				@Override
				public T next() {
					T value = current.GetData();
					current = current.GetNext(); 
					return value; 
				}				
			};
			
			return iterator;
		}
		
	}
	
	private int _size = 0;
	private Node<T> _head = null;
	private Node<T> _tail = null;
	private Class<T> _class = null;
	
	// there has to be a better way of creating generic arrays
	// but I cannot for the life of me find it
	public LinkedList(Class<T> cls) {
		_class = cls;
	}
	
	public int Size() { return _size; }
	
	public boolean Empty() { return Size() == 0; }
	
	protected Node<T> Head() { return _head; }
	
	protected Node<T> Tail() { return _tail; }
	
	@SuppressWarnings({ "rawtypes" })
	public java.util.Iterator Iterator() { return new LinkedListIterator().iterator(); }
	
	public T Get(int index) {
		if (index < 0 || index >= Size())
			throw new IllegalArgumentException("invalid index: 0 <= " + index + " < " + Size());
		
		int i=0;
		Node<T> p = _head;
		for (; i != index && p != null; p = p.GetNext(), ++i) {
			
		}
		
		return p.GetData();
	}
	
	@SuppressWarnings("unchecked")
	public T[] ToArray() {
		T[] array = (T[])Array.newInstance(_class, _size);
		int i=0;
		
		for (Node<T> p = _head; p != null; p = p.GetNext()) {
			array[i++] = p.GetData();
		}
		
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
		
		if (n.equals(_head)) {
			_head = _head.GetNext();
		}
		
		if (n.equals(_tail)) {
			_tail = _tail.GetPrevious();
		}
		
		if (prev != null)
			prev.SetNext(next);
		
		if (next != null)
			next.SetPrevious(prev);
		
		n.SetNext(null);
		n.SetPrevious(null);
		
		--_size;
		
		return this;
	}
	
	public LinkedList<T> RemoveAt(int index) {
		if (index < 0 || index >= Size())
			throw new IllegalArgumentException("invalid index: 0 <= " + index + " < " + Size());
		
		int i=0;
		Node<T> p = _head;
		for (; i != index && p != null; p = p.GetNext(), ++i) {
			
		}
		
		return Remove(p);
	}

	@Override
	public String toString() {
		String result = "[";
		Node<T> p = Head();
		
		if (p == null)
			return "[]";
		
		while (true) {
			result += "'" + p.GetData() + "'";
			
			if (p.GetNext() != null) {
				result += ", ";
				p = p.GetNext();
			} else {
				break;
			}
		}
		
		result += "]";
		
		return result;
//		return super.toString();
	}
	
	
	
	
}
