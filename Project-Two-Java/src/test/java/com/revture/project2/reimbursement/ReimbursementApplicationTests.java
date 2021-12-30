package com.revture.project2.reimbursement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.revture.project2.reimbursement.dao.RequestRepositoryDao;
import com.revture.project2.reimbursement.entity.RequestEntity;
import com.revture.project2.reimbursement.pojo.RequestPojo;
import com.revture.project2.reimbursement.service.RequestServicEmpliment;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ReimbursementApplicationTests {

	//@Autowired
	// for use Mokito
	@InjectMocks
	RequestServicEmpliment requestServicEmpliment;
	
	@Mock
	RequestRepositoryDao requestRepositoryDao;
	
	//----------------------------------Test add Requests---------------------------------------------

	@Test
	public void addRequestTest() {
		
		RequestEntity mockEntity = new RequestEntity(
				23, 
				"A premium pressure washer", 
				500, 
				"STIHL Cutquik® Cut-Off Machines", 
				false, 
				Date.valueOf("2021-12-01"),
				null, 
				"https://cdn4.vectorstock.com/i/1000x1000/07/13/receipt-template-vector-22560713.jpg",
				7, 
				false);
		
		//mockito rule
		// any request return from entity 
		doReturn(mockEntity).when(requestRepositoryDao).saveAndFlush(any(RequestEntity.class));
		//when(requestRepositoryDao.saveAndFlush(any(RequestEntity.class)))
		
		RequestPojo actualRequest  = null;
		RequestPojo requestPojo = new RequestPojo(
				0, 
				"A premium pressure washer", 
				500, 
				"STIHL Cutquik® Cut-Off Machines", 
				false, 
				Date.valueOf("2021-12-01"),
				null, 
				"https://cdn4.vectorstock.com/i/1000x1000/07/13/receipt-template-vector-22560713.jpg",
				7, 
				false);
		
			 actualRequest = requestServicEmpliment.addRequest(requestPojo);
	
		// if the return Id is 0 it mean it added sucissfly
		assertNotEquals(actualRequest.getId(), 0);
		}
			
	//----------------------------------Test User Pending Requests---------------------------------------------
	@Test
		public void userPendingRequestesTest() {
		
		List<RequestEntity> mockRequestEntity = new ArrayList<RequestEntity>();
		mockRequestEntity.add( new RequestEntity(
				17, 
				"A premium pressure washer", 
				500, 
				"STIHL Cutquik® Cut-Off Machines", 
				false, 
				Date.valueOf("2021-12-01"),
				null, 
				"https://cdn4.vectorstock.com/i/1000x1000/07/13/receipt-template-vector-22560713.jpg",
				7, 
				false));
		
		//mockito rule
		//Mockito.when(object.method name() ) . thenReturn ( specific result );
		// Mockito.when(userDao.getUserById(3)).thenReturn(new User(200, "I'm mock 3"));	     
		//when((requestRepositoryDao.findUserPendingRequestes(7)).thenReturn(actualRequestEntity));
		doReturn(mockRequestEntity).when(requestRepositoryDao).findUserPendingRequestes(7);
		
			List<RequestPojo> actualRequest = null;
			
					actualRequest = requestServicEmpliment.userPendingRequestes(7);
			
				
			int expectedSize = 1;
			assertEquals(expectedSize , actualRequest.size());
	}	

	
	//-----------------------Test user Resolve Request----------------------------------------------------------------------------------------------

	@Test
	public void userResolveRequestesTest() {
	
	List<RequestEntity> mockRequestEntity = new ArrayList<RequestEntity>();
	mockRequestEntity.add( new RequestEntity(
			17, 
			"A premium pressure washer", 
			500, 
			"STIHL Cutquik® Cut-Off Machines", 
			true, 
			Date.valueOf("2021-12-01"),
			null, 
			"https://cdn4.vectorstock.com/i/1000x1000/07/13/receipt-template-vector-22560713.jpg",
			7, 
			false));
	
	doReturn(mockRequestEntity).when(requestRepositoryDao).findUserResolveRequest(12);
	
		List<RequestPojo> actualRequest = null;
			
				actualRequest = requestServicEmpliment.userResolveRequest(12);
		
			
		int expectedSize = 1;
		assertEquals(expectedSize , actualRequest.size());
}	
	//------------------------Test all Pending Requests---------------------------------------------------------------------------------------------
	@Test
	public void TestAllPendingRequsts() {
		
		List<RequestEntity> mockAllPendingEntity = new ArrayList<RequestEntity>();
		mockAllPendingEntity.add(
				
				new RequestEntity(
						17, 
						"A premium pressure washer", 
						500, 
						"STIHL Cutquik® Cut-Off Machines", 
						false, 
						Date.valueOf("2021-12-01"),
						null, 
						"https://cdn4.vectorstock.com/i/1000x1000/07/13/receipt-template-vector-22560713.jpg",
						7, 
						false)); 
		
		mockAllPendingEntity.add (
				new RequestEntity(
						25, 
						"A premium pressure washer", 
						50, 
						"STIHL Cutquik® Cut-Off Machines", 
						false, 
						Date.valueOf("2021-12-01"),
						null, 
						"https://cdn4.vectorstock.com/i/1000x1000/07/13/receipt-template-vector-22560713.jpg",
						7, 
						false
			    ));
		doReturn(mockAllPendingEntity).when(requestRepositoryDao).findAllPendingRequsts();
				List<RequestPojo> actuallAllPending = null;
					actuallAllPending = requestServicEmpliment.allPendingRequsts();
				int expectedSize = 2;
				assertEquals(expectedSize , actuallAllPending.size());
	}
	
	//------------------------Test all Resolved Requests---------------------------------------------------------------------------------------------
	@Test
	public void TestAllResolvedRequsts() {
		List<RequestEntity> mockAllPendingEntity = new ArrayList<RequestEntity>();
		mockAllPendingEntity.add(
				
				new RequestEntity(
						17, 
						"A premium pressure washer", 
						500, 
						"STIHL Cutquik® Cut-Off Machines", 
						true, 
						Date.valueOf("2021-12-01"),
						null, 
						"https://cdn4.vectorstock.com/i/1000x1000/07/13/receipt-template-vector-22560713.jpg",
						7, 
						false)); 			
		mockAllPendingEntity.add (
				
				new RequestEntity(
						25, 
						"A premium pressure washer", 
						50, 
						"STIHL Cutquik® Cut-Off Machines", 
						true, 
						Date.valueOf("2021-12-01"),
						null, 
						"https://cdn4.vectorstock.com/i/1000x1000/07/13/receipt-template-vector-22560713.jpg",
						7, 
						false
			    ));
		doReturn(mockAllPendingEntity).when(requestRepositoryDao).findAllResolvedRequsts();
				List<RequestPojo> actuallAllPending = null;
				
					actuallAllPending = requestServicEmpliment.allResolvedRequsts();
			
				int expectedSize = 2;
				assertEquals(expectedSize , actuallAllPending.size());
	}

	//---------------------------Test Specific Employee Requests------------------------------------------------------------------------------------------

	@Test
	public void TestSpacificEmployeeRequests() {
		List<RequestEntity> mockAllPendingEntity = new ArrayList<RequestEntity>();
		mockAllPendingEntity.add(
				
				new RequestEntity(
						17, 
						"A premium pressure washer", 
						500, 
						"STIHL Cutquik® Cut-Off Machines", 
						false, 
						Date.valueOf("2021-12-01"),
						null, 
						"https://cdn4.vectorstock.com/i/1000x1000/07/13/receipt-template-vector-22560713.jpg",
						7, 
						false)); 
		
		doReturn(mockAllPendingEntity).when(requestRepositoryDao).findSpacificEmployeeRequests(17);
	
		List<RequestPojo> actuallAllPending = null;
		
		
			actuallAllPending = requestServicEmpliment.spacificEmployeeRequests(17);
		
		int expectedSize = 1;
		assertEquals(expectedSize , actuallAllPending.size());

	}
	
	//----------------------------------Test approve Requests---------------------------------------------
//	@Test
//	public void approveRequest() {
//		
//		RequestEntity mockApproveEntity = new RequestEntity(
//				17, 
//				"", 
//				0, 
//				"", 
//				true, 
//				Date.valueOf("2021-12-15"),
//				null, 
//				"",
//				0, 
//				false);
//		
//		//mockito rule
//		// any request return from entity 
//		//Mockitn (mockApproveEntity).when(requestRepositoryDao).findById(17);
//	    // when(requestRepositoryDao.findById(17)).doReturn(true);
//		//doReturn(true).when(requestRepositoryDao.findById(17));
//		//doReturn(true).when(requestRepositoryDao.findById(17)).
//		//when(requestRepositoryDao.findById(17)).then(returnOptionalOf(myBaaValue));
//        //Mockito.when(requestRepositoryDao.findById(17)).thenReturn(true);
//		//Mockito.<Optional<RequestEntity>>when(Optional<RequestEntity>requestRepositoryDao.findById(17)).thenReturn(true);
//		
//		boolean actualRequest  = false;
//		RequestPojo requestPojo = new RequestPojo(
//				17, 
//				"", 
//				0, 
//				"", 
//				true, 
//				Date.valueOf("2021-12-01"),
//				null, 
//				"",
//				0, 
//				false);
//		
//			 actualRequest = requestServicEmpliment.approveRequest(17);
//		// if the return Id is 0 it mean it added sucissfly
//		assertEquals(true, actualRequest);
//		}
		
		
	}



