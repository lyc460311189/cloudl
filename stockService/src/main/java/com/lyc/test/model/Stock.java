package com.lyc.test.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liyuchun
 * @since 2022-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("stock")
public class Stock extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("goods_id")
    private Long goods_id;

    @TableField("num")
    private Integer num;

    @TableField("price")
    private BigDecimal price;

    @TableField("total_money")
    private BigDecimal total_money;

    @TableField("order_no")
    private String order_no;

    @TableField("create_time")
    private Date create_time;

    /**
     * 1、入库2、出库
     */
    @TableField("stock_type")
    private Integer stock_type;


}
