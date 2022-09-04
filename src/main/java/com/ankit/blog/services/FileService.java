package com.ankit.blog.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String uploadFile(String filePath, MultipartFile file) throws IOException;

	InputStream getResources(String filePath, String fileName) throws FileNotFoundException;

	boolean deleteFile(String filePath, String fileName) throws IOException;

}
