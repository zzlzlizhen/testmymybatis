package lz.dao;

import java.util.List;
import lz.model.RoleResource;
import lz.model.RoleResourceExample;
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

public interface RoleResourceMapper {
	@SelectProvider(type=RoleResourceSqlProvider.class, method="countByExample")
    int countByExample(RoleResourceExample example);

    @DeleteProvider(type=RoleResourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(RoleResourceExample example);

    @Delete({
        "delete from t_role_resource",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_role_resource (id, resource_id, ",
        "role_id)",
        "values (#{id,jdbcType=CHAR}, #{resourceId,jdbcType=CHAR}, ",
        "#{roleId,jdbcType=CHAR})"
    })
    int insert(RoleResource record);

    @InsertProvider(type=RoleResourceSqlProvider.class, method="insertSelective")
    int insertSelective(RoleResource record);

    @SelectProvider(type=RoleResourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.CHAR),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.CHAR)
    })
    List<RoleResource> selectByExample(RoleResourceExample example);

    @Select({
        "select",
        "id, resource_id, role_id",
        "from t_role_resource",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.CHAR),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.CHAR)
    })
    RoleResource selectByPrimaryKey(String id);

    @UpdateProvider(type=RoleResourceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    @UpdateProvider(type=RoleResourceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    @UpdateProvider(type=RoleResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoleResource record);

    @Update({
        "update t_role_resource",
        "set resource_id = #{resourceId,jdbcType=CHAR},",
          "role_id = #{roleId,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(RoleResource record);
}