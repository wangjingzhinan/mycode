package com.wjzn.service;

import com.wjzn.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjzn.entity.Budget;
import com.wjzn.vo.OptionVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjzn
 * @since 2021-08-16
 */
public interface AccountService extends IService<Account> {
    public Integer finID();
    public List<OptionVO> getMyAccount(List<Account> accounts);
    public boolean change(Account account,Integer type);
}
