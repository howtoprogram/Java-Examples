package com.howtoprogram.book;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.howtoprogram.service.StorageService;

@RestController
@RequestMapping(value = "/v1")
public class FileUploadController {

	@Autowired
	private StorageService storageService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity uploadFile(@RequestParam(value = "file") MultipartFile multipartFile) {
		// stores the file to disk
		storageService.store(multipartFile);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	@RequestMapping(value = "/uploads", method = RequestMethod.POST)
	public ResponseEntity uploadFile(MultipartHttpServletRequest request) {

		try {
			Iterator<String> itr = request.getFileNames();
			while (itr.hasNext()) {
				String uploadedFile = itr.next();
				MultipartFile file = request.getFile(uploadedFile);
				String mimeType = file.getContentType();
				String filename = file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				System.out.println(mimeType);
				System.out.println(filename);

			}
		} catch (Exception e) {
			return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

}
