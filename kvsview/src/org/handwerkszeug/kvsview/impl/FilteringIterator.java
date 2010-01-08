package org.handwerkszeug.kvsview.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.handwerkszeug.kvsview.Filter;

import voldemort.annotations.concurrency.NotThreadsafe;

@NotThreadsafe
public class FilteringIterator<V> implements Iterator<V> {

	Filter<V> filter;
	Iterator<V> delegate;
	V nextValue;

	public FilteringIterator(Filter<V> filter, Iterator<V> delegate) {
		this.filter = filter;
		this.delegate = delegate;
	}

	@Override
	public boolean hasNext() {
		while (this.delegate.hasNext()) {
			V v = this.delegate.next();
			if (this.filter.filter(v)) {
				this.nextValue = v;
				return true;
			}
		}
		return false;
	}

	@Override
	public V next() {
		if (this.nextValue == null) {
			throw new NoSuchElementException();
		}
		V returnValue = this.nextValue;
		this.nextValue = null;
		return returnValue;
	}

	@Override
	public void remove() {
		this.delegate.remove();
	}

}
