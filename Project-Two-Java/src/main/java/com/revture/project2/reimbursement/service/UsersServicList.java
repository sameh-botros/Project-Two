package com.revture.project2.reimbursement.service;

import java.util.List;

import com.revture.project2.reimbursement.pojo.UsersPojo;

public interface UsersServicList {
	
	UsersPojo updateUserProfile(UsersPojo userPojo);
	UsersPojo logIn(UsersPojo userPojo);
	UsersPojo register(UsersPojo userPojo);
	List<UsersPojo> allEmployees();
	UsersPojo getUserById(int userId);
	boolean sendEmail(int userId, int flag);


}
