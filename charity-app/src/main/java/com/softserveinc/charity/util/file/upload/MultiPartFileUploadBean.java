package com.softserveinc.charity.util.file.upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MultiPartFileUploadBean {

    private List<MultipartFile> files;

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }
}
