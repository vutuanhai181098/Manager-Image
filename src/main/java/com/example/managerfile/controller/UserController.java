package com.example.managerfile.controller;

import com.example.managerfile.entities.File;
import com.example.managerfile.entities.User;
import com.example.managerfile.service.FileService;
import com.example.managerfile.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserService userService;
    private final FileService fileService;

    public UserController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    // == API tra về HTML ==
    @GetMapping("/users")
    public String getUserPage(Model model){
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "web/user-page";
    }

    @GetMapping("/users/{id}/files")
    public String getFilePage(Model model, @PathVariable Long id){
        List<File> fileList = fileService.getFileByUserId(id);
        model.addAttribute("fileList", fileList);
        model.addAttribute("userId", id);
        return "web/file-page";
    }

    // == API tra về JSON ==
    @PostMapping("/api/users/{id}/files")
    public ResponseEntity<?> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileService.uploadFile(id, file));
    }
}
