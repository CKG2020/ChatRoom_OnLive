package com.easyarch.mapper;

import com.easyarch.entity.UserBoard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//UserBoard　表格
@Repository
@Mapper
public interface UserBoardMapper {

    @Select("select msgCount from SSHpro.UserBoard where Sno=#{Sno}")
    public int countBoardMsg(@Param("Sno") String Sno);


//    @Insert("insert into SSHpro.UserBoard(Sno, Birth, Board) values (#{sno},#{birth},#{board})")
//    public int insertUserBoard(@Param("sno") String sno,@Param("birth")String birth,@Param("board")String board);
//    @Update(" update SSHpro.UserBoard set Scores=#{scores} where Sno=#{sno}")
//    public int updateUserBoard(@Param("scores")int scores,@Param("sno")String sno);

    @Insert(" insert into SSHpro.UserBoard(Sno, Birth, Board) values (#{sno},#{birth},#{board})")
    public int Init(@Param("sno") String sno, @Param("birth") String birth, @Param("board") String board);

    @Update(" update SSHpro.UserBoard set Scores=#{scores} where Sno=#{sno}")
    public int submitScores(@Param("scores") int scores, @Param("sno") String sno);

    @Select(" select SSHpro.UserBoard.Scores FROM SSHpro.UserBoard WHERE Sno=#{sno}")
    public int searchScore(@Param("sno") String sno);

    @Update("update SSHpro.UserInfo set Birth=#{birth} where Sno=#{sno}\n ")
    public int updateUserBoard(@Param("birth") String birth, @Param("sno") String sno);

    @Insert("insert into SSHpro.UserBoard(Sno)values(#{Sno})")
    public int addUserBoard(@Param("sno") String sno);

    @Update(" update SSHpro.UserBoard SET IsFinishedQuestion=#{isFinishedQuestion},\n" +
            "    Scores=#{scores}\n" +
            "        where Sno = #{sno}")
    public int updateFinishedQuestion(@Param("isFinishedQuestion") boolean isFinishedQuestion, @Param("scores") int scores, @Param("sno") String sno);


    @Select("        select SSHpro.UserBoard.IsFinishedQuestion from SSHpro.UserBoard where Sno=#{sno}\n")
    public boolean isFinished(@Param("sno") String sno);


//    <select id="isAllFinished">
//    select SSHpro.UserBoard.IsFinishedQuestion,Sno from SSHpro.UserBoard
//            </select>

    @Select("        select COUNT(*) from SSHpro.UserBoard where IsFinishedQuestion=1\n")
    int allFinished();

    @Select("        select SSHpro.UserBoard.Scores from SSHpro.UserBoard where IsFinishedQuestion=1\n")
    int findScore();

    @Select("        select * from SSHpro.UserBoard where Sno=#{sno}\n")
    UserBoard findUserBoard(@Param("sno") String sno);


    @Select("        select msgCount from SSHpro.UserBoard where Sno=#{sno}\n")
    int historyMsgCount(@Param("sno") String sno);

    @Select("        select requestCount from SSHpro.UserBoard where Sno=#{sno}\n")
    int historyRequestCount(@Param("sno") String sno);

    @Update("   update SSHpro.UserBoard set msgCount=#{count} where sno=#{sno}\n")
    int setHistoryMsgCount(@Param("sno") String sno, @Param("count") int count);

    //更新出生日
    @Update(" update SSHpro.UserBoard set Birth=#{birth} where Sno=#{sno}")
    int updateBirth(@Param("birth") String birth, @Param("sno") String sno);

    @Delete(" delete from SSHpro.UserBoard where Sno = #{sno}")
    int delb(@Param("sno") String sno);


    @Update("update SSHpro.UserBoard set requestCount=#{count} where sno=#{sno}")
    int setHistoryRequestCount(int count, String sno);


}

//
//    @Select("SELECT * FROM  member   }")
//    List<Member> findAllMember();
//
//    @Select("SELECT * FROM  member   WHERE   id=#{id,jdbcType=INTEGER}")
//    Member findById(@Param("id") int id);
//
//
//    @Delete("DELETE FROM member WHERE phone=#{phone,jdbcType=VARCHAR}")
//    int  deleteMember(@Param("phone") String phone);
//
//
//    @Update("UPDATE  member  set  adress=#{adress,jdbcType=VARCHAR} where phone=#{phone,jdbcType=VARCHAR}")
//    int updateAdress(@Param("adress") String adress, @Param("phone") String phone);