package com.softserveinc.charity.service;

import com.softserveinc.charity.model.need.Need;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface NeedService {
    Need save(final Need need, final Map<String, MultipartFile> files);
}
