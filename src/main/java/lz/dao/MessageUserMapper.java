package lz.dao;

import java.util.List;
import lz.model.MessageUser;
import lz.model.MessageUserExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface MessageUserMapper {
    @SelectProvider(type=MessageUserSqlProvider.class, method="countByExample")
    int countByExample(MessageUserExample example);

    @DeleteProvider(type=MessageUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(MessageUserExample example);

    @Delete({
        "delete from t_message_user",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_message_user (id, user_id, message_id, ",
        "message_type)",
        "values (#{id,jdbcType=CHAR}, #{userId,jdbcType=CHAR}, #{messageId,jdbcType=CHAR}, ",
        "#{messageType,jdbcType=CHAR})"
    })
    int insert(MessageUser record);

    @InsertProvider(type=MessageUserSqlProvider.class, method="insertSelective")
    int insertSelective(MessageUser record);

    @SelectProvider(type=MessageUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.CHAR),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.CHAR),
        @Result(column="message_type", property="messageType", jdbcType=JdbcType.CHAR)
    })
    List<MessageUser> selectByExample(MessageUserExample example);

    @Select({
        "select",
        "id, user_id, message_id, message_type",
        "from t_message_user",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.CHAR),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.CHAR),
        @Result(column="message_type", property="messageType", jdbcType=JdbcType.CHAR)
    })
    MessageUser selectByPrimaryKey(String id);

    @UpdateProvider(type=MessageUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MessageUser record, @Param("example") MessageUserExample example);

    @UpdateProvider(type=MessageUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MessageUser record, @Param("example") MessageUserExample example);

    @UpdateProvider(type=MessageUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MessageUser record);

    @Update({
        "update t_message_user",
        "set user_id = #{userId,jdbcType=CHAR},",
          "message_id = #{messageId,jdbcType=CHAR},",
          "message_type = #{messageType,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(MessageUser record);
}