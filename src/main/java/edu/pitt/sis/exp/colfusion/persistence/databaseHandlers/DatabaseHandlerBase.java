/**
 * 
 */
package edu.pitt.sis.exp.colfusion.persistence.databaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.pitt.sis.exp.colfusion.persistence.managers.ExecutionInfoManager;

/**
 * @author Evgeny
 *
 */
public abstract class DatabaseHandlerBase {
	private String host;
    private int port;
    private String user;
    private String password;
    private String database;
    private DatabaseHanderType databaseHanderType;
    
    protected Connection connection;
    
    protected ExecutionInfoManager executionInfoMgr; 
    protected int executionLogId;
    
    Logger logger = LogManager.getLogger(DatabaseHandlerBase.class.getName());
    
    public DatabaseHandlerBase(final String host, final int port, final String user, final String password, final String database, final DatabaseHanderType databaseHanderType,
    		final ExecutionInfoManager executionInfoMgr, final int executionLogId) {
    	setHost(host);
    	setPort(port);
    	setUser(user);
    	setPassword(password);
    	setDatabase(database);
    	setDatabaseHanderType(databaseHanderType);
    	
    	this.executionInfoMgr = executionInfoMgr;
    	this.executionLogId = executionLogId;
    }
    
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(final String host) {
		this.host = host;
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(final int port) {
		this.port = port;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(final String user) {
		this.user = user;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}
	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}
	/**
	 * @param database the database to set
	 */
	public void setDatabase(final String database) {
		this.database = database;
	}
	/**
	 * @return the databaseHanderType
	 */
	public DatabaseHanderType getDatabaseHanderType() {
		return databaseHanderType;
	}
	/**
	 * @param databaseHanderType the databaseHanderType to set
	 */
	public void setDatabaseHanderType(final DatabaseHanderType databaseHanderType) {
		this.databaseHanderType = databaseHanderType;
	}
    
	/**
	 * Initializes the connection.
	 * @return the connection.
	 * @throws Exception 
	 */
	protected void openConnection(final String connectionString) throws Exception {
		try {
			connection = DriverManager.getConnection(connectionString, getUser(), getPassword());
			
		} catch (SQLException e) {
			
			if (executionInfoMgr != null) {
				executionInfoMgr.appendLog(executionLogId, String.format("[ERROR] openConnection failed. Error: %s", e.toString()));
			}
			
			logger.error("getConnection failed in MySQLDatabaseHandler", e);
			throw e;
		} 
	}
	
	/**
	 * Closes the connection.
	 * @throws SQLException 
	 */
	public void close() throws SQLException {
		if (connection != null) {
			try { 
				connection.close(); 
			} 
			catch (SQLException ignore) {
				logger.error("close failed ", ignore);
			}
		}
	}
	
	/**
	 * Creates a connection string and return it.
	 * @return connection string.
	 */
	public abstract String getConnectionString();
	
	/**
     * Create a database for given name if it doesn't exist yet. In both cases connection is updated to use the database.
     * @return if execution was successful.
	 * @throws SQLException 
	 * @throws Exception 
     */
	public abstract boolean createDatabaseIfNotExist(String databaseName) throws SQLException, Exception;
	
	/**
     * Delete a database for given name if it exists yet. 
     * @return if execution was successful.
	 * @throws SQLException 
	 * @throws Exception 
     */
	public abstract boolean deleteDatabaseIfExists(String databaseName) throws SQLException, Exception;

	/**
	 * Creates a table where the data should be loaded.
	 * @param tableName the name of the table to create.
	 * @param variables the list of columns.
	 * @return if execution was successful.
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public abstract boolean createTableIfNotExist(String tableName, List<String> variables) throws SQLException, Exception;

	/**
	 * Creates indices on a list of columns. Depending on combineColumns parameter one or many indices will be created.
	 * @param columnNames - a list of names of columns on which index (indices) need to be created.
	 * @param combineColumns - if true, only one index will be created where key will be combination of all columns, otherwise a separate index on each column will be created.
	 */
	public abstract void createIndeces(List<String> columnNames, boolean combineColumns);
	
	
	
}
