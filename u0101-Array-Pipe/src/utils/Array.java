package utils;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.IntUnaryOperator;

public class Array<T> implements Iterable<T> {

	private static final IntUnaryOperator DefaultReAllocStrategy = x -> x * 2;

	@SuppressWarnings( "unchecked")
	private T[] elements = ( T[]) new Object[2];

	private int size;
	private IntUnaryOperator reAllocStrategy = DefaultReAllocStrategy;

	private int modCount = 0;
	
    /**
     * An optimized version of AbstractList.Itr
     */
    private class Itr implements Iterator<T> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = Array.this.elements;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) elementData[lastRet = i];
        }

        public void remove() {
           throw new UnsupportedOperationException();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

   /**
     * An optimized version of AbstractList.ListItr
     */
    private class ListItr extends Itr implements ListIterator<T> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public T previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = Array.this.elements;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (T) elementData[lastRet = i];
        }

        public void set(T e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                Array.this.set( lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

		@Override
		public void add( T e) {
			throw new UnsupportedOperationException();
		}
    }


	public void add( T element) {
		ensureCapcity();
		elements[ size] = element;
		size++;
		modCount++;
	}

	public void set( int index, T e) {
		if ( index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		elements[ index] = e;
	}

	public int size() {
		return size;
	}

	public T get( int index) {
		if ( index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return elements[ index];
	}

	/**
	 * set reallocation strategy. If null, revert to
	 * {@link #DefaultReAllocStrategy}
	 * 
	 * @param op
	 */
	public void setReAllocStrategy( IntUnaryOperator op) {
		if ( op == null) {
			reAllocStrategy = DefaultReAllocStrategy;
		} else {
			reAllocStrategy = op;
		}
	}

	private void ensureCapcity() {
		if ( elements.length == size) {
			elements = Arrays.copyOf( elements, reAllocStrategy.applyAsInt( size));
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new ListItr( 0);
	}

	public Pipe<T> pipe() {
		return new PipeImpl( this);
	}
}
