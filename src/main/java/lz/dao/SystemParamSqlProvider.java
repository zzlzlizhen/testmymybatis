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

import lz.model.SystemParam;
import lz.model.SystemParamExample;
import lz.model.SystemParamExample.Criteria;
import lz.model.SystemParamExample.Criterion;

public class SystemParamSqlProvider {

	public String countByExample(SystemParamExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_param");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(SystemParamExample example) {
        BEGIN();
        DELETE_FROM("t_param");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(SystemParam record) {
        BEGIN();
        INSERT_INTO("t_param");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=CHAR}");
        }
        
        if (record.getParamKey() != null) {
            VALUES("param_key", "#{paramKey,jdbcType=CHAR}");
        }
        
        if (record.getParamValue() != null) {
            VALUES("param_value", "#{paramValue,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=CHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(SystemParamExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("param_key");
        SELECT("param_value");
        SELECT("create_time");
        FROM("t_param");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SystemParam record = (SystemParam) parameter.get("record");
        SystemParamExample example = (SystemParamExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_param");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=CHAR}");
        }
        
        if (record.getParamKey() != null) {
            SET("param_key = #{record.paramKey,jdbcType=CHAR}");
        }
        
        if (record.getParamValue() != null) {
            SET("param_value = #{record.paramValue,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=CHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("t_param");
        
        SET("id = #{record.id,jdbcType=CHAR}");
        SET("param_key = #{record.paramKey,jdbcType=CHAR}");
        SET("param_value = #{record.paramValue,jdbcType=CHAR}");
        SET("create_time = #{record.createTime,jdbcType=CHAR}");
        
        SystemParamExample example = (SystemParamExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(SystemParam record) {
        BEGIN();
        UPDATE("t_param");
        
        if (record.getParamKey() != null) {
            SET("param_key = #{paramKey,jdbcType=CHAR}");
        }
        
        if (record.getParamValue() != null) {
            SET("param_value = #{paramValue,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=CHAR}");
        }
        
        WHERE("id = #{id,jdbcType=CHAR}");
        
        return SQL();
    }

    protected void applyWhere(SystemParamExample example, boolean includeExamplePhrase) {
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
    
    
    //************************自定义sql**********************
    public String selectParamByPage(Map<String,Object> map){
    	StringBuffer sb = new StringBuffer("select id,param_key,param_value,create_time from t_param");
    	if(map.get("searchId")!=null){
    		sb.append(" where param_key like #{searchId} or param_value like #{searchId} ");
    	}
    	sb.append(" order by id desc ");
    	return sb.toString();
    }
    public String batchDelSystemParam(Map<String,Object> map){
    	@SuppressWarnings("unchecked")
		List<String> list = (List<String>)map.get("batchDelIds");
    	StringBuffer sb = new StringBuffer("delete from t_param where id in ( ");
    	for(int i=0;i<list.size();i++){
    		sb.append("#{batchDelIds["+i+"]}");
    		if(i<list.size()-1){
    			sb.append(",");
    		}
    	}
    	sb.append(" )");
    	return sb.toString();
    }
}