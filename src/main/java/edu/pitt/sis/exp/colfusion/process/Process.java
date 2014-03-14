/**
 * 
 */
package edu.pitt.sis.exp.colfusion.process;

/**
 * @author Evgeny
 *
 */
public interface Process extends Runnable {
	public boolean isImmediate();
	    
    abstract public boolean isRunning();
    abstract public boolean isDone();
    
    abstract public void startPerforming(ProcessManager manager);
    abstract public void cancel();
}
