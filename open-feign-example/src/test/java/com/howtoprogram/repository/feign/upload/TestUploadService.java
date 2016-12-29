package com.howtoprogram.repository.feign.upload;

import com.howtoprogram.repository.feign.auth.AuthStatus;
import com.howtoprogram.repository.feign.auth.RequestReponseService;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestUploadService {

    @Test
    public void testUploadFile() {
        UploadService uploadService = new UploadService();
        File file = new File("notes.txt");
        assertTrue(file.exists());
        assertTrue(uploadService.uploadFile("The optional file name",file));

    }

}
