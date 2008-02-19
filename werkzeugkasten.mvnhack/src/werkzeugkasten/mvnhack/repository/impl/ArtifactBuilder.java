package werkzeugkasten.mvnhack.repository.impl;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.mvnhack.repository.Artifact;

public class ArtifactBuilder implements StreamFilter {

	Logger logger = Logger.getLogger(ArtifactBuilder.class.getName());

	public Artifact build(InputStream pom) {
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
			InputStream stream = new BufferedInputStream(pom);
			XMLStreamReader reader = factory.createXMLStreamReader(stream,
					"UTF-8");
			reader = factory.createFilteredReader(reader, this);

			DefaultArtifact a = new DefaultArtifact();
			for (; reader.hasNext(); reader.next()) {
				String local = reader.getLocalName();
				if ("dependency".equalsIgnoreCase(local)) {
					DefaultDependency d = new DefaultDependency();
					for (; reader.hasNext(); reader.next()) {
						String e = reader.getLocalName();
						setValue(reader, e, d);
						if (reader.isEndElement()
								&& "dependency".equalsIgnoreCase(e)) {
							if (validate(d)) {
								a.add(d);
							}
							break;
						}
					}
				} else {
					setValue(reader, local, a);
				}
			}

			if (validate(a)) {
				return a;
			}
			return null;
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
		return null;
	}

	protected boolean validate(Artifact a) {
		for (String s : new String[] { a.getGroupId(), a.getArtifactId(),
				a.getVersion() }) {
			if (StringUtil.isEmpty(s)) {
				return false;
			}
		}
		return true;
	}

	protected void setValue(XMLStreamReader reader, String e, DefaultArtifact a)
			throws XMLStreamException {
		if (reader.isStartElement()) {
			if ("groupId".equalsIgnoreCase(e)) {
				a.setGroupId(reader.getElementText());
			} else if ("artifactId".equalsIgnoreCase(e)) {
				a.setArtifactId(reader.getElementText());
			} else if ("version".equalsIgnoreCase(e)) {
				a.setVersion(reader.getElementText());
			}
		}
	}

	@Override
	public boolean accept(XMLStreamReader reader) {
		return reader.isStartElement() || reader.isEndElement();
	}
}
