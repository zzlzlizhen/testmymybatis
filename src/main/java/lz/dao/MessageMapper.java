package lz.dao;

import java.util.List;
import java.util.Map;

import lz.model.Message;
import lz.model.MessageExample;
import lz.model.SystemParam;

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

public interface MessageMapper {
	@SelectProvider(type=MessageSqlProvider.class, method="countByExample")
    int countByExample(MessageExample example);

    @DeleteProvider(type=MessageSqlProvider.class, method="deleteByExample")
    int deleteByExample(MessageExample example);

    @Delete({
        "delete from t_message",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_message (id, message_type, ",
        "message_head, message_content, ",
        "message_status, created_user, ",
        "create_time, receiver, ",
        "publish_time, destory_time)",
        "values (#{id,jdbcType=CHAR}, #{messageType,jdbcType=CHAR}, ",
        "#{messageHead,jdbcType=CHAR}, #{messageContent,jdbcType=VARCHAR}, ",
        "#{messageStatus,jdbcType=CHAR}, #{createdUser,jdbcType=CHAR}, ",
        "#{createTime,jdbcType=CHAR}, #{receiver,jdbcType=CHAR}, ",
        "#{publishTime,jdbcType=CHAR}, #{destoryTime,jdbcType=CHAR})"
    })
    int insert(Message record);

    @InsertProvider(type=MessageSqlProvider.class, method="insertSelective")
    int insertSelective(Message record);

    @SelectProvider(type=MessageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="message_type", property="messageType", jdbcType=JdbcType.CHAR),
        @Result(column="message_head", property="messageHead", jdbcType=JdbcType.CHAR),
        @Result(column="message_content", property="messageContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_status", property="messageStatus", jdbcType=JdbcType.CHAR),
        @Result(column="created_user", property="createdUser", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.CHAR),
        @Result(column="publish_time", property="publishTime", jdbcType=JdbcType.CHAR),
        @Result(column="destory_time", property="destoryTime", jdbcType=JdbcType.CHAR)
    })
    List<Message> selectByExample(MessageExample example);

    @Select({
        "select",
        "id, message_type, message_head, message_content, message_status, created_user, ",
        "create_time, receiver, publish_time, destory_time",
        "from t_message",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="message_type", property="messageType", jdbcType=JdbcType.CHAR),
        @Result(column="message_head", property="messageHead", jdbcType=JdbcType.CHAR),
        @Result(column="message_content", property="messageContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_status", property="messageStatus", jdbcType=JdbcType.CHAR),
        @Result(column="created_user", property="createdUser", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.CHAR),
        @Result(column="publish_time", property="publishTime", jdbcType=JdbcType.CHAR),
        @Result(column="destory_time", property="destoryTime", jdbcType=JdbcType.CHAR)
    })
    Message selectByPrimaryKey(String id);

    @UpdateProvider(type=MessageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    @UpdateProvider(type=MessageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    @UpdateProvider(type=MessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Message record);

    @Update({
        "update t_message",
        "set message_type = #{messageType,jdbcType=CHAR},",
          "message_head = #{messageHead,jdbcType=CHAR},",
          "message_content = #{messageContent,jdbcType=VARCHAR},",
          "message_status = #{messageStatus,jdbcType=CHAR},",
          "created_user = #{createdUser,jdbcType=CHAR},",
          "create_time = #{createTime,jdbcType=CHAR},",
          "receiver = #{receiver,jdbcType=CHAR},",
          "publish_time = #{publishTime,jdbcType=CHAR},",
          "destory_time = #{destoryTime,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(Message record);
    /*
     * 消息管理，分页查询
     */
    @SelectProvider(type=MessageSqlProvider.class, method="selectMessageByPage")
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="message_type", property="messageType", jdbcType=JdbcType.CHAR),
        @Result(column="message_head", property="messageHead", jdbcType=JdbcType.CHAR),
        @Result(column="message_content", property="messageContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_status", property="messageStatus", jdbcType=JdbcType.CHAR),
        @Result(column="created_user", property="createdUser", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.CHAR),
        @Result(column="publish_time", property="publishTime", jdbcType=JdbcType.CHAR),
        @Result(column="destory_time", property="destoryTime", jdbcType=JdbcType.CHAR)
    })
    List<Message> selectMessageByPage(Map<String,Object> map);
    /*
     * 个人消息，分页查询
     */
    @SelectProvider(type=MessageSqlProvider.class, method="selectPersonMessageByPage")
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="message_type", property="messageType", jdbcType=JdbcType.CHAR),
        @Result(column="message_head", property="messageHead", jdbcType=JdbcType.CHAR),
        @Result(column="message_content", property="messageContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="message_status", property="messageStatus", jdbcType=JdbcType.CHAR),
        @Result(column="publish_time", property="publishTime", jdbcType=JdbcType.CHAR),
    })
    List<Message> selectPersonMessageByPage(Map<String,Object> map);
    /**
     * 批量删除
     * @param id
     * @return
     */
    @DeleteProvider(type=MessageSqlProvider.class, method="batchDelMessage")
    int batchDelMessage(@Param("batchDelIds") List<String> delId);
}