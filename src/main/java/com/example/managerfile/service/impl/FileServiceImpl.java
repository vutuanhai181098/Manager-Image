package com.example.managerfile.service.impl;

import com.example.managerfile.entities.File;
import com.example.managerfile.entities.User;
import com.example.managerfile.exception.ResourceNotFoundException;
import com.example.managerfile.repository.FileRepository;
import com.example.managerfile.repository.UserRepository;
import com.example.managerfile.response.ErrorResponse;
import com.example.managerfile.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    public FileServiceImpl(FileRepository fileRepository,
                       UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }
    @Override
    public File getFileById(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with id" + id));
    }

    @Override
    public void deleteFile(Long id) {
        File file = getFileById(id);
        fileRepository.delete(file);
    }

    @Override
    public File uploadFile(Long userId, MultipartFile file) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        File newFile = new File();
        newFile.setType(file.getContentType());
        newFile.setData(file.getBytes());
        newFile.setUser(user);
        fileRepository.save(newFile);
        return newFile;
    }

    @Override
    public List<File> getFileByUserId(Long id) {
        return fileRepository.findByUser_IdOrderByCreatedAtDesc(id);
    }

}
