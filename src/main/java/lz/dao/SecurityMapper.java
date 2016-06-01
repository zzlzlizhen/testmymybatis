package lz.dao;

import java.util.List;
import lz.model.Security;
import lz.model.SecurityExample;
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

public interface SecurityMapper {
    @SelectProvider(type=SecuritySqlProvider.class, method="countByExample")
    int countByExample(SecurityExample example);

    @DeleteProvider(type=SecuritySqlProvider.class, method="deleteByExample")
    int deleteByExample(SecurityExample example);

    @Delete({
        "delete from t_security",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_security (id, phone, security_code, ",
        "type, create_time, email)",
        "values (#{id,jdbcType=CHAR}, #{phone,jdbcType=CHAR}, #{securityCode,jdbcType=CHAR}, ",
        "#{type,jdbcType=CHAR}, #{createTime,jdbcType=CHAR}, #{email,jdbcType=CHAR})"
    })
    int insert(Security record);

    @InsertProvider(type=SecuritySqlProvider.class, method="insertSelective")
    int insertSelective(Security record);

    @SelectProvider(type=SecuritySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="phone", property="phone", jdbcType=JdbcType.CHAR),
        @Result(column="security_code", property="securityCode", jdbcType=JdbcType.CHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.CHAR)
    })
    List<Security> selectByExample(SecurityExample example);

    @Select({
        "select",
        "id, phone, security_code, type, create_time, email",
        "from t_security",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="phone", property="phone", jdbcType=JdbcType.CHAR),
        @Result(column="security_code", property="securityCode", jdbcType=JdbcType.CHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.CHAR)
    })
    Security selectByPrimaryKey(String id);

    @UpdateProvider(type=SecuritySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Security record, @Param("example") SecurityExample example);

    @UpdateProvider(type=SecuritySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Security record, @Param("example") SecurityExample example);

    @UpdateProvider(type=SecuritySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Security record);

    @Update({
        "update t_security",
        "set phone = #{phone,jdbcType=CHAR},",
          "security_code = #{securityCode,jdbcType=CHAR},",
          "type = #{type,jdbcType=CHAR},",
          "create_time = #{createTime,jdbcType=CHAR},",
          "email = #{email,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(Security record);
}