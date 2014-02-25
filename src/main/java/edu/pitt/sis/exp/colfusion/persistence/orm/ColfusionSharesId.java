package edu.pitt.sis.exp.colfusion.persistence.orm;

// Generated Feb 24, 2014 10:00:48 PM by Hibernate Tools 3.4.0.CR1

/**
 * ColfusionSharesId generated by hbm2java
 */
public class ColfusionSharesId implements java.io.Serializable {

	private Integer vid;
	private Integer userId;
	private Integer privilege;

	public ColfusionSharesId() {
	}

	public ColfusionSharesId(Integer vid, Integer userId, Integer privilege) {
		this.vid = vid;
		this.userId = userId;
		this.privilege = privilege;
	}

	public Integer getVid() {
		return this.vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ColfusionSharesId))
			return false;
		ColfusionSharesId castOther = (ColfusionSharesId) other;

		return ((this.getVid() == castOther.getVid()) || (this.getVid() != null
				&& castOther.getVid() != null && this.getVid().equals(
				castOther.getVid())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())))
				&& ((this.getPrivilege() == castOther.getPrivilege()) || (this
						.getPrivilege() != null
						&& castOther.getPrivilege() != null && this
						.getPrivilege().equals(castOther.getPrivilege())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getVid() == null ? 0 : this.getVid().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getPrivilege() == null ? 0 : this.getPrivilege().hashCode());
		return result;
	}

}
