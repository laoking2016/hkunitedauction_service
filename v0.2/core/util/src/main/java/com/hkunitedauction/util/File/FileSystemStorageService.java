package com.hkunitedauction.util.File;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileSystemStorageService implements StorageService {

    private String root;

    private String baseUrl;

    public FileSystemStorageService(String root, String baseUrl){
        this.root = root;
        this.baseUrl = baseUrl;
    }

    @Override
    public StorageResult store(MultipartFile file) throws IOException, NoSuchAlgorithmException {

        SimpleDateFormat formator = new SimpleDateFormat("yyyyMM");
        String folder = formator.format(new Date());

        new File(root + "/" + folder).mkdirs();
        Path path = Paths.get(root + "/" + folder);

        InputStream is =  file.getInputStream();
        byte[] bytes = FileUtil.toByteArray(is);

        String md5 = FileUtil.MD5(bytes);
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());

        String filename = md5 + "." + ext;
        String thumbnailName = "icon_" + filename;

        try {
            if (file.isEmpty()) {
                throw new IOException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new IOException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }

            Files.copy(file.getInputStream(), path.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            Thumbnails.of(path.resolve(filename).toString()).scale(0.2f).toFile(path.resolve(thumbnailName).toString());

            StorageResult storeResult = new StorageResult();
            storeResult.setVpath(folder);
            storeResult.setIconVpath(folder);
            storeResult.setPath(root + "/" + folder);
            storeResult.setIconPath(root + "/" + folder);
            storeResult.setName(filename);
            storeResult.setIconName(thumbnailName);
            storeResult.setCheckSum(md5);
            if("/".equals(baseUrl)){
                baseUrl = "";
            }
            storeResult.setUrl(baseUrl + "/" + folder + '/' + filename);
            storeResult.setIconUrl(baseUrl + "/" + folder + '/' + thumbnailName);

            return storeResult;
        }
        catch (IOException e) {
            throw new IOException("Failed to store file " + filename, e);
        }
    }
}
