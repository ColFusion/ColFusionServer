package edu.pitt.sis.exp.colfusion.persistence.orm;

// Generated Feb 24, 2014 10:00:48 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * ColfusionRelationshipsColumns generated by hbm2java
 */
public class ColfusionRelationshipsColumns implements java.io.Serializable {

	private ColfusionRelationshipsColumnsId id;
	private ColfusionRelationships colfusionRelationships;
	private BigDecimal dataMatchingFromRatio;
	private BigDecimal dataMatchingToRatio;

	public ColfusionRelationshipsColumns() {
	}

	public ColfusionRelationshipsColumns(ColfusionRelationshipsColumnsId id,
			ColfusionRelationships colfusionRelationships) {
		this.id = id;
		this.colfusionRelationships = colfusionRelationships;
	}

	public ColfusionRelationshipsColumns(ColfusionRelationshipsColumnsId id,
			ColfusionRelationships colfusionRelationships,
			BigDecimal dataMatchingFromRatio, BigDecimal dataMatchingToRatio) {
		this.id = id;
		this.colfusionRelationships = colfusionRelationships;
		this.dataMatchingFromRatio = dataMatchingFromRatio;
		this.dataMatchingToRatio = dataMatchingToRatio;
	}

	public ColfusionRelationshipsColumnsId getId() {
		return this.id;
	}

	public void setId(ColfusionRelationshipsColumnsId id) {
		this.id = id;
	}

	public ColfusionRelationships getColfusionRelationships() {
		return this.colfusionRelationships;
	}

	public void setColfusionRelationships(
			ColfusionRelationships colfusionRelationships) {
		this.colfusionRelationships = colfusionRelationships;
	}

	public BigDecimal getDataMatchingFromRatio() {
		return this.dataMatchingFromRatio;
	}

	public void setDataMatchingFromRatio(BigDecimal dataMatchingFromRatio) {
		this.dataMatchingFromRatio = dataMatchingFromRatio;
	}

	public BigDecimal getDataMatchingToRatio() {
		return this.dataMatchingToRatio;
	}

	public void setDataMatchingToRatio(BigDecimal dataMatchingToRatio) {
		this.dataMatchingToRatio = dataMatchingToRatio;
	}

}
