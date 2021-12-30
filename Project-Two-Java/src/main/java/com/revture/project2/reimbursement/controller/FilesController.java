package com.revture.project2.reimbursement.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.revture.project2.reimbursement.entity.FileEntity;
import com.revture.project2.reimbursement.entity.FileResponse;
import com.revture.project2.reimbursement.service.FileService;

@RestController
@RequestMapping("files")
@CrossOrigin
public class FilesController {

	private final FileService fileService;

	@Autowired
	public FilesController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping("{id}")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) {
		try {
			fileService.save(file, id);

			return ResponseEntity.status(HttpStatus.OK)
					.body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
		}
	}

	@GetMapping
	public List<FileResponse> list() {
		return fileService.getAllFiles().stream().map(this::mapToFileResponse).collect(Collectors.toList());
	}

	private FileResponse mapToFileResponse(FileEntity fileEntity) {
		String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
				.path(fileEntity.getIod()).toUriString();
		FileResponse fileResponse = new FileResponse();
		fileResponse.setIod(fileEntity.getIod());
		fileResponse.setName(fileEntity.getName());
		fileResponse.setContentType(fileEntity.getContentType());
		fileResponse.setSize(fileEntity.getSize());
		fileResponse.setUrl(downloadURL);
		fileResponse.setId(fileEntity.getId());
		return fileResponse;
	}

	@GetMapping("{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		Optional<FileEntity> fileEntityOptional = fileService.getFile(id);

		if (!fileEntityOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		FileEntity fileEntity = fileEntityOptional.get();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")

				.contentType(MediaType.valueOf(fileEntity.getContentType())).body(fileEntity.getData());
	}

	@GetMapping("/rbid/{id}")
	public ResponseEntity<byte[]> getFile2(@PathVariable int id) {
		Optional<FileEntity> fileEntityOptional = fileService.getFile2(id);

		if (!fileEntityOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		FileEntity fileEntity = fileEntityOptional.get();
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
				.contentType(MediaType.valueOf(fileEntity.getContentType())).body(fileEntity.getData());
	}

}