package com.wjzn.service;

import com.wjzn.entity.Bill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wjzn.vo.BarVO;
import com.wjzn.vo.PieVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjzn
 * @since 2021-08-16
 */
public interface BillService extends IService<Bill> {
    public BarVO barVOList();
    public List<PieVO> pieVOList();
    public List<PieVO> pieVOList2();
    public Integer finID();
    public Double[] now();
    public List<Bill> MyStatus();
    public void chooseAcconut(Bill bill);
    public void updateAccount(Bill bill);
    public void deleteAccount(Integer id);
}
