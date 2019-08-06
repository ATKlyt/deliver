package cn.deliver.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class UploadFileUtil {



    public static String uploadFile(MultipartFile multipartFile,String uploadFolder) {

        try {
            //2.把文件保存在某个路径
            //2.1文件保存的文件夹路径
            File uploadFolderFile = new File(uploadFolder);
            if(!uploadFolderFile.exists()){
                uploadFolderFile.mkdir();
            }
            //2.2.文件
            //取得所上传文件的文件名称和后缀
            String fileName = multipartFile.getOriginalFilename().split("\\.")[0];
            String suffix = multipartFile.getOriginalFilename().split("\\.")[1];
            //使用UUID设置文件名
            String newFileName =
                    fileName+ UUID.randomUUID().toString().replace("-","") + "." + suffix;
            //文件总路径
            String totalPath = uploadFolder + "\\" + newFileName;
            FileCopyUtils.copy(multipartFile.getInputStream(),new FileOutputStream(new File(totalPath)));
            return totalPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
