package org.handwerkszeug.kvsview.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.handwerkszeug.kvsview.Filter;

import voldemort.annotations.concurrency.NotThreadsafe;
import voldemort.versioning.Versioned;

@NotThreadsafe
public class VersionedFilteringIterator<V> implements Iterator<Versioned<V>> {

	Filter<Versioned<V>> filter;
	Iterator<Versioned<V>> delegate;
	Versioned<V> nextValue;

	public VersionedFilteringIterator(Filter<Versioned<V>> filter,
			Iterator<Versioned<V>> delegate) {
		this.filter = filter;
		this.delegate = delegate;
	}

	@Override
	public boolean hasNext() {
		while (this.delegate.hasNext()) {
			Versioned<V> v = this.delegate.next();
			if (this.filter.filter(v)) {
				this.nextValue = v;
				return true;
			}
		}
		return false;
	}

	@Override
	public Versioned<V> next() {
		if (this.nextValue == null) {
			throw new NoSuchElementException();
		}
		Versioned<V> returnValue = this.nextValue;
		this.nextValue = null;
		return returnValue;
	}

	@Override
	public void remove() {
		this.delegate.remove();
	}

}
