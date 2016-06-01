package lz.dao;

import java.util.List;
import java.util.Map;

import lz.model.Resource;
import lz.model.Role;
import lz.model.RoleExample;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RoleMapper {
	 @SelectProvider(type=RoleSqlProvider.class, method="countByExample")
    int countByExample(RoleExample example);

    @DeleteProvider(type=RoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(RoleExample example);

    @Delete({
        "delete from t_role",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_role (id, role_name, ",
        "remark, create_time, update_time, ",
        "role_type)",
        "values (#{id,jdbcType=CHAR}, #{roleName,jdbcType=CHAR}, ",
        "#{remark,jdbcType=CHAR}, #{createTime,jdbcType=CHAR}, #{updateTime,jdbcType=CHAR}, ",
        "#{roleType,jdbcType=CHAR})"
    })
    int insert(Role record);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    @SelectProvider(type=RoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.CHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR),
        @Result(column="role_type", property="roleType", jdbcType=JdbcType.CHAR)
    })
    List<Role> selectByExample(RoleExample example);

    @Select({
        "select",
        "id, role_name, remark, create_time, update_time, role_type",
        "from t_role",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.CHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR),
        @Result(column="role_type", property="roleType", jdbcType=JdbcType.CHAR)
    })
    Role selectByPrimaryKey(String id);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update t_role",
        "set role_name = #{roleName,jdbcType=CHAR},",
          "remark = #{remark,jdbcType=CHAR},",
          "create_time = #{createTime,jdbcType=CHAR},",
          "update_time = #{updateTime,jdbcType=CHAR},",
          "role_type = #{roleType,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(Role record);
    //********************自定义sql******************
    /*
     * 分页查询
     */
    @SelectProvider(type=RoleSqlProvider.class, method="selectRoleByPage")
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.CHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR),
        @Result(column="role_type", property="roleType", jdbcType=JdbcType.CHAR)
    })
    List<Role> selectRoleByPage(Map<String,Object> map);
    
    @Select({
        "select",
        "id, role_name, remark,create_time,update_time,role_type",
        "from t_role",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.CHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR),
        @Result(column="role_type", property="roleType", jdbcType=JdbcType.CHAR),
        @Result(property="resources",column="id",javaType=List.class,many=@Many(select="lz.dao.ResourceMapper.selectResourceByRoleId")),
    })
    Role selectById(String id);
    /**
     * 批量删除
     * @param id
     * @return
     */
    @DeleteProvider(type=RoleSqlProvider.class, method="batchDelRole")
    int batchDelRole(@Param("batchDelIds") List<String> delId);
    /**
     * 根据userId查询所有的角色信息
     */
    @Select({
        "SELECT t.id, t.role_name, t.remark, t.create_time, t.update_time,t.role_type",
        "FROM t_role t,t_user_role ur ",
        "WHERE t.id=ur.role_id AND ur.user_id=#{userId,jdbcType=CHAR}"
    })
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.CHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR),
        @Result(column="role_type", property="roleType", jdbcType=JdbcType.CHAR)
    })
    List<Role> selectRoleByUserId(String userId);
}