package com.bluedon.monitor.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TB_COMMON_USER_BASEINFO database table.
 * 
 */
@Entity
@Table(name="TB_COMMON_USER_BASEINFO")
@NamedQuery(name="TbCommonUserBaseinfo.findAll", query="SELECT t FROM TbCommonUserBaseinfo t")
public class TbCommonUserBaseinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private Date birthday;
	private String cardNum;
	private String cardType;
	private String contactAddress;
	private String country;
	private Long createPeople;
	private Date createTime;
	private String email;
	private String homeAddress;
	private String mobilePhone;
	private String nationality;
	private String nativePlace;
	private String photo;
	private String postCode;
	private String sex;
	private Long updatePeople;
	private Date updateTime;
	private Long userId;

	public TbCommonUserBaseinfo() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	@Column(name="CARD_NUM")
	public String getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}


	@Column(name="CARD_TYPE")
	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	@Column(name="CONTACT_ADDRESS")
	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}


	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	@Column(name="CREATE_PEOPLE")
	public Long getCreatePeople() {
		return this.createPeople;
	}

	public void setCreatePeople(Long createPeople) {
		this.createPeople = createPeople;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="HOME_ADDRESS")
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}


	@Column(name="MOBILE_PHONE")
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	@Column(name="NATIVE_PLACE")
	public String getNativePlace() {
		return this.nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}


	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	@Column(name="POST_CODE")
	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}


	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	@Column(name="UPDATE_PEOPLE")
	public Long getUpdatePeople() {
		return this.updatePeople;
	}

	public void setUpdatePeople(Long updatePeople) {
		this.updatePeople = updatePeople;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_TIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	@Column(name="USER_ID")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}