package org.handwerkszeug.dns.conf;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.dns.conf.Partition.PartitionType;
import org.junit.Test;

public class PartitionerTest {

	@Test
	public void testPartition() throws Exception {
		String s = "A \t\tB C;hogehoge fugafuga\r\n\nmogemoge\t\t\tABC\"aaaa \n bbbbb\"";
		// String s = "ZYX\t\t\tABC";
		// String s = "\nmogemoge";

		List<Partition> list = new ArrayList<Partition>();
		list.add(new Partition(PartitionType.Default, "A".getBytes()));
		list.add(new Partition(PartitionType.Whitespace, " \t\t".getBytes()));
		list.add(new Partition(PartitionType.Default, "B".getBytes()));
		list.add(new Partition(PartitionType.Whitespace, " ".getBytes()));
		list.add(new Partition(PartitionType.Default, "C".getBytes()));
		list.add(new Partition(PartitionType.Comment, ";hogehoge fugafuga\r\n"
				.getBytes()));
		list.add(Partition.EOL);
		list.add(new Partition(PartitionType.Default, "mogemoge".getBytes()));
		list.add(new Partition(PartitionType.Whitespace, "\t\t\t".getBytes()));
		list.add(new Partition(PartitionType.Default, "ABC".getBytes()));
		list.add(new Partition(PartitionType.Quoted, "\"aaaa \n bbbbb\""
				.getBytes()));

		Partitioner p = create(s);
		for (Partition exp : list) {
			Partition act = p.partition();
			System.out.println(act);
			assertEquals(exp, act);
		}

		// while (true) {
		// Partition pp = p.partition();
		// if (pp == Partition.EOF) {
		// break;
		// }
		// System.out.println(pp);
		// }
	}

	@Test
	public void testLPRP() throws Exception {
		String s = "\t\t\t(aaaa)";

		List<Partition> list = new ArrayList<Partition>();
		list.add(new Partition(PartitionType.Whitespace, "\t\t\t".getBytes()));
		list.add(Partition.LP);
		list.add(new Partition(PartitionType.Default, "aaaa".getBytes()));
		list.add(Partition.RP);
		Partitioner p = create(s);

		for (Partition exp : list) {
			assertEquals(exp, p.partition());
		}
	}

	Partitioner create(String testdata) throws Exception {
		ByteArrayInputStream bai = new ByteArrayInputStream(testdata.getBytes());
		return new Partitioner(bai);
	}
}
