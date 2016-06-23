package lz.dao;

import java.util.List;
import java.util.Map;

import lz.model.OperLog;
import lz.model.OperLogExample;
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

public interface OperLogMapper {
	@SelectProvider(type=OperLogSqlProvider.class, method="countByExample")
    int countByExample(OperLogExample example);

    @DeleteProvider(type=OperLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(OperLogExample example);

    @Delete({
        "delete from t_oper_log",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_oper_log (id, name, log_business, ",
        "log_desc, create_time, oper_ip)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=CHAR}, #{logBusiness,jdbcType=CHAR}, ",
        "#{logDesc,jdbcType=CHAR}, #{createTime,jdbcType=CHAR}, #{operIp,jdbcType=CHAR})"
    })
    int insert(OperLog record);

    @InsertProvider(type=OperLogSqlProvider.class, method="insertSelective")
    int insertSelective(OperLog record);

    @SelectProvider(type=OperLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="log_business", property="logBusiness", jdbcType=JdbcType.CHAR),
        @Result(column="log_desc", property="logDesc", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="oper_ip", property="operIp", jdbcType=JdbcType.CHAR)
    })
    List<OperLog> selectByExample(OperLogExample example);

    @Select({
        "select",
        "id, name, log_business, log_desc, create_time, oper_ip",
        "from t_oper_log",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="log_business", property="logBusiness", jdbcType=JdbcType.CHAR),
        @Result(column="log_desc", property="logDesc", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="oper_ip", property="operIp", jdbcType=JdbcType.CHAR)
    })
    OperLog selectByPrimaryKey(String id);

    @UpdateProvider(type=OperLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OperLog record, @Param("example") OperLogExample example);

    @UpdateProvider(type=OperLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OperLog record, @Param("example") OperLogExample example);

    @UpdateProvider(type=OperLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OperLog record);

    @Update({
        "update t_oper_log",
        "set name = #{name,jdbcType=CHAR},",
          "log_business = #{logBusiness,jdbcType=CHAR},",
          "log_desc = #{logDesc,jdbcType=CHAR},",
          "create_time = #{createTime,jdbcType=CHAR},",
          "oper_ip = #{operIp,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(OperLog record);
    /*
     * 分页查询
     */
    @SelectProvider(type=OperLogSqlProvider.class, method="selectOperLogByPage")
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="log_business", property="logBusiness", jdbcType=JdbcType.CHAR),
        @Result(column="log_desc", property="logDesc", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="oper_ip", property="operIp", jdbcType=JdbcType.CHAR)
    })
    List<OperLog> selectOperLogByPage(Map<String,Object> map);
    /**
     * 批量删除
     * @return
     */
    @DeleteProvider(type=OperLogSqlProvider.class, method="batchDelOperLog")
    int batchDelOperLog(@Param("batchDelIds") List<String> delId);
}