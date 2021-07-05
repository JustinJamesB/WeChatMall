package com.example.demo.dao;

import com.example.demo.entity.UserVo;
import com.example.demo.entity.adminUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApiadminUserMapper {
    //管理员登陆
    Integer getadminUser(String username,String password);
//    获取所有普通用户

    List<UserVo> getallUser();

    Integer deleteOneUser(Integer user_id);


}
