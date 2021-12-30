package com.revture.project2.reimbursement.pojo;

import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

//Use Lombok
@Data // or we can use QGetter and @Setter
@NoArgsConstructor
@ToString 
public class RequestPojo {
	
	
	private int id; 
	@NotNull
	private String reqName; 
	
	@NotNull
	@Min(0)
	private int reqPrice; 
	
	@NotNull
	private String reqDescription; 
	private boolean reqFlag; 
	private Date reqDate;
	private LocalDate acceptDate;
	private String img; 
	private int usersId;
	private boolean reject;
	

	public RequestPojo(int id, String reqName, int reqPrice, String reqDescription, boolean reqFlag, Date reqDate,
			LocalDate acceptDate, String img, int usersId, boolean reject) {
		super();
		this.id = id;
		this.reqName = reqName;
		this.reqPrice = reqPrice;
		this.reqDescription = reqDescription;
		this.reqFlag = reqFlag;
		this.reqDate = reqDate;
		this.acceptDate = acceptDate;
		this.img = img;
		this.usersId = usersId;
		this.reject = reject;
	}


}
