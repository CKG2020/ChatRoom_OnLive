package com.easyarch.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
//pyq表格
@Repository
@Mapper
public interface PyqMapper {
    @Select("select COUNT(*) from SSHpro.pyq where Sno1=#{sno} and addStatus=1")
    int countFriends (@Param("sno") String sno);
    @Select("        select COUNT(*) from SSHpro.pyq where Sno2=#{sno} and addStatus=0\n")
    int countRequest(@Param("sno") String sno);
    @Insert(" insert into SSHpro.pyq(Sno1,Sno2,addStatus) values (#{sno1},#{sno2},0)")

    int addFriendRequest(@Param("sno1") String sno1,@Param("sno2") String sno2);

    @Update("   update SSHpro.pyq set addStatus=1\n" +
            "        where sno1=#{sno1} and sno2=#{sno2}")
    int acceptAdd(@Param("sno1") String sno1,@Param("sno2") String sno2);
    @Insert("insert into SSHpro.pyq(Sno1,Sno2,addStatus) values (#{sno2},#{sno1},1)")
    int addFriend(@Param("sno1") String sno1,@Param("sno2") String sno2);
    @Delete("        delete from SSHpro.pyq where sno1=#{sno1} and sno2=#{sno2}")
    int refuseAdd(@Param("sno1") String sno1,@Param("sno2") String sno2);

    @Delete("        delete from SSHpro.pyq where Sno1=#{sno1} and Sno2=#{sno2}")
    int delFriend1(@Param("sno1") String sno1,@Param("sno2") String sno2);

    @Delete("        delete from SSHpro.pyq where Sno2=#{sno1} and Sno1=#{sno2}")
    int delFriend2(@Param("sno2") String sno1,@Param("sno1") String sno2);

    @Delete(" delete from SSHpro.pyq where Sno1=#{sno} or Sno2=#{sno}")
    int delpyq(@Param("sno") String sno);





}





