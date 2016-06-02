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

import lz.model.OperLog;
import lz.model.OperLogExample.Criteria;
import lz.model.OperLogExample.Criterion;
import lz.model.OperLogExample;

public class OperLogSqlProvider {

    public String countByExample(OperLogExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_oper_log");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(OperLogExample example) {
        BEGIN();
        DELETE_FROM("t_oper_log");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(OperLog record) {
        BEGIN();
        INSERT_INTO("t_oper_log");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=CHAR}");
        }
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=CHAR}");
        }
        
        if (record.getLogBusiness() != null) {
            VALUES("log_business", "#{logBusiness,jdbcType=CHAR}");
        }
        
        if (record.getLogDesc() != null) {
            VALUES("log_desc", "#{logDesc,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=CHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(OperLogExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("log_business");
        SELECT("log_desc");
        SELECT("create_time");
        FROM("t_oper_log");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        OperLog record = (OperLog) parameter.get("record");
        OperLogExample example = (OperLogExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_oper_log");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=CHAR}");
        }
        
        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=CHAR}");
        }
        
        if (record.getLogBusiness() != null) {
            SET("log_business = #{record.logBusiness,jdbcType=CHAR}");
        }
        
        if (record.getLogDesc() != null) {
            SET("log_desc = #{record.logDesc,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=CHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("t_oper_log");
        
        SET("id = #{record.id,jdbcType=CHAR}");
        SET("name = #{record.name,jdbcType=CHAR}");
        SET("log_business = #{record.logBusiness,jdbcType=CHAR}");
        SET("log_desc = #{record.logDesc,jdbcType=CHAR}");
        SET("create_time = #{record.createTime,jdbcType=CHAR}");
        
        OperLogExample example = (OperLogExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(OperLog record) {
        BEGIN();
        UPDATE("t_oper_log");
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=CHAR}");
        }
        
        if (record.getLogBusiness() != null) {
            SET("log_business = #{logBusiness,jdbcType=CHAR}");
        }
        
        if (record.getLogDesc() != null) {
            SET("log_desc = #{logDesc,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=CHAR}");
        }
        
        WHERE("id = #{id,jdbcType=CHAR}");
        
        return SQL();
    }

    protected void applyWhere(OperLogExample example, boolean includeExamplePhrase) {
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
    public String selectOperLogByPage(Map<String,Object> map){
    	StringBuffer sb = new StringBuffer("select id,name,log_business,log_desc,create_time from t_oper_log where 1=1 ");
    	if(map.get("name")!=null){
    		sb.append(" and name like #{name}");
    	}
    	if(map.get("logDesc")!=null){
    		sb.append(" and log_desc like #{logDesc}");
    	}
    	if(map.get("startTime")!=null){
    		sb.append(" and create_time >= #{startTime}");
    	}
    	if(map.get("endTime")!=null){
    		sb.append(" and create_time <= #{endTime}");
    	}
    	sb.append(" order by id desc ");
    	return sb.toString();
    }
    public String batchDelOperLog(Map<String,Object> map){
    	@SuppressWarnings("unchecked")
		List<String> list = (List<String>)map.get("batchDelIds");
    	StringBuffer sb = new StringBuffer("delete from t_oper_log where id in ( ");
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