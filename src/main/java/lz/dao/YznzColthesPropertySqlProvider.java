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
import lz.model.YznzColthesProperty;
import lz.model.YznzColthesPropertyExample.Criteria;
import lz.model.YznzColthesPropertyExample.Criterion;
import lz.model.YznzColthesPropertyExample;

public class YznzColthesPropertySqlProvider {

    public String countByExample(YznzColthesPropertyExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_yznz_colthes_property");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(YznzColthesPropertyExample example) {
        BEGIN();
        DELETE_FROM("t_yznz_colthes_property");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(YznzColthesProperty record) {
        BEGIN();
        INSERT_INTO("t_yznz_colthes_property");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=CHAR}");
        }
        
        if (record.getColthesId() != null) {
            VALUES("colthes_id", "#{colthesId,jdbcType=CHAR}");
        }
        
        if (record.getSizeType() != null) {
            VALUES("size_type", "#{sizeType,jdbcType=CHAR}");
        }
        
        if (record.getColourType() != null) {
            VALUES("colour_type", "#{colourType,jdbcType=CHAR}");
        }
        
        if (record.getPurchaseCount() != null) {
            VALUES("purchase_count", "#{purchaseCount,jdbcType=INTEGER}");
        }
        
        if (record.getSaleCount() != null) {
            VALUES("sale_count", "#{saleCount,jdbcType=INTEGER}");
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

    public String selectByExample(YznzColthesPropertyExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("colthes_id");
        SELECT("size_type");
        SELECT("colour_type");
        SELECT("purchase_count");
        SELECT("sale_count");
        SELECT("add_by");
        SELECT("add_time");
        SELECT("update_by");
        SELECT("update_time");
        FROM("t_yznz_colthes_property");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        YznzColthesProperty record = (YznzColthesProperty) parameter.get("record");
        YznzColthesPropertyExample example = (YznzColthesPropertyExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_yznz_colthes_property");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=CHAR}");
        }
        
        if (record.getColthesId() != null) {
            SET("colthes_id = #{record.colthesId,jdbcType=CHAR}");
        }
        
        if (record.getSizeType() != null) {
            SET("size_type = #{record.sizeType,jdbcType=CHAR}");
        }
        
        if (record.getColourType() != null) {
            SET("colour_type = #{record.colourType,jdbcType=CHAR}");
        }
        
        if (record.getPurchaseCount() != null) {
            SET("purchase_count = #{record.purchaseCount,jdbcType=INTEGER}");
        }
        
        if (record.getSaleCount() != null) {
            SET("sale_count = #{record.saleCount,jdbcType=INTEGER}");
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
        UPDATE("t_yznz_colthes_property");
        
        SET("id = #{record.id,jdbcType=CHAR}");
        SET("colthes_id = #{record.colthesId,jdbcType=CHAR}");
        SET("size_type = #{record.sizeType,jdbcType=CHAR}");
        SET("colour_type = #{record.colourType,jdbcType=CHAR}");
        SET("purchase_count = #{record.purchaseCount,jdbcType=INTEGER}");
        SET("sale_count = #{record.saleCount,jdbcType=INTEGER}");
        SET("add_by = #{record.addBy,jdbcType=VARCHAR}");
        SET("add_time = #{record.addTime,jdbcType=VARCHAR}");
        SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        SET("update_time = #{record.updateTime,jdbcType=VARCHAR}");
        
        YznzColthesPropertyExample example = (YznzColthesPropertyExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(YznzColthesProperty record) {
        BEGIN();
        UPDATE("t_yznz_colthes_property");
        
        if (record.getColthesId() != null) {
            SET("colthes_id = #{colthesId,jdbcType=CHAR}");
        }
        
        if (record.getSizeType() != null) {
            SET("size_type = #{sizeType,jdbcType=CHAR}");
        }
        
        if (record.getColourType() != null) {
            SET("colour_type = #{colourType,jdbcType=CHAR}");
        }
        
        if (record.getPurchaseCount() != null) {
            SET("purchase_count = #{purchaseCount,jdbcType=INTEGER}");
        }
        
        if (record.getSaleCount() != null) {
            SET("sale_count = #{saleCount,jdbcType=INTEGER}");
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

    protected void applyWhere(YznzColthesPropertyExample example, boolean includeExamplePhrase) {
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