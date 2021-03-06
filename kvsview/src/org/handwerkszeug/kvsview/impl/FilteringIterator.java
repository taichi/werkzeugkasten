package org.handwerkszeug.kvsview.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.handwerkszeug.kvsview.Filter;

import voldemort.annotations.concurrency.NotThreadsafe;
import voldemort.versioning.Versioned;

@NotThreadsafe
public class FilteringIterator<V> implements Iterator<V> {

	Filter<V> filter;
	Iterator<Versioned<V>> delegate;
	V nextValue;

	public FilteringIterator(Filter<V> filter, Iterator<Versioned<V>> delegate) {
		this.filter = filter;
		this.delegate = delegate;
	}

	@Override
	public boolean hasNext() {
		while (this.delegate.hasNext()) {
			Versioned<V> v = this.delegate.next();
			V nv = v.getValue();
			if (this.filter.filter(nv)) {
				this.nextValue = nv;
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
