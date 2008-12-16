package werkzeugkasten.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * ConverterUtil is an utility class that converts object.
 * 
 * @author shot
 * @author taichi
 */
public class ConverterUtil {

	public static final Integer INT_DEFAULT_VALUE = new Integer(0);

	public static final Double DOUBLE_DEFAULT_VALUE = new Double(0);

	public static final Long LONG_DEFAULT_VALUE = new Long(0);

	public static final Float FLOAT_DEFAULT_VALUE = new Float(0);

	public static final Short SHORT_DEFAULT_VALUE = new Short((short) 0);

	public static final Boolean BOOLEAN_DEFAULT_VALUE = Boolean.FALSE;

	public static final Byte BYTE_DEFAULT_VALUE = Byte.valueOf((byte) 0);

	public static final Character CHAR_DEFAULT_VALUE = Character
			.valueOf((char) 0);

	public static final String NULL_PATTERN = null;

	public static final Pattern YES_PATTERN = Pattern.compile("(yes|true|y|0)",
			Pattern.CASE_INSENSITIVE);

	private static Map<Class<?>, Converter<?>> map = new HashMap<Class<?>, Converter<?>>();

	private static boolean initialized = false;

	public static void init() {
		if (!initialized) {
			synchronized (ConverterUtil.class) {
				init0();
			}
		}
	}

	private static void init0() {
		map.put(BigDecimal.class, BIGDECIMAL_CONVERTER);
		map.put(BigInteger.class, BIGINTEGER_CONVERTER);
		map.put(Byte.class, BYTE_CONVERTER);
		map.put(byte.class, PRIMITIVE_BYTE_CONVERTER);
		map.put(byte[].class, BINARY_CONVERTER);
		map.put(Boolean.class, BOOLEAN_CONVERTER);
		map.put(boolean.class, PRIMITIVE_BOOLEAN_CONVERTER);
		map.put(Calendar.class, CALENDAR_CONVERTER);
		map.put(Date.class, DATE_CONVERTER);
		map.put(Double.class, DOUBLE_CONVERTER);
		map.put(double.class, PRIMITIVE_DOUBLE_CONVERTER);
		map.put(Float.class, FLOAT_CONVERTER);
		map.put(float.class, PRIMITIVE_FLOAT_CONVERTER);
		map.put(Integer.class, INTEGER_CONVERTER);
		map.put(int.class, PRIMITIVE_INTEGER_CONVERTER);
		map.put(Long.class, LONG_CONVERTER);
		map.put(long.class, PRIMITIVE_LONG_CONVERTER);
		map.put(Short.class, SHORT_CONVERTER);
		map.put(short.class, PRIMITIVE_SHORT_CONVERTER);
		map.put(java.sql.Date.class, SQLDATE_CONVERTER);
		map.put(String.class, STRING_CONVERTER);
		map.put(Time.class, TIME_CONVERTER);
		map.put(Timestamp.class, TIMESTAMP_CONVERTER);
	}

	public static void clear() {
		map.clear();
		init();
	}

	public static <T> T convert(Object target, Class<T> convertClass) {
		return convert(target, convertClass, NULL_PATTERN);
	}

	@SuppressWarnings("unchecked")
	public static <T> T convert(Object target, Class<T> convertClass,
			String pattern) {
		init();
		Converter<T> converter = (Converter<T>) map.get(convertClass);
		if (converter == null && convertClass.isInstance(target)) {
			return (T) target;
		}
		return converter.convert(target, pattern);
	}

	public static String convertAsString(Object o) {
		return convertAsString(o, NULL_PATTERN);
	}

	public static String convertAsString(Object o, String pattern) {
		return STRING_CONVERTER.convert(o, pattern);
	}

	public static BigDecimal convertAsBigDecimal(Object o) {
		return convertAsBigDecimal(o, NULL_PATTERN);
	}

	public static BigDecimal convertAsBigDecimal(Object o, String pattern) {
		return BIGDECIMAL_CONVERTER.convert(o, pattern);
	}

	public static BigInteger convertAsBigInteger(Object o) {
		return convertAsBigInteger(o, NULL_PATTERN);
	}

