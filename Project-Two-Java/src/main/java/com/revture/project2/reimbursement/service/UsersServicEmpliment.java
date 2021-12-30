package com.revture.project2.reimbursement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.revture.project2.reimbursement.dao.UsersRepositoryDao;
import com.revture.project2.reimbursement.entity.UsersEntity;
import com.revture.project2.reimbursement.pojo.UsersPojo;

@Service // we config the service class as a bean so we can inject it
public class UsersServicEmpliment implements UsersServicList {

	
	@Autowired
	UsersRepositoryDao usersRepositoryDao ;
	// for the Email injection
	@Autowired
    JavaMailSender mailSender;

	
	public UsersServicEmpliment() {
	}
	//-----------------update User Profile-----------------------------------------------------------------------------
	@Override
	public UsersPojo updateUserProfile(UsersPojo userPojo) {
		UsersEntity upsateUsersEntity = new UsersEntity(
				userPojo.getId(), 
				userPojo.getName(), 
				userPojo.getPassword(), 
				userPojo.getEmail(), 
				userPojo.getUsertypeId(), 
				userPojo.getAddres(), 
				userPojo.getContact());
		this.usersRepositoryDao.save(upsateUsersEntity);
		return userPojo;
	}
	//-----------------logIn-----------------------------------------------------------------------------
	@Override
	public UsersPojo logIn(UsersPojo userPojo){
		Optional<UsersEntity> optional = this.usersRepositoryDao.findBynameAndpassword(userPojo.getName(), userPojo.getPassword());
		if(optional.isPresent()) {
			UsersEntity usersEntity = optional.get();
		// copying entity into pojo
			userPojo = new UsersPojo(
					usersEntity.getId(), 
					usersEntity.getName(), 
					usersEntity.getPassword(), 
					usersEntity.getEmail(), 
					usersEntity.getUsertypeId(), 
					usersEntity.getAddres(), 
					usersEntity.getContact()); 
		}
		return userPojo;	
	}
	//------------------register----------------------------------------------------------------------------
	@Override
	public UsersPojo register(UsersPojo userPojo) {
		UsersEntity addusersEntity = new UsersEntity(
				userPojo.getId(), 
				userPojo.getName(), 
				userPojo.getPassword(), 
				userPojo.getEmail(), 
				userPojo.getUsertypeId(), 
				userPojo.getAddres(), 
				userPojo.getContact());
		UsersEntity returnSignIn = this.usersRepositoryDao.saveAndFlush(addusersEntity);
		userPojo.setId(returnSignIn.getId());	 
		return userPojo;
	}
	//-----------------------allEmployees-----------------------------------------------------------------------
	@Override
	public List<UsersPojo> allEmployees() {
//		 
//		        List<UsersEntity> employeeList = usersRepositoryDao.findAll();
//		        if(employeeList.size() > 0) {
//		            return employeeList;
//		        } else {
//		            return new ArrayList<UsersEntity>(); 
		
		List<UsersEntity> allEmployeeEntity = this.usersRepositoryDao.findAll();
		List<UsersPojo> returnAllEmployees = new ArrayList<UsersPojo>();
		allEmployeeEntity.forEach((allEmployee)-> {
			UsersPojo userPojo = new  UsersPojo(
					allEmployee.getId(), 
					allEmployee.getName(), 
					allEmployee.getPassword(), 
					allEmployee.getEmail(), 					 
					allEmployee.getUsertypeId(),
					allEmployee.getAddres(), 
					allEmployee.getContact()); 
		returnAllEmployees.add(userPojo);
		});
		return returnAllEmployees;
	}
	//------------------User Profile----------------------------------------------------------------------------

	@Override
	public UsersPojo getUserById(int userId) {
		UsersPojo userPojo = null;
		// call the findById to fetch a record by ID
		// findById returns java.util.Optional which contains the users entity 
		Optional<UsersEntity> optional  = this.usersRepositoryDao.findById(userId);
		if(optional.isPresent()) {
			//take out the book entity from the optional and store 
			// in a Book reference
			UsersEntity usersEntity = optional.get();
		// copying entity into pojo
			userPojo = new UsersPojo(
					usersEntity.getId(), 
					usersEntity.getName(), 
					usersEntity.getPassword(), 
					usersEntity.getEmail(), 
					usersEntity.getUsertypeId(), 
					usersEntity.getAddres(), 
					usersEntity.getContact()); 
		}
		return userPojo;
	}
	//--------------------------Send Email-------------------------------------------------------------------------------------------
	
	public boolean sendEmail(int userId, int flag) {
		Optional<UsersEntity> optional = this.usersRepositoryDao.findById(userId);
		if(optional.isPresent()) {
			UsersEntity requestEntity = optional.get();
			String getEmail = requestEntity.getEmail();
			String getName = requestEntity.getName();
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("samehhabib99@gmail.com");
			message.setTo(getEmail);
			message.setSubject("Reimbursement Request");
			message.setText(getName);
			if(flag == 1) {
			message.setText("Your reimbursement Request Approved");
			}else {
			message.setText("Sorry Your reimbursement Request Rejected");
			}
			mailSender.send(message);
	}
		return true;
	}

}
