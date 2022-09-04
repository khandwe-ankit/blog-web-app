package com.ankit.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ankit.blog.services.FileService;

@Service
public class FileserviceImpl implements FileService {

	@Override
	public String uploadFile(String filePath, MultipartFile receivedFile) throws IOException {
		// fileName
		String fileName = receivedFile.getOriginalFilename();

		// random file name
		String randomString = UUID.randomUUID().toString();
		String randomFileName = randomString.concat(fileName.substring(fileName.lastIndexOf('.')));
		// create full path
		String fullPath = new StringBuilder().append(filePath)
		        .append(File.separator)
		        .append(randomFileName)
		        .toString();

		// create folder if doesn't exists
		File newFile = new File(filePath);
		if (!newFile.exists()) {
			newFile.mkdirs();
		}
		// copy file to folder
		Files.copy(receivedFile.getInputStream(), Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);
		return randomFileName;
	}

	@Override
	public boolean deleteFile(String filePath, String fileName) throws IOException {
		String fullPath = new StringBuilder().append(filePath)
		        .append(File.separator)
		        .append(fileName)
		        .toString();

		return Files.deleteIfExists(Paths.get(fullPath));
	}

	@Override
	public InputStream getResources(String filePath, String fileName) throws FileNotFoundException {
		String fullPath = new StringBuilder().append(filePath).append(File.separator).append(fileName).toString();
		return new FileInputStream(fullPath);
	}

}