	public static BigInteger convertAsBigInteger(Object o, String pattern) {
		return BIGINTEGER_CONVERTER.convert(o, pattern);
	}

	public static byte[] convertAsBinary(Object o) {
		return convertAsBinary(o, NULL_PATTERN);
	}

	public static byte[] convertAsBinary(Object o, String pattern) {
		return BINARY_CONVERTER.convert(o, pattern);
	}

	public static Boolean convertAsBoolean(Object o) {
		return convertAsBoolean(o, NULL_PATTERN);
	}

	public static Boolean convertAsBoolean(Object o, String pattern) {
		return BOOLEAN_CONVERTER.convert(o, pattern);
	}

	public static Boolean convertAsPrimitiveBoolean(Object o) {
		return convertAsPrimitiveBoolean(o, NULL_PATTERN);
	}

	public static Boolean convertAsPrimitiveBoolean(Object o, String pattern) {
		return PRIMITIVE_BOOLEAN_CONVERTER.convert(o, pattern);
	}

	public static Byte convertAsByte(Object o) {
		return convertAsByte(o, NULL_PATTERN);
	}

	public static Byte convertAsByte(Object o, String pattern) {
		return BYTE_CONVERTER.convert(o, pattern);
	}

	public static Byte convertAsPrimitiveByte(Object o) {
		return convertAsPrimitiveByte(o, NULL_PATTERN);
	}

	public static Byte convertAsPrimitiveByte(Object o, String pattern) {
		return PRIMITIVE_BYTE_CONVERTER.convert(o, pattern);
	}

	public static Double convertAsDouble(Object o) {
		return convertAsDouble(o, NULL_PATTERN);
	}

	public static Double convertAsDouble(Object o, String pattern) {
		return DOUBLE_CONVERTER.convert(o, pattern);
	}

	public static Double convertAsPrimitiveDouble(Object o) {
		return convertAsPrimitiveDouble(o, NULL_PATTERN);
	}

	public static Double convertAsPrimitiveDouble(Object o, String pattern) {
		return PRIMITIVE_DOUBLE_CONVERTER.convert(o, pattern);
	}

	public static Float convertAsFloat(Object o) {
		return convertAsFloat(o, NULL_PATTERN);
	}

	public static Float convertAsFloat(Object o, String pattern) {
		return FLOAT_CONVERTER.convert(o, pattern);
	}

	public static Float convertAsPrimitiveFloat(Object o) {
		return convertAsPrimitiveFloat(o, NULL_PATTERN);
	}

	public static Float convertAsPrimitiveFloat(Object o, String pattern) {
		return PRIMITIVE_FLOAT_CONVERTER.convert(o, pattern);
	}

	public static Integer convertAsInteger(Object o) {
		return convertAsInteger(o, NULL_PATTERN);
	}

	public static Integer convertAsInteger(Object o, String pattern) {
		return INTEGER_CONVERTER.convert(o, pattern);
	}

	public static Integer convertAsPrimitiveInteger(Object o) {
		return convertAsPrimitiveInteger(o, NULL_PATTERN);
	}

	public static Integer convertAsPrimitiveInteger(Object o, String pattern) {
		return PRIMITIVE_INTEGER_CONVERTER.convert(o, pattern);
	}

	public static Long convertAsLong(Object o) {
		return convertAsLong(o, NULL_PATTERN);
	}

	public static Long convertAsLong(Object o, String pattern) {
		return LONG_CONVERTER.convert(o, pattern);
	}

	public static Long convertAsPrimitiveLong(Object o) {
		return convertAsPrimitiveLong(o, NULL_PATTERN);
	}

	public static Long convertAsPrimitiveLong(Object o, String pattern) {
		return PRIMITIVE_LONG_CONVERTER.convert(o, pattern);
	}

	public static Short convertAsShort(Object o) {
		return convertAsShort(o, NULL_PATTERN);
	}

	public static Short convertAsShort(Object o, String pattern) {
		return SHORT_CONVERTER.convert(o, pattern);
	}

	public static Short convertAsPrimitiveShort(Object o) {
		return convertAsPrimitiveShort(o, NULL_PATTERN);
	}

