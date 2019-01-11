package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.model.ImagePath;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.util.FileTools;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImageController {

    /**
     * 上传图片
     * @param file
     * @param account
     * @param item
     * @return
     */
    @RequestMapping(value = {"/uploadImg"}, method = RequestMethod.POST)
    public ResultModel uploadImg(@RequestParam("file")MultipartFile file,
                                 String account, Integer item){
        if(account==null || item == null){
            return ResultTools.result(1001,"",null);
        }
        if (file.isEmpty()) {
            return ResultTools.result(1004,"",null);
        }
        ImagePath img = new ImagePath();
        img.setItem(item);
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        img = FileTools.imgPath(img, account,suffixName);
        fileName = img.getPath();
        System.out.println(fileName);
        try {
            FileTools.uploadFile(file.getBytes(),fileName);
        } catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
        }
        return ResultTools.result(0,"",null);
    }

    @RequestMapping(value = {"/getImage"}, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResultModel getImg(String account,Integer item) throws IOException {
        if(account == null || item == null){
            throw new IOException("参数错误");
        }
        try{
            BufferedImage bi = null;
            String filePath = null;
            if(item == 0){
                filePath = "travelPic/headPic/";
                ArrayList<String> files = FileTools.getFiles(filePath);
                for (int i = 0;i<files.size();i++){
                    if (files.get(i).substring(0, files.get(i).lastIndexOf('.')).equals(account)){
                        bi = ImageIO.read(new FileInputStream(new File(filePath + files.get(i))));
                    }
                }
            }else if(item == 1){
                filePath = "travelPic/codePic/";
                ArrayList<String> files = FileTools.getFiles(filePath);
                for (int i = 0;i<files.size();i++){
                    if (files.get(i).substring(0, files.get(i).lastIndexOf('.')).equals(account)){
                        bi = ImageIO.read(new FileInputStream(new File(filePath + files.get(i))));
                    }
                }
            }else if (item == 2){
                filePath = "travelPic/commentPic/";
                ArrayList<String> files = FileTools.getFiles(filePath);
                for (int i = 0;i<files.size();i++){
                    if (files.get(i).substring(0, files.get(i).lastIndexOf('.')).equals(account)){
                        bi = ImageIO.read(new FileInputStream(new File(filePath + files.get(i))));
                    }
                }
            }else if (item == 3){
                filePath = "travelPic/activityPic/";
                ArrayList<String> files = FileTools.getFiles(filePath);
                for (int i = 0;i<files.size();i++){
                    if (files.get(i).substring(0, files.get(i).lastIndexOf('.')).equals(account)){
                        bi = ImageIO.read(new FileInputStream(new File(filePath + files.get(i))));
                    }
                }
            }else {
                throw new IOException("Item参数错误");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("BufferImage", bi);
            return ResultTools.result(0,"",map);
        }catch (Exception e){
            throw new IOException(e.getMessage());
        }
    }
}
