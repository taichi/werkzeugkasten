package com.google.werkzeugkasten.core.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BeansMap<T> implements Map<String, Object> {

	private static final long serialVersionUID = 994547742000273679L;

	protected Map<String, MapDescriptor<T>> descriptors;

	protected T bean;

	public BeansMap(T t, Map<String, MapDescriptor<T>> descriptors) {
		this.bean = t;
		this.descriptors = descriptors;
	}

	@Override
	public void clear() {
		for (String key : this.descriptors.keySet()) {
			MapDescriptor<T> desc = this.descriptors.get(key);
			if (desc != null) {
				desc.set(this.bean, null);
			}
		}
	}

	@Override
	public boolean isEmpty() {
		return this.descriptors.isEmpty();
	}

	@Override
	public int size() {
		return this.descriptors.size();
	}

	@Override
	public boolean containsKey(Object key) {
		return descriptors.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		if (value == null) {
			return containsNullValue();
		}
		for (String s : this.descriptors.keySet()) {
			MapDescriptor<T> desc = this.descriptors.get(s);
			if (value.equals(desc.get(this.bean)))
				return true;
		}
		return false;
	}

	protected boolean containsNullValue() {
		for (String s : this.descriptors.keySet()) {
			MapDescriptor<T> desc = this.descriptors.get(s);
			if (desc.get(this.bean) == null)
				return true;
		}
		return false;
	}

	@Override
	public Object get(Object key) {
		MapDescriptor<T> desc = this.descriptors.get(key);
		return desc != null ? desc.get(this.bean) : null;
	}

	@Override
	public Object put(String key, Object value) {
		MapDescriptor<T> desc = this.descriptors.get(key);
		if (desc != null) {
			desc.set(this.bean, value);
		}
		return value;
	}

	@Override
	public Object remove(Object key) {
		Object result = null;
		MapDescriptor<T> desc = this.descriptors.get(key);
		if (desc != null) {
			result = desc.get(this.bean);
			desc.set(this.bean, null);
		}
		return result;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		for (String key : m.keySet()) {
			MapDescriptor<T> desc = this.descriptors.get(key);
			if (desc != null) {
				desc.set(this.bean, m.get(key));
			}
		}
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		Set<java.util.Map.Entry<String, Object>> result = new HashSet<Entry<String, Object>>();
		for (String key : this.descriptors.keySet()) {
			result.add(new BeanEntry(key));
		}
		return result;
	}

	protected class BeanEntry implements java.util.Map.Entry<String, Object> {
		protected String key;

		public BeanEntry(String key) {
			this.key = key;
		}

		@Override
		public String getKey() {
			return this.key;
		}

		@Override
		public Object getValue() {
			MapDescriptor<T> desc = BeansMap.this.descriptors.get(this.key);
			if (desc != null) {
				return desc.get(BeansMap.this.bean);
			}
			return null;
		}

		@Override
		public Object setValue(Object value) {
			MapDescriptor<T> desc = BeansMap.this.descriptors.get(this.key);
			if (desc != null) {
				desc.set(BeansMap.this.bean, value);
			}
			return value;
		}

	}

	@Override
	public Set<String> keySet() {
		return this.descriptors.keySet();
	}

	@Override
	public Collection<Object> values() {
		Collection<Object> result = new ArrayList<Object>();
		for (String key : this.descriptors.keySet()) {
			MapDescriptor<T> desc = this.descriptors.get(key);
			if (desc != null) {
				result.add(desc.get(this.bean));
			}
		}
		return result;
	}
}
