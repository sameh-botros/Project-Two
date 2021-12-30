package com.revture.project2.reimbursement.service;

import java.util.List;

import com.revture.project2.reimbursement.pojo.RequestPojo;

public interface RequestServicList {
	
	RequestPojo addRequest(RequestPojo requestPojo);
	List<RequestPojo> userPendingRequestes(int Id);
	List<RequestPojo>   userResolveRequest(int Id);
	boolean approveRequest(int reqId);
	List<RequestPojo>  allPendingRequsts();
	List<RequestPojo> allResolvedRequsts();
	List<RequestPojo>  spacificEmployeeRequests(int userId);
	boolean rejectRequest(int reqId);

}
