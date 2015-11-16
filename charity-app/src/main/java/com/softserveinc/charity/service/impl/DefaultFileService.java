package com.softserveinc.charity.service.impl;

import com.softserveinc.charity.service.FileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@PropertySource("classpath:application.properties")
public class DefaultFileService implements FileService {

    private static final Logger LOG = Logger.getLogger(DefaultFileService.class);

    @Value("${images.folder}")
    private String imagesFolder;

    @Override
    public String saveImage(final MultipartFile file, final String prefix) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        new File(imagesFolder + prefix).mkdirs();
        final String fileName = imagesFolder + prefix + file.getOriginalFilename();
        final File newFile = new File(fileName);

        try {
            inputStream = file.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newFile.getAbsolutePath();
    }

    public void setImagesFolder(String imagesFolder) {
        this.imagesFolder = imagesFolder;
    }
}
