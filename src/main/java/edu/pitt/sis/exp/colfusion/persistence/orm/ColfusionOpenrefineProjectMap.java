package edu.pitt.sis.exp.colfusion.persistence.orm;

// Generated 2014-5-12 21:35:22 by Hibernate Tools 3.4.0.CR1

/**
 * ColfusionOpenrefineProjectMap generated by hbm2java
 */
public class ColfusionOpenrefineProjectMap implements java.io.Serializable {

	private ColfusionOpenrefineProjectMapId id;
	private String projectId;

	public ColfusionOpenrefineProjectMap() {
	}

	public ColfusionOpenrefineProjectMap(ColfusionOpenrefineProjectMapId id,
			String projectId) {
		this.id = id;
		this.projectId = projectId;
	}

	public ColfusionOpenrefineProjectMapId getId() {
		return this.id;
	}

	public void setId(ColfusionOpenrefineProjectMapId id) {
		this.id = id;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
