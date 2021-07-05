package com.example.demo.dao;

import com.example.demo.entity.GoodsVo;
import com.example.demo.entity.adminGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiadminGoodsMapper {
    List<GoodsVo> getallGoods();

    Integer InsertGoods( adminGoods adminGoods);
    Integer InsertImage(Integer goods_id,String b_img);

    Integer DeleteGoods(Integer goods_id);

    Integer DeleteImage(Integer goods_id);




}
