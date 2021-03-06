package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.mapper.UserMapper;
import com.aglie.nju.traveltogetherapi.mapper.VerifyStudentMapper;
import com.aglie.nju.traveltogetherapi.model.ImagePath;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.model.UserInfo;
import com.aglie.nju.traveltogetherapi.util.FileTools;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerifyStudentMapper verifyStudentMapper;

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
        if(item == 1){
            if (account.startsWith("\"") && account.endsWith("\"")){
                account = account.substring(1,account.length()-1);
//            System.out.println(account);
            }
            UserInfo user = userMapper.selectUserByAccount(account);
            System.out.println(user.getAccount());
            user.setStatus(1);
            verifyStudentMapper.changeStudentStatus(user);
        }
        return ResultTools.result(0,"",null);
    }
}
