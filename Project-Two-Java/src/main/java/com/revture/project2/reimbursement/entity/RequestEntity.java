package com.revture.project2.reimbursement.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity // mandatory
@Table(name="request") // optional if the entity class name and the table name match, , preferred is specify it even if it matches
public class RequestEntity {
	
	
	@Id
	// we specify the primary key auto generation strategy used in the underlying DB 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") // optional if the variable name and the column name match, preferred is specify it even if it matches
	private int id; 
	
	@Column(name="req_name")
	private String reqName; 
	
	@Column(name="req_price")
	private int reqPrice; 
	
	@Column(name="req_description")
	private String reqDescription; 
	
	@Column(name="req_flag")
	private boolean reqFlag; 
	
	@Column(name="req_date")
	private Date reqDdate;
	
	@Column(name="accept_date")
	//@Temporal(TemporalType.DATE)
	private LocalDate acceptDate;
	
	@Column(name="img")
	private String img; 
	
	@Column(name="users_id")
	private int usersId;
	
	@Column(name="reject")
	private boolean reject;

	public RequestEntity() {
		super();
	}

	public RequestEntity(int id, String reqName, int reqPrice, String reqDescription, boolean reqFlag, Date reqDdate,
			LocalDate acceptDate, String img, int usersId, boolean reject) {
		super();
		this.id = id;
		this.reqName = reqName;
		this.reqPrice = reqPrice;
		this.reqDescription = reqDescription;
		this.reqFlag = reqFlag;
		this.reqDdate = reqDdate;
		this.acceptDate = acceptDate;
		this.img = img;
		this.usersId = usersId;
		this.reject = reject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
	}

	public int getReqPrice() {
		return reqPrice;
	}

	public void setReqPrice(int reqPrice) {
		this.reqPrice = reqPrice;
	}

	public String getReqDescription() {
		return reqDescription;
	}

	public void setReqDescription(String reqDescription) {
		this.reqDescription = reqDescription;
	}

	public boolean isReqFlag() {
		return reqFlag;
	}

	public void setReqFlag(boolean reqFlag) {
		this.reqFlag = reqFlag;
	}

	public Date getReqDdate() {
		return reqDdate;
	}

	public void setReqDdate(Date reqDdate) {
		this.reqDdate = reqDdate;
	}

	public LocalDate getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(LocalDate today) {
		this.acceptDate = today;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public boolean isReject() {
		return reject;
	}

	public void setReject(boolean reject) {
		this.reject = reject;
	}

	@Override
	public String toString() {
		return "RequestEntity [id=" + id + ", reqName=" + reqName + ", reqPrice=" + reqPrice + ", reqDescription="
				+ reqDescription + ", reqFlag=" + reqFlag + ", reqDdate=" + reqDdate + ", acceptDate=" + acceptDate
				+ ", img=" + img + ", usersId=" + usersId + ", reject=" + reject + "]";
	}




	
	
}
