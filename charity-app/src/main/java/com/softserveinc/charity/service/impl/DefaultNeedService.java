package com.softserveinc.charity.service.impl;

import com.softserveinc.charity.model.Image;
import com.softserveinc.charity.model.need.Need;
import com.softserveinc.charity.repository.converter.Converter;
import com.softserveinc.charity.repository.jpa.ImageRepository;
import com.softserveinc.charity.repository.jpa.NeedRepository;
import com.softserveinc.charity.repository.search.NeedSearchRepository;
import com.softserveinc.charity.service.FileService;
import com.softserveinc.charity.service.NeedService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public class DefaultNeedService implements NeedService {

    private static final Logger LOG = Logger.getLogger(DefaultNeedService.class);

    @Autowired
    private NeedRepository needRepository;

    @Autowired
    private NeedSearchRepository needSearchRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileService fileService;

    @Override
    @Transactional
    public Need save(final Need need, final Map<String, MultipartFile> files) {
        final Need savedNeed = needRepository.save(need);
        final int newId = savedNeed.getId();

        final Set<Image> images = new HashSet<>();
        for (final MultipartFile file : files.values()) {
            final String pathToFile = fileService.saveImage(file, "need/" + need.getId() + "/");
            images.add(imageRepository.save(new Image(pathToFile)));
            LOG.debug(file.getOriginalFilename() + "  " + newId + " " + pathToFile);
        }
        if (!images.isEmpty()) {
            savedNeed.setImages(images);
            needRepository.save(savedNeed);
        }

        needSearchRepository.save(Converter.convert(savedNeed));

        return savedNeed;
    }
}
