package com.wjzn.controller;


import com.wjzn.entity.Bill;
import com.wjzn.service.BillService;
import com.wjzn.vo.BarVO;
import com.wjzn.vo.PieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjzn
 * @since 2021-08-16
 */
@RestController
@RequestMapping("//bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("/list")
    public List<Bill> list(){
        return this.billService.MyStatus();
    }
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        this.billService.deleteAccount(id);
        return this.billService.removeById(id);
    }
    @GetMapping("/find/{id}")
    public Bill findById(@PathVariable("id") Integer id){
        return this.billService.getById(id);
    }
    @GetMapping("/findid")
    public Integer findId(){ return this.billService.finID(); }
    @GetMapping("/findtime")
    public String findtime(){
        Calendar calendar = Calendar.getInstance(); // get current instance of the calendar
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(calendar.getTime()));
        return formatter.format(calendar.getTime());
    }
    @GetMapping("/now")
    public Double[] now(){
        return this.billService.now();
    }
    @PutMapping("/update")
    public boolean modify(@RequestBody Bill bill){
        this.billService.updateAccount(bill);
        return this.billService.updateById(bill);
    }
    @PostMapping("/add")
    public boolean add(@RequestBody Bill bill){
        this.billService.chooseAcconut(bill);
        return this.billService.save(bill);
    }
    @GetMapping("/barVO")
    public BarVO barVO(){
        return  this.billService.barVOList();
    }
    @GetMapping("/pieVO")
    public List<PieVO> type(){
        return  this.billService.pieVOList();
    }
    @GetMapping("/pieVO2")
    public List<PieVO> type2(){
        return  this.billService.pieVOList2();
    }

}

