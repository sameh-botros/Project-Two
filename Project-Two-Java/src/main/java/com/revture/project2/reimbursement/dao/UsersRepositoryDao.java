package com.revture.project2.reimbursement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revture.project2.reimbursement.entity.UsersEntity;

@Repository // we config the Dao class as a bean so we can inject it

//for Generic add the entity class name and PK type
public interface UsersRepositoryDao extends JpaRepository<UsersEntity, Integer> {


	//--------------------logIn------------------------------------------------------------------------------

	@Query( "FROM UsersEntity u WHERE u.name= ?1 AND u.password= ?2")
	Optional<UsersEntity> findBynameAndpassword(String name, String password);
	// UsersPojo logIn(UsersPojo userPojo)throws ApplicationException;

	//-------------------------------------------------------------------------------------------------------


}