	public static Short convertAsPrimitiveShort(Object o, String pattern) {
		return PRIMITIVE_SHORT_CONVERTER.convert(o, pattern);
	}

	public static java.sql.Date convertAsSqlDate(Object o) {
		return convertAsSqlDate(o, NULL_PATTERN);
	}

	public static java.sql.Date convertAsSqlDate(Object o, String pattern) {
		return SQLDATE_CONVERTER.convert(o, pattern);
	}

	public static Timestamp convertAsTimestamp(Object o) {
		return convertAsTimestamp(o, NULL_PATTERN);
	}

	public static Timestamp convertAsTimestamp(Object o, String pattern) {
		return TIMESTAMP_CONVERTER.convert(o, pattern);
	}

	public static Time convertAsTime(Object o) {
		return convertAsTime(o, NULL_PATTERN);
	}

	public static Time convertAsTime(Object o, String pattern) {
		return TIME_CONVERTER.convert(o, pattern);
	}

	public static Calendar convertAsCalendar(Object o) {
		return convertAsCalendar(o, NULL_PATTERN);
	}

	public static Calendar convertAsCalendar(Object o, String pattern) {
		return CALENDAR_CONVERTER.convert(o, pattern);
	}

	public static Date convertAsDate(Object o) {
		return convertAsDate(o, NULL_PATTERN);
	}

	public static Date convertAsDate(Object o, String pattern) {
		return DATE_CONVERTER.convert(o, pattern);
	}

	public static interface Converter<T> {

		T convert(Object o);

		T convert(Object o, String pattern);

	}

	public static Converter<BigDecimal> BIGDECIMAL_CONVERTER = new Converter<BigDecimal>() {

		@Override
		public BigDecimal convert(Object o, String pattern) {
			if (o == null) {
				return null;
			}
			if (BigDecimal.class.isInstance(o)) {
				return (BigDecimal) o;
			} else if (o instanceof java.util.Date) {
				if (pattern != null) {
					return new BigDecimal(new SimpleDateFormat(pattern)
							.format(o));
				} else {
					return new BigDecimal(((java.util.Date) o).getTime());
				}
			} else if (Integer.class.isInstance(o)) {
				int i = Integer.class.cast(o).intValue();
				return new BigDecimal(i);
			} else if (Double.class.isInstance(o)) {
				double d = Double.class.cast(o).doubleValue();
				return new BigDecimal(d);
			} else if (Long.class.isInstance(o)) {
				long l = Long.class.cast(o).longValue();
				return new BigDecimal(l);
			} else if (Float.class.isInstance(o)) {
				float f = Float.class.cast(o).floatValue();
				return new BigDecimal(f);
			} else if (Byte.class.isInstance(o)) {
				byte b = Byte.class.cast(o).byteValue();
				return new BigDecimal(b);
			} else if (BigInteger.class.isInstance(o)) {
				BigInteger bi = BigInteger.class.cast(o);
				return new BigDecimal(bi);
			} else if (String.class.isInstance(o)) {
				String s = DecimalFormatUtil.normalize((String) o);
				if (StringUtil.isEmpty(s)) {
					return null;
				} else {
					return new BigDecimal(s);
				}
			} else {
				String s = DecimalFormatUtil.normalize((String) o);
				return new BigDecimal(s);
			}

		}

		@Override
		public BigDecimal convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

	};

	public static Converter<BigInteger> BIGINTEGER_CONVERTER = new Converter<BigInteger>() {

		@Override
		public BigInteger convert(Object o, String pattern) {
			if (o == null) {
				return null;
			} else if (o instanceof BigInteger) {
				return (BigInteger) o;
			} else {
				long l = PRIMITIVE_LONG_CONVERTER.convert(o, pattern)
						.longValue();
				return BigInteger.valueOf(l);
			}
		}

		@Override
		public BigInteger convert(Object o) {
			return convert(o, null);
		}

	};

