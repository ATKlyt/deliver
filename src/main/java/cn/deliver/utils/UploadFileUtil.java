package cn.deliver.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 93231
 */
public class UploadFileUtil {
    /**
     * 在service.xml中配置映射地址，用/upload映射服务器本地磁盘中的地址
     */
    private static final String UPLOADPATH = "/uploads";

    private UploadFileUtil(){}

    /**
     * 上传文件
     * @param file 封装文件信息的Multipartfile对象
     * @return 图片的url
     */
    public static String uploadFile(MultipartFile file ,String type){
        String path = UPLOADPATH + "/" + type;
        File files = new File(path);
        if(!files.exists()){
            files.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-","");
        fileName = uuid + "_" + fileName;
        try {
            file.transferTo(new File(path,fileName));
            return path + "/" + fileName;
        } catch (IOException e) {
            return null;
        }
    }

}
