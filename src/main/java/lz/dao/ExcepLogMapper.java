package lz.dao;

import java.util.List;
import java.util.Map;

import lz.model.ExcepLog;
import lz.model.ExcepLogExample;
import lz.model.OperLog;

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

public interface ExcepLogMapper {
    @SelectProvider(type=ExcepLogSqlProvider.class, method="countByExample")
    int countByExample(ExcepLogExample example);

    @DeleteProvider(type=ExcepLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(ExcepLogExample example);

    @Delete({
        "delete from t_excep_log",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_excep_log (id, name, exception_desc, ",
        "exception_business, exception_info, ",
        "create_time, exception_target)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=CHAR}, #{exceptionDesc,jdbcType=CHAR}, ",
        "#{exceptionBusiness,jdbcType=CHAR}, #{exceptionInfo,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=CHAR}, #{exceptionTarget,jdbcType=CHAR})"
    })
    int insert(ExcepLog record);

    @InsertProvider(type=ExcepLogSqlProvider.class, method="insertSelective")
    int insertSelective(ExcepLog record);

    @SelectProvider(type=ExcepLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="exception_desc", property="exceptionDesc", jdbcType=JdbcType.CHAR),
        @Result(column="exception_business", property="exceptionBusiness", jdbcType=JdbcType.CHAR),
        @Result(column="exception_info", property="exceptionInfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="exception_target", property="exceptionTarget", jdbcType=JdbcType.CHAR)
    })
    List<ExcepLog> selectByExample(ExcepLogExample example);

    @Select({
        "select",
        "id, name, exception_desc, exception_business, exception_info, create_time, exception_target",
        "from t_excep_log",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="exception_desc", property="exceptionDesc", jdbcType=JdbcType.CHAR),
        @Result(column="exception_business", property="exceptionBusiness", jdbcType=JdbcType.CHAR),
        @Result(column="exception_info", property="exceptionInfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="exception_target", property="exceptionTarget", jdbcType=JdbcType.CHAR)
    })
    ExcepLog selectByPrimaryKey(String id);

    @UpdateProvider(type=ExcepLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ExcepLog record, @Param("example") ExcepLogExample example);

    @UpdateProvider(type=ExcepLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ExcepLog record, @Param("example") ExcepLogExample example);

    @UpdateProvider(type=ExcepLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ExcepLog record);

    @Update({
        "update t_excep_log",
        "set name = #{name,jdbcType=CHAR},",
          "exception_desc = #{exceptionDesc,jdbcType=CHAR},",
          "exception_business = #{exceptionBusiness,jdbcType=CHAR},",
          "exception_info = #{exceptionInfo,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=CHAR},",
          "exception_target = #{exceptionTarget,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(ExcepLog record);
    /*
     * 分页查询
     */
    @SelectProvider(type=ExcepLogSqlProvider.class, method="selectExcepLogByPage")
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="exception_desc", property="exceptionDesc", jdbcType=JdbcType.CHAR),
        @Result(column="exception_business", property="exceptionBusiness", jdbcType=JdbcType.CHAR),
        @Result(column="exception_info", property="exceptionInfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="exception_target", property="exceptionTarget", jdbcType=JdbcType.CHAR)
    })
    List<ExcepLog> selectExcepLogByPage(Map<String,Object> map);
    /**
     * 批量删除
     * @return
     */
    @DeleteProvider(type=ExcepLogSqlProvider.class, method="batchDelExcepLog")
    int batchDelExcepLog(@Param("batchDelIds") List<String> delId);
}