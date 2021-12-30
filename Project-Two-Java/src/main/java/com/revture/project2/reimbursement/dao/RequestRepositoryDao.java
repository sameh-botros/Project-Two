package com.revture.project2.reimbursement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revture.project2.reimbursement.entity.RequestEntity;


	// for Generic add the entity class name and PK type
	@Repository // we config the Dao class as a bean so we can inject it
	// Integer related to the Primary key id type
	public interface RequestRepositoryDao extends JpaRepository<RequestEntity, Integer>  {
	
	//-------------------------user Pending Request---------------------------------------------------------------------

		// we use JPQL in @Query
		// JPQL uses entity class and not table name
		@Query("FROM RequestEntity rq WHERE rq.usersId= ?1 AND rq.reqFlag=false AND rq.reject=false")
	 List<RequestEntity> findUserPendingRequestes(int id);
	//List<RequestPojo> userPendingRequestes(int Id)throws ApplicationException;
	 
	//--------------------------user Resolve Request--------------------------------------------------------------------
	 
	@Query("SELECT rq FROM RequestEntity rq WHERE rq.usersId= :id AND rq.reqFlag=true And rq.reject=false ")
	 List<RequestEntity> findUserResolveRequest(@Param ("id") int id);
	//List<RequestPojo>   userResolveRequest(int Id)throws ApplicationException;
	 
	//-------------------------all Pending Requests----------------------------------------------------------------------
	 
	@Query(value="SELECT * FROM REQUEST rq WHERE rq.req_flag=false And rq.reject=false",nativeQuery = true)
	 List<RequestEntity> findAllPendingRequsts();
	 //List<RequestPojo>  allPendingRequsts()throws ApplicationException;
	 
	//-------------------------all Resolved Requests---------------------------------------------------------------------
	 
	@Query("SELECT rq FROM RequestEntity rq WHERE rq.reqFlag=true And rq.reject=false ")
	 List<RequestEntity> findAllResolvedRequsts();
	//List<RequestPojo> allResolvedRequsts()throws ApplicationException;

	//--------------------------Specific Employee Requests---------------------------------------------------------------

	 @Query("SELECT rq FROM RequestEntity rq WHERE rq.usersId= ?1")
	 List<RequestEntity> findSpacificEmployeeRequests(int id);
	//List<RequestPojo>  spacificEmployeeRequests(int userId)throws ApplicationException;
	 
	//-------------------------------------------------------------------------------------------------------------------


}

