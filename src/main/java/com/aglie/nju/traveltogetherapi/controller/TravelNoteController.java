package com.aglie.nju.traveltogetherapi.controller;

import com.aglie.nju.traveltogetherapi.mapper.TravelNoteMapper;
import com.aglie.nju.traveltogetherapi.model.ResultModel;
import com.aglie.nju.traveltogetherapi.model.TravelNoteInfo;
import com.aglie.nju.traveltogetherapi.util.CkeckParameter;
import com.aglie.nju.traveltogetherapi.util.ResultTools;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TravelNoteController {
    @Autowired
    private TravelNoteMapper travelNoteMapper;

    /**
     * 用户创建游记
     * @param noteInfo
     * @return
     */
    @RequestMapping(value = "/createTravelNote", method = RequestMethod.GET)
    public ResultModel createTravelNote(TravelNoteInfo noteInfo){

        try {
            if (!CkeckParameter.checkTravelNote(noteInfo)){
                return ResultTools.result(1001,"",null);
            }
            int code = travelNoteMapper.createTravelNote(noteInfo);
            if(code == 1){
                return ResultTools.result(0, "success", null);
            }
            return ResultTools.result(404,"failed", null);
        }catch (Exception e){
            return ResultTools.result(404,e.getMessage(),null);
        }
    }

    /**
     * 查看所有用户的游记
     * @return
     */
    @RequestMapping(value = "/queryAllTravelNote", method = RequestMethod.GET)
    public ResultModel queryAllTravelNote(){
        try {
            List<TravelNoteInfo> noteInfos = travelNoteMapper.selectAllTravelNote();
            for(TravelNoteInfo noteInfo:noteInfos){
                noteInfo.setImgPath("/image/2_"+noteInfo.getId() +".jpg");

            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", noteInfos);
            return ResultTools.result(0, "", map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/queryUserTravelNote", method = RequestMethod.GET)
    public ResultModel queryUserTravelNote(String account){
        try {
            List<TravelNoteInfo> noteInfos = travelNoteMapper.selectTravelNote(account);
            for(TravelNoteInfo noteInfo:noteInfos){
                noteInfo.setImgPath("/image/2_"+noteInfo.getId() +".jpg");

            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", noteInfos);
            return ResultTools.result(0, "", map);
        }catch (Exception e){
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

}
