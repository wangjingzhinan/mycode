package com.wjzn.service.impl;

import com.wjzn.entity.Account;
import com.wjzn.mapper.AccountMapper;
import com.wjzn.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjzn.vo.OptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjzn
 * @since 2021-08-16
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Integer finID() {
        List<Account> accounts = this.accountMapper.selectList(null);
        Integer id=0;
        for(Account account:accounts){
            id=account.getId()+1;
        }
        return id;
    }

    @Override
    public List<OptionVO> getMyAccount(List<Account> accounts) {
        List<OptionVO> optionVOList= new ArrayList<>();
        for(int i=0;i<accounts.size();i++){
            OptionVO optionVo = new OptionVO();
            optionVo.setValue(accounts.get(i).getAccount());
            optionVo.setLable(accounts.get(i).getAccount());
            optionVOList.add(optionVo);
        }
        return optionVOList;
    }

    @Override
    public boolean change(Account account,Integer type) {
        System.out.println(account);
        List<Account> accounts = this.accountMapper.selectList(null);
        Account select1=accounts.get(account.getId()-1);
        Account select2=null;
        for(Account ac:accounts){
            if(ac.getAccount().equals(account.getAccount())){
                select2=ac;
            }
        }
        if(type==1){
            select1.setMoney(select1.getMoney()+account.getMoney());
            select2.setMoney(select2.getMoney()-account.getMoney());
            this.accountMapper.updateById(select1);
            this.accountMapper.updateById(select2);
            return true;
        }else{
            select1.setMoney(select1.getMoney()-account.getMoney());
            select2.setMoney(select2.getMoney()+account.getMoney());
            this.accountMapper.updateById(select1);
            this.accountMapper.updateById(select2);
            return true;
        }
    }
}
