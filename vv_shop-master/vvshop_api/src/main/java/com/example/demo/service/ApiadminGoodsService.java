package com.example.demo.service;

import com.example.demo.dao.ApiadminGoodsMapper;
import com.example.demo.entity.GoodsVo;
import com.example.demo.entity.adminGoods;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiadminGoodsService {
     @Autowired
    ApiadminGoodsMapper apiadminGoodsMapper;
    public List<GoodsVo>  GetAllGoods(){
        List<GoodsVo> goodsVos = apiadminGoodsMapper.getallGoods();

        return goodsVos;
    }
    public Integer InsertGoods( adminGoods adminGoods){
        Integer integer = apiadminGoodsMapper.InsertGoods(adminGoods);
        return integer;
    }

   public Integer InsertImage(Integer goods_id,String b_img){

       Integer integer = apiadminGoodsMapper.InsertImage(goods_id, b_img);
       return integer;
   }


   public Integer DeleteGoods(Integer goods_id){
       Integer integer1 = apiadminGoodsMapper.DeleteImage(goods_id);

       Integer integer = apiadminGoodsMapper.DeleteGoods(goods_id);
//       Integer integer1 = apiadminGoodsMapper.DeleteImage(goods_id);
       System.out.println(integer);
       System.out.println(integer1);
       return 1;

   }



}
