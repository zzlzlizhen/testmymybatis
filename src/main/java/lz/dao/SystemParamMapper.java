package lz.dao;

import java.util.List;
import java.util.Map;

import lz.model.SystemParam;
import lz.model.SystemParamExample;

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

public interface SystemParamMapper {
	@SelectProvider(type=SystemParamSqlProvider.class, method="countByExample")
    int countByExample(SystemParamExample example);

    @DeleteProvider(type=SystemParamSqlProvider.class, method="deleteByExample")
    int deleteByExample(SystemParamExample example);

    @Delete({
        "delete from t_param",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_param (id, param_key, ",
        "param_value, create_time)",
        "values (#{id,jdbcType=CHAR}, #{paramKey,jdbcType=CHAR}, ",
        "#{paramValue,jdbcType=CHAR}, #{createTime,jdbcType=CHAR})"
    })
    int insert(SystemParam record);

    @InsertProvider(type=SystemParamSqlProvider.class, method="insertSelective")
    int insertSelective(SystemParam record);

    @SelectProvider(type=SystemParamSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="param_key", property="paramKey", jdbcType=JdbcType.CHAR),
        @Result(column="param_value", property="paramValue", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR)
    })
    List<SystemParam> selectByExample(SystemParamExample example);

    @Select({
        "select",
        "id, param_key, param_value, create_time",
        "from t_param",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="param_key", property="paramKey", jdbcType=JdbcType.CHAR),
        @Result(column="param_value", property="paramValue", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR)
    })
    SystemParam selectByPrimaryKey(String id);

    @UpdateProvider(type=SystemParamSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SystemParam record, @Param("example") SystemParamExample example);

    @UpdateProvider(type=SystemParamSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SystemParam record, @Param("example") SystemParamExample example);

    @UpdateProvider(type=SystemParamSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SystemParam record);

    @Update({
        "update t_param",
        "set param_key = #{paramKey,jdbcType=CHAR},",
          "param_value = #{paramValue,jdbcType=CHAR},",
          "create_time = #{createTime,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemParam record);
    
    
    /*
     * 分页查询
     */
    @SelectProvider(type=SystemParamSqlProvider.class, method="selectParamByPage")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="param_key", property="paramKey", jdbcType=JdbcType.CHAR),
        @Result(column="param_value", property="paramValue", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR)
    })
    List<SystemParam> selectParamByPage(Map<String,Object> map);
    /**
     * 批量删除
     * @param id
     * @return
     */
    @DeleteProvider(type=SystemParamSqlProvider.class, method="batchDelSystemParam")
    int batchDelSystemParam(@Param("batchDelIds") List<String> delId);
}