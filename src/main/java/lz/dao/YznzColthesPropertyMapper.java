package lz.dao;

import java.util.List;
import lz.model.YznzColthesProperty;
import lz.model.YznzColthesPropertyExample;
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

public interface YznzColthesPropertyMapper {
    @SelectProvider(type=YznzColthesPropertySqlProvider.class, method="countByExample")
    int countByExample(YznzColthesPropertyExample example);

    @DeleteProvider(type=YznzColthesPropertySqlProvider.class, method="deleteByExample")
    int deleteByExample(YznzColthesPropertyExample example);

    @Delete({
        "delete from t_yznz_colthes_property",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_yznz_colthes_property (id, colthes_id, ",
        "size_type, colour_type, ",
        "purchase_count, sale_count, ",
        "add_by, add_time, ",
        "update_by, update_time)",
        "values (#{id,jdbcType=CHAR}, #{colthesId,jdbcType=CHAR}, ",
        "#{sizeType,jdbcType=CHAR}, #{colourType,jdbcType=CHAR}, ",
        "#{purchaseCount,jdbcType=INTEGER}, #{saleCount,jdbcType=INTEGER}, ",
        "#{addBy,jdbcType=VARCHAR}, #{addTime,jdbcType=VARCHAR}, ",
        "#{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=VARCHAR})"
    })
    int insert(YznzColthesProperty record);

    @InsertProvider(type=YznzColthesPropertySqlProvider.class, method="insertSelective")
    int insertSelective(YznzColthesProperty record);

    @SelectProvider(type=YznzColthesPropertySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="colthes_id", property="colthesId", jdbcType=JdbcType.CHAR),
        @Result(column="size_type", property="sizeType", jdbcType=JdbcType.CHAR),
        @Result(column="colour_type", property="colourType", jdbcType=JdbcType.CHAR),
        @Result(column="purchase_count", property="purchaseCount", jdbcType=JdbcType.INTEGER),
        @Result(column="sale_count", property="saleCount", jdbcType=JdbcType.INTEGER),
        @Result(column="add_by", property="addBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR)
    })
    List<YznzColthesProperty> selectByExample(YznzColthesPropertyExample example);

    @Select({
        "select",
        "id, colthes_id, size_type, colour_type, purchase_count, sale_count, add_by, ",
        "add_time, update_by, update_time",
        "from t_yznz_colthes_property",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="colthes_id", property="colthesId", jdbcType=JdbcType.CHAR),
        @Result(column="size_type", property="sizeType", jdbcType=JdbcType.CHAR),
        @Result(column="colour_type", property="colourType", jdbcType=JdbcType.CHAR),
        @Result(column="purchase_count", property="purchaseCount", jdbcType=JdbcType.INTEGER),
        @Result(column="sale_count", property="saleCount", jdbcType=JdbcType.INTEGER),
        @Result(column="add_by", property="addBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR)
    })
    YznzColthesProperty selectByPrimaryKey(String id);

    @UpdateProvider(type=YznzColthesPropertySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") YznzColthesProperty record, @Param("example") YznzColthesPropertyExample example);

    @UpdateProvider(type=YznzColthesPropertySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") YznzColthesProperty record, @Param("example") YznzColthesPropertyExample example);

    @UpdateProvider(type=YznzColthesPropertySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(YznzColthesProperty record);

    @Update({
        "update t_yznz_colthes_property",
        "set colthes_id = #{colthesId,jdbcType=CHAR},",
          "size_type = #{sizeType,jdbcType=CHAR},",
          "colour_type = #{colourType,jdbcType=CHAR},",
          "purchase_count = #{purchaseCount,jdbcType=INTEGER},",
          "sale_count = #{saleCount,jdbcType=INTEGER},",
          "add_by = #{addBy,jdbcType=VARCHAR},",
          "add_time = #{addTime,jdbcType=VARCHAR},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(YznzColthesProperty record);
}