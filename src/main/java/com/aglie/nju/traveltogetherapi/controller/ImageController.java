package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.model.ImagePath;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.util.FileTools;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
//        File dest = new File(fileName);
        try {
//            file.transferTo(dest);
//            file.transferTo(new File(fileName));
//            int read = 0;
//            FileOutputStream out = new FileOutputStream(new File(fileName));
//            out.write(file.getBytes());
//            out.flush();
//            out.close();
            FileTools.uploadFile(file.getBytes(),fileName);
        } catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
        }
        return ResultTools.result(0,"",null);
    }
}
