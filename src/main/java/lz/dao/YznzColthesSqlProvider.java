package lz.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.List;
import java.util.Map;

import lz.model.YznzColthes;
import lz.model.YznzColthesExample.Criteria;
import lz.model.YznzColthesExample.Criterion;
import lz.model.YznzColthesExample;

public class YznzColthesSqlProvider {

    public String countByExample(YznzColthesExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_yznz_colthes");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(YznzColthesExample example) {
        BEGIN();
        DELETE_FROM("t_yznz_colthes");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(YznzColthes record) {
        BEGIN();
        INSERT_INTO("t_yznz_colthes");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=CHAR}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPurchaseCount() != null) {
            VALUES("purchase_count", "#{purchaseCount,jdbcType=INTEGER}");
        }
        
        if (record.getSaleCount() != null) {
            VALUES("sale_count", "#{saleCount,jdbcType=INTEGER}");
        }
        
        if (record.getPurchasePrice() != null) {
            VALUES("purchase_price", "#{purchasePrice,jdbcType=VARCHAR}");
        }
        
        if (record.getPreSalePrice() != null) {
            VALUES("pre_sale_price", "#{preSalePrice,jdbcType=VARCHAR}");
        }
        
        if (record.getBusinessType() != null) {
            VALUES("business_type", "#{businessType,jdbcType=CHAR}");
        }
        
        if (record.getStyleType() != null) {
            VALUES("style_type", "#{styleType,jdbcType=CHAR}");
        }
        
        if (record.getCategoryType() != null) {
            VALUES("category_type", "#{categoryType,jdbcType=CHAR}");
        }
        
        if (record.getPicUrl() != null) {
            VALUES("pic_url", "#{picUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getBusinessAddress() != null) {
            VALUES("business_address", "#{businessAddress,jdbcType=VARCHAR}");
        }
        
        if (record.getAddBy() != null) {
            VALUES("add_by", "#{addBy,jdbcType=VARCHAR}");
        }
        
        if (record.getAddTime() != null) {
            VALUES("add_time", "#{addTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateBy() != null) {
            VALUES("update_by", "#{updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(YznzColthesExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("purchase_count");
        SELECT("sale_count");
        SELECT("purchase_price");
        SELECT("pre_sale_price");
        SELECT("business_type");
        SELECT("style_type");
        SELECT("category_type");
        SELECT("pic_url");
        SELECT("business_address");
        SELECT("add_by");
        SELECT("add_time");
        SELECT("update_by");
        SELECT("update_time");
        FROM("t_yznz_colthes");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        YznzColthes record = (YznzColthes) parameter.get("record");
        YznzColthesExample example = (YznzColthesExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_yznz_colthes");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=CHAR}");
        }
        
        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getPurchaseCount() != null) {
            SET("purchase_count = #{record.purchaseCount,jdbcType=INTEGER}");
        }
        
        if (record.getSaleCount() != null) {
            SET("sale_count = #{record.saleCount,jdbcType=INTEGER}");
        }
        
        if (record.getPurchasePrice() != null) {
            SET("purchase_price = #{record.purchasePrice,jdbcType=VARCHAR}");
        }
        
        if (record.getPreSalePrice() != null) {
            SET("pre_sale_price = #{record.preSalePrice,jdbcType=VARCHAR}");
        }
        
        if (record.getBusinessType() != null) {
            SET("business_type = #{record.businessType,jdbcType=CHAR}");
        }
        
        if (record.getStyleType() != null) {
            SET("style_type = #{record.styleType,jdbcType=CHAR}");
        }
        
        if (record.getCategoryType() != null) {
            SET("category_type = #{record.categoryType,jdbcType=CHAR}");
        }
        
        if (record.getPicUrl() != null) {
            SET("pic_url = #{record.picUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getBusinessAddress() != null) {
            SET("business_address = #{record.businessAddress,jdbcType=VARCHAR}");
        }
        
        if (record.getAddBy() != null) {
            SET("add_by = #{record.addBy,jdbcType=VARCHAR}");
        }
        
        if (record.getAddTime() != null) {
            SET("add_time = #{record.addTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateBy() != null) {
            SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{record.updateTime,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("t_yznz_colthes");
        
        SET("id = #{record.id,jdbcType=CHAR}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("purchase_count = #{record.purchaseCount,jdbcType=INTEGER}");
        SET("sale_count = #{record.saleCount,jdbcType=INTEGER}");
        SET("purchase_price = #{record.purchasePrice,jdbcType=VARCHAR}");
        SET("pre_sale_price = #{record.preSalePrice,jdbcType=VARCHAR}");
        SET("business_type = #{record.businessType,jdbcType=CHAR}");
        SET("style_type = #{record.styleType,jdbcType=CHAR}");
        SET("category_type = #{record.categoryType,jdbcType=CHAR}");
        SET("pic_url = #{record.picUrl,jdbcType=VARCHAR}");
        SET("business_address = #{record.businessAddress,jdbcType=VARCHAR}");
        SET("add_by = #{record.addBy,jdbcType=VARCHAR}");
        SET("add_time = #{record.addTime,jdbcType=VARCHAR}");
        SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        SET("update_time = #{record.updateTime,jdbcType=VARCHAR}");
        
        YznzColthesExample example = (YznzColthesExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(YznzColthes record) {
        BEGIN();
        UPDATE("t_yznz_colthes");
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPurchaseCount() != null) {
            SET("purchase_count = #{purchaseCount,jdbcType=INTEGER}");
        }
        
        if (record.getSaleCount() != null) {
            SET("sale_count = #{saleCount,jdbcType=INTEGER}");
        }
        
        if (record.getPurchasePrice() != null) {
            SET("purchase_price = #{purchasePrice,jdbcType=VARCHAR}");
        }
        
        if (record.getPreSalePrice() != null) {
            SET("pre_sale_price = #{preSalePrice,jdbcType=VARCHAR}");
        }
        
        if (record.getBusinessType() != null) {
            SET("business_type = #{businessType,jdbcType=CHAR}");
        }
        
        if (record.getStyleType() != null) {
            SET("style_type = #{styleType,jdbcType=CHAR}");
        }
        
        if (record.getCategoryType() != null) {
            SET("category_type = #{categoryType,jdbcType=CHAR}");
        }
        
        if (record.getPicUrl() != null) {
            SET("pic_url = #{picUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getBusinessAddress() != null) {
            SET("business_address = #{businessAddress,jdbcType=VARCHAR}");
        }
        
        if (record.getAddBy() != null) {
            SET("add_by = #{addBy,jdbcType=VARCHAR}");
        }
        
        if (record.getAddTime() != null) {
            SET("add_time = #{addTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateBy() != null) {
            SET("update_by = #{updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{updateTime,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=CHAR}");
        
        return SQL();
    }

    protected void applyWhere(YznzColthesExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
    
    public String selectPurchaseByPage(Map<String,Object> map){
    	StringBuffer sb = new StringBuffer("select id, name, purchase_count, sale_count, purchase_price, pre_sale_price, business_type,style_type, category_type, pic_url, business_address, add_by, add_time, update_by,update_time");
    	sb.append(" from t_yznz_colthes ");
    	sb.append(" order by id desc ");
    	return sb.toString();
    }
}