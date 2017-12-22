package com.apptech.apps.easypark.dao;

import java.io.IOException;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

public class HSQLEmbadedDBServer {

	private static Server hsqlServer = new Server();

	public static void run() {
		HsqlProperties p = new HsqlProperties();
		// hsqldb/data ... could wite in some authentication provider.
		p.setProperty(DBProps.DATABASE.hsqProp, DBProps.DATABASE.hsqEntry);
		p.setProperty(DBProps.DBNAME.hsqProp, DBProps.DBNAME.hsqEntry);
		p.setProperty(DBProps.PORT.hsqProp, DBProps.PORT.hsqEntry);
		try {
			hsqlServer.setProperties(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AclFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hsqlServer.setLogWriter(null); // can use custom writer
		hsqlServer.setErrWriter(null); // can use custom writer

		hsqlServer.start();
	}

	private enum DBProps {
		DBNAME("server.dbname.0", "easypark"), URL(
				"jdbc:hsqldb:hsql://localhost:4808/easypark", ""), PORT(
				"server.port", "4808"), DATABASE("server.database.0",
				"file:./memdb/park;user=sa;password=sa;hsqldb.write_delay=false");
		String hsqEntry;
		String hsqProp;

		DBProps(String hsqProp, String hsqEntry) {
			this.hsqEntry = hsqEntry;
			this.hsqProp = hsqProp;
		}

		public String hsqEntry() {
			return hsqEntry;
		}

		public String hsqProp() {
			return hsqProp;
		}
	}

	public static void main(String... arg) {
		run();
	}

}
