package com.revture.project2.reimbursement.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.revture.project2.reimbursement.dao.FilesRepository;
import com.revture.project2.reimbursement.entity.FileEntity;




@Service
public class FileService {

    private final FilesRepository fileRepository;

    @Autowired
    public FileService(FilesRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    public void save(MultipartFile file, int id) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setId(id);
        fileRepository.save(fileEntity);
//        fileRepository.save(file2);

        System.out.println("Test"+fileEntity);
        
    }

    @Transactional
    public Optional<FileEntity> getFile(String id) {
        return fileRepository.findById(id);
    }
    @Transactional
    public Optional<FileEntity> getFile2(int id) {
        return fileRepository.findById(id);
    }
    @Transactional
    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
    
    
}