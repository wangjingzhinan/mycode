package com.wjzn.service.impl;

import com.wjzn.entity.Account;
import com.wjzn.entity.Bill;
import com.wjzn.entity.Budget;
import com.wjzn.mapper.AccountMapper;
import com.wjzn.mapper.BillMapper;
import com.wjzn.mapper.BudgetMapper;
import com.wjzn.service.AccountService;
import com.wjzn.service.BillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjzn.util.DataUtil;
import com.wjzn.vo.BarVO;
import com.wjzn.vo.DataVo;
import com.wjzn.vo.PieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private BudgetMapper budgetMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BarVO barVOList(){
        BarVO barVO = new BarVO();
        List<String> names = new ArrayList<>();
        names.add("收入");
        names.add("支出");
        Double io[] = new Double[2];
        List<DataVo> values = new ArrayList<>();
        List<Bill> bills = this.billMapper.selectList(null);
        for (int i=0;i<io.length;i++){
            io[i]=0.00;
        }
        //xAlias
        for(Bill bill:bills){
            if(bill.getType().equals("收入")) {
                io[0]+=bill.getMoney();
            }else{
                io[1]+=bill.getMoney();
            }
        }
        for(int i=0;i<2;i++){
            DataVo dataVo = new DataVo();
            //money
            dataVo.setValue(io[i]);
            //itemstyle
            dataVo.setItemStyle(DataUtil.createItemStyle(i));
            values.add(dataVo);
        }
        barVO.setNames(names);
        barVO.setValues(values);
        System.out.println(values);
        //转换数据VO
        return barVO;
    }
    @Override
    public List<PieVO> pieVOList() {
        List<PieVO> pieVOList = new ArrayList<>();
        String aspects[] ={"工资","兼职收入"};
        List<Bill> bills = this.billMapper.selectList(null);
        Double io[] = new Double[2];
        for (int i=0;i<io.length;i++){
            io[i]=0.00;
        }
        for(Bill bill:bills){
            if (bill.getAspect().equals("工资")){
                io[0]+=bill.getMoney();
            }else if (bill.getAspect().equals("兼职收入")){
                io[1]+=bill.getMoney();
            }
        }
        for (int i=0;i<io.length;i++){
            PieVO pieVO = new PieVO();
            pieVO.setValue(io[i]);
            pieVO.setName(aspects[i]);
            pieVO.setItemStyle(DataUtil.createItemStyle(i));
            pieVOList.add(pieVO);
        }
        System.out.println(pieVOList);
        return pieVOList;
    }
    @Override
    public List<PieVO> pieVOList2() {
        List<PieVO> pieVOList = new ArrayList<>();
        String aspects[] ={"饮食","衣服","娱乐","生活用品"};
        List<Bill> bills = this.billMapper.selectList(null);
        Double io[] = new Double[4];
        for (int i=0;i<io.length;i++){
            io[i]=0.00;
        }
        for(Bill bill:bills){
            if (bill.getAspect().equals("饮食")){
                io[0]+=bill.getMoney();
            }else  if (bill.getAspect().equals("衣服")){
                io[1]+=bill.getMoney();
            }else  if (bill.getAspect().equals("娱乐")){
                io[2]+=bill.getMoney();
            }else if (bill.getAspect().equals("生活用品")){
                io[3]+=bill.getMoney();
            }
        }
        for (int i=0;i<io.length;i++){
            PieVO pieVO = new PieVO();
            pieVO.setValue(io[i]);
            pieVO.setName(aspects[i]);
            pieVO.setItemStyle(DataUtil.createItemStyle(i+2));
            pieVOList.add(pieVO);
        }
        return pieVOList;
    }

    @Override
    public Integer finID() {
        List<Bill> bills = this.billMapper.selectList(null);
        Integer id=0;
        for(Bill bill:bills){
            id=bill.getId()+1;
        }
        return id;
    }

    @Override
    public Double[] now() {
        Double now[]=new Double[2];
        for (int i=0;i<now.length;i++){
            now[i]=0.00;
        }
        List<Bill> bills = this.billMapper.selectList(null);
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH );//获取月
        for(Bill bill:bills){
            Date date = bill.getTime();
            int t=date.getMonth();
            if(t==month){
                System.out.println("111");
                if(bill.getType().equals("收入")){
                    now[0]+=bill.getMoney();
                }else{
                    now[1]+=bill.getMoney();
                }
            }
        }
        return now;
    }

    @Override
    public List<Bill> MyStatus() {
        List<Bill> bills = this.billMapper.selectList(null);
        List<Budget> budgets = this.budgetMapper.selectList(null);
        String status[] = new String[bills.size()];
        for(int i=0;i<bills.size();i++){
            status[i]="";
        }
        for (Bill bill:bills){
            Double hope=0.00,now=0.00;
            for(Budget budget:budgets){
                if(budget.getName().equals(bill.getAspect())){
                    hope=budget.getMoney();
                }
            }
            for (int i=0;i<bill.getId();i++){
                if(bills.get(i).getAspect().equals(bill.getAspect())){
                    now+=bills.get(i).getMoney();
                }
            }
            if(bill.getType().equals("支出")) {
                if ((hope - now) > 50) {
                    bill.setStatus("未超预算");
                } else if ((hope - now) <= 50 && (hope - now) >0) {
                    bill.setStatus("接近预算");
                } else if ((hope - now) <= 0 && hope > 0) {
                    bill.setStatus("超过预算");
                }else if ((hope - now) == 0 && hope == 0){
                    bill.setStatus("无预算");
                }
            }else{
                bill.setStatus("收入");
            }
            this.billMapper.updateById(bill);
        }
        return bills;
    }

    @Override
    public void chooseAcconut(Bill bill) {
        List<Account> accounts = this.accountMapper.selectList(null);
        for(Account account : accounts){
            if(account.getAccount().equals(bill.getAccount())){
                if(bill.getType().equals("收入")){
                    account.setMoney(account.getMoney() + bill.getMoney());
                }else {
                    account.setMoney(account.getMoney() - bill.getMoney());
                }
                this.accountMapper.updateById(account);
            }
        }
    }

    @Override
    public void updateAccount(Bill bill) {
        List<Account> accounts = this.accountMapper.selectList(null);
        List<Bill> bills = this.billMapper.selectList(null);
        Bill previous = new Bill();
        String now = bill.getAccount();
        for(Bill b:bills){
            if(b.getId()==bill.getId()){
                previous=b;
            }
        }
        System.out.println(previous+"************************************* "+now);
        for (Account account:accounts){
            if(account.getAccount().equals(previous.getAccount())){
                if(previous.getType().equals("收入")){
                    account.setMoney(account.getMoney()-previous.getMoney());
                }else{
                    account.setMoney(account.getMoney()+bill.getMoney());
                }
                this.accountMapper.updateById(account);
            }
            if(account.getAccount().equals(now)){
                if(bill.getType().equals("收入")){
                    account.setMoney(account.getMoney()+bill.getMoney());
                }else{
                    account.setMoney(account.getMoney()-bill.getMoney());
                }
                this.accountMapper.updateById(account);
            }
        }
    }
    @Override
    public void deleteAccount(Integer id) {
        List<Account> accounts = this.accountMapper.selectList(null);
        Bill bill = this.billMapper.selectById(id);
        for (Account account:accounts){
             if(account.getAccount().equals(bill.getAccount())){
                if(bill.getType().equals("收入")){
                    account.setMoney(account.getMoney()-bill.getMoney());
                }else{
                    account.setMoney(account.getMoney()+bill.getMoney());
                }
            }
             this.accountMapper.updateById(account);
        }
    }

}
