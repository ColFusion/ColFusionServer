/**
 * 
 */
package edu.pitt.sis.exp.colfusion.dal.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import edu.pitt.sis.exp.colfusion.utils.ConfigManager;
import edu.pitt.sis.exp.colfusion.utils.PropertyKeys;



public class HibernateUtil {
 
	private static final Logger logger = LogManager.getLogger(HibernateUtil.class.getName());
	private static SessionFactory sessionFactory;
	
	static {
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml"); 
			
			setProperties(cfg);
			
			StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
	        sb.applySettings(cfg.getProperties());
	        StandardServiceRegistry standardServiceRegistry = sb.build();                   
	        sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
		} catch (Throwable ex) {
			// Log the exception.
			logger.error("sessionFactory initialization failed!", ex);
			throw new RuntimeException(ex);
		}
	}
 
	/**
	 * Set hibernate properties from {@link ConfigManager}
	 * 
	 * @param cfg
	 * 			hibernate configuration to set properties to 
	 */
	private static void setProperties(final Configuration cfg) {
		ConfigManager configMng = ConfigManager.getInstance();
		cfg.setProperty(PropertyKeys.HIBERNATE_CONNECTION_DRIVER_CLASS.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_CONNECTION_DRIVER_CLASS));
		cfg.setProperty(PropertyKeys.HIBERNATE_CONNECTION_URL.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_CONNECTION_URL));
		cfg.setProperty(PropertyKeys.HIBERNATE_DEFAULT_CATALOG.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_DEFAULT_CATALOG));
		cfg.setProperty(PropertyKeys.HIBERNATE_CONNECTION_USERNAME.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_CONNECTION_USERNAME));
		cfg.setProperty(PropertyKeys.HIBERNATE_CONNECTION_PASSWORD.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_CONNECTION_PASSWORD));		
		cfg.setProperty(PropertyKeys.HIBERNATE_DIALECT.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_DIALECT));
		
		cfg.setProperty(PropertyKeys.HIBERNATE_HBM2DDL_AUTO.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_HBM2DDL_AUTO));
		
		cfg.setProperty(PropertyKeys.HIBERNATE_CONNECTION_ZERO_DATE_TIME_BEHAVIOR.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_CONNECTION_ZERO_DATE_TIME_BEHAVIOR));
		
		cfg.setProperty(PropertyKeys.HIBERNATE_C3P0_MIN_SIZE.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_C3P0_MIN_SIZE));
		cfg.setProperty(PropertyKeys.HIBERNATE_C3P0_MAX_SIZE.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_C3P0_MAX_SIZE));
		cfg.setProperty(PropertyKeys.HIBERNATE_C3P0_TIMEOUT.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_C3P0_TIMEOUT));
		cfg.setProperty(PropertyKeys.HIBERNATE_C3P0_MAX_STATEMENTS.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_C3P0_MAX_STATEMENTS));
		cfg.setProperty(PropertyKeys.HIBERNATE_C3P0_IDLE_TEST_PERIOD.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_C3P0_IDLE_TEST_PERIOD));
		
		// Somehow setting this property at this time doesn't work. So it is set via the hibernate config file.
		cfg.setProperty(PropertyKeys.HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS));
		cfg.setProperty(PropertyKeys.HIBERNATE_SHOW_SQL.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_SHOW_SQL));
		cfg.setProperty(PropertyKeys.HIBERNATE_FORMAT_SQL.getKey(), configMng.getProperty(PropertyKeys.HIBERNATE_FORMAT_SQL));
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void beginTransaction() throws HibernateException {
		try {
			Session hibernateSession = HibernateUtil.getSession();
			
			if (hibernateSession.getTransaction() != null
		            && hibernateSession.getTransaction().isActive()) {

				//TODO change from Error to INFO. right now use error, because I want to receive an email when it happens.
				logger.error("beginTransaction hibernateSession.getTransaction() != null and hibernateSession.getTransaction().isActive() is TURE. Does it mean "
						+ "the code is trying to open nested tractions? Check!!!.");
				
				Transaction transaction = hibernateSession.getTransaction();
				logger.info(String.format("beginTransaction: There is an active transaction %s", transaction));
		    } else {
		    	Transaction transaction = hibernateSession.beginTransaction();
//		    	logger.info(String.format("beginTransaction: Began transaction %s", transaction));
		    }
		}
		catch (Exception e) {
			logger.error("beginTransaction failed. ", e);
			
			throw new HibernateException(e);
		}
	}
	 
	public static void commitTransaction() throws HibernateException {
		try {
		
			if (HibernateUtil.getSession().getTransaction() != null) {
				org.hibernate.Transaction trans = HibernateUtil.getSession().getTransaction();
				
				trans.commit();
				
				if (!trans.wasCommitted()) {
				
					//TODO change from Error to INFO. right now use error, because I want to receive an email when it happens.
					logger.error("commitTransaction trans.wasCommitted() is false. Starting while loop until wasCommitted is true. Might lead for inf loop though, maybe put break after a some time.");
					
					while (!trans.wasCommitted()) {
						
					}
					
					//TODO change from Error to INFO. right now use error, because I want to receive an email when it happens.
					logger.error("commitTransaction: after while (!trans.wasCommitted()).");					
				}
				
				String message = String.format("commitTransaction: commited trasaction %s", trans);
//				logger.info(message);
			}
			else {
				logger.error("commitTransaction was trying to commit but HibernateUtil.getSession().getTransaction() equals NULL");
				//TODO: maybe here we need to throw an error, because the data was not committed.
			}
		}
		catch (Exception e) {
			logger.error("commitTransaction failed", e);
			
			//TODO should be throw e further?
			
			throw new HibernateException(e);
		}
	}
	 
	public static void rollbackTransaction() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
		} catch (Exception e1) {
			logger.error("rollbackTransaction failed on HibernateUtil.getSession()", e1);
			
			//TODO: maybe we should throw e1 here further.
			return;
		}
		
		if (session.getTransaction() != null) {
			try {
				org.hibernate.Transaction trans = HibernateUtil.getSession().getTransaction();
				
				trans.rollback();
				
				if (!trans.wasRolledBack()) {
				
					//TODO change from Error to INFO. right now use error, because I want to receive an email when it happens.
					logger.error("rollbackTransaction trans.rollback() is false. Starting while loop until wasRolledBack is true. Might lead for inf loop though, maybe put break after a some time.");
					
					while (!trans.wasRolledBack()) {
						
					}
					
					//TODO change from Error to INFO. right now use error, because I want to receive an email when it happens.
					logger.error("rollbackTransaction: after while (!trans.wasRolledBack()).");					
				}
				
				String message = String.format("commitTransaction: commited trasaction %s", trans);
				logger.info(message);
			} catch (Exception e) {
				logger.error("rollbackTransaction: HibernateUtil.getSession().getTransaction().rollback()  failed even though HibernateUtil.getSession().getTransaction() not equals", e);
			}
			
		}
		else {
			logger.error("rollbackTransaction: HibernateUtil.getSession().getTransaction() equals NULL");
		}
	}
	 
	/**
	 * Gets current session.
	 * 
	 * Right now it logs if something failed and also propagates the exception, but this is only for testing. Later this method will just throw exception if something happened, without logging.
	 * @return current session.
	 * @throws Exception
	 */
	public static Session getSession() throws HibernateException {
		
		if (sessionFactory == null) {
			String message = String.format("getSession: sessionFactory == null, need to do something here");
			logger.error(message);
			
			throw new NullPointerException(message);
		}
		
		try {
			Session hibernateSession = sessionFactory.getCurrentSession();
			return hibernateSession;
		} catch (Exception e) {
			logger.error("getSession failed", e);
			
			throw new HibernateException(e);
		}
	}

	/**
	 * Checks whether there is an active transaction.
	 * 
	 * @return true if there is an active transaction, otherwise false
	 */
	public static boolean isTransactionOpen() {
		Session hibernateSession = HibernateUtil.getSession();
		
		if (hibernateSession == null) {
			return false;
		}
		
		return hibernateSession.getTransaction() != null && hibernateSession.getTransaction().isActive();
	}
}
