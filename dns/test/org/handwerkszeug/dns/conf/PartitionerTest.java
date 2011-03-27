package org.handwerkszeug.dns.conf;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class PartitionerTest {

	@Test
	public void testPartition() throws Exception {
		String s = "A \t\tB C;hogehoge fugafuga\r\n\nmogemoge\t\t\tABC\"aaaa \n bbbbb\"";
		// String s = "ZYX\t\t\tABC";
		// String s = "\nmogemoge";
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
