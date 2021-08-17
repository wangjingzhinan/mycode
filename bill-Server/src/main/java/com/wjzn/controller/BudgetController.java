package com.wjzn.controller;


import com.wjzn.entity.Budget;
import com.wjzn.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("//budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @GetMapping("/list")
    public List<Budget> list(){
        return this.budgetService.getMyRest(this.budgetService.list());
    }
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        return this.budgetService.removeById(id);
    }
    @GetMapping("/find/{id}")
    public Budget findById(@PathVariable("id") Integer id){
        return this.budgetService.getById(id);
    }
    @GetMapping("/findid")
    public Integer findId(){ return this.budgetService.finID(); }
    @PutMapping("/update")
    public boolean modify(@RequestBody Budget budget){
        return this.budgetService.updateById(budget);
    }
    @PostMapping("/add")
    public boolean add(@RequestBody Budget budget){
        System.out.println("add");
        return this.budgetService.save(budget);
    }

}

