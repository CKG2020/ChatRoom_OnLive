package com.easyarch.mapper;

import com.easyarch.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
//admin 表格
@Repository
@Mapper
public interface AdminMapper {

    @Select("      select * from SSHpro.admins where username = #{username}\n")
    Admin selectAdmin (@Param("username") String username);



}
