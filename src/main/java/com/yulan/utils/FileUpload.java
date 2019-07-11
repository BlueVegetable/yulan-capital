package com.yulan.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileUpload {

    private final static int LENGTH=1024;

    private final static String PATH = "D:\\Downloads\\apache-tomcat-9.0.12\\upload";//本地路径
//    private final static String PATH = "D:\\application\\apache-tomcat-main\\upload";//服务器路劲
    private final static String PaymentBill_PATH = "/paymentBill-image/";//保存银行汇款图



    public static Map<String,Object> copyFile(MultipartFile file, String path, String fileName) {
        String type = file.getContentType();
        String typeValue = type.substring(type.lastIndexOf('/')+1);
        //      String fileName = System.currentTimeMillis()+"-"+file.hashCode()+"-"+(int)(100000000000000000L*Math.random())+"."+typeValue;
        //     String fileName = file.getOriginalFilename();
        String filePath = path+fileName + "."+ typeValue;
        Integer code = 0;
        String msg="SUCCESS";

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
            code = 1;
            msg="FALSE";
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
        result.put("msg",msg);
        result.put("fileName",fileName + "." + typeValue);
        result.put("filePath",filePath);
        result.put("sqlpath",PaymentBill_PATH+fileName + "." + typeValue);//保存到数据库的相对路径
        result.put("fileTypecopyImg",type);
        return result;
    }

    public static Map<String,Object> copyPaymentBillImg(MultipartFile file,String fileName) {
        Map<String,Object> result = copyFile(file,PATH + PaymentBill_PATH ,fileName);
        return result;
    }

    public static Map deleteFile(String deletePath) {
        Map result=new HashMap();
        deletePath=PATH+PaymentBill_PATH+deletePath;
        String Msg="SUCCESS";
        Integer code=0;
        Path path = Paths.get(deletePath);
        if(Files.exists(path)) {
            try {
                Files.delete(path);

            } catch (IOException e) {
                Msg="FALSE";
                code=1;
                e.printStackTrace();
            }
        }
        result.put("msg",Msg);
        result.put("code",code);
        return  result;
    }
    public static String getPayBillRealPath(String fileName){
        return PATH+PaymentBill_PATH+fileName;
    }

}
