package edu.pitt.sis.exp.colfusion.persistence.orm;

// Generated May 7, 2014 10:39:32 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * ColfusionCachedQueriesInfo generated by hbm2java
 */
public class ColfusionCachedQueriesInfo implements java.io.Serializable {

	private Integer id;
	private String query;
	private String serverAddress;
	private String port;
	private String driver;
	private String userName;
	private String password;
	private String database;
	private String tableName;
	private Date expirationDate;

	public ColfusionCachedQueriesInfo() {
	}

	public ColfusionCachedQueriesInfo(String query, String serverAddress,
			String port, String driver, String userName, String password,
			String database, String tableName, Date expirationDate) {
		this.query = query;
		this.serverAddress = serverAddress;
		this.port = port;
		this.driver = driver;
		this.userName = userName;
		this.password = password;
		this.database = database;
		this.tableName = tableName;
		this.expirationDate = expirationDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getServerAddress() {
		return this.serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
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

	public String getDatabase() {
		return this.database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
