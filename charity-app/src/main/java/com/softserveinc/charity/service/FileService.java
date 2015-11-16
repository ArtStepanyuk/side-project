package com.softserveinc.charity.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String saveImage(final MultipartFile file, final String prefix);

}
