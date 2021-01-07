package com.easyarch.mapper;

import com.easyarch.entity.UserShow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

//userInfo表格的查询
@Repository
@Mapper
public interface UserShowMapper {
    //联合查询
//
//      <select id="findbysno" resultType="Com.easyArch.entity.UserShow">
//    select SSHpro.UserInfo.Sno,Sage,Scall,Sname,Sclass,Collage,Major,SSHpro.UserBoard.Scores
//    from SSHpro.UserInfo,SSHpro.UserBoard
//    where( UserInfo.Sno=UserBoard.Sno ) and UserInfo.Sno like  concat(concat("%",#{Sno}),"%");
//
//    </select>
    @Select("   select SSHpro.UserInfo.Sno,Sage,Scall,Sname,Sclass,Collage,Major,SSHpro.UserBoard.Scores\n" +
            "        from SSHpro.UserInfo,SSHpro.UserBoard\n" +
            "        where( UserInfo.Sno=UserBoard.Sno ) and UserInfo.Sno like  concat(concat(\"%\",#{sno}),\"%\");\n")
   List<UserShow> findBySno(@Param("sno") String sno);




    //联合查询
    @Select("select SSHpro.UserInfo.Sno,Sage,Scall,Sname,Sclass,Collage,Major,SSHpro.UserBoard.Scores\n" +
            "        from SSHpro.UserInfo,SSHpro.UserBoard\n" +
            "        where( UserInfo.Sno=UserBoard.Sno ) and UserInfo.Sage = #{sage}")
    List<UserShow> findByAge(@Param("sage") int sage);

    @Select("select SSHpro.UserInfo.Sno,Sage,Scall,Sname,Sclass,Collage,Major,SSHpro.UserBoard.Scores\n" +
            "        from SSHpro.UserInfo,SSHpro.UserBoard\n" +
            "        where( UserInfo.Sno=UserBoard.Sno ) and UserInfo.Sname like  concat(concat(\"%\",#{sname}),\"%\")")
   List<UserShow> findByName(@Param("sname") String sname);


    @Select("  select SSHpro.UserInfo.Sno,Sage,Scall,Sname,Sclass,Collage,Major,SSHpro.UserBoard.Scores\n" +
            "        from SSHpro.UserInfo,SSHpro.UserBoard\n" +
            "        where( UserInfo.Sno=UserBoard.Sno ) and UserInfo.Collage like concat(concat(\"%\",#{collage}),\"%\")")
    List<UserShow>  findByCollage(@Param("collage") String collage);

    @Select("    select SSHpro.UserInfo.Sno,Sage,Scall,Sname,Sclass,Collage,Major,SSHpro.UserBoard.Scores\n" +
            "        from SSHpro.UserInfo,SSHpro.UserBoard\n" +
            "        where( UserInfo.Sno=UserBoard.Sno ) and UserInfo.Sclass like concat(concat(\"%\",#{sclass}),\"%\")")
    List<UserShow> findByClass(@Param("sclass") String sclass);

//===============================＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝















}
