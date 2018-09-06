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
import lz.model.YznzSale;
import lz.model.YznzSaleExample.Criteria;
import lz.model.YznzSaleExample.Criterion;
import lz.model.YznzSaleExample;

public class YznzSaleSqlProvider {

    public String countByExample(YznzSaleExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_yznz_sale");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(YznzSaleExample example) {
        BEGIN();
        DELETE_FROM("t_yznz_sale");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(YznzSale record) {
        BEGIN();
        INSERT_INTO("t_yznz_sale");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=CHAR}");
        }
        
        if (record.getColthesId() != null) {
            VALUES("colthes_id", "#{colthesId,jdbcType=CHAR}");
        }
        
        if (record.getColthesPropertyId() != null) {
            VALUES("colthes_property_id", "#{colthesPropertyId,jdbcType=CHAR}");
        }
        
        if (record.getPurchasePrice() != null) {
            VALUES("purchase_price", "#{purchasePrice,jdbcType=VARCHAR}");
        }
        
        if (record.getRealPrice() != null) {
            VALUES("real_price", "#{realPrice,jdbcType=VARCHAR}");
        }
        
        if (record.getAddBy() != null) {
            VALUES("add_by", "#{addBy,jdbcType=VARCHAR}");
        }
        
        if (record.getAddTime() != null) {
            VALUES("add_time", "#{addTime,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(YznzSaleExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("colthes_id");
        SELECT("colthes_property_id");
        SELECT("purchase_price");
        SELECT("real_price");
        SELECT("add_by");
        SELECT("add_time");
        FROM("t_yznz_sale");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        YznzSale record = (YznzSale) parameter.get("record");
        YznzSaleExample example = (YznzSaleExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_yznz_sale");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=CHAR}");
        }
        
        if (record.getColthesId() != null) {
            SET("colthes_id = #{record.colthesId,jdbcType=CHAR}");
        }
        
        if (record.getColthesPropertyId() != null) {
            SET("colthes_property_id = #{record.colthesPropertyId,jdbcType=CHAR}");
        }
        
        if (record.getPurchasePrice() != null) {
            SET("purchase_price = #{record.purchasePrice,jdbcType=VARCHAR}");
        }
        
        if (record.getRealPrice() != null) {
            SET("real_price = #{record.realPrice,jdbcType=VARCHAR}");
        }
        
        if (record.getAddBy() != null) {
            SET("add_by = #{record.addBy,jdbcType=VARCHAR}");
        }
        
        if (record.getAddTime() != null) {
            SET("add_time = #{record.addTime,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("t_yznz_sale");
        
        SET("id = #{record.id,jdbcType=CHAR}");
        SET("colthes_id = #{record.colthesId,jdbcType=CHAR}");
        SET("colthes_property_id = #{record.colthesPropertyId,jdbcType=CHAR}");
        SET("purchase_price = #{record.purchasePrice,jdbcType=VARCHAR}");
        SET("real_price = #{record.realPrice,jdbcType=VARCHAR}");
        SET("add_by = #{record.addBy,jdbcType=VARCHAR}");
        SET("add_time = #{record.addTime,jdbcType=VARCHAR}");
        
        YznzSaleExample example = (YznzSaleExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(YznzSale record) {
        BEGIN();
        UPDATE("t_yznz_sale");
        
        if (record.getColthesId() != null) {
            SET("colthes_id = #{colthesId,jdbcType=CHAR}");
        }
        
        if (record.getColthesPropertyId() != null) {
            SET("colthes_property_id = #{colthesPropertyId,jdbcType=CHAR}");
        }
        
        if (record.getPurchasePrice() != null) {
            SET("purchase_price = #{purchasePrice,jdbcType=VARCHAR}");
        }
        
        if (record.getRealPrice() != null) {
            SET("real_price = #{realPrice,jdbcType=VARCHAR}");
        }
        
        if (record.getAddBy() != null) {
            SET("add_by = #{addBy,jdbcType=VARCHAR}");
        }
        
        if (record.getAddTime() != null) {
            SET("add_time = #{addTime,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=CHAR}");
        
        return SQL();
    }

    protected void applyWhere(YznzSaleExample example, boolean includeExamplePhrase) {
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
}