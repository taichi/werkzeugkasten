package org.handwerkszeug.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.Event.ID;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.parser.Parser;
import org.yaml.snakeyaml.parser.ParserImpl;
import org.yaml.snakeyaml.reader.StreamReader;

public class YamlEventParserTest {

	static final String PATH = ClassUtil
			.toPackagePath(YamlEventParserTest.class);

	@Test
	public void testSkipMap() {
		Parser p = create("map.yml");
		assertTrue(p.getEvent().is(ID.MappingStart));

		Event outer = p.getEvent();
		assertTrue(outer.is(ID.Scalar));
		ScalarEvent se = (ScalarEvent) outer;
		assertEquals("outer", se.getValue());

		Event e = p.getEvent();
		assertTrue(e.is(ID.MappingStart));
		YamlEventParser target = new YamlEventParser(p);
		target.skip(e);
		e = p.getEvent();
		assertTrue(e.is(ID.Scalar));

	}

	@Test
	public void testSkipSeq() {
		Parser p = create("seq.yml");

		Event e = p.getEvent();
		assertTrue(e.is(ID.SequenceStart));
		e = p.getEvent();
		assertTrue(e.is(ID.SequenceStart));
		YamlEventParser target = new YamlEventParser(p);
		target.skip(e);
		e = p.getEvent();
		assertTrue(e.is(ID.SequenceStart));
		e = p.getEvent();
		assertTrue(e.is(ID.Scalar));
	}

	@Test
	public void testSkipMapSeq() {
		Parser p = create("mapseq.yml");
		assertTrue(p.getEvent().is(ID.MappingStart));

		Event outer = p.getEvent();
		assertTrue(outer.is(ID.Scalar));
		ScalarEvent se = (ScalarEvent) outer;
		assertEquals("outer", se.getValue());

		Event e = p.getEvent();
		assertTrue(e.is(ID.SequenceStart));
		YamlEventParser target = new YamlEventParser(p);
		target.skip(e);

		e = p.getEvent();
		assertTrue(e.is(ID.Scalar));
		se = (ScalarEvent) e;
		assertEquals("outer", se.getValue());

		e = p.getEvent();
		assertTrue(e.is(ID.Scalar));
		se = (ScalarEvent) e;
		assertEquals("7", se.getValue());
	}

	protected Parser create(String path) {
		InputStream in = YamlEventParserTest.class.getResourceAsStream(path);
		assertNotNull(in);
		Parser p = new ParserImpl(new StreamReader(new InputStreamReader(in)));
		assertEquals(true, p.getEvent().is(ID.StreamStart));
		assertTrue(p.getEvent().is(ID.DocumentStart));
		return p;
	}
}
