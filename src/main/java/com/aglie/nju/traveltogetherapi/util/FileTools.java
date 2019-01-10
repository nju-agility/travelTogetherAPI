package com.aglie.nju.traveltogetherapi.util;

import com.aglie.nju.traveltogetherapi.model.ImagePath;

import java.io.File;
import java.io.FileOutputStream;

public class FileTools {
    /**
     * 生成文件存放路径
     * item的值
     * 0---------头像
     * 1---------身份证
     * 2---------评论图片
     * 3---------活动图片
     * @param img
     * @param account
     * @param suffixName
     * @return
     */
    public static ImagePath imgPath(ImagePath img, String account, String suffixName){
        if(img.getItem()==0){
            String path = "travelPic/headPic/" + account + suffixName; // 新文件名
            img.setPath(path);
            return img;
        }else if(img.getItem()==1){
            String path = "travelPic/codePic/" + account + suffixName; // 新文件名
            img.setPath(path);
            return img;
        }else if(img.getItem()==2){
            String path = "travelPic/commentPic/" + account + suffixName; // 新文件名
            img.setPath(path);
            return img;
        }else if(img.getItem()==3){
            String path = "travelPic/activityPic/" + account + suffixName; // 新文件名
            img.setPath(path);
            return img;
        }else{
            return null;
        }
    }

    public static void uploadFile(byte[] file, String fileName) throws Exception {
        File imgFile = new File(fileName);
        FileOutputStream out = new FileOutputStream(imgFile);
        if (!imgFile.getParentFile().exists()) {
            imgFile.getParentFile().mkdirs();
        }
        out.write(file);
        out.flush();
        out.close();
    }
}
