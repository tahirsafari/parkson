package com.parkson.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MediaManager {
	@Inject
	@Value("${media.files.uploads.foldername}")
	private String folderName;
	
	private static String uploadsFolderName;
	
	@PostConstruct
	private void init(){
		MediaManager.setUploadFolderName(folderName);
	}
	
	private static void setUploadFolderName(String folderName){
		MediaManager.uploadsFolderName = folderName;
	}
	
	public static String getAbsoluteUploadsPath(){
		return System.getProperty("user.home")
					 .concat("/").concat(uploadsFolderName);
	}
	
	public static void saveBase64EncodedFile(String base64Image, String fileName) throws IOException {
		File folder = new File(MediaManager.getAbsoluteUploadsPath());
		if(!folder.exists()){
			folder.mkdir();
		}
		
		String path = folder.getPath().concat("/").concat(fileName);
		FileOutputStream outFileStream = new FileOutputStream(path);
		
		byte[] imageByteArray = Base64.getDecoder().decode(base64Image.replace("data:image/jpeg;base64,", ""));
		outFileStream.write(imageByteArray);
		outFileStream.close();
	}
	
	public static String getBase64EncodedFile(String fileName) throws IOException {
		String path = MediaManager.getAbsoluteUploadsPath().concat("/").concat(fileName);
		File file = new File(path);
		FileInputStream inFileStream = new FileInputStream(file);
		
		byte fileData[] = new byte[(int) file.length()];
		inFileStream.read(fileData);
		String base64Image = Base64.getEncoder().encodeToString(fileData);
		inFileStream.close();
		
		return base64Image;
	}
	
}

