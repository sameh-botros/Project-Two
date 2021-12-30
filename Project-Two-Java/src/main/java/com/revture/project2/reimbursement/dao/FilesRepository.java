package com.revture.project2.reimbursement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revture.project2.reimbursement.entity.FileEntity;

@Repository
public interface FilesRepository extends JpaRepository<FileEntity, String> {
	
	
	
	  Optional<FileEntity> findById(int id);
	
	
	}
