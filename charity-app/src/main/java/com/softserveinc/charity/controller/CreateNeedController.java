package com.softserveinc.charity.controller;

import com.softserveinc.charity.facade.NeedFacade;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.model.request.NeedRequestData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class CreateNeedController {

    private static final Logger LOG = Logger.getLogger(CreateNeedController.class);

    @Autowired
    private NeedFacade needFacade;

    @RequestMapping(value = "/api/createNeed", method = RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can create need by posting to this same URL.";
    }

    @RequestMapping(value = "/api//createNeed", method = RequestMethod.POST)
    public @ResponseBody Need handleFileUpload(final MultipartHttpServletRequest request, final NeedRequestData requestData) {

        LOG.info("Creating New Need");

        final Need createdNeed = needFacade.save(requestData);

        LOG.info("New Need Created");
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(new File(name)));
//                stream.write(bytes);
//                stream.close();
//                return "You successfully uploaded " + name + "!";
//            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name + " because the file was empty.";
//        }

        return createdNeed;
    }
}
