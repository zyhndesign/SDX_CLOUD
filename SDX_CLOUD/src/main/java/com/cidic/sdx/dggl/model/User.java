package com.cidic.sdx.dggl.model;
// Generated 2017-2-21 14:08:23 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "sdx_cloud")
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String serialnumber;
	private String phone;
	private Shop shop;
	private Date entrytime;
	private Byte gender;
	private String username;
	private String password;
	private String headicon;
	private Date createtime;
	private String slot;
	private int valid; //用户有效标志
	
	private Set<Share> shares = new HashSet<Share>(0);
	
	private Set<Match> matches = new HashSet<Match>(0);

	private Set<Vipuser> vipusers = new HashSet<Vipuser>(0);
	
	public User() {
	}

	public User(String username, String password, String headicon, Date createtime) {
		this.username = username;
		this.password = password;
		this.headicon = headicon;
		this.createtime = createtime;
	}

	public User(String username, String password, String headicon, String phone, Date entrytime,
			Byte gender, byte valid, Date createtime, String slot, Set<Vipuser> vipusers, Set<Match> matches) {
		this.username = username;
		this.password = password;
		this.headicon = headicon;
		this.phone = phone;
		this.entrytime = entrytime;
		this.gender = gender;
		this.valid = valid;
		this.createtime = createtime;
		this.slot = slot;
		this.vipusers = vipusers;
		this.matches = matches;
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

	@Column(name = "serialnumber", length = 16)
	public String getSerialnumber() {
		return this.serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	
	@Column(name = "username", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "headicon", length = 100)
	public String getHeadicon() {
		return this.headicon;
	}

	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}
	
	@Column(name = "phone", length = 11)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shopId", nullable = false)
	public Shop getShop() {
		return this.shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "entrytime", length = 19)
	public Date getEntrytime() {
		return this.entrytime;
	}

	public void setEntrytime(Date entrytime) {
		this.entrytime = entrytime;
	}

	@Column(name = "gender")
	public Byte getGender() {
		return this.gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}
	
	@Column(name = "valid")
	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	public Set<Share> getShares() {
		return this.shares;
	}

	public void setShares(Set<Share> shares) {
		this.shares = shares;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	public Set<Match> getMatches() {
		return this.matches;
	}

	public void setMatches(Set<Match> matches) {
		this.matches = matches;
	}
	
	@Column(name = "slot", length = 40)
	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	@Transient
	public String getCredentialsSalt() {
		return username + slot;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	public Set<Vipuser> getVipusers() {
		return this.vipusers;
	}

	public void setVipusers(Set<Vipuser> vipusers) {
		this.vipusers = vipusers;
	}
}
