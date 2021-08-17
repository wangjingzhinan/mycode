package com.wjzn.service.impl;

import com.wjzn.entity.Budget;
import com.wjzn.mapper.BudgetMapper;
import com.wjzn.service.BillService;
import com.wjzn.service.BudgetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjzn.vo.BarVO;
import com.wjzn.vo.DataVo;
import com.wjzn.vo.PieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjzn
 * @since 2021-08-16
 */
@Service
public class BudgetServiceImpl extends ServiceImpl<BudgetMapper, Budget> implements BudgetService {
    @Autowired
    private BudgetMapper budgetMapper;
    @Autowired
    private BillService billService;
    @Override
    public Integer finID() {
        List<Budget> budgets = this.budgetMapper.selectList(null);
        Integer id=0;
        for(Budget budget:budgets){
            id=budget.getId()+1;
        }
        return id;
    }

    @Override
    public List<Budget> getMyRest(List<Budget> budgets) {
        BarVO barVO = this.billService.barVOList();
        List<DataVo> values=barVO.getValues();
        Double out[] = new Double[5];
        String names[] = new String[5];
        for (int i=0;i<out.length;i++){
            out[i]=0.00;
            names[i]="";
        }
        out[0]= values.get(1).getValue();
        names[0] = "总支出";
        List<PieVO> pieVOList2= this.billService.pieVOList2();
        for (int i=1;i<pieVOList2.toArray().length;i++){
            out[i]=pieVOList2.get(i-1).getValue();
            names[i]=pieVOList2.get(i-1).getName();
        }
        for(Budget budget:budgets){
            for(int i=0;i<5;i++) {
                if (budget.getName().equals(names[i])){
                    budget.setRest(budget.getMoney()-out[i]);
                }
            }
            this.budgetMapper.updateById(budget);
        }
        return budgets;
    }

}
