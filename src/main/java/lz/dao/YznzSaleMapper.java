package lz.dao;

import java.util.List;
import lz.model.YznzSale;
import lz.model.YznzSaleExample;
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

public interface YznzSaleMapper {
    @SelectProvider(type=YznzSaleSqlProvider.class, method="countByExample")
    int countByExample(YznzSaleExample example);

    @DeleteProvider(type=YznzSaleSqlProvider.class, method="deleteByExample")
    int deleteByExample(YznzSaleExample example);

    @Delete({
        "delete from t_yznz_sale",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_yznz_sale (id, colthes_id, ",
        "colthes_property_id, purchase_price, ",
        "real_price, add_by, ",
        "add_time)",
        "values (#{id,jdbcType=CHAR}, #{colthesId,jdbcType=CHAR}, ",
        "#{colthesPropertyId,jdbcType=CHAR}, #{purchasePrice,jdbcType=VARCHAR}, ",
        "#{realPrice,jdbcType=VARCHAR}, #{addBy,jdbcType=VARCHAR}, ",
        "#{addTime,jdbcType=VARCHAR})"
    })
    int insert(YznzSale record);

    @InsertProvider(type=YznzSaleSqlProvider.class, method="insertSelective")
    int insertSelective(YznzSale record);

    @SelectProvider(type=YznzSaleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="colthes_id", property="colthesId", jdbcType=JdbcType.CHAR),
        @Result(column="colthes_property_id", property="colthesPropertyId", jdbcType=JdbcType.CHAR),
        @Result(column="purchase_price", property="purchasePrice", jdbcType=JdbcType.VARCHAR),
        @Result(column="real_price", property="realPrice", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_by", property="addBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.VARCHAR)
    })
    List<YznzSale> selectByExample(YznzSaleExample example);

    @Select({
        "select",
        "id, colthes_id, colthes_property_id, purchase_price, real_price, add_by, add_time",
        "from t_yznz_sale",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="colthes_id", property="colthesId", jdbcType=JdbcType.CHAR),
        @Result(column="colthes_property_id", property="colthesPropertyId", jdbcType=JdbcType.CHAR),
        @Result(column="purchase_price", property="purchasePrice", jdbcType=JdbcType.VARCHAR),
        @Result(column="real_price", property="realPrice", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_by", property="addBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.VARCHAR)
    })
    YznzSale selectByPrimaryKey(String id);

    @UpdateProvider(type=YznzSaleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") YznzSale record, @Param("example") YznzSaleExample example);

    @UpdateProvider(type=YznzSaleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") YznzSale record, @Param("example") YznzSaleExample example);

    @UpdateProvider(type=YznzSaleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(YznzSale record);

    @Update({
        "update t_yznz_sale",
        "set colthes_id = #{colthesId,jdbcType=CHAR},",
          "colthes_property_id = #{colthesPropertyId,jdbcType=CHAR},",
          "purchase_price = #{purchasePrice,jdbcType=VARCHAR},",
          "real_price = #{realPrice,jdbcType=VARCHAR},",
          "add_by = #{addBy,jdbcType=VARCHAR},",
          "add_time = #{addTime,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(YznzSale record);
}