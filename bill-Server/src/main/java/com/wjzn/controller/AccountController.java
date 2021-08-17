package com.wjzn.controller;


import com.wjzn.entity.Account;
import com.wjzn.entity.Budget;
import com.wjzn.service.AccountService;
import com.wjzn.service.BudgetService;
import com.wjzn.vo.OptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjzn
 * @since 2021-08-16
 */
@RestController
@RequestMapping("//account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    public List<Account> list(){
        return this.accountService.list();
    }
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        return this.accountService.removeById(id);
    }
    @GetMapping("/find/{id}")
    public Account findById(@PathVariable("id") Integer id){
        return this.accountService.getById(id);
    }
    @GetMapping("/findid")
    public Integer findId(){ return this.accountService.finID(); }
    @PutMapping("/update")
    public boolean modify(@RequestBody Account account){
        return this.accountService.updateById(account);
    }
    @PostMapping("/changein")
    public boolean changeIn(@RequestBody Account account){
        return this.accountService.change(account,1);
    }
    @PostMapping("/changeout")
    public boolean changeOut(@RequestBody Account account){
        return this.accountService.change(account,0);
    }
    @GetMapping("/findaccount")
    public List<OptionVO> findAccounts(){ return this.accountService.getMyAccount(this.accountService.list()); }
    @PostMapping("/add")
    public boolean add(@RequestBody Account account){
        return this.accountService.save(account);
    }
}

