package werkzeugkasten.common.util;

import java.util.Map;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XMLStreamReaderUtil {

	public static void put(Map<String, Handler> m, Handler h) {
		m.put(h.getTagName(), h);
	}

	public static void parse(XMLStreamReader reader,
			Map<String, Handler> handlers, String end)
			throws XMLStreamException {
		for (; reader.hasNext();) {
			int event = reader.next();
			String localname = reader.getLocalName();
			if (XMLStreamConstants.START_ELEMENT == event) {
				Handler handler = handlers.get(localname);
				if (handler == null) {
					skipTo(reader, localname);
				} else {
					handler.handle(reader);
				}
			} else if (XMLStreamConstants.END_ELEMENT == event
					&& end.equals(localname)) {
				return;
			}
		}
	}

	public static void skipTo(XMLStreamReader reader, String end)
			throws XMLStreamException {
		for (; reader.hasNext();) {
			if (XMLStreamConstants.END_ELEMENT == reader.next()) {
				if (end.equals(reader.getLocalName())) {
					break;
				}
			}
		}
	}

	public interface Handler {
		String getTagName();

		void handle(XMLStreamReader reader) throws XMLStreamException;
	}

	public static class DefaultHandler implements Handler {
		protected String tag;

		public DefaultHandler(String tag) {
			this.tag = tag;
		}

		@Override
		public String getTagName() {
			return tag;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
		}
	}
}
