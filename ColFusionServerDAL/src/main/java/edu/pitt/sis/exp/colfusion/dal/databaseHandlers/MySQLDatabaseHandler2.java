
package edu.pitt.sis.exp.colfusion.dal.databaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used to read actual data values from target databases;
 * 
 * @author xxl
 * 
 */
public class MySQLDatabaseHandler2 extends DatabaseHandler {

    final static Logger logger = LogManager.getLogger(MySQLDatabaseHandler.class.getName());

    public MySQLDatabaseHandler2(DatabaseConnectionInfo databaseConnectionInfo) throws ClassNotFoundException {
        super(databaseConnectionInfo);// TODO Auto-generated constructor stub

        Class.forName("com.mysql.jdbc.Driver");
    }

    @Override
    protected Connection getConnection()
            throws SQLException {
        String connectionString = getConnectionString();

        try {
            return DriverManager.getConnection(connectionString, databaseConnectionInfo.getUser(),
                    databaseConnectionInfo.getPassword());
        } catch (SQLException e) {
            String message = String.format("Could not open connection for connection string %s", connectionString);
            logger.error(message);

            throw e;
        }
    }

    @Override
    protected String getConnectionString() {
        return String.format("jdbc:mysql://%s:%d/%s", databaseConnectionInfo.getHost(),
                databaseConnectionInfo.getPort(), databaseConnectionInfo.getDatabase());
    }

