package com.cidic.sdx.dggl.model;
// Generated 2017-4-26 11:49:28 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Apphelp generated by hbm2java
 */
@Entity
@Table(name = "apphelp", catalog = "sdx_cloud")
public class Apphelp implements java.io.Serializable {

	private Integer id;
	private int userId;
	private String info;

	public Apphelp() {
	}

	public Apphelp(int userId, String info) {
		this.userId = userId;
		this.info = info;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userId", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "info", nullable = false)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
