package com.example.managerfile.repository;

import com.example.managerfile.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByUser_IdOrderByCreatedAtDesc(Long id);
}
