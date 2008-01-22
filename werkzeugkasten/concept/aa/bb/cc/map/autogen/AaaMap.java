package aa.bb.cc.map.autogen;

import java.util.HashMap;
import java.util.Map;

import aa.bb.cc.map.Aaa;

import com.google.werkzeugkasten.core.map.BeansMap;
import com.google.werkzeugkasten.core.map.MapDescriptor;

public class AaaMap extends BeansMap<Aaa> {

	private static Map<String, MapDescriptor<Aaa>> sharedDescriptors;

	static {
		sharedDescriptors = new HashMap<String, MapDescriptor<Aaa>>(3);
		sharedDescriptors.put("bbb", new Bbb());
		sharedDescriptors.put("ccc", new Ccc());
		sharedDescriptors.put("ddd", new Ddd());
	}

	public AaaMap(Aaa t) {
		super(t, sharedDescriptors);
	}

	private static class Bbb implements MapDescriptor<Aaa> {

		public Object get(Aaa target) {
			return target.getBbb();
		}

		public void set(Aaa target, Object value) {
			if (value instanceof Integer) {
				target.setBbb((Integer) value);
			}
		}

	}

	private static class Ccc implements MapDescriptor<Aaa> {

		public Object get(Aaa target) {
			return target.getCcc();
		}

		public void set(Aaa target, Object value) {
			if (value instanceof String) {
				target.setCcc((String) value);
			}
		}
	}

	private static class Ddd implements MapDescriptor<Aaa> {

		public Object get(Aaa target) {
			return target.getDdd();
		}

		@SuppressWarnings("unchecked")
		public void set(Aaa target, Object value) {
			if (value instanceof Map) {
				target.setDdd((Map) value);
			}
		}
	}
}