    @Override
    public boolean tempTableExist(int sid, String tableName)
            throws SQLException {
        logger.info(String.format("Getting if temp table exists for sid %d and tablename %s", sid, tableName));

        try (Connection connection = getConnection()) {

            String sql = String.format("SHOW TABLES LIKE 'temp_%s'", tableName);

            try (Statement statement = connection.createStatement()) {

                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {
                logger.error(String.format("Getting if temp table exists for sid %d and tablename %s FAILED", sid,
                        tableName), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to getting if temp table exists for sid %d and tablename %s", sid,
                    tableName));
            throw e;
        }
    }

    @Override
    public void removeTable(int sid, String tableName)
            throws SQLException {
        logger.info(String.format("Removing table for sid %d and tablename %s", sid, tableName));

        try (Connection connection = getConnection()) {

            String sql = String.format("DROP TABLE %s", tableName);

            try (Statement statement = connection.createStatement()) {

                statement.executeUpdate(sql);

            } catch (SQLException e) {
                logger.error(String.format("Removing table for sid %d and tablename %s FAILED", sid, tableName), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to removing table for sid %d and tablename %s", sid, tableName));
            throw e;
        }
    }

    @Override
    public void backupOriginalTable(int sid, String tableName)
            throws SQLException {

        logger.info(String.format("Backing up table for sid %d and tablename %s", sid, tableName));

        try (Connection connection = getConnection()) {

            String sql = String.format("CREATE TABLE %s SELECT * FROM %s", "temp_" + tableName, tableName);

            try (Statement statement = connection.createStatement()) {

                statement.executeUpdate(sql);

            } catch (SQLException e) {
                logger.error(String.format("Backing up table for sid %d and tablename %s FAILED", sid, tableName), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to Backing up table for sid %d and tablename %s", sid, tableName));
            throw e;
        }
    }

    @Override
    public int getColCount(int sid, String tableName) throws SQLException {
        logger.info(String.format("Getting column count for sid %d", sid));

        try (Connection connection = getConnection()) {

            String sql = "select count(*) from information_schema.columns where table_schema= ? and table_name= ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "colfusion_filetodb_" + sid);
                statement.setString(2, tableName);

                int colCount = 0;
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    colCount = Integer.parseInt(rs.getString(1));
                }
                return colCount;

            } catch (SQLException e) {
                logger.error(String.format("Getting column count for sid %d FAILED", sid), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to Getting column count for sid %d", sid));
            throw e;
        }
    }
    
    @Override
    public ArrayList<ArrayList<String>> getRows(String tableName, int colCount) throws SQLException {
        logger.info(String.format("Getting rows for table %s", tableName));
        ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
        try (Connection connection = getConnection()) {

            String sql = String.format("select * from %s", tableName);

            try (Statement statement = connection.createStatement()) {

                ResultSet rs1 = statement.executeQuery(sql);
                while (rs1.next()) {
                    int colIndex = 1;
                    ArrayList<String> temp = new ArrayList<String>();
                    while (colIndex <= colCount) {
                        temp.add(rs1.getString(colIndex));
                        colIndex++;
                    }
                    rows.add(temp);
                }
                return rows;

            } catch (SQLException e) {
                logger.error(String.format("Getting rows for table %s FAILED", tableName), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to getting rows for table %s", tableName));
            throw e;
        }
    }
    @Override
    public void createTable(int sid, String tableName)
            throws SQLException {
        logger.info(String.format("Creating table from temp_table for sid %d and tablename %s", sid, tableName));

        try (Connection connection = getConnection()) {

            String sql = String.format("CREATE TABLE %s SELECT * FROM %s", tableName, "temp_" + tableName);

            try (Statement statement = connection.createStatement()) {

                statement.executeUpdate(sql);

            } catch (SQLException e) {
                logger.error(String.format("Creating table from temp_table for sid %d and tablename %s FAILED", sid, tableName), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to Creating table from temp_table for sid %d and tablename %s", sid, tableName));
            throw e;
        }
    }
    
    @Override
    public void createTempTable(String query, int sid, String tableName)
            throws SQLException {

        logger.info(String.format("Creating temp table for sid %d and table %s", sid, tableName));

        try (Connection connection = getConnection()) {

            try (Statement statement = connection.createStatement()) {

                statement.executeUpdate(query);

            } catch (SQLException e) {
                logger.error(String.format("Creating temp table for sid %d and table %s FAILED", sid, tableName), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to Creating temp table for sid %d and table %s", sid, tableName));
            throw e;
        }
    }
    
    @Override
    public void insertIntoTempTable(String query, int sid, String tableName)
            throws SQLException {

        logger.info(String.format("Inserting into temp table temp_%s for sid %d", tableName, sid));

        try (Connection connection = getConnection()) {

            try (Statement statement = connection.createStatement()) {

                statement.executeUpdate(query);

            } catch (SQLException e) {
                logger.error(String.format("Inserting into temp table temp_%s for sid %d FAILED", tableName, sid), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to Inserting into temp table temp_%s for sid %d", tableName, sid));
            throw e;
        }
    }
    
    // addition here
    @Override
    public void createOriginalTable( String query,  int sid,  String tableName)
            throws SQLException {

        logger.info(String.format("Creating table for sid %d and table %s", sid, tableName));

        try (Connection connection = getConnection()) {

            try (Statement statement = connection.createStatement()) {

                statement.executeUpdate(query);

            } catch (SQLException e) {
                logger.error(String.format("Creating table for sid %d and table %s FAILED", sid, tableName), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to Creating table for sid %d and table %s", sid, tableName));
            throw e;
        }
    }
    
    @Override
    public void insertIntoTable(final String query, final int sid, final String tableName)
            throws SQLException {

        logger.info(String.format("Inserting into table temp_%s for sid %d", tableName, sid));

        try (Connection connection = getConnection()) {

            try (Statement statement = connection.createStatement()) {

                statement.executeUpdate(query);

            } catch (SQLException e) {
                logger.error(String.format("Inserting into table temp_%s for sid %d FAILED", tableName, sid), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to Inserting into table temp_%s for sid %d", tableName, sid));
            throw e;
        }
    }
    
    @Override
    public void insertIntoTable(int sid, String tableName, ArrayList<ArrayList<String>> rows, ArrayList<String> columnNames)
            throws SQLException {

        logger.info(String.format("New!!!!!!!!!!!!!!!Inserting into table %s for sid %d", tableName, sid));

        try (Connection connection = getConnection()) {

            String sql = getSql(tableName, columnNames);

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                final int batchSize = 1000;
                int count = 0;
                
                for(ArrayList<String> row : rows) {
                    int index = 1;
                    for(String cell : row) {
                        statement.setString(index++, cell);
                    }
                    statement.addBatch();

                    if(++count % batchSize == 0) {
                        statement.executeBatch();
                    }
                }
                statement.executeBatch();

            } catch (SQLException e) {
                logger.error(String.format("Inserting into table %s for sid %d FAILED", tableName, sid), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to Inserting into table %s for sid %d", tableName, sid));
            throw e;
        }
    }
    @Override
    public void importCsvToTable(final String dir, final String tableName) throws SQLException {
        
        logger.info(String.format("Importing from %s to table %s", dir, tableName));

        try (Connection connection = getConnection()) {

            String sql = String.format("LOAD DATA LOCAL INFILE '%s' into table %s  fields terminated by ','  optionally enclosed by '\"' escaped by '\"' lines terminated by '\\r\\n'", dir, tableName);

            try (Statement statement = connection.createStatement()) {
            System.out.println("****************");
            System.out.println(sql);
            System.out.println("****************");
                statement.executeUpdate(sql);
                
            } catch (SQLException e) {
                logger.error(String.format("Importing from %s to table %s FAILED", dir, tableName), e);

                throw e;
            }
        } catch (SQLException e) {
            logger.info(String.format("FAILED to Importing from %s to table %s", dir, tableName));
            throw e;
        }
    }
    
    private String getSql(final String tableName, final ArrayList<String> columnNames) {
        String sql = "insert into " + tableName + " values (";
        String values = "";
        int size = columnNames.size();

        for(int i = 0; i < size; i++) {
            if(i != size - 1) {
                values += "?, ";
            } else {
                values += "?)";
            }
        }
        
        sql += values;
        
        return sql;
    }

}
