/**
 * 
 */
package org.handwerkszeug.kvsview;

public interface Filter<V> {
	boolean filter(V v);
}