package lz.dao;

import java.util.List;
import java.util.Map;

import lz.model.SystemParam;
import lz.model.YznzColthes;
import lz.model.YznzColthesExample;

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

public interface YznzColthesMapper {
    @SelectProvider(type=YznzColthesSqlProvider.class, method="countByExample")
    int countByExample(YznzColthesExample example);

    @DeleteProvider(type=YznzColthesSqlProvider.class, method="deleteByExample")
    int deleteByExample(YznzColthesExample example);

    @Delete({
        "delete from t_yznz_colthes",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into t_yznz_colthes (id, name, purchase_count, ",
        "sale_count, purchase_price, ",
        "pre_sale_price, business_type, ",
        "style_type, category_type, ",
        "pic_url, business_address, ",
        "add_by, add_time, ",
        "update_by, update_time)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{purchaseCount,jdbcType=INTEGER}, ",
        "#{saleCount,jdbcType=INTEGER}, #{purchasePrice,jdbcType=VARCHAR}, ",
        "#{preSalePrice,jdbcType=VARCHAR}, #{businessType,jdbcType=CHAR}, ",
        "#{styleType,jdbcType=CHAR}, #{categoryType,jdbcType=CHAR}, ",
        "#{picUrl,jdbcType=VARCHAR}, #{businessAddress,jdbcType=VARCHAR}, ",
        "#{addBy,jdbcType=VARCHAR}, #{addTime,jdbcType=VARCHAR}, ",
        "#{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=VARCHAR})"
    })
    int insert(YznzColthes record);

    @InsertProvider(type=YznzColthesSqlProvider.class, method="insertSelective")
    int insertSelective(YznzColthes record);

    @SelectProvider(type=YznzColthesSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="purchase_count", property="purchaseCount", jdbcType=JdbcType.INTEGER),
        @Result(column="sale_count", property="saleCount", jdbcType=JdbcType.INTEGER),
        @Result(column="purchase_price", property="purchasePrice", jdbcType=JdbcType.VARCHAR),
        @Result(column="pre_sale_price", property="preSalePrice", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_type", property="businessType", jdbcType=JdbcType.CHAR),
        @Result(column="style_type", property="styleType", jdbcType=JdbcType.CHAR),
        @Result(column="category_type", property="categoryType", jdbcType=JdbcType.CHAR),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_address", property="businessAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_by", property="addBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR)
    })
    List<YznzColthes> selectByExample(YznzColthesExample example);

    @Select({
        "select",
        "id, name, purchase_count, sale_count, purchase_price, pre_sale_price, business_type, ",
        "style_type, category_type, pic_url, business_address, add_by, add_time, update_by, ",
        "update_time",
        "from t_yznz_colthes",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="purchase_count", property="purchaseCount", jdbcType=JdbcType.INTEGER),
        @Result(column="sale_count", property="saleCount", jdbcType=JdbcType.INTEGER),
        @Result(column="purchase_price", property="purchasePrice", jdbcType=JdbcType.VARCHAR),
        @Result(column="pre_sale_price", property="preSalePrice", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_type", property="businessType", jdbcType=JdbcType.CHAR),
        @Result(column="style_type", property="styleType", jdbcType=JdbcType.CHAR),
        @Result(column="category_type", property="categoryType", jdbcType=JdbcType.CHAR),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_address", property="businessAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_by", property="addBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR)
    })
    YznzColthes selectByPrimaryKey(String id);

    @UpdateProvider(type=YznzColthesSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") YznzColthes record, @Param("example") YznzColthesExample example);

    @UpdateProvider(type=YznzColthesSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") YznzColthes record, @Param("example") YznzColthesExample example);

    @UpdateProvider(type=YznzColthesSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(YznzColthes record);

    @Update({
        "update t_yznz_colthes",
        "set name = #{name,jdbcType=VARCHAR},",
          "purchase_count = #{purchaseCount,jdbcType=INTEGER},",
          "sale_count = #{saleCount,jdbcType=INTEGER},",
          "purchase_price = #{purchasePrice,jdbcType=VARCHAR},",
          "pre_sale_price = #{preSalePrice,jdbcType=VARCHAR},",
          "business_type = #{businessType,jdbcType=CHAR},",
          "style_type = #{styleType,jdbcType=CHAR},",
          "category_type = #{categoryType,jdbcType=CHAR},",
          "pic_url = #{picUrl,jdbcType=VARCHAR},",
          "business_address = #{businessAddress,jdbcType=VARCHAR},",
          "add_by = #{addBy,jdbcType=VARCHAR},",
          "add_time = #{addTime,jdbcType=VARCHAR},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(YznzColthes record);
    /*
     * 分页查询
     */
    @SelectProvider(type=YznzColthesSqlProvider.class, method="selectPurchaseByPage")
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="purchase_count", property="purchaseCount", jdbcType=JdbcType.INTEGER),
        @Result(column="sale_count", property="saleCount", jdbcType=JdbcType.INTEGER),
        @Result(column="purchase_price", property="purchasePrice", jdbcType=JdbcType.VARCHAR),
        @Result(column="pre_sale_price", property="preSalePrice", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_type", property="businessType", jdbcType=JdbcType.CHAR),
        @Result(column="style_type", property="styleType", jdbcType=JdbcType.CHAR),
        @Result(column="category_type", property="categoryType", jdbcType=JdbcType.CHAR),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_address", property="businessAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_by", property="addBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="add_time", property="addTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR)
    })
    List<YznzColthes> selectPurchaseByPage(Map<String,Object> map);
    
    /**
     * 批量删除
     * @param id
     * @return
     */
    @DeleteProvider(type=YznzColthesSqlProvider.class, method="batchDelYznzClothes")
    int batchDelYznzClothes(@Param("batchDelIds") List<String> delId);
}