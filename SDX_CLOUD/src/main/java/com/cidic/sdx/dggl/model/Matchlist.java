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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Matchlist generated by hbm2java
 */
@Entity
@Table(name = "matchlist", catalog = "sdx_cloud")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Matchlist implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Match match;
	private int innerClothId;
	private Integer outClothId;
	private int trousersId;
	private int modelNum;
	private String modelurl;
	private Date createtime;
	private Set<Feedback> feedbacks = new HashSet<Feedback>(0);

	private String innerClothUrl;
	private String outClothUrl;
	private String trousersUrl;
	
	private String innerClothName;
	private String outClothName;
	private String trouserName;
	
	private String innerClothShopUrl;
	private String outClothShopUrl;
	private String trouserShopUrl;
	
	private String innerClothNum;
	private String outClothNum;
	private String trouserClothNum;
	
	@Transient
	public String getInnerClothNum() {
		return innerClothNum;
	}

	public void setInnerClothNum(String innerClothNum) {
		this.innerClothNum = innerClothNum;
	}

	@Transient
	public String getOutClothNum() {
		return outClothNum;
	}

	public void setOutClothNum(String outClothNum) {
		this.outClothNum = outClothNum;
	}

	@Transient
	public String getTrouserClothNum() {
		return trouserClothNum;
	}

	public void setTrouserClothNum(String trouserClothNum) {
		this.trouserClothNum = trouserClothNum;
	}

	public Matchlist() {
	}

	public Matchlist(Match match, int innerClothId, int trousersId, String modelurl, Date createtime) {
		this.match = match;
		this.innerClothId = innerClothId;
		this.trousersId = trousersId;
		this.modelurl = modelurl;
		this.createtime = createtime;
	}

	public Matchlist(Match match, int innerClothId, Integer outClothId, int trousersId, String modelurl,
			Date createtime, Set<Feedback> feedbacks) {
		this.match = match;
		this.innerClothId = innerClothId;
		this.outClothId = outClothId;
		this.trousersId = trousersId;
		this.modelurl = modelurl;
		this.createtime = createtime;
		this.feedbacks = feedbacks;
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
	@JoinColumn(name = "matchId", nullable = false)
	@JsonIgnore
	public Match getMatch() {
		return this.match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Column(name = "innerClothId", nullable = false)
	public int getInnerClothId() {
		return this.innerClothId;
	}

	public void setInnerClothId(int innerClothId) {
		this.innerClothId = innerClothId;
	}

	@Column(name = "outClothId")
	public Integer getOutClothId() {
		return this.outClothId;
	}

	public void setOutClothId(Integer outClothId) {
		this.outClothId = outClothId;
	}

	@Column(name = "trousersId")
	public int getTrousersId() {
		return this.trousersId;
	}

	public void setTrousersId(int trousersId) {
		this.trousersId = trousersId;
	}
	
	@Column(name = "modelNum")
	public int getModelNum() {
		return modelNum;
	}

	public void setModelNum(int modelNum) {
		this.modelNum = modelNum;
	}

	@Column(name = "modelurl")
	public String getModelurl() {
		return this.modelurl;
	}

	public void setModelurl(String modelurl) {
		this.modelurl = modelurl;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade={CascadeType.ALL}, mappedBy = "matchlist")
	@JsonIgnore
	public Set<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Transient
	public String getInnerClothUrl() {
		return innerClothUrl;
	}

	public void setInnerClothUrl(String innerClothUrl) {
		this.innerClothUrl = innerClothUrl;
	}

	@Transient
	public String getOutClothUrl() {
		return outClothUrl;
	}

	public void setOutClothUrl(String outClothUrl) {
		this.outClothUrl = outClothUrl;
	}

	@Transient
	public String getTrousersUrl() {
		return trousersUrl;
	}

	public void setTrousersUrl(String trousersUrl) {
		this.trousersUrl = trousersUrl;
	}

	@Transient
	public String getInnerClothName() {
		return innerClothName;
	}

	public void setInnerClothName(String innerClothName) {
		this.innerClothName = innerClothName;
	}

	@Transient
	public String getOutClothName() {
		return outClothName;
	}

	public void setOutClothName(String outClothName) {
		this.outClothName = outClothName;
	}

	@Transient
	public String getTrouserName() {
		return trouserName;
	}

	public void setTrouserName(String trouserName) {
		this.trouserName = trouserName;
	}

	@Transient
	public String getInnerClothShopUrl() {
		return innerClothShopUrl;
	}

	public void setInnerClothShopUrl(String innerClothShopUrl) {
		this.innerClothShopUrl = innerClothShopUrl;
	}

	@Transient
	public String getOutClothShopUrl() {
		return outClothShopUrl;
	}

	public void setOutClothShopUrl(String outClothShopUrl) {
		this.outClothShopUrl = outClothShopUrl;
	}

	@Transient
	public String getTrouserShopUrl() {
		return trouserShopUrl;
	}

	public void setTrouserShopUrl(String trouserShopUrl) {
		this.trouserShopUrl = trouserShopUrl;
	}

	
}
