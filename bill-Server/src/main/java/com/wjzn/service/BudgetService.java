package com.wjzn.service;

import com.wjzn.entity.Budget;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjzn
 * @since 2021-08-16
 */
public interface BudgetService extends IService<Budget> {
    public Integer finID();
    public List<Budget> getMyRest(List<Budget> budgets);

}