	public static Converter<Byte> BYTE_CONVERTER = new Converter<Byte>() {

		@Override
		public Byte convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

		protected Byte toByte(String s) {
			if (StringUtil.isEmpty(s)) {
				return null;
			}
			return new Byte(DecimalFormatUtil.normalize(s));
		}

		@Override
		public Byte convert(Object o, String pattern) {
			if (o == null) {
				return null;
			} else if (o instanceof Byte) {
				return (Byte) o;
			} else if (o instanceof Number) {
				return new Byte(((Number) o).byteValue());
			} else if (o instanceof String) {
				return toByte((String) o);
			} else if (o instanceof java.util.Date) {
				if (pattern != null) {
					return new Byte(new SimpleDateFormat(pattern).format(o));
				}
				return new Byte((byte) ((java.util.Date) o).getTime());
			} else if (o instanceof Boolean) {
				return ((Boolean) o).booleanValue() ? new Byte((byte) 1)
						: new Byte((byte) 0);
			} else {
				return toByte(o.toString());
			}
		}
	};

	public static Converter<Byte> PRIMITIVE_BYTE_CONVERTER = new Converter<Byte>() {

		@Override
		public Byte convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

		@Override
		public Byte convert(Object o, String pattern) {
			Byte convert = BYTE_CONVERTER.convert(o, pattern);
			if (convert == null) {
				return BYTE_DEFAULT_VALUE;
			}
			return convert;
		}

	};

	public static Converter<byte[]> BINARY_CONVERTER = new Converter<byte[]>() {

		@Override
		public byte[] convert(Object o) {
			if (o instanceof byte[]) {
				return (byte[]) o;
			} else if (o == null) {
				return null;
			} else {
				if (o instanceof String) {
					return ((String) o).getBytes();
				}
				throw new IllegalArgumentException(o.getClass().toString());
			}
		}

		@Override
		public byte[] convert(Object o, String pattern) {
			return convert(o);
		}

	};

	public static Converter<Boolean> BOOLEAN_CONVERTER = new Converter<Boolean>() {

		@Override
		public Boolean convert(Object value) {
			if (value == null) {
				return null;
			}
			if (Boolean.class.isInstance(value)) {
				return Boolean.class.cast(value);
			} else if (String.class.isInstance(value)) {
				String s = String.class.cast(value);
				return Boolean.valueOf(YES_PATTERN.matcher(s).matches());
			} else if (Number.class.isInstance(value)) {
				Number n = Number.class.cast(value);
				return Boolean.valueOf(n.intValue() != 0);
			} else {
				return Boolean.TRUE;
			}
		}

		@Override
		public Boolean convert(Object o, String pattern) {
			return convert(o);
		}

	};

	public static Converter<Boolean> PRIMITIVE_BOOLEAN_CONVERTER = new Converter<Boolean>() {

		@Override
		public Boolean convert(Object o) {
			Boolean convert = BOOLEAN_CONVERTER.convert(o);
			if (convert == null) {
				return BOOLEAN_DEFAULT_VALUE;
			}
			return convert;
		}

		@Override
		public Boolean convert(Object o, String pattern) {
			return convert(o);
		}

	};

	public static Converter<Calendar> CALENDAR_CONVERTER = new Converter<Calendar>() {

		@Override
		public Calendar convert(Object o, String pattern) {
			if (o instanceof Calendar) {
				return (Calendar) o;
			}
			java.util.Date date = DATE_CONVERTER.convert(o, pattern);
			if (date != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				return cal;
			}
			return null;
		}

		@Override
		public Calendar convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

	};

	public abstract static class DateConverter implements Converter<Date> {

		public Date toDate(String s, String pattern) {
			return toDate(s, pattern, Locale.getDefault());
		}

		public Date toDate(String s, String pattern, Locale locale) {
			SimpleDateFormat sdf = getDateFormat(s, pattern, locale);
			try {
				return sdf.parse(s);
			} catch (ParseException ex) {
				throw new IllegalStateException(ex);
			}
		}

		public SimpleDateFormat getDateFormat(String s, String pattern,
				Locale locale) {
			if (pattern != null) {
				return createSimpleDateFormat(pattern);
			}
			return getDateFormat(s, locale);
		}

