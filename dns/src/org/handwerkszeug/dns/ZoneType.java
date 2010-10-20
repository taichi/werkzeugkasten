package org.handwerkszeug.dns;

public enum ZoneType {

	master {
		@Override
		public Zone newZone() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	slave {
		@Override
		public Zone newZone() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	forward {
		@Override
		public Zone newZone() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public abstract Zone newZone();
}
