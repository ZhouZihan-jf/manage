package com.jiudian.manage.controller;

import com.jiudian.manage.model.Income;
import com.jiudian.manage.service.impl.IncomeServiceImpl;
import com.jiudian.manage.until.State;
import com.jiudian.manage.until.StateSignal;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "收入控制器")
@RequestMapping(value = "/income")
public class IncomeController {

    @Autowired
    IncomeServiceImpl incomeService;

    @RequestMapping("/addIncome.do")
    public Map addIncome(@RequestParam double tmoney, @RequestParam double troom, @RequestParam String ttime){
        boolean b = incomeService.addIncome(tmoney,troom,ttime);
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

    @RequestMapping("/getAllIncome.do")
    @ResponseBody
    public List<Income> getAllIncome(@RequestParam int pageNum, @RequestParam int pageSize){
        List<Income> allIncome = incomeService.getAllLog(pageNum,pageSize);

        System.err.println(allIncome.toString());

        return  allIncome;
    }

    @RequestMapping("/getIncomeById.do")
    public Map getRoomById(){
        Income income = incomeService.getIncomeById();
        StateSignal signal = new StateSignal();

        if(income!=null){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
            signal.put("income",income);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return  signal.getResult();
    }
}
