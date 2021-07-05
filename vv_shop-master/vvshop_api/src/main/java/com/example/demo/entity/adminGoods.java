package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class adminGoods {
    //图片链接
    private String goods_img_url;
    //主键
    private Integer goods_id;
    //商品类型Id
    private Integer category_id;
    //名称
    private String goods_name;
    //排序
    private Integer goods_sort;
    //删除状态
    private Integer is_delete;
    //专柜价格
    private Integer counter_price;
    //是否新商品
    private Integer is_new;
    //热销
    private Integer is_hot;
    //商品规格
    private  Integer spec_type;
    //库存计算方式
    private  Integer deduct_stock_type;
    //商品详情
    private  String content;
    //实际销量
    private  Integer sales_actual;
    //商品状态
    private Integer goods_status;
    //商品库存
    private  Integer stock_num;
    //商品banner
//    private String b_img;
    //商品详情图
    private  String c_img;
    //购买商品数量
    private  Integer count;

    private List<String> b_img;
}
