package com.example.managerfile.service;

import com.example.managerfile.entities.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    File getFileById(Long id);

    void deleteFile(Long id);

    File uploadFile(Long userId, MultipartFile file) throws IOException;

    List<File> getFileByUserId(Long id);

}
