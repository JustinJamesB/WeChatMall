package com.example.demo.service;

import com.example.demo.dao.ApiadminUserMapper;
import com.example.demo.entity.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiadminUserService {
    @Autowired
    ApiadminUserMapper apiadminUserMapper;
    public Integer getadminUser(String username, String password){
        Integer resUser = apiadminUserMapper.getadminUser(username,password);
//        System.out.println(resUser);
    return resUser;

    }

    public List<UserVo> getallUser(){
        List<UserVo> userVos = apiadminUserMapper.getallUser();
        return userVos;
    }

    public int deleteOneUser(int user_id){
        Integer integer = apiadminUserMapper.deleteOneUser(user_id);
        return integer;
    }

}
