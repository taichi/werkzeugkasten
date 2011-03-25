package org.handwerkszeug.dns.conf;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class PartitionerTest {

	@Test
	public void testPartition() throws Exception {
		// String s = "A \t\tB ;hogehoge fugafuga\r\nmogemoge\t\t\tAAA";
		String s = "\t\t\tAAA";
		ByteArrayInputStream bai = new ByteArrayInputStream(s.getBytes("UTF-8"));
		Partitioner p = new Partitioner(bai);
		while (true) {
			Partitioner.Partition pp = p.partition();
			if (pp == Partitioner.EOF) {
				break;
			}
			System.out.println(pp);
		}
	}

}
