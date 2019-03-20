package com.aglie.nju.traveltogetherapi.util;

import com.aglie.nju.traveltogetherapi.model.ImagePath;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FileTools {
    /**
     * 生成文件存放路径
     * item的值
     * 0---------头像
     * 1---------身份证
     * 2---------评论图片
     * 3---------活动图片
     * @param imagePath
     * @param account
     * @param suffixName
     * @return
     */
    public static ImagePath imgPath(ImagePath imagePath, String account, String suffixName){
        ImagePath img = imagePath;
        if (account.startsWith("\"") && account.endsWith("\"")){
            account = account.substring(1,account.length()-1);
//            System.out.println(account);
        }
        if(img.getItem()==0){
            String path = "travelPic/" + img.getItem() + "_" + account + suffixName; // 新文件名
            img.setPath(path);
            return img;
        }else if(img.getItem()==1){
            String path = "travelPic/" + img.getItem() + "_" + account + suffixName; // 新文件名
            img.setPath(path);
            return img;
        }else if(img.getItem()==2){
            String path = "travelPic/" + img.getItem() + "_" + account + suffixName; // 新文件名
            img.setPath(path);
            return img;
        }else if(img.getItem()==3){
            String path = "travelPic/" + img.getItem() + "_" + account + suffixName; // 新文件名
            img.setPath(path);
            return img;
        }else{
            return null;
        }
    }

    public static void uploadFile(byte[] file, String fileName) throws Exception {
        File imgFile = new File(fileName);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(imgFile);
            if (!imgFile.getParentFile().exists()) {
                imgFile.getParentFile().mkdirs();
            }
            out.write(file);
            out.flush();
        }catch (Exception e){


        }finally {
            out.close();

        }
    }

    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
            }
        }
        return files;
    }

    public static String getImg(String account,Integer item){
        String filePath = "travelPic/";
        if (account.startsWith("\"") && account.endsWith("\"")){
            account = account.substring(1,account.length()-1);
//            System.out.println(account);
        }
        if(item == 0){
            ArrayList<String> files = FileTools.getFiles(filePath);
            for (int i = 0;i<files.size();i++){
                if (files.get(i).substring(files.get(i).lastIndexOf('\\')+1, files.get(i).lastIndexOf('.')).equals("0_"+account)){
//                    System.out.println(files.get(i));
//                    System.out.println(files.get(i).substring(files.get(i).lastIndexOf('\\')+1));
                    return files.get(i).substring(files.get(i).lastIndexOf('\\')+1);
                }else {
//                    System.out.println(files.get(i).lastIndexOf('\\')+1);
                }
            }
            return "default.jpg";
        }else if(item == 1){
            ArrayList<String> files = FileTools.getFiles(filePath);
            for (int i = 0;i<files.size();i++){
                if (files.get(i).substring(files.get(i).lastIndexOf('\\')+1, files.get(i).lastIndexOf('.')).equals("1_"+account)){
//                    System.out.println(files.get(i));
//                    System.out.println(files.get(i).substring(files.get(i).lastIndexOf('\\')+1));
                    return files.get(i).substring(files.get(i).lastIndexOf('\\')+1);
                }
            }
            return "Dont't find image!";
        }else if (item == 2){
            ArrayList<String> files = FileTools.getFiles(filePath);
            for (int i = 0;i<files.size();i++){
                if (files.get(i).substring(files.get(i).lastIndexOf('\\')+1, files.get(i).lastIndexOf('.')).equals("2_"+account)){
//                    System.out.println(files.get(i));
//                    System.out.println(files.get(i).substring(files.get(i).lastIndexOf('\\')+1));
                    return files.get(i).substring(files.get(i).lastIndexOf('\\')+1);
                }
            }
            return "Dont't find image!";
        }else if (item == 3){
            ArrayList<String> files = FileTools.getFiles(filePath);
            for (int i = 0;i<files.size();i++){
                if (files.get(i).substring(files.get(i).lastIndexOf('\\')+1, files.get(i).lastIndexOf('.')).equals("3_"+account)){
//                    System.out.println(files.get(i));
//                    System.out.println(files.get(i).substring(files.get(i).lastIndexOf('\\')+1));
                    return files.get(i).substring(files.get(i).lastIndexOf('\\')+1);
                }
            }
            return "activity_default.jpg";
        }else {
            return "Item error!";
        }
    }
}
