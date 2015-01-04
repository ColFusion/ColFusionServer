/**
 * 
 */
package edu.pitt.sis.exp.colfusion.utils;

/**
 * @author Evgeny
 *
 */
public class PairOf<T1, T2> {
	
	private T1 value1;
	private T2 value2;
	
	public PairOf(final T1 value1, final T2 value2) {
		setValue1(value1);
		setValue2(value2);
	}

	/**
	 * @return the value1
	 */
	public T1 getValue1() {
		return value1;
	}

	/**
	 * @param value1 the value1 to set
	 */
	public void setValue1(final T1 value1) {
		this.value1 = value1;
	}

	/**
	 * @return the value2
	 */
	public T2 getValue2() {
		return value2;
	}

	/**
	 * @param value2 the value2 to set
	 */
	public void setValue2(final T2 value2) {
		this.value2 = value2;
	}
}
