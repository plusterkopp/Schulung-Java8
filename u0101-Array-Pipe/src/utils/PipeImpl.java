package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PipeImpl<T> implements Pipe<T> {

	Iterable<T> iterable = null;
	final private Pipe<T> head;
	final private Pipe<T> previous;
	
	public PipeImpl( Pipe<T> p) {
		previous = p;
		head = previous.getHead();
	}

	public PipeImpl( Iterable<T> it) {
		head = this;
		previous = null;
		iterable = it;
	}

	@Override
	public Pipe<T> getHead() {
		return head;
	}

	@Override
	public Pipe<T> getPrevious() {
		return previous;
	}

	@Override
	public void forEach( Consumer<T> cons) {
		Iterator<T>	it = iterable().iterator();
		while ( it.hasNext()) {
			cons.accept( it.next());
		}
	}

	@Override
	public Pipe<T> map( Function<T, T> function) {
		Iterator<T>	it = iterable().iterator();
		return new PipeImpl<T>( previous) {
			Iterator<T> iterator() {
				return new Iterator<T>() {
					@Override
					public boolean hasNext() {
						return it.hasNext();
					}
					@Override
					public T next() {
						return function.apply( it.next());
					}
				};
			}
		};
	}

	public Pipe<T> filter_1( Predicate<T> pred) {
		Iterator<T>	it = iterable().iterator();
		Collection<T> filtered = new ArrayList<T>();
		for ( T t : iterable()) {
			if ( pred.test( t)) {
				filtered.add( t);
			}
		}
		return new PipeImpl<T>( filtered);
	}

	@Override
	public Pipe<T> filter( Predicate<T> pred) {
		Iterator<T>	it = iterable().iterator();
		return new PipeImpl<T>( this) {
			public Iterable<T>	iterable() {
				return new Iterable<T>() {
					@Override
					public Iterator<T> iterator() {
						return new Iterator<T>() {
							T	nextUnfiltered;
							T	nextFiltered;
							@Override
							public boolean hasNext() {
								while ( it.hasNext()) {
									nextUnfiltered = it.next();
									if ( pred.test( nextUnfiltered)) {
										nextFiltered = nextUnfiltered;
										return true;
									}
								}
								return false;
							}

							@Override
							public T next() {
								if ( nextFiltered != null) {
									T	result = nextFiltered;
									nextFiltered = null;
									return result;
								}
								return null;
							}
						};
					}
				};
			}
		};
	}

	@Override
	public Iterable<T> iterable() {
		if ( previous != null) {
			return previous.iterable();
		}
		return iterable;
	}


}
