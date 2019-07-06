package com.yulan.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileUpload {

    private final static int LENGTH=1024;
    /**
     * 本地路径
     */
    private final static String PATH = "H:\\upload";
    private final static String PaymentBill_PATH = "/paymentBill-image/";//保存银行汇款图


    public static Map<String,Object> copyFile(MultipartFile file, String path, String fileName) {
        String type = file.getContentType();
        String typeValue = type.substring(type.lastIndexOf('/')+1);
        //      String fileName = System.currentTimeMillis()+"-"+file.hashCode()+"-"+(int)(100000000000000000L*Math.random())+"."+typeValue;
        //     String fileName = file.getOriginalFilename();
        String filePath = path+fileName + "."+ typeValue;
        String code = "SUCCESS";

        InputStream is = null;
        OutputStream os = null;
        try {
            if(!Files.exists(Paths.get(path)))
                Files.createDirectories(Paths.get(path));
            is = file.getInputStream();
            os = new FileOutputStream(Paths.get(filePath).toFile());
            byte[] buffer = new byte[LENGTH];
            int size;
            while((size=is.read(buffer,0,LENGTH))>0) {
                os.write(buffer,0,size);
            }
        } catch (IOException e) {
            code = "FAILED";
            e.printStackTrace();
        } finally {
            try {
                if(os!=null) {
                    os.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }

        Map<String,Object> result = new HashMap<>(4);
        result.put("code",code);
        result.put("fileName",fileName + "." + typeValue);
        result.put("filePath",filePath);
        result.put("fileTypecopyImg",type);
        return result;
    }
}
