package me.bnnq.homework.Utils;

import me.bnnq.homework.Models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class Resources
{
    private static final String IMAGE_UPLOAD_DIRECTORY = new File("src/main/resources/static/images").getAbsolutePath();

    public static String getImageUploadDirectory()
    {
        return IMAGE_UPLOAD_DIRECTORY;
    }

    public static String saveImage(MultipartFile image) {
        try {
            byte[] bytes = image.getBytes();

            Path path = Paths.get(IMAGE_UPLOAD_DIRECTORY);
            if (!Files.exists(path))
            {
                Files.createDirectories(path);
            }

            String originalFilename = image.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            String filename = UUID.randomUUID() + extension;

            Path filePath = Paths.get(IMAGE_UPLOAD_DIRECTORY + "/" + filename);
            Files.write(filePath, bytes);

            return "/images/" + filename;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


}