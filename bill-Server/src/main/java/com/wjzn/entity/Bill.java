package com.wjzn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @TableName("Bill")
public class Bill implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String type;

    private Double money;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;

    private String aspect;

    private String status;

    private String account;


}
