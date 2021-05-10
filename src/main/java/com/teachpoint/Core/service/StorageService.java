package com.teachpoint.Core.service;

import org.springframework.web.multipart.MultipartFile;

public interface   StorageService {

    String saveFile(MultipartFile file, String extention);

}
