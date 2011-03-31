package org.handwerkszeug.dns.conf.masterfile;

import static org.handwerkszeug.util.Validation.notNull;

import java.io.File;
import java.io.InputStream;

import org.handwerkszeug.dns.conf.MasterDataHandler;
import org.handwerkszeug.dns.conf.MasterDataResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.common.util.FileUtil;

/**
 * RFC1035 5. MASTER FILES
 * 
 * @author taichi
 */
public class MasterFileParser implements MasterDataResource {

	static final Logger LOG = LoggerFactory.getLogger(MasterFileParser.class);

	final Partitioner partitioner;

	public MasterFileParser(File master) {
		this(FileUtil.open(master));
	}

	public MasterFileParser(InputStream in) {
		this.partitioner = new Partitioner(in);
	}

	@Override
	public void initialize() {
		if (LOG.isInfoEnabled()) {
			LOG.info("initialize");
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		if (LOG.isInfoEnabled()) {
			LOG.info("dispose");
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void process(MasterDataHandler processor) {
		notNull(processor, "processor");
		try {
			processor.initialize();
			// TODO not implemented...
		} catch (RuntimeException e) {
			processor.rollback();
			throw e;
		} finally {
			processor.dispose();
		}
	}
}
