package edu.pitt.sis.exp.colfusion.persistence.orm;

// Generated May 7, 2014 10:39:32 PM by Hibernate Tools 3.4.0.CR1

/**
 * ColfusionOldUrls generated by hbm2java
 */
public class ColfusionOldUrls implements java.io.Serializable {

	private Integer oldId;
	private int oldLinkId;
	private String oldTitleUrl;

	public ColfusionOldUrls() {
	}

	public ColfusionOldUrls(int oldLinkId, String oldTitleUrl) {
		this.oldLinkId = oldLinkId;
		this.oldTitleUrl = oldTitleUrl;
	}

	public Integer getOldId() {
		return this.oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}

	public int getOldLinkId() {
		return this.oldLinkId;
	}

	public void setOldLinkId(int oldLinkId) {
		this.oldLinkId = oldLinkId;
	}

	public String getOldTitleUrl() {
		return this.oldTitleUrl;
	}

	public void setOldTitleUrl(String oldTitleUrl) {
		this.oldTitleUrl = oldTitleUrl;
	}

}
