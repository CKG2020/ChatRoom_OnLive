package com.easyarch.mapper;

import com.easyarch.entity.BoardMsg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//Board_Msg  表格
@Repository
@Mapper
public interface BoardMsgMapper {

    @Select("   select Board_Msg.board_text,Board_Msg.from_name,Board_Msg.date_time from SSHpro.Board_Msg\n" +
            "        where SSHpro.Board_Msg.Sno=#{sno} order by date_time DESC")
    List<BoardMsg> showBoardMsg(@Param("sno") String sno);

    @Insert("insert into SSHpro.Board_Msg(sno, from_name, board_text, date_time)\n" +
            "        values (#{sno},#{from_name},#{board_text},#{date_time")
    public int insertBoardMsg(@Param("sno") String sno,@Param("form_name") String form_name,@Param("board_text") String board_text,@Param("date_time") String date_time);


    @Select("select count(*) from SSHpro.Board_Msg where sno=#{sno}")
    int boardMsgCount(@Param("sno") String sno);


}
