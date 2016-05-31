package lz.dao;

import java.util.List;
import java.util.Map;

import lz.model.Resource;
import lz.model.ResourceExample;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ResourceMapper {
	@SelectProvider(type=ResourceSqlProvider.class, method="countByExample")
    int countByExample(ResourceExample example);

    @DeleteProvider(type=ResourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(ResourceExample example);

    @Delete({
        "delete from t_resource",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_resource (id, resource_name, ",
        "resource_url, pid, remark, ",
        "icons, create_time, update_time)",
        "values (#{id,jdbcType=CHAR}, #{resourceName,jdbcType=CHAR}, ",
        "#{resourceUrl,jdbcType=CHAR}, #{pid,jdbcType=CHAR}, #{remark,jdbcType=CHAR}, ",
        "#{icons,jdbcType=CHAR}, #{createTime,jdbcType=CHAR}, #{updateTime,jdbcType=CHAR})"
    })
    int insert(Resource record);

    @InsertProvider(type=ResourceSqlProvider.class, method="insertSelective")
    int insertSelective(Resource record);

    @SelectProvider(type=ResourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="resource_name", property="resourceName", jdbcType=JdbcType.CHAR),
        @Result(column="resource_url", property="resourceUrl", jdbcType=JdbcType.CHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.CHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.CHAR),
        @Result(column="icons", property="icons", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR)
    })
    List<Resource> selectByExample(ResourceExample example);

    @Select({
        "select",
        "id, resource_name, resource_url, pid, remark, icons, create_time, update_time",
        "from t_resource",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="resource_name", property="resourceName", jdbcType=JdbcType.CHAR),
        @Result(column="resource_url", property="resourceUrl", jdbcType=JdbcType.CHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.CHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.CHAR),
        @Result(column="icons", property="icons", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR)
    })
    Resource selectByPrimaryKey(String id);

    @UpdateProvider(type=ResourceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    @UpdateProvider(type=ResourceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    @UpdateProvider(type=ResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Resource record);

    @Update({
        "update t_resource",
        "set resource_name = #{resourceName,jdbcType=CHAR},",
          "resource_url = #{resourceUrl,jdbcType=CHAR},",
          "pid = #{pid,jdbcType=CHAR},",
          "remark = #{remark,jdbcType=CHAR},",
          "icons = #{icons,jdbcType=CHAR},",
          "create_time = #{createTime,jdbcType=CHAR},",
          "update_time = #{updateTime,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(Resource record);
    
    //*******************自定义sql*******************
    /*
     * 分页查询
     */
    @SelectProvider(type=ResourceSqlProvider.class, method="selectResourceByPage")
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="resource_name", property="resourceName", jdbcType=JdbcType.CHAR),
        @Result(column="resource_url", property="resourceUrl", jdbcType=JdbcType.CHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.CHAR),
        @Result(column="pname", property="remark", jdbcType=JdbcType.CHAR),
        @Result(column="icons", property="icons", jdbcType=JdbcType.CHAR)
    })
    List<Resource> selectResourceByPage(Map<String,Object> map);
    /**
     * 
     * @param id
     * @return
     */
    @Select({
        "select",
        "id, resource_name, resource_url, pid, remark, icons",
        "from t_resource",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="resource_name", property="resourceName", jdbcType=JdbcType.CHAR),
        @Result(column="resource_url", property="resourceUrl", jdbcType=JdbcType.CHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.CHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.CHAR),
        @Result(column="icons", property="icons", jdbcType=JdbcType.CHAR),
        @Result(property="pResource",column="pid",javaType=Resource.class,one=@One(select="lz.dao.ResourceMapper.selectByPrimaryKey")),
    })
    Resource selectById(String id);
    /**
     * 批量删除
     * @param id
     * @return
     */
    @DeleteProvider(type=ResourceSqlProvider.class, method="batchDelResource")
    int batchDelResource(@Param("batchDelIds") List<String> delId);
    /**
     * 根据roleId查询所有的资源列表
     */
    @Select({
        "SELECT t.id, t.resource_name, t.resource_url, t.pid, t.remark, t.icons ",
        "FROM t_resource t,t_role_resource rt ",
        "WHERE t.id=rt.resource_id AND rt.role_id=#{roleId,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="resource_name", property="resourceName", jdbcType=JdbcType.CHAR),
        @Result(column="resource_url", property="resourceUrl", jdbcType=JdbcType.CHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.CHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.CHAR),
        @Result(column="icons", property="icons", jdbcType=JdbcType.CHAR)
    })
    List<Resource> selectResourceByRoleId(String roleId);
    /**
     * 根据userId和pid查询资源信息
     */
    @Select({
        "SELECT re.id,re.resource_name,re.resource_url,re.pid,re.icons ",
        "FROM t_user u,t_user_role ur,t_role_resource ures,t_resource re ",
        "WHERE u.id=ur.user_id AND ur.role_id = ures.role_id AND ures.resource_id = re.id ",
        "AND u.id=#{userId,jdbcType=CHAR} AND pid = #{pid,jdbcType=CHAR} GROUP BY re.id"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="resource_name", property="resourceName", jdbcType=JdbcType.CHAR),
        @Result(column="resource_url", property="resourceUrl", jdbcType=JdbcType.CHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.CHAR),
        @Result(column="icons", property="icons", jdbcType=JdbcType.CHAR)
    })
    List<Resource> getResourceMenuByUserId(Map<String,Object> map);
    
}