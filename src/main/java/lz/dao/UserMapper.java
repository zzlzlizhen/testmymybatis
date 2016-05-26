package lz.dao;

import java.util.List;
import java.util.Map;

import lz.model.Resource;
import lz.model.User;
import lz.model.UserExample;

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
import org.apache.ibatis.annotations.Many;
public interface UserMapper {
	@SelectProvider(type=UserSqlProvider.class, method="countByExample")
    int countByExample(UserExample example);

    @DeleteProvider(type=UserSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserExample example);

    @Delete({
        "delete from t_user",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_user (id, name, pwd, ",
        "create_time, update_time, ",
        "phone, email, status)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=CHAR}, #{pwd,jdbcType=CHAR}, ",
        "#{createTime,jdbcType=CHAR}, #{updateTime,jdbcType=CHAR}, ",
        "#{phone,jdbcType=CHAR}, #{email,jdbcType=CHAR}, #{status,jdbcType=CHAR})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @SelectProvider(type=UserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.CHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.CHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR)
    })
    List<User> selectByExample(UserExample example);

    @Select({
        "select",
        "id, name, pwd, create_time, update_time, phone, email, status",
        "from t_user",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.CHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.CHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR)
    })
    User selectByPrimaryKey(String id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update t_user",
        "set name = #{name,jdbcType=CHAR},",
          "pwd = #{pwd,jdbcType=CHAR},",
          "create_time = #{createTime,jdbcType=CHAR},",
          "update_time = #{updateTime,jdbcType=CHAR},",
          "phone = #{phone,jdbcType=CHAR},",
          "email = #{email,jdbcType=CHAR},",
          "status = #{status,jdbcType=CHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(User record);
  //*******************自定义sql*******************
    /*
     * 分页查询
     */
    @SelectProvider(type=UserSqlProvider.class, method="selectUserByPage")
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.CHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.CHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR)
    })
    List<User> selectUserByPage(Map<String,Object> map);
    /**
     * 分页查询总记录数
     * @param map
     * @return
     */
    @SelectProvider(type=UserSqlProvider.class, method="getCountByPage")
    int getCountByPage(Map<String,Object> map);
    /**
     * 根据id查询user对象和user的级联userRole对象
     */
    @Select({
    	"select",
        "id, name, pwd, create_time, update_time, phone, email, status",
        "from t_user",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.CHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.CHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.CHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.CHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.CHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(property="roleLists",column="id",javaType=List.class,many=@Many(select="lz.dao.RoleMapper.selectRoleByUserId")),
    })
    User selectById(String id);
}