		public SimpleDateFormat getDateFormat(String s, Locale locale) {
			String pattern = getPattern(locale);
			String shortPattern = removeDelimiter(pattern);
			String delimitor = findDelimiter(s);
			if (delimitor == null) {
				if (s.length() == shortPattern.length()) {
					return createSimpleDateFormat(shortPattern);
				}
				if (s.length() == shortPattern.length() + 2) {
					return createSimpleDateFormat(StringUtil.replace(
							shortPattern, "yy", "yyyy"));
				}
			} else {
				String[] array = s.split(delimitor);
				for (int i = 0; i < array.length; ++i) {
					if (array[i].length() == 4) {
						pattern = StringUtil.replace(pattern, "yy", "yyyy");
						break;
					}
				}
				return createSimpleDateFormat(pattern);
			}
			return createSimpleDateFormat();
		}

		public SimpleDateFormat getDateFormat(Locale locale) {
			return createSimpleDateFormat(getPattern(locale));
		}

		public SimpleDateFormat getY4DateFormat(Locale locale) {
			return createSimpleDateFormat(getY4Pattern(locale));
		}

		public String getY4Pattern(Locale locale) {
			String pattern = getPattern(locale);
			if (pattern.indexOf("yyyy") < 0) {
				pattern = StringUtil.replace(pattern, "yy", "yyyy");
			}
			return pattern;
		}

		public String getPattern(Locale locale) {
			SimpleDateFormat df = (SimpleDateFormat) DateFormat
					.getDateInstance(DateFormat.SHORT, locale);
			String pattern = df.toPattern();
			int index = pattern.indexOf(' ');
			if (index > 0) {
				pattern = pattern.substring(0, index);
			}
			if (pattern.indexOf("MM") < 0) {
				pattern = StringUtil.replace(pattern, "M", "MM");
			}
			if (pattern.indexOf("dd") < 0) {
				pattern = StringUtil.replace(pattern, "d", "dd");
			}
			return pattern;
		}

		public String findDelimiter(String value) {
			for (int i = 0; i < value.length(); ++i) {
				char c = value.charAt(i);
				if (Character.isDigit(c)) {
					continue;
				}
				return Character.toString(c);
			}
			return null;
		}

		public String removeDelimiter(String pattern) {
			StringBuilder builder = new StringBuilder(pattern.length());
			for (int i = 0; i < pattern.length(); ++i) {
				char c = pattern.charAt(i);
				if (c == 'y' || c == 'M' || c == 'd') {
					builder.append(c);
				}
			}
			return builder.toString();
		}

		protected SimpleDateFormat createSimpleDateFormat(String pattern) {
			return new SimpleDateFormat(pattern);
		}

		protected SimpleDateFormat createSimpleDateFormat() {
			return new SimpleDateFormat();
		}

	}

	public static DateConverter DATE_CONVERTER = new DateConverter() {

		@Override
		public Date convert(Object o, String pattern) {
			if (o == null) {
				return null;
			} else if (o instanceof String) {
				return toDate((String) o, pattern);
			} else if (o instanceof Date) {
				return (Date) o;
			} else if (o instanceof Calendar) {
				return ((Calendar) o).getTime();
			} else {
				return toDate(o.toString(), pattern);
			}
		}

		@Override
		public Date convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

	};

	public static Converter<Double> DOUBLE_CONVERTER = new Converter<Double>() {

		@Override
		public Double convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

		@Override
		public Double convert(Object o, String pattern) {
			if (o == null) {
				return null;
			} else if (o instanceof Double) {
				return (Double) o;
			} else if (o instanceof Number) {
				return new Double(((Number) o).doubleValue());
			} else if (o instanceof String) {
				return toDouble((String) o);
			} else if (o instanceof java.util.Date) {
				if (pattern != null) {
					return new Double(new SimpleDateFormat(pattern).format(o));
				}
				return new Double(((java.util.Date) o).getTime());
			} else {
				return toDouble(o.toString());
			}
		}

		protected Double toDouble(String s) {
			return new Double(DecimalFormatUtil.normalize(s));
		}

	};

