package com.hkunitedauction.util.File;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface StorageService {
    StorageResult store(MultipartFile file) throws IOException, NoSuchAlgorithmException;
}
