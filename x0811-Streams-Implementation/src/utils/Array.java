package utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.IntFunction;

public class Array<T> implements Iterable<T> {
	@SuppressWarnings("unchecked")
	private T[] elements = (T[]) new Object[2];
	private int size;
	private final IntFunction<Integer> reallocator;
	
	public Array(IntFunction<Integer> reallocator) {
		this.reallocator = reallocator;
	}
	
	public Array() {
		this.reallocator = oldSize -> oldSize + 10;
	}

	public void add(T element) {
		this.ensureCapcity();
		this.elements[this.size] = element;
		this.size++;
	}
	public int size() {
		return this.size;
	}
	
	public T get(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		return this.elements[index];
	}
	private void ensureCapcity() {
		if (this.elements.length == size) {
			this.elements = Arrays.copyOf(elements, this.reallocator.apply(this.size));
		}
	}
	
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index;
			public boolean hasNext() {
				return index < Array.this.size;
			}
			public T next() {
				final T element = Array.this.elements[this.index];
				this.index++;
				return element;
			}
		};
	}
	
	public Stream<T> stream() {
		return (Stream<T>)new Pipeline.Head<T>(this.iterator());
	}
}