	public static Converter<Double> PRIMITIVE_DOUBLE_CONVERTER = new Converter<Double>() {

		@Override
		public Double convert(Object o, String pattern) {
			Double convert = DOUBLE_CONVERTER.convert(o, pattern);
			if (convert == null) {
				return DOUBLE_DEFAULT_VALUE;
			}
			return convert;
		}

		@Override
		public Double convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

	};

	public static Converter<Float> FLOAT_CONVERTER = new Converter<Float>() {

		@Override
		public Float convert(Object o, String pattern) {
			if (o == null) {
				return null;
			} else if (o instanceof Float) {
				return (Float) o;
			} else if (o instanceof Number) {
				return new Float(((Number) o).floatValue());
			} else if (o instanceof String) {
				return toFloat((String) o);
			} else if (o instanceof java.util.Date) {
				if (pattern != null) {
					return new Float(new SimpleDateFormat(pattern).format(o));
				}
				return new Float(((java.util.Date) o).getTime());
			} else {
				return toFloat(o.toString());
			}
		}

		protected Float toFloat(String s) {
			return new Float(DecimalFormatUtil.normalize(s));
		}

		@Override
		public Float convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

	};

	public static Converter<Float> PRIMITIVE_FLOAT_CONVERTER = new Converter<Float>() {

		@Override
		public Float convert(Object o, String pattern) {
			Float convert = FLOAT_CONVERTER.convert(o, pattern);
			if (convert == null) {
				return FLOAT_DEFAULT_VALUE;
			}
			return convert;
		}

		@Override
		public Float convert(Object o) {
			return convert(o, NULL_PATTERN);
		}
	};

	public static Converter<Integer> INTEGER_CONVERTER = new Converter<Integer>() {

		@Override
		public Integer convert(Object o, String pattern) {
			if (o == null) {
				return null;
			} else if (o instanceof Integer) {
				return (Integer) o;
			} else if (o instanceof Number) {
				return new Integer(((Number) o).intValue());
			} else if (o instanceof String) {
				return toInteger((String) o);
			} else if (o instanceof java.util.Date) {
				if (pattern != null) {
					return new Integer(new SimpleDateFormat(pattern).format(o));
				}
				return new Integer((int) ((java.util.Date) o).getTime());
			} else if (o instanceof Boolean) {
				return ((Boolean) o).booleanValue() ? new Integer(1)
						: new Integer(0);
			} else {
				return toInteger(o.toString());
			}
		}

		protected Integer toInteger(String s) {
			return new Integer(DecimalFormatUtil.normalize(s));
		}

		@Override
		public Integer convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

	};

	public static Converter<Integer> PRIMITIVE_INTEGER_CONVERTER = new Converter<Integer>() {

		@Override
		public Integer convert(Object o, String pattern) {
			Integer convert = INTEGER_CONVERTER.convert(o, pattern);
			if (convert == null) {
				return INT_DEFAULT_VALUE;
			}
			return convert;
		}

		@Override
		public Integer convert(Object o) {
			return convert(o, NULL_PATTERN);
		}
	};

	public static Converter<Long> LONG_CONVERTER = new Converter<Long>() {

		@Override
		public Long convert(Object o, String pattern) {
			if (o == null) {
				return null;
			} else if (o instanceof Long) {
				return (Long) o;
			} else if (o instanceof Number) {
				return new Long(((Number) o).longValue());
			} else if (o instanceof String) {
				return toLong((String) o);
			} else if (o instanceof java.util.Date) {
				if (pattern != null) {
					return new Long(new SimpleDateFormat(pattern).format(o));
				}
				return new Long(((java.util.Date) o).getTime());
			} else if (o instanceof Boolean) {
				return ((Boolean) o).booleanValue() ? new Long(1) : new Long(0);
			} else {
				return toLong(o.toString());
			}
		}

		protected Long toLong(String s) {
			return new Long(DecimalFormatUtil.normalize(s));
		}

		@Override
		public Long convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

	};

	public static Converter<Long> PRIMITIVE_LONG_CONVERTER = new Converter<Long>() {

		@Override
		public Long convert(Object o, String pattern) {
			Long convert = LONG_CONVERTER.convert(o, pattern);
			if (convert == null) {
				return LONG_DEFAULT_VALUE;
			}
			return convert;
		}

		@Override
		public Long convert(Object o) {
			return convert(o, NULL_PATTERN);
		}
	};

