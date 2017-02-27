package com.cidic.sdx.dggl.model;
// Generated 2017-2-21 14:08:23 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Match generated by hbm2java
 */
@Entity
@Table(name = "match", catalog = "sdx_cloud")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Match implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private String seriesname;
	private byte status;
	private Date createtime;
	private Set<Matchlist> matchlists = new HashSet<Matchlist>(0);
	private Set<Share> shares = new HashSet<Share>(0);

	public Match() {
	}

	public Match(User user, String seriesname, byte status, Date createtime) {
		this.user = user;
		this.seriesname = seriesname;
		this.status = status;
		this.createtime = createtime;
	}

	public Match(User user, String seriesname, byte status, Date createtime, Set<Matchlist> matchlists,
			Set<Share> shares) {
		this.user = user;
		this.seriesname = seriesname;
		this.status = status;
		this.createtime = createtime;
		this.matchlists = matchlists;
		this.shares = shares;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "seriesname", length = 30)
	public String getSeriesname() {
		return this.seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}

	@Column(name = "status")
	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL}, mappedBy = "match")
	public Set<Matchlist> getMatchlists() {
		return this.matchlists;
	}

	public void setMatchlists(Set<Matchlist> matchlists) {
		this.matchlists = matchlists;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "match")
	public Set<Share> getShares() {
		return this.shares;
	}

	public void setShares(Set<Share> shares) {
		this.shares = shares;
	}

}
