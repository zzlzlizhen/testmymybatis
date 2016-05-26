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

import lz.model.Resource;
import lz.model.ResourceExample.Criteria;
import lz.model.ResourceExample.Criterion;
import lz.model.ResourceExample;

public class ResourceSqlProvider {

	public String countByExample(ResourceExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_resource");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(ResourceExample example) {
        BEGIN();
        DELETE_FROM("t_resource");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(Resource record) {
        BEGIN();
        INSERT_INTO("t_resource");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=CHAR}");
        }
        
        if (record.getResourceName() != null) {
            VALUES("resource_name", "#{resourceName,jdbcType=CHAR}");
        }
        
        if (record.getResourceUrl() != null) {
            VALUES("resource_url", "#{resourceUrl,jdbcType=CHAR}");
        }
        
        if (record.getPid() != null) {
            VALUES("pid", "#{pid,jdbcType=CHAR}");
        }
        
        if (record.getRemark() != null) {
            VALUES("remark", "#{remark,jdbcType=CHAR}");
        }
        
        if (record.getIcons() != null) {
            VALUES("icons", "#{icons,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=CHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=CHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(ResourceExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("resource_name");
        SELECT("resource_url");
        SELECT("pid");
        SELECT("remark");
        SELECT("icons");
        SELECT("create_time");
        SELECT("update_time");
        FROM("t_resource");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Resource record = (Resource) parameter.get("record");
        ResourceExample example = (ResourceExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_resource");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=CHAR}");
        }
        
        if (record.getResourceName() != null) {
            SET("resource_name = #{record.resourceName,jdbcType=CHAR}");
        }
        
        if (record.getResourceUrl() != null) {
            SET("resource_url = #{record.resourceUrl,jdbcType=CHAR}");
        }
        
        if (record.getPid() != null) {
            SET("pid = #{record.pid,jdbcType=CHAR}");
        }
        
        if (record.getRemark() != null) {
            SET("remark = #{record.remark,jdbcType=CHAR}");
        }
        
        if (record.getIcons() != null) {
            SET("icons = #{record.icons,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=CHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{record.updateTime,jdbcType=CHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("t_resource");
        
        SET("id = #{record.id,jdbcType=CHAR}");
        SET("resource_name = #{record.resourceName,jdbcType=CHAR}");
        SET("resource_url = #{record.resourceUrl,jdbcType=CHAR}");
        SET("pid = #{record.pid,jdbcType=CHAR}");
        SET("remark = #{record.remark,jdbcType=CHAR}");
        SET("icons = #{record.icons,jdbcType=CHAR}");
        SET("create_time = #{record.createTime,jdbcType=CHAR}");
        SET("update_time = #{record.updateTime,jdbcType=CHAR}");
        
        ResourceExample example = (ResourceExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(Resource record) {
        BEGIN();
        UPDATE("t_resource");
        
        if (record.getResourceName() != null) {
            SET("resource_name = #{resourceName,jdbcType=CHAR}");
        }
        
        if (record.getResourceUrl() != null) {
            SET("resource_url = #{resourceUrl,jdbcType=CHAR}");
        }
        
        if (record.getPid() != null) {
            SET("pid = #{pid,jdbcType=CHAR}");
        }
        
        if (record.getRemark() != null) {
            SET("remark = #{remark,jdbcType=CHAR}");
        }
        
        if (record.getIcons() != null) {
            SET("icons = #{icons,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=CHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{updateTime,jdbcType=CHAR}");
        }
        
        WHERE("id = #{id,jdbcType=CHAR}");
        
        return SQL();
    }

    protected void applyWhere(ResourceExample example, boolean includeExamplePhrase) {
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
    public String selectResourceByPage(Map<String,Object> map){
    	StringBuffer sb = new StringBuffer("SELECT t1.id,t1.resource_name,t1.resource_url,t1.pid,t2.resource_name pname,t1.icons FROM t_resource t1 LEFT JOIN t_resource t2 ON t1.pid=t2.id ");
    	if(map.get("resourceName")!=null){
    		sb.append(" where t1.resource_name like #{resourceName} ");
    	}
    	sb.append(" order by id desc limit #{start,jdbcType=INTEGER},#{end,jdbcType=INTEGER}");
    	return sb.toString();
    }
    public String getCountByPage(Map<String,Object> map){
    	StringBuffer sb = new StringBuffer("select count(id) from t_resource");
    	if(map.get("resourceName")!=null){
    		sb.append(" where resource_name like #{resourceName} ");
    	}
    	return sb.toString();
    }
    public String batchDelResource(Map<String,Object> map){
    	@SuppressWarnings("unchecked")
		List<String> list = (List<String>)map.get("batchDelIds");
    	StringBuffer sb = new StringBuffer("delete from t_resource where id in ( ");
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