package com.ankit.blog.controllers;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ankit.blog.payloads.ApiResponse;
import com.ankit.blog.services.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {

	@Value("${project.filepath}")
	String projectFilePath;

	@Autowired
	private FileService fileService;

	@PostMapping("/upload")
	public ResponseEntity<ApiResponse> uploadFileToFolder(@RequestParam(value = "file") MultipartFile uploadedFile)
	        throws IOException {
		String uploadImage = this.fileService.uploadFile(projectFilePath, uploadedFile);
		ApiResponse apiResponse = new ApiResponse("Image upload with new name: " + uploadImage, true);
		return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/download/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		InputStream inputStream = this.fileService.getResources(projectFilePath, fileName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
		inputStream.close();
	}
}
