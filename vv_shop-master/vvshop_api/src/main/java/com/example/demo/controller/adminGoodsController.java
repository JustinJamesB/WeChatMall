package com.example.demo.controller;

import com.example.demo.annotation.Authorize;
import com.example.demo.entity.CommonResult;
import com.example.demo.entity.adminGoods;
import com.example.demo.service.ApiadminGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Authorize
@Slf4j
public class adminGoodsController {

    /**
     * 分页
     */
    @Autowired
    ApiadminGoodsService apiadminGoodsService;
    @ResponseBody
    @RequestMapping("/adminGoods/getOnepage")
    public CommonResult getOnepage(@RequestParam(value ="pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(defaultValue = "10") int pageSize
    ){
//        System.out.println(pageNum);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(apiadminGoodsService.GetAllGoods());

        return new CommonResult(200,"获取该页商品，获取成功",pageInfo);
    }



    @ResponseBody
    @RequestMapping("/adminGoods/addGoods")
    public CommonResult addGoods(@RequestBody adminGoods adminGoods){

        Integer integer = apiadminGoodsService.InsertGoods(adminGoods);
        if(integer>0){
            Integer goods_id = adminGoods.getGoods_id();
            List<String> b_img = adminGoods.getB_img();
            for (String s : b_img) {
                apiadminGoodsService.InsertImage(goods_id,s);
            }
            return new CommonResult(200,"商品",adminGoods);

        }
        return new CommonResult(400,"商品",adminGoods);

    }


    @ResponseBody
    @RequestMapping("/adminGoods/DeleteGoods")
    public CommonResult DeleteGoods(@RequestBody Map<String,Integer> param){
        Integer goods_id = param.get("goods_id");
        Integer integer = apiadminGoodsService.DeleteGoods(goods_id);
        if(integer>0){
            return new CommonResult(200,"删除成功");

        }
        return new CommonResult(400,"删除失败");

    }
}
