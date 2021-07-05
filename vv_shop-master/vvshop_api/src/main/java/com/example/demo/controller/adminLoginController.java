package com.example.demo.controller;

import com.example.demo.annotation.Authorize;
import com.example.demo.entity.CommonResult;
import com.example.demo.entity.UserVo;
import com.example.demo.entity.adminUser;
import com.example.demo.service.ApiadminUserService;
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
public class adminLoginController {
    @Autowired
    ApiadminUserService apiadminUserService;

    @ResponseBody  // 把返回的结果给浏览器
    @PostMapping("/user/adminUserlogin")
    public CommonResult adminUserlogin(@RequestBody adminUser user){
        String username = user.getUsername().trim();
        String password=user.getPassword().trim();
//        System.out.println(username+password);
        Integer integer = apiadminUserService.getadminUser(username, password);
//        System.out.println("返回"+integer);
        if(integer>0){
            return new CommonResult(200,"登陆成功");
        }
        else
            return new CommonResult(401,"登陆失败");

    }


    @ResponseBody  // 把返回的结果给浏览器
    @PostMapping("/user/getallUser")
    public CommonResult getallUser(){
        List<UserVo> userVos = apiadminUserService.getallUser();
        return new CommonResult(200,"获取所有用户，获取成功",userVos);
    }

    /**
     * 分页
     */
    @RequestMapping("user/getOnepage")
    public CommonResult getOnepage(@RequestParam(value ="pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize
                               ){
//        System.out.println(pageNum);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(apiadminUserService.getallUser());

        return new CommonResult(200,"获取所有用户，获取成功",pageInfo);
    }

    @PostMapping("user/deleteOneUser")
    public CommonResult deleteOneUser(@RequestBody Map<String,Integer> mp){
        Integer user_id = mp.get("user_id");

        int i = apiadminUserService.deleteOneUser(user_id);
        if(i>0){
            return new CommonResult(200,"删除成功",user_id);
        }else {
            return new CommonResult(400,"删除失败",user_id);

        }

    }



}
