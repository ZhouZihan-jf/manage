package com.jiudian.manage.controller;

import com.jiudian.manage.model.Log;
import com.jiudian.manage.service.impl.LogServiceImpl;
import com.jiudian.manage.until.POIUtil;
import com.jiudian.manage.until.State;
import com.jiudian.manage.until.StateSignal;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "日志控制器")
@RequestMapping(value = "/log")
public class LogController {
    @Autowired
    LogServiceImpl logService;

    @RequestMapping("/addLog.do")
    public Map addLog(@RequestParam String householdname, @RequestParam long holdphone,
                      @RequestParam String starttime, @RequestParam String endtime,@RequestParam double money,
                      @RequestParam int roomid, @RequestParam int userid){
        boolean b = logService.addLog(householdname,holdphone,starttime,endtime,money,roomid,userid);
        StateSignal signal = new StateSignal();
        if(b){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return signal.getResult();
    }

    @RequestMapping("/delLog.do")
    public Map delLog(){
        int b = logService.delLog();
        StateSignal signal = new StateSignal();
        if(b>0){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return signal.getResult();
    }

    @RequestMapping("/getAllLog.do")
    public Map getAllLog(@RequestParam int pageNum,@RequestParam int pageSize){
        List<Log> allLog = logService.getAllLog(pageNum,pageSize);
        StateSignal signal = new StateSignal();
        if(allLog!=null){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
            signal.put("List",allLog);
            signal.put("pageNum",pageNum);
            signal.put("pageSize",pageSize);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return  signal.getResult();
    }

    @RequestMapping("/export.do")
    public ResponseEntity<byte[]> excelExport(@RequestParam int pageNum,@RequestParam int pageSize){
        List<Log> log=logService.getAllLog(pageNum,pageSize);
        return POIUtil.ExportExcel(log);
    }

}
