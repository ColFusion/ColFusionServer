package edu.pitt.sis.exp.colfusion.persistence.orm;

// Generated Feb 24, 2014 10:00:48 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * ColfusionPentahoLogLoggingChannelsId generated by hbm2java
 */
public class ColfusionPentahoLogLoggingChannelsId implements
		java.io.Serializable {

	private Integer idBatch;
	private String channelId;
	private Date logDate;
	private String loggingObjectType;
	private String objectName;
	private String objectCopy;
	private String repositoryDirectory;
	private String filename;
	private String objectId;
	private String objectRevision;
	private String parentChannelId;
	private String rootChannelId;

	public ColfusionPentahoLogLoggingChannelsId() {
	}

	public ColfusionPentahoLogLoggingChannelsId(Integer idBatch,
			String channelId, Date logDate, String loggingObjectType,
			String objectName, String objectCopy, String repositoryDirectory,
			String filename, String objectId, String objectRevision,
			String parentChannelId, String rootChannelId) {
		this.idBatch = idBatch;
		this.channelId = channelId;
		this.logDate = logDate;
		this.loggingObjectType = loggingObjectType;
		this.objectName = objectName;
		this.objectCopy = objectCopy;
		this.repositoryDirectory = repositoryDirectory;
		this.filename = filename;
		this.objectId = objectId;
		this.objectRevision = objectRevision;
		this.parentChannelId = parentChannelId;
		this.rootChannelId = rootChannelId;
	}

	public Integer getIdBatch() {
		return this.idBatch;
	}

	public void setIdBatch(Integer idBatch) {
		this.idBatch = idBatch;
	}

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getLoggingObjectType() {
		return this.loggingObjectType;
	}

	public void setLoggingObjectType(String loggingObjectType) {
		this.loggingObjectType = loggingObjectType;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectCopy() {
		return this.objectCopy;
	}

	public void setObjectCopy(String objectCopy) {
		this.objectCopy = objectCopy;
	}

	public String getRepositoryDirectory() {
		return this.repositoryDirectory;
	}

	public void setRepositoryDirectory(String repositoryDirectory) {
		this.repositoryDirectory = repositoryDirectory;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectRevision() {
		return this.objectRevision;
	}

	public void setObjectRevision(String objectRevision) {
		this.objectRevision = objectRevision;
	}

	public String getParentChannelId() {
		return this.parentChannelId;
	}

	public void setParentChannelId(String parentChannelId) {
		this.parentChannelId = parentChannelId;
	}

	public String getRootChannelId() {
		return this.rootChannelId;
	}

	public void setRootChannelId(String rootChannelId) {
		this.rootChannelId = rootChannelId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ColfusionPentahoLogLoggingChannelsId))
			return false;
		ColfusionPentahoLogLoggingChannelsId castOther = (ColfusionPentahoLogLoggingChannelsId) other;

		return ((this.getIdBatch() == castOther.getIdBatch()) || (this
				.getIdBatch() != null && castOther.getIdBatch() != null && this
				.getIdBatch().equals(castOther.getIdBatch())))
				&& ((this.getChannelId() == castOther.getChannelId()) || (this
						.getChannelId() != null
						&& castOther.getChannelId() != null && this
						.getChannelId().equals(castOther.getChannelId())))
				&& ((this.getLogDate() == castOther.getLogDate()) || (this
						.getLogDate() != null && castOther.getLogDate() != null && this
						.getLogDate().equals(castOther.getLogDate())))
				&& ((this.getLoggingObjectType() == castOther
						.getLoggingObjectType()) || (this
						.getLoggingObjectType() != null
						&& castOther.getLoggingObjectType() != null && this
						.getLoggingObjectType().equals(
								castOther.getLoggingObjectType())))
				&& ((this.getObjectName() == castOther.getObjectName()) || (this
						.getObjectName() != null
						&& castOther.getObjectName() != null && this
						.getObjectName().equals(castOther.getObjectName())))
				&& ((this.getObjectCopy() == castOther.getObjectCopy()) || (this
						.getObjectCopy() != null
						&& castOther.getObjectCopy() != null && this
						.getObjectCopy().equals(castOther.getObjectCopy())))
				&& ((this.getRepositoryDirectory() == castOther
						.getRepositoryDirectory()) || (this
						.getRepositoryDirectory() != null
						&& castOther.getRepositoryDirectory() != null && this
						.getRepositoryDirectory().equals(
								castOther.getRepositoryDirectory())))
				&& ((this.getFilename() == castOther.getFilename()) || (this
						.getFilename() != null
						&& castOther.getFilename() != null && this
						.getFilename().equals(castOther.getFilename())))
				&& ((this.getObjectId() == castOther.getObjectId()) || (this
						.getObjectId() != null
						&& castOther.getObjectId() != null && this
						.getObjectId().equals(castOther.getObjectId())))
				&& ((this.getObjectRevision() == castOther.getObjectRevision()) || (this
						.getObjectRevision() != null
						&& castOther.getObjectRevision() != null && this
						.getObjectRevision().equals(
								castOther.getObjectRevision())))
				&& ((this.getParentChannelId() == castOther
						.getParentChannelId()) || (this.getParentChannelId() != null
						&& castOther.getParentChannelId() != null && this
						.getParentChannelId().equals(
								castOther.getParentChannelId())))
				&& ((this.getRootChannelId() == castOther.getRootChannelId()) || (this
						.getRootChannelId() != null
						&& castOther.getRootChannelId() != null && this
						.getRootChannelId()
						.equals(castOther.getRootChannelId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdBatch() == null ? 0 : this.getIdBatch().hashCode());
		result = 37 * result
				+ (getChannelId() == null ? 0 : this.getChannelId().hashCode());
		result = 37 * result
				+ (getLogDate() == null ? 0 : this.getLogDate().hashCode());
		result = 37
				* result
				+ (getLoggingObjectType() == null ? 0 : this
						.getLoggingObjectType().hashCode());
		result = 37
				* result
				+ (getObjectName() == null ? 0 : this.getObjectName()
						.hashCode());
		result = 37
				* result
				+ (getObjectCopy() == null ? 0 : this.getObjectCopy()
						.hashCode());
		result = 37
				* result
				+ (getRepositoryDirectory() == null ? 0 : this
						.getRepositoryDirectory().hashCode());
		result = 37 * result
				+ (getFilename() == null ? 0 : this.getFilename().hashCode());
		result = 37 * result
				+ (getObjectId() == null ? 0 : this.getObjectId().hashCode());
		result = 37
				* result
				+ (getObjectRevision() == null ? 0 : this.getObjectRevision()
						.hashCode());
		result = 37
				* result
				+ (getParentChannelId() == null ? 0 : this.getParentChannelId()
						.hashCode());
		result = 37
				* result
				+ (getRootChannelId() == null ? 0 : this.getRootChannelId()
						.hashCode());
		return result;
	}

}
