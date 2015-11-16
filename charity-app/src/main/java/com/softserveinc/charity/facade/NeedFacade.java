package com.softserveinc.charity.facade;

import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.request.NeedRequestData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface NeedFacade {

    Need save(final NeedRequestData requestData, Map<String, MultipartFile> files);

}
