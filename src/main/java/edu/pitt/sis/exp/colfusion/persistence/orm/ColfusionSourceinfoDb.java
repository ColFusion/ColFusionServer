package edu.pitt.sis.exp.colfusion.persistence.orm;

// Generated Feb 24, 2014 10:00:48 PM by Hibernate Tools 3.4.0.CR1

/**
 * ColfusionSourceinfoDb generated by hbm2java
 */
public class ColfusionSourceinfoDb implements java.io.Serializable {

	private int sid;
	private ColfusionSourceinfo colfusionSourceinfo;
	private String serverAddress;
	private int port;
	private String userName;
	private String password;
	private String sourceDatabase;
	private String driver;
	private Integer isLocal;
	private String linkedServerName;

	public ColfusionSourceinfoDb() {
	}

	public ColfusionSourceinfoDb(ColfusionSourceinfo colfusionSourceinfo,
			String serverAddress, int port, String userName, String password,
			String sourceDatabase, String driver) {
		this.colfusionSourceinfo = colfusionSourceinfo;
		this.serverAddress = serverAddress;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.sourceDatabase = sourceDatabase;
		this.driver = driver;
	}

	public ColfusionSourceinfoDb(ColfusionSourceinfo colfusionSourceinfo,
			String serverAddress, int port, String userName, String password,
			String sourceDatabase, String driver, Integer isLocal,
			String linkedServerName) {
		this.colfusionSourceinfo = colfusionSourceinfo;
		this.serverAddress = serverAddress;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.sourceDatabase = sourceDatabase;
		this.driver = driver;
		this.isLocal = isLocal;
		this.linkedServerName = linkedServerName;
	}

	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public ColfusionSourceinfo getColfusionSourceinfo() {
		return this.colfusionSourceinfo;
	}

	public void setColfusionSourceinfo(ColfusionSourceinfo colfusionSourceinfo) {
		this.colfusionSourceinfo = colfusionSourceinfo;
	}

	public String getServerAddress() {
		return this.serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSourceDatabase() {
		return this.sourceDatabase;
	}

	public void setSourceDatabase(String sourceDatabase) {
		this.sourceDatabase = sourceDatabase;
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Integer getIsLocal() {
		return this.isLocal;
	}

	public void setIsLocal(Integer isLocal) {
		this.isLocal = isLocal;
	}

	public String getLinkedServerName() {
		return this.linkedServerName;
	}

	public void setLinkedServerName(String linkedServerName) {
		this.linkedServerName = linkedServerName;
	}

}
