/**
 * 
 */
package edu.pitt.sis.exp.colfusion.responseModels;

import edu.pitt.sis.exp.colfusion.viewmodels.PreviewFileViewModel;

/**
 * @author Evgeny
 *
 */
public class PreviewFileResponse extends GeneralResponseImpl {
	private PreviewFileViewModel payload;

	/**
	 * @return the payload
	 */
	public PreviewFileViewModel getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(PreviewFileViewModel payload) {
		this.payload = payload;
	}
}
