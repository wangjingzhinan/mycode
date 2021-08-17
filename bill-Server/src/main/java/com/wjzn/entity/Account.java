package com.wjzn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wjzn
 * @since 2021-08-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("Account")
public class Account implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String account;

    private Double money;


}