	public static Converter<Short> SHORT_CONVERTER = new Converter<Short>() {

		@Override
		public Short convert(Object o, String pattern) {
			if (o == null) {
				return null;
			} else if (o instanceof Short) {
				return (Short) o;
			} else if (o instanceof Number) {
				return new Short(((Number) o).shortValue());
			} else if (o instanceof String) {
				return toShort((String) o);
			} else if (o instanceof java.util.Date) {
				if (pattern != null) {
					return new Short(new SimpleDateFormat(pattern).format(o));
				}
				return new Short((short) ((java.util.Date) o).getTime());
			} else if (o instanceof Boolean) {
				return ((Boolean) o).booleanValue() ? new Short((short) 1)
						: new Short((short) 0);
			} else {
				return toShort(o.toString());
			}
		}

		protected Short toShort(String s) {
			return new Short(DecimalFormatUtil.normalize(s));
		}

		@Override
		public Short convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

	};

	public static Converter<Short> PRIMITIVE_SHORT_CONVERTER = new Converter<Short>() {

		@Override
		public Short convert(Object o, String pattern) {
			Short convert = SHORT_CONVERTER.convert(o, pattern);
			if (convert == null) {
				return SHORT_DEFAULT_VALUE;
			}
			return convert;
		}

		@Override
		public Short convert(Object o) {
			return convert(o, NULL_PATTERN);
		}
	};

	public static Converter<java.sql.Date> SQLDATE_CONVERTER = new Converter<java.sql.Date>() {

		@Override
		public java.sql.Date convert(Object o, String pattern) {
			if (o instanceof Date) {
				return (java.sql.Date) o;
			}
			Date date = DATE_CONVERTER.convert(o, pattern);
			if (date != null) {
				return new java.sql.Date(date.getTime());
			}
			return null;
		}

		@Override
		public java.sql.Date convert(Object o) {
			return convert(o, NULL_PATTERN);
		}
	};

	public static Converter<String> STRING_CONVERTER = new Converter<String>() {

		@Override
		public String convert(Object value, String pattern) {
			if (value == null) {
				return null;
			} else if (value instanceof String) {
				return (String) value;
			} else if (value instanceof java.util.Date) {
				return toString((java.util.Date) value, pattern);
			} else if (value instanceof Number) {
				return toString((Number) value, pattern);
			} else if (value instanceof byte[]) {
				return Base64Util.encode((byte[]) value);
			} else {
				return value.toString();
			}
		}

		protected String toString(Number value, String pattern) {
			if (value != null) {
				if (pattern != null) {
					return new DecimalFormat(pattern).format(value);
				}
				return value.toString();
			}
			return null;
		}

		protected String toString(java.util.Date value, String pattern) {
			if (value != null) {
				if (pattern != null) {
					return new SimpleDateFormat(pattern).format(value);
				}
				return value.toString();
			}
			return null;
		}

		@Override
		public String convert(Object o) {
			return convert(o, NULL_PATTERN);
		}
	};

	public static Converter<Time> TIME_CONVERTER = new Converter<Time>() {

		@Override
		public Time convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

		@Override
		public Time convert(Object o, String pattern) {
			if (o instanceof Time) {
				return (Time) o;
			}
			Date date = DATE_CONVERTER.convert(o, pattern);
			if (date != null) {
				return new Time(date.getTime());
			}
			return null;
		}
	};

	public static Converter<Timestamp> TIMESTAMP_CONVERTER = new Converter<Timestamp>() {

		@Override
		public Timestamp convert(Object o) {
			return convert(o, NULL_PATTERN);
		}

		@Override
		public Timestamp convert(Object o, String pattern) {
			if (o instanceof Timestamp) {
				return (Timestamp) o;
			}
			Date date = DATE_CONVERTER.convert(o, pattern);
			if (date != null) {
				return new Timestamp(date.getTime());
			}
			return null;
		}
	};

}
