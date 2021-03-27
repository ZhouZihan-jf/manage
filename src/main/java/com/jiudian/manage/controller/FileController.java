package com.jiudian.manage.controller;

import com.jiudian.manage.service.UserService;
import com.jiudian.manage.until.FileUtil;
import com.jiudian.manage.until.State;
import com.jiudian.manage.until.StateSignal;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@RestController
@Api(value = "文件控制器，用于上传信息")
@RequestMapping(value = "/upFile")
public class FileController {
    @Autowired
    UserService userService;

    @RequestMapping("/upFilePhoto.do")
    public Map upFilePhoto(@RequestParam MultipartFile file,@RequestParam int userid){
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();

        String filePath = ".\\src\\main\\resources\\static\\File\\";
        String RealfilePath = "File\\"+fileName;
        boolean photo = userService.photo(userid, RealfilePath);
        boolean b = false;
        try {
           b = FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StateSignal signal = new StateSignal();
        if(b&&photo){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return signal.getResult();
    }

    //为了导出日志表所用
    @RequestMapping("/exportLoad.do")
    public String fileLoad(){
        return "file";
    }


}
