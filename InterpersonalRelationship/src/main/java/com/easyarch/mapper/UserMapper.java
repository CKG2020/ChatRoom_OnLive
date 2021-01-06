package com.easyarch.mapper;

import com.easyarch.entity.MyUser;
import com.easyarch.entity.UserShow;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//userInfo的查询
@Repository
@Mapper
public interface UserMapper {
    //登录查询
    @Select("select * from SSHpro.UserInfo where Sno= #{Sno}")
    MyUser loginUser(String Sno);
    //注册验证学号是否重复
    @Select("  select Sno, Sname, Sage, Scall, Collage, Major, Sclass, Gender, Birth from SSHpro.UserInfo\n" +
            "        where Sno = #{sno}")
    MyUser findUserBySno(@Param("sno") String sno);

    //添加Myuser
    @Insert(" insert into SSHpro.UserInfo(Sno,Spwd) values (#{sno},#{spwd})")
    int addUser(@Param("sno") String sno,@Param("spwd") String spwd);





    @Select("select * from SSHpro.UserInfo where Sno= #{Sno}")
    public MyUser selectUser(@Param("sno")String  sno);

    @Select("select SSHpro.UserInfo.Sno,Sage,Scall,Sname,Sclass,Collage,Major,SSHpro.UserBoard.Scores from SSHpro.UserInfo,SSHpro.UserBoard where UserInfo.Sno=UserBoard.Sno")
    public UserShow findUserShow();

    @Select("  select\n" +
            "        UserInfo.Sno, Sname, Sage, Scall, Collage, Major, Sclass, Gender, Birth\n" +
            "        from SSHpro.UserInfo where UserInfo.Sno in (select SSHpro.pyq.Sno2 from SSHpro.pyq where Sno1=#{sno} and addStatus=1)")
    List<MyUser>  myFriends(@Param("sno") String sno);

 @Select(" select\n" +
         "        UserInfo.Sno, Sname, Sage, Scall, Collage, Major, Sclass, Gender, Birth\n" +
         "        from SSHpro.UserInfo where UserInfo.Sno in (select SSHpro.pyq.Sno1 from SSHpro.pyq where Sno2=#{sno} and addStatus=0)")
    MyUser frinedsRequest(@Param("sno") String sno);

    @Select("  select COUNT(*) from SSHpro.UserInfo")
    int findallcount();



      //汇总查询
    //年龄
   @Select(" select COUNT(*) from SSHpro.UserInfo where Sage = #{age}")
      int findAgeCount(@Param("age") int age);
      //学院
   @Select(" select COUNT(*) from SSHpro.UserInfo where Collage like concat(concat(\"%\",#{collage}),\"%\")")
    int findCollageCount (@Param("collage") String collage);

  //班级
   @Select("  select COUNT(*) from SSHpro.UserInfo where Sclass like concat(concat(\"%\",#{sclass}),\"%\")")
   int  findClassCount(@Param("sclass")  String sclass);
   //名字
   @Select(" select COUNT(*) from SSHpro.UserInfo where Sname like  concat(concat(\"%\",#{sname}),\"%\")")
   int findNameCount(@Param("sname") String sname);
   //学号
   @Select("  select COUNT(*) from SSHpro.UserInfo where Sno like  concat(concat(\"%\",#{sno}),\"%\")")
   int findSnoCount(@Param("sno") String sno);



    //更新myuser 信息
    @Update("update SSHpro.UserInfo set\n" +
            "        Sname=#{sname},Sage=#{sage},\n" +
            "        Scall=#{scall},Collage=#{collage},\n" +
            "        Major=#{major},Sclass=#{sclass},\n" +
            "        Gender=#{gender},Birth=#{birth}\n" +
            "        where Sno=#{sno};")
    int updateUser(@Param("sname") String sname,@Param("sage") int sage,@Param("scall") String scall,
                   @Param("collage") String collage,
                   @Param("major")String major,@Param("sclass") String sclass,@Param("gender") int gender,
                   @Param("birth")String birth,@Param("sno") String sno);



    @Delete(" delete from SSHpro.UserInfo where Sno=#{sno};")
    int delUser(@Param("sno") String sno);

    //查找名字
    @Select(" select SSHpro.UserInfo.Sname from SSHpro.UserInfo where Sno=#{sno}")
    String searchSname(@Param("sno")String sno);
    //与上面一样
    @Select(" select SSHpro.UserInfo.Sname from SSHpro.UserInfo where Sno=#{sno}")
    String findUserNameBySno(@Param("sno")String sno);


//    int updateUser(String name, int age, String call, String collage, String major, String sclass, int gender, String birth, String sno);
}
