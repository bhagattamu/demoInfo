package com.bgurung.demoTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="designations")
public class Designation {
	@Id
	@Column(name="design_id")
	private Long designId;
	@Column(name="design_short")
	private String designShort;
	@Column(name="design_full")
	private String designFull;
	public Long getDesignId() {
		return designId;
	}
	public void setDesignId(Long designId) {
		this.designId = designId;
	}
	public String getDesignShort() {
		return designShort;
	}
	public void setDesignShort(String designShort) {
		this.designShort = designShort;
	}
	public String getDesignFull() {
		return designFull;
	}
	public void setDesignFull(String designFull) {
		this.designFull = designFull;
	}
}
