package com.hkunitedauction.maindata.configuration;

import com.hkunitedauction.util.File.FileSystemStorageService;
import com.hkunitedauction.util.File.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {

    @Value("${file.root}")
    private String fileRoot;

    @Value("${file.baseUrl}")
    private String baseUrl;

    @Bean
    public StorageService storageService(){
        return new FileSystemStorageService(fileRoot, baseUrl);
    }
}
