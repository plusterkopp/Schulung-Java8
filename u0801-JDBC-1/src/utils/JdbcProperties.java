package utils;

import java.util.Properties;

public class JdbcProperties {
	private final String driver;
	private final String url;
	private final String user;
	private final String password;
	private final String schema;
	public JdbcProperties(String filename) {
		try {
			Properties props = new Properties();
			props.load(ClassLoader.getSystemResourceAsStream(filename));
			this.driver = props.getProperty("db.driver");
			this.url = props.getProperty("db.url");
			this.user = props.getProperty("db.user");
			this.password = props.getProperty("db.passsword");
			this.schema = props.getProperty("db.schema");
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	public String getDriver() {
		return driver;
	}
	public String getUrl() {
		return url;
	}
	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}
	public String getSchema() {
		return schema;
	}

	@Override
	public String toString() {
		return "DbProperties [" + driver + ", " + url + ", " + user + ", " + password + ", " + schema + "]";
	}
}
