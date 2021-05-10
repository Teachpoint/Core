package com.teachpoint.Core.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${teachpoint.core.storagePath}")
    private String path;
    private Path root = Paths.get("c:\\profile_photos");

    public StorageServiceImpl(){


    }

    @Override
    public String saveFile(MultipartFile file, String extention) {

        UUID fileId = UUID.randomUUID();

        try {
            System.out.println(root.toString());
            Files.copy(file.getInputStream(), this.root.resolve(fileId.toString() + "."+extention));
            return fileId.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
